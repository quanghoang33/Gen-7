package hackerank;

public class HappyNumber {

    public static boolean isHappy(int n) {
        int p1 = sumSquare(n);  // slow pointer
        int p2 = sumSquare(sumSquare(n));  // fast pointer
        while (p1 != p2) {
            p1 = sumSquare(p1);
            p2 = sumSquare(sumSquare(p2));
        }
        if (p1 == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static int sumSquare(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n = n/10;
        }
        return sum;
    }
}
