package number_of_1_bits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SolutionTest {

    @Test
    void hammingWeight() {
        int n = 00000001001101;
        int actual = Solution.hammingWeight(n);

        assertEquals(4, actual);
    }
}