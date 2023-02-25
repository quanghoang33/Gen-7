package org.global.dev.day_1;

import org.global.dev.common.ListNode;

/**
 * @author duyntc 02/2023
 */
public class Ex11_MiddleOftheLinkedList {
     
     /**
      * https://leetcode.com/problems/middle-of-the-linked-list/
      * Time: O(n)
      * Space: O(1)
      */

     public ListNode middleNode(ListNode head) {
	
	     ListNode slow = head;
	     ListNode fast = head;
	
	     while (fast != null && fast.next != null) {
		     fast = fast.next.next;
		     slow = slow.next;
	     }
	     return slow;
     }
}
