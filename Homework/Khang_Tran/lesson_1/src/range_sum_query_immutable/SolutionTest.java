package range_sum_query_immutable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution s;

    @BeforeEach
    void setUp() {
        int[] arr = new int[]{-2, 0, 3, -5, 2, -1};
        s = new Solution(arr);
    }

    @Test
    void sumRange() {
        int actual = s.sumRange(2, 5);

        assertEquals(-1, actual);
    }
}