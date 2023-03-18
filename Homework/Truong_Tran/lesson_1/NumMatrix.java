package hackerank;

class NumMatrix {

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        int m = matrix.length + 1;
        int n = matrix[0].length + 1;
        prefix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i][j] = 0;
            }
        }
        prefix[1][1] = matrix[0][0];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                prefix[i][j] = prefix[i][j-1] + prefix[i -1][j]  - prefix[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }

    int[][] prefix;
    int[][] matrix;


    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1 ++;
        col1 ++;
        row2 ++;
        col2 ++;
        return prefix[row2][col2] - prefix[row1-1][col2] - prefix[row2][col1 - 1] + prefix[row1-1][col1-1];
    }
}