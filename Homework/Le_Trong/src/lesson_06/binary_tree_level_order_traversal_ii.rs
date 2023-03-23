use std::{borrow::Borrow, cell::RefCell, collections::VecDeque, rc::Rc};

// Definition for a binary tree node.
#[derive(Debug, PartialEq, Eq)]
pub struct TreeNode {
    pub val: i32,
    pub left: Option<Rc<RefCell<TreeNode>>>,
    pub right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
    #[inline]
    pub fn new(val: i32) -> Self {
        TreeNode {
            val,
            left: None,
            right: None,
        }
    }
}

fn level_order_bottom(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<i32>> {
    let mut result = vec![];
    let mut queue = VecDeque::new();
    queue.push_back(root);

    while queue.len() > 0 {
        let mut sub = vec![];
        let mut len = queue.len();
        while len > 0 {
            len -= 1;
            if let Some(Some(node)) = queue.pop_front() {
                if let Some(left) = &node.as_ref().borrow().left {
                    queue.push_back(Some(Rc::clone(left)));
                }
                if let Some(right) = &node.as_ref().borrow().right {
                    queue.push_back(Some(Rc::clone(right)));
                }
                sub.push(node.as_ref().borrow().val);
            }
        }
        result.push(sub);
    }

    result.reverse();
    return result;
}

#[cfg(test)]
mod tests {
    use std::{cell::RefCell, rc::Rc};

    use super::{level_order_bottom, TreeNode};

    #[test]
    fn test_level_order_bottom() {
        let root = Rc::new(RefCell::new(TreeNode::new(3)));
        let left = Rc::new(RefCell::new(TreeNode::new(9)));
        let right = Rc::new(RefCell::new(TreeNode::new(20)));
        let right_left = Rc::new(RefCell::new(TreeNode::new(15)));
        let right_right = Rc::new(RefCell::new(TreeNode::new(7)));
        root.as_ref().borrow_mut().left = Some(Rc::clone(&left));
        root.as_ref().borrow_mut().right = Some(Rc::clone(&right));
        right.as_ref().borrow_mut().left = Some(Rc::clone(&right_left));
        right.as_ref().borrow_mut().right = Some(Rc::clone(&right_right));

        let result = level_order_bottom(Some(root));

        assert_eq!(result, vec![vec![15, 7], vec![9, 20], vec![3]]);
    }
}
