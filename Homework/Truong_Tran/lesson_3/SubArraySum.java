package com.example.hibernate;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {

    public int subarraySum(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = nums[i] + prefix[i - 1];
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int value;
        for (int i = 0; i < nums.length; i++) {
            int key = prefix[i];
            if (map.containsKey(key)) {
                value = map.get(key);
                map.put(key, value + 1);
            } else {
                map.put(key, 1);
            }
            int a = prefix[i] - k;
            if (map.containsKey(a)) {
                res += map.get(a);
            }
        }
        return res;
    }
}

