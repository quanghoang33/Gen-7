use std::collections::HashMap;

/**
Hint:
s = "ADOBECODEBANC", t = "ABC"
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
    let s_bytes = s.as_bytes();
    let t_bytes = t.as_bytes();
    let mut result: Option<&[u8]> = None;

    // Edge cases
    if s.len() < t.len() {
        return "".to_string();
    }
    if s == t {
        return s;
    }

    // use to count the character's appearance
    // in the current window sliding.
    let mut window_count = HashMap::new();

    // Build a hash map that count the number of appearance
    // of a character in t string.
    let mut t_count = HashMap::new();
    for &byte in t_bytes {
        let count = t_count.entry(byte).or_insert(0);
        *count += 1;
    }
    // Rules == number of unique characters of t string.
    let mut rules = t_count.len();

    let (mut slow, mut fast) = (0, 0);
    while fast < s.len() {
        let byte = s_bytes[fast];

        let count = window_count.entry(byte).or_insert(0);
        *count += 1;

        // If current window[char] == t_count[char] we say that
        // we passed a rule.
        if t_count.contains_key(&byte) && *count == t_count[&byte] {
            rules -= 1;
        }

        // If we passed all rules it means we 
        // found a valid substring. So we need
        // do one more thing is finding smallest
        // valid substring in the valid substring.
        while rules == 0 {
            let slow_byte = &s_bytes[slow];
            let sub_string = &s_bytes[slow..=fast];

            if result.is_none() || result.as_ref().unwrap().len() > sub_string.len() {
                result = Some(sub_string);
            }

            if let Some(count) = window_count.get_mut(slow_byte) {
                *count -= 1;
                if t_count.contains_key(slow_byte) && *count < t_count[slow_byte] {
                    rules += 1;
                }
            }
            slow += 1;
        }

        fast += 1;
    }

    return result.map_or("".to_string(), |bytes| String::from_utf8(bytes.to_vec()).unwrap());
}

#[cfg(test)]
mod tests {
    use super::min_window;

    #[test]
    fn test_min_window() {
        assert_eq!(
            "BANC".to_string(),
            min_window("ADOBECODEBANC".to_string(), "ABC".to_string())
        );

        assert_eq!("a".to_string(), min_window("a".to_string(), "a".to_string()));

        assert_eq!("".to_string(), min_window("a".to_string(), "aa".to_string()));

        assert_eq!("ba".to_string(), min_window("bba".to_string(), "ab".to_string()));

        assert_eq!(
            "baa".to_string(),
            min_window("bbaa".to_string(), "aba".to_string())
        );
    }
}
