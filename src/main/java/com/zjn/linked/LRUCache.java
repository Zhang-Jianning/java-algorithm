package com.zjn.linked;

import java.util.LinkedHashMap;

public class LRUCache {

    private LinkedHashMap<Integer, String> cache = new LinkedHashMap();

    // 容量
    private int capacity = 16;

    public LRUCache() {
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public String get(Integer key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        makeRecently(key);
        return cache.get(key);
    }


    public void put(Integer key, String val) {

        if (cache.containsKey(key)) {
            cache.put(key, val);
            makeRecently(key);
            return;
        }
        if (this.capacity == cache.size()) {
            // 第一个是 head 元素, 也是最久未使用的元素，需要移除
            Integer next = cache.keySet().iterator().next();
            cache.remove(next);
        }
        cache.put(key, val);

    }


    private void makeRecently(Integer key) {
        String val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }





}
