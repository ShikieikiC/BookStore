package org.shiki.service;

import java.util.Collection;
import java.util.List;

public interface RedisService {

    void setHash(String bigKey, String key, Object value, Integer time);

    void setValue(String key, Object value, Integer time);

    void setHash(String bigKey, String key, Object value);

    void setValue(String key, Object value);

    Object getHash(String bigKey, String key);

    List<Object> getHashList(String bigKey);

    Object getValue(String key);

    void delete(Collection<String> keys);

    void delete(String key);

}
