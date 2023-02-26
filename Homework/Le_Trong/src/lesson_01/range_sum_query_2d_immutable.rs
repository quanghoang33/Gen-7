#[derive(Debug)]
pub struct NumMatrix {
    prefix_sum: Vec<Vec<i32>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumMatrix {
    pub fn new(matrix: Vec<Vec<i32>>) -> Self {
        let mut prefix_sum = vec![vec![0 as i32; matrix[0].len() + 1]; matrix.len() + 1];
        for row in 0..matrix.len() {
            for col in 0..matrix[row].len() {
                let row = row as usize;
                let col = col as usize;
                prefix_sum[row + 1][col + 1] =
                    prefix_sum[row + 1][col] + prefix_sum[row][col + 1] + matrix[row][col]
                        - prefix_sum[row][col];
            }
        }
        println!("prefix sum: {:?}", prefix_sum);
        return Self { prefix_sum };
    }

    /**
     * Dùng Prefix sum tính tổng cho các phần tử trong mảng 2d.
     * Vì là mảng 2d nên sẽ cần thêm một bước là phải cộng
     * sum của các hàng lại.
     *
     * Runtime Complextiy: O(1).
     * Space Complextiy: O(n*m) với n*m là số phần tử trong mảng 2d.
     */
    pub fn sum_region(&self, row1: i32, col1: i32, row2: i32, col2: i32) -> i32 {
        let row1 = row1 as usize;
        let col1 = col1 as usize;
        let row2 = row2 as usize;
        let col2 = col2 as usize;

        return self.prefix_sum[row2 + 1][col2 + 1]
            - self.prefix_sum[row1][col2 + 1]
            - self.prefix_sum[row2 + 1][col1]
            + self.prefix_sum[row1][col1];
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_range_sum_query_2d_immutable() {
        let num_matrix = NumMatrix::new(vec![
            vec![3, 0, 1, 4, 2],
            vec![5, 6, 3, 2, 1],
            vec![1, 2, 0, 1, 5],
            vec![4, 1, 0, 1, 7],
            vec![1, 0, 3, 0, 5],
        ]);

        assert_eq!(num_matrix.sum_region(2, 1, 4, 3), 8);
        assert_eq!(num_matrix.sum_region(1, 1, 2, 2), 11);
        assert_eq!(num_matrix.sum_region(1, 2, 2, 4), 12);

        let num_matrix = NumMatrix::new(vec![vec![-4, -5]]);

        assert_eq!(num_matrix.sum_region(0, 0, 0, 0), -4);
        assert_eq!(num_matrix.sum_region(0, 0, 0, 1), -9);
        assert_eq!(num_matrix.sum_region(0, 1, 0, 1), -5);
    }
}
