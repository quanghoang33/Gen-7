from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    # def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
    #     cur = None
    #
    #     while head:
    #         temp = head
    #         head = head.next
    #         temp.next = cur
    #         cur = temp
    #
    #     return cur

    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head

        reverse_node = self.reverseList(head.next)
        head.next.next = head
        head.next = None
        return reverse_node
