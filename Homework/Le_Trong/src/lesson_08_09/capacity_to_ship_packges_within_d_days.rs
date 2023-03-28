fn ship_within_days(weights: Vec<i32>, days: i32) -> i32 {
    let mut min_capacity = weights.iter().max().unwrap();
    let mut max_capacity: i32 = weights.iter().sum();
    let mut result = 0;

    for i in *min_capacity..=max_capacity {
        let mut days_needed = 1;
        let mut current_load = 0;
        for weight in weights.iter() {
            current_load += weight;
            if current_load > i {
                days_needed += 1;
                current_load = *weight;
            }
        }
        if days_needed <= days {
            result = if result == 0 { i } else { result.min(i) };
        }
    }

    return result;
}

#[cfg(test)]
mod tests {
    use super::ship_within_days;

    #[test]
    fn test_ship_widthin_days() {
        assert_eq!(ship_within_days(vec![1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 5), 15);
        assert_eq!(ship_within_days(vec![3, 2, 2, 4, 1, 4], 3), 6);
        assert_eq!(ship_within_days(vec![1, 2, 3, 1, 1], 4), 3);
    }
}
