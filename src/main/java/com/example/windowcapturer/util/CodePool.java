package com.example.windowcapturer.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wlk
 * @date 2023/8/3 10:42
 */
public class CodePool {
    // 使用 ConcurrentHashMap 来存储编号状态，键为编号，值为使用状态
    private ConcurrentHashMap<Integer, Boolean> codes = new ConcurrentHashMap<>();

    public CodePool(int capacity) {
        // 初始时所有编号都处于未使用状态
        for (int i = 1; i <= capacity; i++) {
            codes.put(i, false);
        }
    }

    // 获取一个未被使用的编号
    public Integer allocate() {
        for (Integer key : codes.keySet()) {
            // 使用 replace 方法保证线程安全性
            if (codes.replace(key, false, true)) {
                return key; // 返回被成功置为true的键，表示分配成功
            }
        }
        return null; // 如果没有可用的编号，返回 null
    }

    // 释放占用的编号
    public boolean release(Integer code) {
        if(codes.containsKey(code)) {
            codes.replace(code, true, false); // 如果编号存在，则释放
            return true;
        }
        else  return false;
    }
}

