use std::collections::{HashMap, VecDeque};

const CHARS: [u8; 4] = [b'A', b'C', b'G', b'T'];

fn minimum_genetic_mutation(start_gene: String, end_gene: String, bank: Vec<String>) -> i32 {
    if bank.is_empty() {
        return -1;
    }

    let mut visited = HashMap::new();
    let mut mutations = 0;

    let mut queue = VecDeque::new();
    queue.push_back(start_gene);

    while !queue.is_empty() {
        let len = queue.len();

        for _ in 0..len {
            if let Some(gene) = queue.pop_front() {
                if gene == end_gene {
                    return mutations;
                }

                // Convert the string to bytes of
                // valid utf_8 so we can use indexing
                // to replace characters in place.
                let bytes = gene.as_bytes().to_owned();

                for i in 0..bytes.len() {
                    for new_char in CHARS {
                        let mut new_gene = bytes.clone();
                        new_gene[i] = new_char;
                        let string_gene = String::from_utf8(new_gene.to_vec()).unwrap();

                        if !visited.contains_key(&new_gene) && bank.contains(&string_gene) {
                            queue.push_back(string_gene);
                            visited.insert(new_gene, true);
                        }
                    }
                }
            }
        }

        mutations += 1;
    }

    return -1;
}

#[cfg(test)]
mod tests {
    use super::minimum_genetic_mutation;

    #[test]
    fn test_minimum_genetic_mutation() {
        let result = minimum_genetic_mutation(
            "AACCGGTT".to_string(),
            "AAACGGTA".to_string(),
            vec![
                "AACCGGTA".to_string(),
                "AACCGCTA".to_string(),
                "AAACGGTA".to_string(),
            ],
        );

        assert_eq!(result, 2);

        let result = minimum_genetic_mutation(
            "AACCGGTT".to_string(),
            "AACCGGTA".to_string(),
            vec!["AACCGGTA".to_string()],
        );

        assert_eq!(result, 1);

        let result = minimum_genetic_mutation(
            "AACCTTGG".to_string(),
            "AATTCCGG".to_string(),
            vec![
                "AATTCCGG".to_string(),
                "AACCTGGG".to_string(),
                "AACCCCGG".to_string(),
                "AACCTACC".to_string(),
            ],
        );

        assert_eq!(result, -1);
    }
}
