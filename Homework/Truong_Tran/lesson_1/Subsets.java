package hackerank;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        int size = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        int n = (int) Math.pow(2, size);
        for (int i= 0; i < n; i++) {
            List<Integer> subList = new ArrayList<>();
            String s = toBinary(i, size);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    subList.add(nums[j]);
                }
            }
            result.add(subList);
        }
        return result;
    }

    // return binary string with specific length
    public static String toBinary(int x, int len) {
        if (len > 0) {
            return String.format("%" + len + "s",
                    Integer.toBinaryString(x)).replaceAll(" ", "0");
        }
        return null;
    }

    public static void main(String[] args) {
        int[] a = new int [] {1, 2, 3};
        System.out.println(subsets(a));
    }
}
