package org.global.dev.day_1;

/**
 * @author duyntc 02/2023
 */
public class Ex2_PowerOfTwo {
     /**
      * Time: O(1)
      * Space: O(1)
      */
     public boolean isPowerOfTwo(int n) {
	     // Any number power of two is bigger than 0
	     if (n <= 0)
		     return false;
	     return (n & (n - 1)) == 0;
     }
     
}
