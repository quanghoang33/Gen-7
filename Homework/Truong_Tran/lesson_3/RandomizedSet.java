package com.example.hibernate;

import java.util.*;

public class RandomizedSet {

    Map<Integer, Integer> map = new HashMap<>();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, 1);
            return true;
        }
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            map.remove(val);
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.shuffle(list);
        return list.get(0);
    }
}
