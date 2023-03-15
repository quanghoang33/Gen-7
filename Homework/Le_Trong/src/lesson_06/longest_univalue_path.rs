use std::{cell::RefCell, rc::Rc};

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

fn dfs(node: Option<&Rc<RefCell<TreeNode>>>, max: &mut i32) -> i32 {
    if let Some(node) = node {
        let node = node.as_ref().borrow();
        let left = dfs(node.left.as_ref(), max);
        let right = dfs(node.right.as_ref(), max);

        let (mut left_len, mut right_len) = (0, 0);
        if let Some(ref l) = node.left {
            if node.val == l.as_ref().borrow().val {
                left_len = left + 1;
            }
        }
        if let Some(ref r) = node.right {
            if node.val == r.as_ref().borrow().val {
                right_len = right + 1;
            }
        }

        *max = (*max).max(left_len + right_len);

        return left_len.max(right_len);
    } else {
        return 0;
    }
}

pub fn longest_univalue_path(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    let mut max = 0;

    dfs(root.as_ref(), &mut max);

    return max;
}

#[cfg(test)]
mod tests {
    use std::{cell::RefCell, rc::Rc};

    use super::{dfs, TreeNode};

    #[test]
    fn test_longest_univalue_path() {
        let mut max = 0;
        let tree = Rc::new(RefCell::new(TreeNode::new(5)));
        let left_4 = Rc::new(RefCell::new(TreeNode::new(4)));
        let left_4_left_4 = Rc::new(RefCell::new(TreeNode::new(4)));
        let left_4_right_4 = Rc::new(RefCell::new(TreeNode::new(4)));
        let right_5 = Rc::new(RefCell::new(TreeNode::new(5)));
        let right_5_right_5 = Rc::new(RefCell::new(TreeNode::new(5)));
        tree.as_ref().borrow_mut().left = Some(Rc::clone(&left_4));
        tree.as_ref().borrow_mut().right = Some(Rc::clone(&right_5));
        left_4.as_ref().borrow_mut().left = Some(Rc::clone(&left_4_left_4));
        left_4.as_ref().borrow_mut().right = Some(Rc::clone(&left_4_right_4));
        right_5.as_ref().borrow_mut().right = Some(Rc::clone(&right_5_right_5));

        dfs(Some(Rc::clone(&tree)).as_ref(), &mut max);

        assert_eq!(2, max);
    }
}
