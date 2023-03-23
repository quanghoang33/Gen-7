use std::collections::{HashMap, VecDeque};

const DX: [i8; 8] = [2, 1, -1, -2, -2, -1, 1, 2];
const DY: [i8; 8] = [1, 2, 2, 1, -1, -2, -2, -1];

fn minimum_knight_moves(x: i32, y: i32) -> i32 {
    let mut dis = 0;
    let root: [i32; 2] = [0, 0];
    let mut queue = VecDeque::new();
    queue.push_back(root);
    let mut visited: HashMap<[i32; 2], bool> = HashMap::new();

    while !queue.is_empty() {
        let current_level = queue.len();

        for _ in 0..current_level {
            if let Some(current_position) = queue.pop_front() {
                if current_position[0] == x && current_position[1] == y {
                    return dis;
                }

                for i in 0..8 {
                    let next = [
                        current_position[0] + (DX[i as usize] as i32),
                        current_position[1] + (DY[i as usize] as i32),
                    ];
                    if !visited.contains_key(&next) {
                        visited.insert(next, true);
                        queue.push_back(next);
                    }
                }
            }
        }
        dis += 1;
    }

    return dis;
}

#[cfg(test)]
mod tests {
    use crate::lesson_06::minimum_knight_moves::minimum_knight_moves;

    #[test]
    fn test_minimum_knight_moves() {
        let result = minimum_knight_moves(2, 1);
        assert_eq!(result, 1);

        let result = minimum_knight_moves(5, 5);
        assert_eq!(result, 4);
    }
}
