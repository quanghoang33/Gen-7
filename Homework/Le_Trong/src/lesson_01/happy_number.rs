/**
 * Runtime Complexity: O((n + m) * k) với n là số chữ số, m là appeared_nums.len()
 * và k là số lần chạy cho đến khi sum = 1 hay appeared_nums chứa sum.
 *
 * Space Complexity: O(n + m) với n là số chữ số, m là số phần tử trong mảng.
 */
pub fn is_happy(n: i32) -> bool {
    let mut digits = n.to_string();
    let mut appeared_nums = vec![n];

    loop {
        let mut sum: i32 = 0;

        // Duyệt qua các chữ số của n,
        // tính tổng bình phương của các
        // chữ số đó.
        for digit in digits.chars() {
            sum += (digit.to_digit(10).unwrap() as i32).pow(2);
        }

        // Nếu tổng bằng 1 thì đây là happy number...
        if sum == 1 {
            return true;
        }

        // ... Nếu nằm trong mảng appeared_nums
        // thì có nghĩa là sum này đã xuất hiện
        // hơn 1 lần, nên chắc chắn sẽ không
        // thể tạo ra sum = 1 được nữa. Nên không
        // phải là happy number.
        if appeared_nums.contains(&sum) {
            return false;
        }

        appeared_nums.push(sum);
        digits = sum.to_string();
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_is_happy() {
        assert_eq!(is_happy(19), true);
        assert_eq!(is_happy(2), false);
        assert_eq!(is_happy(1), true);
        assert_eq!(is_happy(7), true);
    }
}
