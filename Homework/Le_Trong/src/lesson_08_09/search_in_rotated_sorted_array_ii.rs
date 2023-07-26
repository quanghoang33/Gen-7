fn search(nums: Vec<i32>, target: i32) -> bool {
    if nums.len() == 1 {
        return nums[0] == target;
    }

    let (mut left, mut right) = (0, nums.len() - 1);
    while left <= right {
        let mid = left + (right - left) / 2;

        if nums[mid] == target {
            return true;
        }

        if nums[mid] == nums[left] {
            left += 1;
            continue;
        }

        let mid_in_first_side = nums[mid] >= nums[left];
        let target_in_first_side = target >= nums[left];
        if mid_in_first_side ^ target_in_first_side {
            if mid_in_first_side {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } else {
            if nums[mid] < target {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return false;
}

#[cfg(test)]
mod tests {
    use super::search;

    #[test]
    fn test_search() {
        assert_eq!(search(vec![2, 5, 6, 0, 0, 1, 2], 0), true);
        assert_eq!(search(vec![1, 0, 1, 1, 1], 0), true);
        assert_eq!(
            search(
                vec![1, 1, 1, 1, 1, 1, 1, 1, 1, 13, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                13
            ),
            true
        );
    }
}
