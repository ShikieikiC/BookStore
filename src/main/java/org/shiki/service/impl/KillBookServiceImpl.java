package org.shiki.service.impl;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.shiki.entity.KillBook;
import org.shiki.entity.KillOrder;
import org.shiki.exception.OutOfStoreException;
import org.shiki.producer.Producer;
import org.shiki.service.KillBookService;
import org.shiki.service.RedisService;
import org.shiki.utils.UserContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@Service
public class KillBookServiceImpl implements KillBookService {

    @Resource
    RedisService redisService;
    @Resource
    RedissonClient redisson;
    @Resource
    Producer producer;

    private RLock rLock;


    @Override
    public void add(KillBook killBook) {
        int betweenStart = Math.toIntExact(DateUtil.between(
                DateUtil.parse(killBook.getStartTime()), DateUtil.date(), DateUnit.MINUTE
        ));
        int betweenEnd = Math.toIntExact(DateUtil.between(
                DateUtil.parse(killBook.getEndTime()), DateUtil.date(), DateUnit.MINUTE
        ));

        producer.send(killBook, "addKill", (betweenStart > 30 ? (betweenStart - 30) * 60 * 1000 : 0));
        producer.send(killBook.getBookId(), "syncKill", betweenEnd * 60 * 1000);

    }

    @Override
    public List<KillBook> queryAll() {
        return redisService.getHashList("cache_kill").stream().map(
                killBook -> (KillBook) killBook
        ).toList();
    }

    @Override
    public void startKill(Integer bookId) throws ParseException {
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

                KillOrder killOrder = new KillOrder();
                killOrder.setBookId(bookId);
                killOrder.setPrice(killBook.getNewPrice());
                killOrder.setKillOrderNum(String.valueOf(new SnowflakeGenerator().next()));
                killOrder.setUserId(UserContext.getUserId());

                producer.send(killOrder, "addKillOrder", 0);
            }

        } finally {
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }


}
