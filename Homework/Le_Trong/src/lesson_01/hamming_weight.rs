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

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_hamming_weight() {
        assert_eq!(hamming_weight(0b00000000000000000000000000001011), 3);
        assert_eq!(hamming_weight(0b00000000000000000000000010000000), 1);
        assert_eq!(hamming_weight(0b11111111111111111111111111111101), 31);
    }
}
