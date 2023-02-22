from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head

        fast, slow = head.next, head
        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next

        head_right = slow.next
        slow.next = None
        left, right = self.sortList(head), self.sortList(head_right)
        return self.merge(left, right)

    def merge(self, h1: Optional[ListNode], h2: Optional[ListNode]) -> Optional[ListNode]:
        if not h1 or not h2:
            return h1 or h2

        dummy = cur = ListNode()
        while h1 and h2:
            if h1.val > h2.val:
                cur.next = h2
                h2 = h2.next
            else:
                cur.next = h1
                h1 = h1.next
            cur = cur.next
        cur.next = h1 or h2

        return dummy.next
