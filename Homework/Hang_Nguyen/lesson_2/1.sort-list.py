# https://leetcode.com/problems/sort-list/

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution:

    # HELPER
    def getMid(self, head):
        slow, fast = head, head.next
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        return slow

    def merge(self, left, right):
        tail = dummy = ListNode()
        while left and right:
            if left.val < right.val:
                tail.next = left
                left = left.next
            else:
                tail.next = right
                right = right.next
            tail = tail.next
        if left:
            tail.next = left
        if right:
            tail.next = right
        return dummy.next
    
    def printList(self, head): 
        current = head
        while current:
            print(current.val)
            current = current.next

    def sortList(self, head):
        if head is None or head.next is None:
            return head
        
        # split list at middle
        left = head
        right = self.getMid(head)
        temp = right.next
        right.next = None
        right = temp

        left = self.sortList(left)
        right = self.sortList(right)
        return self.merge(left, right)
    


# TEST CASE
solution = Solution()

head = ListNode(4, ListNode(2, ListNode(1, ListNode(3))))
head = solution.sortList(head)
print("TEST 1 is ")
solution.printList(head)

head = ListNode(-1, ListNode(5, ListNode(3, ListNode(4, ListNode(0)))))
head = solution.sortList(head)
print("TEST 2 is ")
solution.printList(head)

head = ListNode()
head = solution.sortList(head)
print("TEST 3 is ")
solution.printList(head)