/**
 * Dùng XOR: Số giống nhau khi XOR sẽ
 * bằng 0. Có thể áp dụng cho 1 dãy số
 * mà các số giống nhau nhưng không liền
 * kề nhau.
 *
 * Ví dụ:
 *  1 ^ 1 = 0
 *  1 ^ 2 ^ 1 ^ 2 = 0
 *
 *  Runtime Complexity: O(n) với n là nums.len()
 *  Space Complexity: O(1)
 */
pub fn single_number(nums: Vec<i32>) -> i32 {
    let mut result = nums[0];
    for i in 1..(nums.len()) {
        result = result ^ nums[i];
    }

    return result;
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_single_number() {
        assert_eq!(single_number(vec![2, 2, 1]), 1);
        assert_eq!(single_number(vec![4, 1, 2, 1, 2]), 4);
        assert_eq!(single_number(vec![1]), 1);
    }
}
