// num[] -> num[]
// return an array of numbers which moved all even integers in the first positions
// then odd integers.

/**
 * Sử dụng Dutch Flag Partition.
 * Trường hợp này pivot là điều kiện phần tử
 * là số dương. Vì vậy chỉ cần lặp phần tử
 * kiểm tra là số dương thì swap với phần tử
 * ở boundary và tăng boundary lên 1 đơn vị là
 * được.
 *
 * Runtime Complexity: O(n) với n là nums.len()
 * Space Complexity: O(1)
 */
pub fn sort_array_by_parity(mut nums: Vec<i32>) -> Vec<i32> {
    let mut boundary = 0;
    let mut boundary_value = nums[boundary];

    for i in 0..nums.len() {
        if nums[i] % 2 == 0 {
            nums[boundary] = nums[i];
            nums[i] = boundary_value;

            boundary += 1;
            if boundary < nums.len() {
                boundary_value = nums[boundary];
            }
        }
    }

    return nums;
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_sort_array_by_parity() {
        assert_eq!(sort_array_by_parity(vec![3, 1, 2, 4]), vec![2, 4, 3, 1]);
        assert_eq!(sort_array_by_parity(vec![0]), vec![0]);
    }
}
