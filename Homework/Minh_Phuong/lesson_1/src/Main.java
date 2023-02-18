import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        int[] num3 = {1, 2, 3};
        System.out.println("singleNumberXOR:" + singleNumberXOR(nums));
        System.out.println("singleNumber:" + singleNumber(nums));
        int n = 11;
        System.out.println("hammingWeight:" + hammingWeight(n));
        int[] nums2 = {-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums2);
        int param_1 = obj.sumRange(0, 2);
        System.out.println("sumRange:" + param_1);
        int[][] multi = new int[][]{
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(multi);
        System.out.println("sumRegion:" + numMatrix.sumRegion(2, 1, 4, 3)); // return 8 (i.e sum of the red rectangle)
        System.out.println("sortArrayByParity:");
        printArray(sortArrayByParity(nums));
        ListNode second = new ListNode(2, null);
        ListNode head = new ListNode(1, second);
        ListNode newHead = reverseList(head);
        System.out.println("reverse:" + newHead.val + " " + newHead.next.val);
        System.out.println("linkedListHasCycle:" + hasCycle(head));
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        // Arrays.sort(nums);
        System.out.println(printPowerSet(num3, num3.length, list));

    }

    public static void printArray(int[] nums) {
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
    }

    //https://leetcode.com/problems/single-number/
    public static int singleNumberXOR(int[] nums) {
        int ans = 0; //xOR with 0
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
            i++;
        }
        return nums[nums.length - 1];
    }

    //https://leetcode.com/problems/number-of-1-bits/
    public static int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            result += (n & 1);
            n = n >>> 1;
        }
        return result;
    }

    //https://leetcode.com/problems/sort-array-by-parity
    public static int[] sortArrayByParity(int[] nums) {
        for (int i = 0, j = 0; j < nums.length; j++)
            if (nums[j] % 2 == 0) {
                int temp = nums[i];
                nums[i++] = nums[j];
                nums[j] = temp;
            }
        return nums;
    }

    //https://leetcode.com/problems/reverse-linked-list/
    public static ListNode reverseList(ListNode head) {
        ListNode prevNode = null;
        ListNode currentNode = head;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }
        return prevNode;
    }

    //https://leetcode.com/problems/linked-list-cycle/
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    //https://leetcode.com/problems/subsets/
    public static List<List<Integer>>  printPowerSet(int[] set, int set_size, List<List<Integer>> list) {
        /*set_size of power set of a set
        with set_size n is (2**n -1)*/
        long pow_set_size = (long) Math.pow(2, set_size);
        int counter, j;

        /*Run from counter 000..0 to
        111..1*/
        for (counter = 0; counter < pow_set_size;
             counter++) {
            List<Integer> integerList = new ArrayList<>();
            for (j = 0; j < set_size; j++) {
                /* Check if jth bit in the
                counter is set If set then
                print jth element from set */
                if ((counter & (1 << j)) > 0) {
                    integerList.add(set[j]);
                }
            }

            if (!list.contains(integerList)
                && !integerList.isEmpty()) {
                list.add(integerList);
            }
        }

        return list;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

//https://leetcode.com/problems/range-sum-query-immutable/
class NumArray {
    int[] nums;

    public NumArray(int[] nums) {
        //create a new array contain sum nums[0] ->[i]
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        this.nums = nums;
    }

    public int sumRange(int left, int right) {
        if (left == 0) {
            return nums[right];
        }
        return nums[right] - nums[left - 1];
    }
}

//https://leetcode.com/problems/range-sum-query-2d-immutable/
class NumMatrix {
    int[][] nums;

    public NumMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }
        // System.out.println("length:"+matrix.length);
        this.nums = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int result = 0;
        for (int i = row1; i < row2 + 1; i++) {
            if (col1 == 0) result += nums[i][col2];
            else {
                result += nums[i][col2] - nums[i][col1 - 1];
            }
        }
        return result;
    }

}
