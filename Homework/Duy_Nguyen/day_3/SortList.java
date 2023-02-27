package org.global.dev.day_3;

import org.global.dev.common.ListNode;

public class SortList {
	
	
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode mid = getMid(head);
		ListNode left = sortList(head);
		ListNode right = sortList(mid);
		return merge(left, right);
	}
	
	// 1 -> 5 -> 7
	// 2 -> 3 -> 8
	
	public ListNode merge(ListNode list1, ListNode list2) {
		ListNode head = new ListNode();
		ListNode run = head;
		
		while (list1 != null && list2 != null) {
			
			if (list1.val < list2.val) {
				run.next = list1;
				list1 = list1.next;
			} else {
				run.next = list2;
				list2 = list2.next;
			}
			run = run.next;
		}
		
		run.next = (list1 != null) ? list1 : list2;
		return head.next;
	}
	
	public ListNode getMid(ListNode head) {
		ListNode slow = null;
		ListNode fast = head;
		
		while (fast != null && fast.next != null) {
			slow = (slow == null) ? head : slow.next;
			fast = fast.next.next;
		}
		
		assert slow != null;
		ListNode mid = slow.next;
		slow.next = null;
		return mid;
	}
	
}
