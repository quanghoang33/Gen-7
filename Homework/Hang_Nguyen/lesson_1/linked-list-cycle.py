class ListNode:
    def __init__(self, x, next = None):
        self.val = x
        self.next = next

class Solution:
    def hasCycle(self, head):
        fast = head
        slow = head
        while (fast is not None) and (fast.next is not None):
            slow = slow.next
            fast = fast.next.next
            if slow == fast: return True
        return False


# TEST CASE
node1 = ListNode(3)
node2 = ListNode(2)
node3 = ListNode(0)
node4 = ListNode(4)

node1.next = node2
node2.next = node3
node3.next = node4
node4.next = node2 

# node = node1
# while node:
#     print(node.val)
#     node = node.next

print("TEST 1 has cycle: " + str(Solution().hasCycle(node1)))

nodeA = ListNode(1)
print("TEST 2 has cycle: " + str(Solution().hasCycle(nodeA)))