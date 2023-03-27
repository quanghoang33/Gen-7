use std::collections::HashMap;

/**
* s = "ADOBECODEBANC", t = "ABC"

valid_s_1 = "ADOBECODEBANC"
valid_s_2 = "BANC"
result min (s_1.len(), s_2.len()) => s_2 = "BANC"

Brute force

          i
ADOBECODEBANC ABC
             j
valid_s_1 = "ADOBEC", i = 0, j = 5,
valid_s_2 = "DOBECODEBA", i = 1, j = 11,
valid_s_3 = "OBECODEBA", i = 2, j = 11,
valid_s_4 = "BECODEBA", i = 3, j = 11,
valid_s_5 = "ECODEBA", i = 4, j = 11,
valid_s_6 = "CODEBA", i = 5, j = 11,
valid_s_7 = "ODEBANC", i = 6, j = 12,
valid_s_8 = "DEBANC", i = 7, j = 12,
valid_s_9 = "EBANC", i = 8, j = 12,
valid_s_10 = "BANC", i = 9, j = 12,


valid string:
s = "bbaa", t = "aba"
hash[a] = 2
hash[b] = 1

bba => invalid
hash[a] = 1
hash[b] = -1

baa => valid
hash[a] = 0
hash[b] = 0
*/
fn min_window(s: String, t: String) -> String {
    let mut result = "".to_string();

    if s.len() < t.len() {
        return result;
    }

    if s == t {
        return s;
    }

    let (mut slow, mut fast) = (0, 0);
    while fast < s.len() {
        let sub_string = &s[slow..=fast];

        if sub_string.len() < t.len() {
            fast += 1;
            continue;
        }

        // Buid array-based hashtable for checking substring.
        let mut hash = HashMap::new();
        for char in t.chars() {
            let count = hash.entry(char).or_insert(0);
            *count += 1;
        }
        let mut valid = true;
        for char in sub_string.chars() {
            if hash.contains_key(&char) {
                let count = hash.get_mut(&char).unwrap();
                *count -= 1;
            }
        }
        for (_, count) in &hash {
            if *count > 0 {
                valid = false;
                break;
            }
        }

        if valid {
            if result == "" || result.len() > sub_string.len() {
                result = sub_string.to_string();
            }
            slow += 1;
        } else {
            fast += 1;
        }
    }

    return result;
}

#[cfg(test)]
mod tests {
    use std::ascii::AsciiExt;

    use super::min_window;

    #[test]
    fn test_min_window() {
        assert_eq!(
            "BANC".to_string(),
            min_window("ADOBECODEBANC".to_string(), "ABC".to_string())
        );

        assert_eq!("a".to_string(), min_window("a".to_string(), "a".to_string()));

        assert_eq!("".to_string(), min_window("a".to_string(), "aa".to_string()));
    }
}
