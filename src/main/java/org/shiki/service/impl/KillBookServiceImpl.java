package org.shiki.service.impl;

//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.shiki.entity.KillBook;
import org.shiki.exception.OutOfStoreException;
import org.shiki.mapper.KillBookMapper;
import org.shiki.service.KillBookService;
import org.shiki.service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class KillBookServiceImpl implements KillBookService {
    @Resource
    KillBookMapper killBookMapper;
    @Resource
    RedisService redisService;
    @Resource
    RedissonClient redisson;

    private RLock rLock;


    @Override
    public void add(KillBook killBook) {
        killBookMapper.add(killBook);
        redisService.setHash("cache_kill", "cache_kill_" + killBook.getBookId(), killBook);
    }

    @Override
    public List<KillBook> queryAll() {
        return redisService.getHashList("cache_kill").stream().map(
                killBook -> (KillBook) killBook
        ).toList();
    }

    @Override
    public synchronized void startKill(Integer bookId) {
        try {
            rLock = redisson.getLock("kill_book" + bookId + "_lock");
            if (rLock.tryLock()) {
                KillBook killBook = (KillBook) redisService.getHash("cache_kill", "cache_kill_" + bookId);
                if (killBook.getKillCount() <= 0) {
                    throw new OutOfStoreException(Collections.singletonList(killBook.getBookName()));
                }

                System.err.println("剩余库存" + killBook.getKillCount());
                killBook.setKillCount(killBook.getKillCount() - 1);
                redisService.setHash("cache_kill", "cache_kill_" + bookId, killBook);
            }

        } finally {
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }
}
