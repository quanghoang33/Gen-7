/**
* Use sliding window. Create 2 pointers which are
* fast, and slow. Keep moving fast while the window
* is till valid (k >= 0) and move slow if the window
* become invalid (k < 0) to maintain the valid window.
*
* Runtime Complexity: O(n)
* Space complexity: O(1)
**/
fn longest_ones(nums: Vec<i32>, k: i32) -> i32 {
    let mut k = k;
    let (mut slow, mut fast) = (0, 0);

    while fast < nums.len() {
        if nums[fast] == 0 {
            k -= 1;
        }
        if k < 0 {
            if nums[slow] == 0 {
                k += 1;
            }
            slow += 1;
        }

        fast += 1;
    }

    // After the loop we can ensure the window is valid
    // and is the largest window.
    return (fast - slow) as i32;
}

#[cfg(test)]
mod tests {
    use super::longest_ones;

    #[test]
    fn test_longest_ones() {
        assert_eq!(6, longest_ones(vec![1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], 2));
        assert_eq!(
            10,
            longest_ones(vec![0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1], 3)
        );
    }
}
