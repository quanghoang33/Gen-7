use std::collections::VecDeque;

const ROW: [i32; 4] = [-1, 0, 1, 0];
const COL: [i32; 4] = [0, 1, 0, -1];

fn max_distance(grid: Vec<Vec<i32>>) -> i32 {
    let mut queue = VecDeque::new();
    let mut visited = vec![vec![false; grid[0].len()]; grid.len()];

    for row in 0..grid.len() {
        for col in 0..grid[0].len() {
            if grid[row][col] == 1 {
                queue.push_back((row, col));
                visited[row][col] = true;
            }
        }
    }

    let mut distance = -1;
    while !queue.is_empty() {
        let mut len = queue.len();
        while len > 0 {
            let cell = queue.pop_front().unwrap();

            for i in 0..4 {
                let row = cell.0 as i32 + ROW[i];
                let col = cell.1 as i32 + COL[i];

                if row >= 0
                    && row < grid.len() as i32
                    && col >= 0
                    && col < grid.len() as i32
                    && !visited[row as usize][col as usize]
                {
                    visited[row as usize][col as usize] = true;
                    queue.push_back((row as usize, col as usize));
                }
            }

            len -= 1;
        }

        distance += 1;
    }

    return if distance == 0 { -1 } else { distance };
}

#[cfg(test)]
mod tests {
    use super::max_distance;

    #[test]
    fn test_max_distance() {
        let grid = vec![vec![1, 0, 1], vec![0, 0, 0], vec![1, 0, 1]];

        let result = max_distance(grid);

        assert_eq!(result, 2);
    }
}
