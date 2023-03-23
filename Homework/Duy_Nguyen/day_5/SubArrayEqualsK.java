package org.global.dev.day_5;

import java.util.HashMap;

public class SubArrayEqualsK {
	
	/**
	 * k = 3
	 * [1, 2, 3]
	 * prefix  [1, 3, 6]
	 * <p>
	 * init map (0, 1)
	 * <p>
	 * sum = 1 map (0, 1) | sum - k = -2
	 * sum = 3 map (0, 1) (1, 1) (3, 1) | sum - k = 0
	 * sum = 6 map (0, 1) (1, 1) (3, 1) (6, 1) | sum - k = 3
	 */
	
	public int subarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 0, sum = 0;
		map.put(0, 1);
		
		for (int num : nums) {
			// Prefix sum
			sum += num;
			if (map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}
	
}
