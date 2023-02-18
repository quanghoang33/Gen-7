package single_number;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @org.junit.jupiter.api.Test
    void singleNumber() {
        int[] nums = {4, 1, 2, 1, 2};
        int actual = Solution.singleNumber(nums);
        assertEquals(4, actual);
    }
}