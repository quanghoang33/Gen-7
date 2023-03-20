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
        int depth = 0;
        while (!queue.isEmpty()) {
            List<Node> next = new ArrayList<>();
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.x == (height - 1) && node.y == (width - 1)) {
                    return depth;
                }
                for (int i = 0; i < 4; i++) {
                    int x = node.x + dx[i];
                    int y = node.y + dy[i];
                    if (x >= 0 && x < height && y >= 0 && y < width) {
                        if (grid[x][y] == 1) {
                            if (node.obs > 0 && visited[x][y] < node.obs - 1) {
                                visited[x][y] = node.obs - 1;
                                next.add(new Node(x, y, node.obs - 1));
                            }
                        } else {
                            if (visited[x][y] < node.obs) {
                                visited[x][y] = node.obs;
                                next.add(new Node(x, y, node.obs));
                            }
                        }
                    }
                }
            }
            queue.addAll(next);
            depth++;
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
