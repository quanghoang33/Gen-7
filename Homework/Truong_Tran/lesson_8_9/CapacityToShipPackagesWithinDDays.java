package lesson7;

public class CapacityToShipPackagesWithinDDays {

    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = weights.length * 500;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (satisfy(weights, mid, days) && !satisfy(weights, mid - 1, days)) {
                return mid;
            }
            if (satisfy(weights, mid, days)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean satisfy(int[] weights, int capacity, int days) {
        int sum = 0;
        int count = 1;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > capacity) {
                return false;
            }
            if (count > days) {
                return false;
            }
            if (sum + weights[i] > capacity) {
                count++;
                sum = weights[i];
            }
            else if (sum + weights[i] == capacity && i != weights.length - 1) {
                count++;
                sum = 0;
            } else {
                sum += weights[i];
            }
        }
        return count <= days;
    }
}
