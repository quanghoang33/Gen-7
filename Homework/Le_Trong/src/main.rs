pub mod lesson_01;

use lesson_01::hamming_weight::hamming_weight;
use lesson_01::range_sum_query_immutable::*;
use lesson_01::single_number::single_number;

fn main() {
    println!(
        "Single number of [2, 2, 1]: {:?}",
        single_number(vec![2, 2, 1])
    );

    println!(
        "Hamming weight of 00000000000000000000000000001011: {}",
        hamming_weight(0b00000000000000000000000000001011)
    );

    println!(
        "Range sum query immutable of [-2, 0, 3, -5, 2, -1]: {}",
        (NumArray::new(vec![-2, 0, 3, -5, 2, -1])).sum_range(0, 2)
    )
}
