package org.shiki.service.impl;

import cn.hutool.core.util.RandomUtil;
import org.shiki.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setHash(String bigKey, String key, Object value, Integer time) {
        redisTemplate.opsForHash().put(bigKey, key, value);
        redisTemplate.expire(bigKey, time, TimeUnit.MINUTES);
    }

    @Override
    public void setHash(String bigKey, String key, Object value) {
        setHash(bigKey, key, value, RandomUtil.randomInt(10, 20));
    }

    @Override
    public void setValue(String key, Object value, Integer time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    @Override
    public void setValue(String key, Object value) {
        setValue(key, value, RandomUtil.randomInt(10, 20));
    }


    @Override
    public Object getHash(String bigKey, String key) {
        return redisTemplate.opsForHash().get(bigKey, key);
    }

    @Override
    public List<Object> getHashList(String bigKey) {
        return new ArrayList<>(redisTemplate.opsForHash().entries(bigKey).values());
    }


    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    @Override
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delete(String bigKey, String key) {
        redisTemplate.opsForHash().delete(bigKey, key);
    }


}
