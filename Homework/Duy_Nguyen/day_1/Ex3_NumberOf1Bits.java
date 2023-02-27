package org.global.dev.day_1;

/**
 * @author duyntc 02/2023
 */
public class Ex3_NumberOf1Bits {
     
     /**
      * Time: O(1)
      * Space: O(1)
      */
     static public int hammingWeight(int n) {
	     int count = 0;
	     int mask = 1;
	     int index = 32;
	
	     while (index-- > 0) {
		     if ((mask & n) == 1) {
			     count++;
		     }
		     n = n >> 1;
	     }
	     return count;
     }
     
}
