class Solution {
public:
    bool isHappy(int n) {
        map<int, bool> m;
        m[n]=1;
        while (n!=1) {
            int tmp = 0;
            while (n) {
                tmp+=(n%10)*(n%10);
                n/=10;
            }
            if (m[tmp]) return false;
            m[tmp]=1;
            n = tmp;
        }
        return true;
    }
};