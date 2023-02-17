#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        return Self { val, next: None };
    }
}

/**
 * Runtime Complexity: O(n) với n là số phần tử của list.
 * Space Complexity: O(1).
 */
pub fn reverse_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    let mut current = head;
    let mut prev = None;

    while let Some(mut node) = current.take() {
        let next = node.next.take();
        node.next = prev.take();
        prev = Some(node);
        current = next;
    }

    prev
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_reverse_list() {
        let head = create_a_1_2_3_4_5_linked_list();

        assert_eq!(
            reverse_list(Some(Box::new(head))),
            Some(Box::new(create_a_5_4_3_2_1_linked_list()))
        );
    }

    fn create_a_5_4_3_2_1_linked_list() -> ListNode {
        let mut head = ListNode::new(5);
        head.next = Some(Box::new(ListNode::new(4)));
        head.next.as_mut().unwrap().next = Some(Box::new(ListNode::new(3)));
        head.next.as_mut().unwrap().next.as_mut().unwrap().next = Some(Box::new(ListNode::new(2)));
        head.next
            .as_mut()
            .unwrap()
            .next
            .as_mut()
            .unwrap()
            .next
            .as_mut()
            .unwrap()
            .next = Some(Box::new(ListNode::new(1)));

        return head;
    }
}

pub fn create_a_1_2_3_4_5_linked_list() -> ListNode {
    let mut head = ListNode::new(1);
    head.next = Some(Box::new(ListNode::new(2)));
    head.next.as_mut().unwrap().next = Some(Box::new(ListNode::new(3)));
    head.next.as_mut().unwrap().next.as_mut().unwrap().next = Some(Box::new(ListNode::new(4)));
    head.next
        .as_mut()
        .unwrap()
        .next
        .as_mut()
        .unwrap()
        .next
        .as_mut()
        .unwrap()
        .next = Some(Box::new(ListNode::new(5)));

    return head;
}
