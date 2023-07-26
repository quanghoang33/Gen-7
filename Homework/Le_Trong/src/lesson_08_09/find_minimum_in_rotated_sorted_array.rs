fn find_min(nums: Vec<i32>) -> i32 {
    let len = nums.len();
    if len == 1 || nums[0] < nums[len - 1] {
        return nums[0];
    }

    let mut result = nums[0];
    let (mut left, mut right) = (0, len - 1);
    while left <= right {
        let mid = left + (right - left) / 2;
        if nums[mid] > nums[mid + 1] {
            result = nums[mid + 1];
            break;
        }
        if nums[mid] < nums[mid - 1] {
            result = nums[mid];
            break;
        }

        if nums[mid] > nums[left] {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return result;
}

#[cfg(test)]
mod tests {
    use super::find_min;

    #[test]
    fn test_find_min() {
        assert_eq!(find_min(vec![3, 4, 5, 1, 2]), 1);
        assert_eq!(find_min(vec![4, 5, 6, 7, 0, 1, 2]), 0);
        assert_eq!(find_min(vec![11, 13, 15, 17]), 11);
        assert_eq!(find_min(vec![1, 2]), 1);
        assert_eq!(find_min(vec![4, 5, 1, 2, 3]), 1);
    }
}
