// ListNode ->  ListNode
// produce the head which is the node that has smallest value, and link to others in ascending
// order.

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

fn sort_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    if head.as_ref().unwrap().next.is_none() {
        return head;
    }

    let (right, left) = split(head);

    merge(sort_list(left), sort_list(right))
}

fn split(mut head: Option<Box<ListNode>>) -> (Option<Box<ListNode>>, Option<Box<ListNode>>) {
    let mut fast = head.as_ref();
    let mut prev_mid_len = -1;

    while fast.is_some() && fast.unwrap().next.is_some() {
        fast = fast.unwrap().next.as_ref().unwrap().next.as_ref();
        prev_mid_len += 1;
    }

    let mut mid = head.as_mut();
    while prev_mid_len > 0 {
        mid = mid.unwrap().next.as_mut();
        prev_mid_len -= 1;
    }

    (mid.unwrap().next.take(), head)
}

fn merge(mut left: Option<Box<ListNode>>, mut right: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    let mut dummy = Some(Box::new(ListNode::new(-1)));
    let mut curent_node = dummy.as_mut();

    while left.is_some() && right.is_some() {
        if left.as_ref().unwrap().val > right.as_ref().unwrap().val {
            let temp = right.as_mut().unwrap().next.take();
            curent_node.as_mut().unwrap().next = right;
            curent_node = curent_node.unwrap().next.as_mut();
            right = temp;
        } else {
            let temp = left.as_mut().unwrap().next.take();
            curent_node.as_mut().unwrap().next = left;
            curent_node = curent_node.unwrap().next.as_mut();
            left = temp;
        }
    }

    if left.is_some() {
        curent_node.as_mut().unwrap().next = left;
    } else if right.is_some() {
        curent_node.as_mut().unwrap().next = right;
    }

    dummy.unwrap().next
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
                    next: Some(Box::new(ListNode {
                        val: 1,
                        next: Some(Box::new(ListNode { val: 6, next: None })),
                    })),
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
                        next: Some(Box::new(ListNode {
                            val: 5,
                            next: Some(Box::new(ListNode { val: 6, next: None }))
                        })),
                    })),
                })),
            })),
        )
    }
}
