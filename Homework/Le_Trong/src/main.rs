mod lesson_01;

use lesson_01::*;

fn main() {
    println!(
        "Single number of [2, 2, 1]: {:?}",
        single_number(vec![2, 2, 1])
    );

    println!(
        "Hamming weight of 00000000000000000000000000001011: {}",
        hamming_weight(0b00000000000000000000000000001011)
    );
}
