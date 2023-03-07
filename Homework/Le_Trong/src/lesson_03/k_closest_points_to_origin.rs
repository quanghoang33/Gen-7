fn calculate_distance(point: &Vec<i32>) -> f32 {
    let (x, y) = (point[0] as f32, point[1] as f32);
    (x * x + y * y).sqrt()
}

fn k_closest(points: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
    let mut points = points.clone();
    let len = points.len() as i32;

    quick_select(&mut points, 0, len - 1, k);

    points[..(k as usize)].to_vec()
}

fn quick_select(points: &mut Vec<Vec<i32>>, start: i32, end: i32, k: i32) {
    if start >= end {
        return;
    }

    let pivot_index = partition(points, start, end);

    if k == pivot_index {
        return;
    } else if k < pivot_index {
        quick_select(points, 0, pivot_index - 1, k);
    } else {
        quick_select(points, pivot_index + 1, end, k);
    }
}

fn partition(points: &mut Vec<Vec<i32>>, start: i32, end: i32) -> i32 {
    let pivot = calculate_distance(&points[end as usize]);
    let mut pointer = start;

    for i in start..end {
        if calculate_distance(&points[i as usize]) <= pivot {
            points.swap(i as usize, pointer as usize);
            pointer += 1;
        }
    }

    points.swap(end as usize, pointer as usize);

    pointer
}

#[cfg(test)]
mod tests {
    use super::k_closest;

    #[test]
    fn test_k_closest() {
        let points = vec![
            vec![68, 97],
            vec![34, -84],
            vec![60, 100],
            vec![2, 31],
            vec![-27, -38],
            vec![-73, -74],
            vec![-55, -39],
            vec![62, 91],
            vec![62, 92],
            vec![-57, -67],
        ];
        let k = 5;

        let result = k_closest(points, k);
println!("{:?}", result);
        assert_eq!(
            result,
            vec![
                vec![2, 31],
                vec![-27, -38],
                vec![-55, -39],
                vec![-57, -67],
                vec![34, -84]
            ]
        );
    }

    #[test]
    fn test_k_closest_2() {
        let points = vec![vec![0, 1], vec![1, 0]];
        let k = 2;

        let result = k_closest(points, k);

        assert_eq!(result, vec![vec![0, 1], vec![1, 0]]);
    }

    #[test]
    fn test_k_closest_3() {
        let points = vec![vec![1, 3], vec![-2, 2], vec![2, -2]];
        let k = 2;

        let result = k_closest(points, k);

        assert_eq!(result, vec![vec![-2, 2], vec![2, -2]]);
    }

    #[test]
    fn test_k_closest_4() {
        let points = vec![vec![2, 2], vec![2, 2], vec![3, 3], vec![2, -2], vec![1, 1]];
        let k = 4;

        let result = k_closest(points, k);

        assert_eq!(result, vec![vec![1, 1], vec![2, 2], vec![2, 2], vec![2, -2]]);
    }
}
