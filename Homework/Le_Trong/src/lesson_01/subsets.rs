// Integer[] -> Integer[][]
// produce all possible subsets of the given list of integers.

/**
 * Runtime Complexity: O(2^n * n) với n là nums.len()
 * Space Complexity: O(2^n * n) với n là nums.len()
 */
pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
    let mut result: Vec<Vec<i32>> = vec![];

    for i in 0..2_i32.pow(nums.len() as u32) {
        let mut subset: Vec<i32> = vec![];
        for j in 0..nums.len() {
            let bit = (i >> j) & 1;
            if bit == 1 {
                subset.push(nums[j]);
            }
        }
        result.push(subset);
    }

    return result;
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_subsets() {
        assert_eq!(
            subsets(vec![1, 2, 3]),
            vec![
                vec![],
                vec![1],
                vec![2],
                vec![1, 2],
                vec![3],
                vec![1, 3],
                vec![2, 3],
                vec![1, 2, 3],
            ]
        );
    }
}
