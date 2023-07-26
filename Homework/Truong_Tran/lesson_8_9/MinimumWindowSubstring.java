package lesson7;

import java.util.HashMap;
import java.util.Set;

public class MinimumWindowSubstring {

    HashMap<Character, Integer> freq = new HashMap<>(); // frequency
    HashMap<Character, Integer> dic = new HashMap<>(); // dictionary

    public String minWindow(String s, String t) {
        int start = 0;
        int end = 0;
        int min = s.length();
        String res = "";
        for (Character c : t.toCharArray()) {
            dic.put(c, dic.getOrDefault(c, 0) + 1);
        }
        while (start < s.length()) {
            if (isValidSubString()) {
                if (end - start <= min) {
                    res = s.substring(start, end);
                    min = end - start;
                }
                if (freq.containsKey(s.charAt(start))) {
                    Character key = s.charAt(start);
                    int value = freq.get(key);
                    if (value > 1) {
                        freq.put(key, value - 1);
                    } else {
                        freq.remove(key);
                    }
                }
                start++;
            } else {
                if (end < s.length()) {
                    if (dic.containsKey(s.charAt(end))) {
                        Character key = s.charAt(end);
                        int value = freq.get(key) == null ? 0 : freq.get(key);
                        freq.put(key, value + 1);
                    }
                    end++;
                } else {
                    start++;
                }
            }
        }
        return res;
    }

    private boolean isValidSubString() {
        if (freq.size() != dic.size()) {
            return false;
        }
        Set<Character> keys = dic.keySet();
        for (Character c : keys) {
            if (freq.get(c) == null || dic.get(c) > freq.get(c)) {
                return false;
            }
        }
        return true;
    }
}
