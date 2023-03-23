import java.util.*;

public class ShortestPathWithObstaclesElimination {

    int[] dx = {0, 1, -1, 0};
    int[] dy = {-1, 0, 0, 1};
    int[][] visited; // store max available obstacles at one point (x,y) when this point is visited

    public int shortestPath(int[][] grid, int k) {
        int width = grid[0].length;
        int height = grid.length;
        visited = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                visited[i][j] = -1;  // init visited
            }
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, k));
        int res = 0;
        int queueSize;
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Node node = queue.poll();
                if (node.x == (height - 1) && node.y == (width - 1)) {
                    return res;
                }
                for (int j = 0; j < 4; j++) {
                    int x = node.x + dx[j];
                    int y = node.y + dy[j];
                    if (x >= 0 && x < height && y >= 0 && y < width) {
                        if (grid[x][y] == 1) {
                            if (node.obs > 0 && visited[x][y] < node.obs - 1) {
                                visited[x][y] = node.obs - 1;
                                queue.add(new Node(x, y, node.obs - 1));
                            }
                        } else {
                            if (visited[x][y] < node.obs) {
                                visited[x][y] = node.obs;
                                queue.add(new Node(x, y, node.obs));
                            }
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }

    public class Node {
        int x, y, obs;

        // obs: available obstacles

        public Node(int x, int y, int obs) {
            this.x = x;
            this.y = y;
            this.obs = obs;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,1,1,0,0,1,1},{0,0,0,1,1,0,0,1,1,0,1,0,0,0,1},{1,1,0,0,0,0,0,1,0,1,0,0,1,0,0},{1,0,1,1,1,1,0,0,1,1,0,1,0,0,1},{1,0,0,0,1,1,0,1,1,0,0,1,1,1,1},{0,0,0,1,1,1,0,1,1,0,0,1,1,1,1},{0,0,0,1,0,1,0,0,0,0,1,1,0,1,1},{1,0,0,1,1,1,1,1,1,0,0,0,1,1,0},{0,0,1,0,0,1,1,1,1,1,0,1,0,0,0},{0,0,0,1,1,0,0,1,1,1,1,1,1,0,0},{0,0,0,0,1,1,1,0,0,1,1,1,0,1,0}};
        ShortestPathWithObstaclesElimination test = new ShortestPathWithObstaclesElimination();
        int res = test.shortestPath(grid, 27);
        System.out.println(res);
    }
}
