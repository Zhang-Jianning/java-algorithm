package com.zjn.linked;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {

    // 存储key、value
    private Map<Integer, Integer> kv ;

    // 存储key对应的访问频次
    private Map<Integer, Integer> kf;

    // 频次对应的所有key
    private Map<Integer, LinkedHashSet<Integer>> fKeys;

    // 记录最小的频次
    private int minFrequent = 0;

    // 容量
    private int capacity = 16;

    // 构造器
    public LFUCache(int capacity) {
        this.kv = new HashMap<>();
        this.kf = new HashMap<>();
        this.fKeys = new HashMap<>();
        this.minFrequent = 0;
        this.capacity = capacity;
    }


    public int get(Integer key) {
        if (!kv.containsKey(key)) {
            return 0;
        }

        // 增加访问频次
        this.increaseFreq(key);
        return kv.get(key);

    }


    public void put(Integer key, Integer val) {
        if (key == null || val == null) return;
        if (kv.containsKey(key)) {
            kv.put(key, val);
            // 增加访问频次
            this.increaseFreq(key);
            return;
        }

        // 判断容量
        if (kv.size() >= this.capacity) {
            // 移除使用次数最少的元素
            this.removeMinFreqKey();
        }
        // 插入新值
        kv.put(key, val);
        // 插入key对应的频次
        kf.put(key, 1);
        // 如果频次对应的value是null，初始化 linkedHashSet
        fKeys.putIfAbsent(1, new LinkedHashSet<Integer>());
        // 该频次对应的key值中加入当前元素，并且是添加到末尾
        fKeys.get(1).add(key);
        // 添加新增，最小频次重置为1
        this.minFrequent = 1;

    }

    // 增加元素访问频次
    private void increaseFreq (Integer key) {

        // 当前频次
        Integer fq = kf.get(key);
        // 频次加1
        kf.put(key, fq + 1);
        // 移除fq频次中对应的当前key
        fKeys.get(fq).remove(key);
        // 如果频次对应的value是null，初始化set
        fKeys.putIfAbsent(fq + 1, new LinkedHashSet<>());
        // 当前频次的set中添加该元素
        fKeys.get(fq + 1).add(key);

        if (fKeys.get(fq).isEmpty()) {
            // 频次对应的keys是空的，则移除该频次下的数据
            fKeys.remove(fq);
            // 移除当前元素后频次对应keys是空的，并且最小频次是该频次，则最小频次加1
            if (fq == this.minFrequent) {
                this.minFrequent += 1;
            }
        }
    }

    // 移除使用次数最少且最久未使用元素
    private void removeMinFreqKey() {
        // 视频频次最少且最久未使用的元素
        Integer next = fKeys.get(this.minFrequent).iterator().next();
        fKeys.get(this.minFrequent).remove(next);
        if (fKeys.get(this.minFrequent).isEmpty()) {
            fKeys.remove(this.minFrequent);

            // 这里不需要更新 minFrequent 值 ，因为调用该方法的只有的put方法中，且元素不存在于kv中，此时kv会put，也会重置minFrequent为1
        }
        // 移除key对应的频次
        kf.remove(next);
        // 移除key val
        kv.remove(next);

    }



}
