public class MaxArea {

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    boolean[][] visited;
    int width;
    int height;
    int count;

    public int maxAreaOfIsland(int[][] grid) {
        width = grid.length;
        height = grid[0].length;
        visited = new boolean[width][height];
        int max = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                count = 0;
                dfs(grid, i, j);
                max = Math.max(count, max);
            }
        }
        return max;
    }

    public void dfs(int[][] grid, int x, int y) {
        if (grid[x][y] != 0 && !visited[x][y]) {
            count++;
            for (int k = 0; k < 4; k++) {
                int a = x + dx[k];
                int b = y + dy[k];
                if (a >= 0 && a < width && b >= 0 && b < height) {
                    visited[a][b] = true;
                     dfs(grid, a, b);
                }
            }
        }
    }
}
