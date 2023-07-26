use std::{borrow::Borrow, cell::RefCell, rc::Rc};

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

fn lowest_common_ancestor(
    root: Option<Rc<RefCell<TreeNode>>>,
    p: Option<Rc<RefCell<TreeNode>>>,
    q: Option<Rc<RefCell<TreeNode>>>,
) -> Option<Rc<RefCell<TreeNode>>> {
    let mut result = None;

    fn dfs(
        root: &Option<Rc<RefCell<TreeNode>>>,
        result: &mut Option<Rc<RefCell<TreeNode>>>,
        p: i32,
        q: i32,
    ) -> bool {
        if let Some(ref_node) = root.as_ref() {
            let node = ref_node.as_ref().borrow();

            let found_at_current = node.val == q || node.val == p;

            let left = dfs(&node.left, result, p, q);

            let right = dfs(&node.right, result, p, q);

            if found_at_current && left || found_at_current && right || left && right {
                *result = Some(Rc::clone(&ref_node));
            }

            return found_at_current || left || right;
        } else {
            return false;
        }
    }

    dfs(
        &root,
        &mut result,
        p.as_ref().unwrap().as_ref().borrow().val,
        q.as_ref().unwrap().as_ref().borrow().val,
    );

    return result;
}

#[cfg(test)]
mod tests {
    use std::{cell::RefCell, rc::Rc};

    use super::{lowest_common_ancestor, TreeNode};

    #[test]
    fn test_lowest_common_ancestor() {
        let root = Rc::new(RefCell::new(TreeNode::new(6)));
        let left = Rc::new(RefCell::new(TreeNode::new(2)));
        let left_left = Rc::new(RefCell::new(TreeNode::new(0)));
        let left_right = Rc::new(RefCell::new(TreeNode::new(4)));
        let left_right_left = Rc::new(RefCell::new(TreeNode::new(3)));
        let left_right_right = Rc::new(RefCell::new(TreeNode::new(5)));
        let right = Rc::new(RefCell::new(TreeNode::new(8)));
        let right_left = Rc::new(RefCell::new(TreeNode::new(7)));
        let right_right = Rc::new(RefCell::new(TreeNode::new(9)));
        root.borrow_mut().left = Some(Rc::clone(&left));
        left.borrow_mut().left = Some(Rc::clone(&left_left));
        left.borrow_mut().right = Some(Rc::clone(&left_right));
        left_right.borrow_mut().left = Some(Rc::clone(&left_right_left));
        left_right.borrow_mut().right = Some(Rc::clone(&left_right_right));
        root.borrow_mut().right = Some(Rc::clone(&right));
        right.borrow_mut().left = Some(Rc::clone(&right_left));
        right.borrow_mut().right = Some(Rc::clone(&right_right));

        let result = lowest_common_ancestor(
            Some(Rc::clone(&root)),
            Some(Rc::clone(&left)),
            Some(Rc::clone(&right)),
        );

        assert_eq!(result, Some(Rc::clone(&root)));
    }
}
