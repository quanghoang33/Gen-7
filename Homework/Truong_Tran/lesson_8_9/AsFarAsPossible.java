package lesson7;

import java.util.ArrayDeque;
import java.util.Queue;

public class AsFarAsPossible {

    public int maxDistance(int[][] grid) {
        Queue<Cell> queue = new ArrayDeque<>();
        int width = grid.length;
        int height = grid[0].length;
        boolean[][] visited = new boolean[width][height];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                visited[i][j] = false;
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new Cell(i, j, 0));
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Cell cell = queue.poll();
                ans = Math.max(ans, cell.distance);
                for (int j = 0; j < 4; j++) {
                    int x = cell.x + dx[j];
                    int y = cell.y + dy[j];
                    if ((x >= 0 && x < width) && (y >= 0 && y < height) && !visited[x][y] && grid[x][y] == 0) {
                        visited[x][y] = true;
                        queue.add(new Cell(x, y, cell.distance + 1));
                    }
                }

            }
        }
        return ans == 0 ? -1 : ans;
    }


    public class Cell {
        int x, y;

        public Cell(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        int distance;
    }

    public static void main(String[] args) {
        AsFarAsPossible test = new AsFarAsPossible();
        int[][] a = {{0, 0, 1, 1, 1}, {0, 1, 1, 0, 0}, {0, 0, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 1}};
        System.out.println(test.maxDistance(a));
    }

}
