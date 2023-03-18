struct Grid<'a> {
    matrix: &'a Vec<Vec<i32>>,
    rows: usize,
    cols: usize,
    visited: Vec<Vec<bool>>,
    dr: [isize; 4],
    dc: [isize; 4],
}

impl<'a> Grid<'a> {
    fn new(matrix: &'a Vec<Vec<i32>>) -> Self {
        return Grid {
            matrix,
            rows: matrix.len(),
            cols: matrix[0].len(),
            visited: vec![vec![false; matrix[0].len()]; matrix.len()],
            dr: [0, 1, 0, -1],
            dc: [1, 0, -1, 0],
        };
    }

    fn count_lands_aka_bfs(&mut self, r: usize, c: usize) -> u32 {
        self.visited[r][c] = true;
        let mut count = 1;

        for k in 0..4 {
            let i = (r as isize) + self.dr[k];
            let j = (c as isize) + self.dc[k];

            if i >= 0
                && i < self.rows as isize
                && j >= 0
                && j < self.cols as isize
                && !self.visited[i as usize][j as usize]
                && self.matrix[i as usize][j as usize] == 1
            {
                count += self.count_lands_aka_bfs(i as usize, j as usize);
            }
        }

        return count;
    }
}

fn max_area_of_island(grid: Vec<Vec<i32>>) -> i32 {
    let mut grid = Grid::new(&grid);
    let mut max = 0;
    for r in 0..grid.rows {
        for c in 0..grid.cols {
            if !grid.visited[r][c] && grid.matrix[r][c] == 1 {
                max = max.max(grid.count_lands_aka_bfs(r, c));
            }
        }
    }

    return max as i32;
}

#[cfg(test)]
mod tests {
    use super::max_area_of_island;

    #[test]
    fn test_max_area_of_island() {
        let grid = vec![
            vec![0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0],
            vec![0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0],
            vec![0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
            vec![0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0],
            vec![0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0],
            vec![0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0],
            vec![0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0],
            vec![0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0],
        ];

        max_area_of_island(grid);
    }
}
