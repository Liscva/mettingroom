package com.liscva.mettingroom.cache;

public interface Cache {

    Object getCache(String key);

    void setCache(String key, Object value);
}
