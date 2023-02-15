pub struct NumArray {
    prefix_sum: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumArray {
    pub fn new(mut nums: Vec<i32>) -> Self {
        for i in 1..nums.len() {
            nums[i] = nums[i] + nums[i - 1];
        }

        return NumArray { prefix_sum: nums };
    }

    /**
     * Dùng prefix sum.
     *
     * Runtime Complexity: O(n + m) với n là số queries, và m là nums.len().
     * Space Complexity: O(1).
     */
    pub fn sum_range(&self, left: i32, right: i32) -> i32 {
        return if left == 0 {
            self.prefix_sum[right as usize]
        } else {
            self.prefix_sum[right as usize] - self.prefix_sum[(left - 1) as usize]
        };
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_range_sum_query_immutable() {
        let num_array = NumArray::new(vec![-2, 0, 3, -5, 2, -1]);

        assert_eq!(num_array.sum_range(0, 2), 1);
        assert_eq!(num_array.sum_range(2, 5), -1);
        assert_eq!(num_array.sum_range(0, 5), -3);
    }
}
