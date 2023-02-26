class Solution {
public:
    int happy(int n) {
        int ans = 0;
        while (n) {
            ans+=(n%10)*(n%10);
            n/=10;
        }
        return ans;
    }
    bool isHappy(int n) {
        int slow = n, fast = n;
        while (fast!=1) {
            slow = happy(slow);
            fast = happy(fast);
            fast = happy(fast);
            if (slow==fast && slow!=1) return false;
        }
        return true;
    }
};