class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution:
    # iterative way
    def reverseList(self, head):
        prev = None
        current = head
        while(current is not None):
            next = current.next
            current.next = prev
            prev = current
            current = next
        return prev

    # recursive way
    def reverse(self, head):
        if head is None or head.next is None:
            return head
        newHead = self.reverse(head.next)
        head.next.next = head
        head.next = None
        return newHead



# TEST CASE
node1 = ListNode(5)
node2 = ListNode(9)
node3 = ListNode(12)
node4 = ListNode(8)
node5 = ListNode(7)

node1.next = node2
node2.next = node3
node3.next = node4
node4.next = node5 

node = node1
while node:
    print(node.val)
    node = node.next

print("/----------Reverse Linked List: -----------/")

# node1 = Solution().reverseList(node1)
node1 = Solution().reverse(node1)
node = node1
while node:
    print(node.val)
    node = node.next