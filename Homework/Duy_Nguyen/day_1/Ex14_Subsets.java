package org.global.dev.day_1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duyntc 02/2023
 */
public class Ex14_Subsets {
     
     /**
      * https://leetcode.com/problems/subsets/description/
      * Time: O(n)
      * Space: O(1)
      */

     static public List<List<Integer>> subsets(int[] nums) {
	     List<List<Integer>> result = new ArrayList<>();
	
	     // Build bit map
	     int total = (int) Math.pow(2, nums.length);
	     List<String> bitMap = new ArrayList<>(total);
	     for (int i = 0; i < total; i++) {
		     String binary = Integer.toBinaryString(i);
		     bitMap.add("0".repeat(nums.length - binary.length()) + binary);
	     }
	
	     for (int i = 0; i < total; i++) {
		
		     List<Integer> temp = new ArrayList<>();
		     String mask = bitMap.get(i);
		     for (int j = 0; j < mask.length(); j++) {
			     if (mask.charAt(j) == '1') {
				     temp.add(nums[j]);
			     }
		     }
		     result.add(temp);
	     }
	
	     return result;
     }
     
     public static void main(String[] args) {
	System.out.println(subsets(new int[]{1, 2, 3}));
     }
}
