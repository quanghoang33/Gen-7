use std::collections::{HashMap, VecDeque};

const DY: [i8; 4] = [0, 1, 0, -1];
const DX: [i8; 4] = [1, 0, -1, 0];

fn shortest_path(grid: Vec<Vec<i32>>, k: i32) -> i32 {
    let mut current_depth = 0;
    let mut visited = HashMap::new();
    let (y_len, x_len) = (grid.len(), grid[0].len());

    let mut queue = VecDeque::new();
    queue.push_back((0, 0, k));

    while !queue.is_empty() {
        for _ in 0..queue.len() {
            let (y, x, k) = queue.pop_front().unwrap();

            if (y, x) == (y_len - 1, x_len - 1) {
                return current_depth;
            }

            for i in 0..4 {
                let next_y = y as isize + DY[i] as isize;
                let next_x = x as isize + DX[i] as isize;

                if next_y >= 0 && next_y < y_len as isize && next_x >= 0 && next_x < x_len as isize {
                    let cell = grid[next_y as usize][next_x as usize];
                    let new_k = k - cell;

                    if new_k >= 0 && !visited.contains_key(&(next_y, next_x, new_k)) {
                        visited.insert((next_y, next_x, new_k), true);
                        queue.push_back((next_y as usize, next_x as usize, new_k));
                    }
                }
            }
        }

        current_depth += 1;
    }

    return -1;
}

#[cfg(test)]
mod tests {
    use super::shortest_path;

    #[test]
    fn test_shortest_path() {
        // let grid = vec![
        //     vec![0, 0, 0],
        //     vec![1, 1, 0],
        //     vec![0, 0, 0],
        //     vec![0, 1, 1],
        //     vec![0, 0, 0],
        // ];
        // let k = 1;
        //
        // let result = shortest_path(grid, k);
        //
        // assert_eq!(result, 6);
        //
        // let grid = vec![vec![0, 1, 1], vec![1, 1, 1], vec![1, 0, 0]];
        // let k = 1;
        //
        // let result = shortest_path(grid, k);
        //
        // assert_eq!(result, -1);
        //
        // let grid = vec![
        //     vec![0, 0, 0, 1, 0],
        //     vec![0, 1, 0, 1, 0],
        //     vec![0, 1, 0, 1, 1],
        //     vec![0, 1, 1, 0, 1],
        //     vec![1, 1, 0, 0, 0],
        // ];
        // let k = 14;
        //
        // let result = shortest_path(grid, k);
        //
        // assert_eq!(result, 8);

        let grid = vec![
            vec![0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            vec![0, 1, 1, 1, 1, 1, 1, 1, 1, 0],
            vec![0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
            vec![0, 1, 0, 1, 1, 1, 1, 1, 1, 1],
            vec![0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
            vec![0, 1, 1, 1, 1, 1, 1, 1, 1, 0],
            vec![0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
            vec![0, 1, 0, 1, 1, 1, 1, 1, 1, 1],
            vec![0, 1, 0, 1, 1, 1, 1, 0, 0, 0],
            vec![0, 1, 0, 0, 0, 0, 0, 0, 1, 0],
            vec![0, 1, 1, 1, 1, 1, 1, 0, 1, 0],
            vec![0, 0, 0, 0, 0, 0, 0, 0, 1, 0],
        ];
        let k = 1;

        let result = shortest_path(grid, k);

        assert_eq!(result, 20);
    }
}
