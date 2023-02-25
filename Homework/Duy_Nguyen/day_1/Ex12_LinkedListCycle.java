package org.global.dev.day_1;

import org.global.dev.common.ListNode;

/**
 * @author duyntc 02/2023
 */
public class Ex12_LinkedListCycle {
	
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		
		ListNode slow = head;
		ListNode fast = head.next;
		
		while (slow != fast) {
			if (fast == null || fast.next == null)
				return false;
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
		
	}
}
