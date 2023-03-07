package org.global.dev.day_5;

import java.util.*;

public class InsertDeleteGetRandomO_1 {
	
	static class RandomizedSet {
		Map<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> arr = new ArrayList<>();
		public RandomizedSet() {
		}
		
		public boolean insert(int val) {
			if (map.containsKey(val)) return false;
			int size = arr.size(); // Have to size current size, using arr.size() is wrong
			arr.add(size, val);
			map.put(val, size);
			return true;
		}
		
		public boolean remove(int val) {
			
			if (!map.containsKey(val)) return false;
			/**
			 * Swap two element the last one and the remove
			 *
			 * For example:
			 * REMOVE 3
			 * ARR: 1 -2 3 0
			 *
			 * Map
			 *  1 -> 0
			 *  -2 -> 1
			 *  3 -> 2
			 *  0 -> 3
			 *
			 *  Swap Arr = 3 and the last one
			 *  ARR: 1 -2 0 0
			 *  Map
			 *  1 -> 0
			 *  -2 -> 1
			 *  3 -> 2
			 *  0 -> map.get(3)
			 *
			 *  Remove last index of arr
			 *  Remove key of map
			 */
			
			int lastElement = arr.get(arr.size() - 1);
			int valIndex = map.get(val);
			
			// Update Arr
			arr.set(valIndex, lastElement);
			arr.remove(arr.size() - 1); // remove last index
			
			// Update map
			map.put(lastElement, valIndex);
			map.remove(val);
			return true;
		}
		
		public int getRandom() {
			int index = (new Random().nextInt() & Integer.MAX_VALUE) % arr.size();
			return arr.get(index);
		}
	}
	
	public static void main(String[] args) {
		RandomizedSet set = new RandomizedSet();
		boolean x = set.insert(0);
		boolean y = set.insert(1);
		boolean z = set.remove(0);
		set.insert(2);
		set.remove(1);
		System.out.println(set.getRandom()); // expect 2
	}
	
}
