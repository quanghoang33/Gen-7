package org.global.dev.day_1;

import org.global.dev.common.ListNode;

/**
 * @author duyntc 02/2023
 */
public class Ex10_ReverseLinkedList {
	
	static public ListNode reverseListRecursive(ListNode head) {
		if (head == null)
			return null;
		if (head.next == null)
			return head;
		return run(head, null);
	}
	
	// null -> 1 -> 2
	// null
	static public ListNode run(ListNode head, ListNode prev) {
		if (head == null) {
			return prev;
		}
		
		ListNode temp = head.next;
		head.next = prev;
		prev = head;
		head = temp;
		
		return run(head, prev);
	}
	
	public static void main(String[] args) {
		ListNode root = new ListNode(1);
		root.next = new ListNode(2);
		root.next.next = new ListNode(3);
		
		reverseListRecursive(root);
	}
	
	/**
	 * https://leetcode.com/problems/reverse-linked-list/
	 * Time: O(n)
	 * Space: O(1)
	 */
	
	public ListNode reverseList(ListNode head) {
		
		if (head == null)
			return null;
		if (head.next == null)
			return head;
		
		ListNode prev = null;
		
		while (head != null) {
			ListNode temp = head.next;
			head.next = prev;
			prev = head;
			head = temp;
		}
		
		return prev;
	}
}
