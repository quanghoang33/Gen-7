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

/**
 * Sử dụng AND với 1 để kiểm tra xem bit đầu
 * tiên của n có phải là 1 không. Kết hợp
 * Shift right và để quy để loại bỏ bit vừa kiểm tra,
 * và gọi lại hàm cho n mới cho đến khi n = 0.
 *
 * Runtime Complexity: O(n) với n là chiều dài của chuỗi bits đầu vào.
 * Space Complexity: O(n) với n là chiều dài của chuỗi bits đầu vào.
 */
pub fn hamming_weight(n: u32) -> i32 {
    return if n == 0 {
        0
    } else {
        (n & 1) as i32 + hamming_weight(n >> 1)
    };
}

struct NumArray {
    nums: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumArray {
    fn new(nums: Vec<i32>) -> Self {
        return NumArray { nums };
    }

    /**
     * Dùng vòng lặp.
     *
     * Runtime Complexity: O(n * m) với n là số queries, và m là nums.len().
     * Space Complexity: O(1).
     */
    fn sum_range(&self, left: i32, right: i32) -> i32 {
        let mut result = 0;
        for i in left..=right {
            result += self.nums[i as usize];
        }
        return result;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * let obj = NumArray::new(nums);
 * let ret_1: i32 = obj.sum_range(left, right);
 */

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_single_number() {
        assert_eq!(single_number(vec![2, 2, 1]), 1);
        assert_eq!(single_number(vec![4, 1, 2, 1, 2]), 4);
        assert_eq!(single_number(vec![1]), 1);
    }

    #[test]
    fn test_hamming_weight() {
        assert_eq!(hamming_weight(0b00000000000000000000000000001011), 3);
        assert_eq!(hamming_weight(0b00000000000000000000000010000000), 1);
        assert_eq!(hamming_weight(0b11111111111111111111111111111101), 31);
    }

    #[test]
    fn test_range_sum_query_immutable() {
        let num_array = NumArray::new(vec![-2, 0, 3, -5, 2, -1]);

        assert_eq!(num_array.sum_range(0, 2), 1);
        assert_eq!(num_array.sum_range(2, 5), -1);
        assert_eq!(num_array.sum_range(0, 5), -3);
    }
}
