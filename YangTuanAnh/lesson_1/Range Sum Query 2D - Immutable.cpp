class NumMatrix {
public:
    int a[205][205];
    NumMatrix(vector<vector<int>>& matrix) {
        int n = matrix.size(), m = matrix[0].size();
        memset(a, 0, sizeof(a));
        for (int i=1; i<=n; i++)
            for (int j=1; j<=m; j++)
                a[i][j] = a[i-1][j]+a[i][j-1]-a[i-1][j-1]+matrix[i-1][j-1];

        // for (int i=0; i<=n; i++) {
        //     for (int j=0; j<=m; j++)
        //         cout << a[i][j] << ' ';
        //     cout << '\n';
        // }
    }
    
    int sumRegion(int row1, int col1, int row2, int col2) {
        return a[row2+1][col2+1]-a[row1][col2+1]-a[row2+1][col1]+a[row1][col1];
    }
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix* obj = new NumMatrix(matrix);
 * int param_1 = obj->sumRegion(row1,col1,row2,col2);
 */