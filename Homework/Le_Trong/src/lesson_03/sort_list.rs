// ListNode ->  ListNode
// produce the head which is the node that has smallest value, and link to others in ascending
// order.

use std::mem;

// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode { next: None, val }
    }
}

/**
 * Runtime Complexity: O(2k + (m * n * log n)) with k is the linked list's length,
 * (m*n*log n) is sort_by_key's runtime.
 *
 * Space Complexity: O(n + m) with n is the vector's length, and
 * m is linked list's length
 */
fn sort_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    let mut current_node = head;

    // Convert linked list to a vector nodes
    // for sorting them later.
    let mut array = vec![];
    while let Some(mut node) = current_node.take() {
        current_node = mem::replace(&mut node.next, None);
        array.push(node);
    }

    // Sort the vector
    array.sort_by_key(|node| node.as_ref().val);

    // Convert back the vector to linked list
    let mut dummy = ListNode::new(-1);
    let mut tail = &mut dummy;
    for node in array {
        tail.next = Some(node);
        tail = tail.next.as_mut().unwrap();
    }

    dummy.next
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_sort_list() {
        let head = Some(Box::new(ListNode {
            val: 4,
            next: Some(Box::new(ListNode {
                val: 3,
                next: Some(Box::new(ListNode {
                    val: 5,
                    next: Some(Box::new(ListNode { val: 1, next: None })),
                })),
            })),
        }));

        let sorted = sort_list(head);

        assert_eq!(
            sorted,
            Some(Box::new(ListNode {
                val: 1,
                next: Some(Box::new(ListNode {
                    val: 3,
                    next: Some(Box::new(ListNode {
                        val: 4,
                        next: Some(Box::new(ListNode { val: 5, next: None })),
                    })),
                })),
            })),
        )
    }
}
