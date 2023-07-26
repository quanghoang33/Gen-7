pub mod lesson_01;
pub mod lesson_03;
pub mod lesson_06;
pub mod lesson_08_09;

use lesson_01::hamming_weight::hamming_weight;
use lesson_01::happy_number::is_happy;
use lesson_01::range_sum_query_2d_immutable::*;
use lesson_01::range_sum_query_immutable::*;
use lesson_01::reverse_linked_list::*;
use lesson_01::single_number::single_number;
use lesson_01::sort_array_by_parity::sort_array_by_parity;
use lesson_01::subsets::subsets;

fn main() {
    println!("Single number of [2, 2, 1]: {:?}", single_number(vec![2, 2, 1]));

    println!(
        "Hamming weight of 00000000000000000000000000001011: {}",
        hamming_weight(0b00000000000000000000000000001011)
    );

    println!(
        "Range sum query immutable of [-2, 0, 3, -5, 2, -1]: {}",
        (NumArray::new(vec![-2, 0, 3, -5, 2, -1])).sum_range(0, 2)
    );

    let num_matrix = NumMatrix::new(vec![
        vec![3, 0, 1, 4, 2],
        vec![5, 6, 3, 2, 1],
        vec![1, 2, 0, 1, 5],
        vec![4, 1, 0, 1, 7],
        vec![1, 0, 3, 0, 5],
    ]);
    println!(
        "Range sum query immutable of {:?}: {}",
        num_matrix,
        num_matrix.sum_region(2, 1, 4, 3)
    );

    let nums = vec![3, 1, 2, 4];
    println!(
        "Sort array by parity if {:?}: {:?}",
        nums,
        sort_array_by_parity(nums.clone())
    );

    let head = Some(Box::new(create_a_1_2_3_4_5_linked_list()));
    println!("Reversed of list {:?}: {:?}", head.clone(), reverse_list(head));

    println!("{} is happy number? {:?}", 19, is_happy(19));
    println!("{} is happy number? {:?}", 1, is_happy(1));
    println!("{} is happy number? {:?}", 2, is_happy(2));

    println!("subsets of {:?}: {:?}", vec![1, 2, 3], subsets(vec![1, 2, 3]));
}
