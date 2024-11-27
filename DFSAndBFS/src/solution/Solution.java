package solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Solution {

  public static void main(String[] args) {
    int[][] edges = { { 0, 1 }, { 1, 6 }, { 0, 2 }, { 7, 2 }, { 8, 2 }, { 3, 4 }, { 3, 5 },
        { 4, 5 } };
    Solution.validPath(9, edges, 0, 5);
    char[][] grid = { { '1', '1', '1', '1', '1', '0', '1', '1', '1', '1' },
        { '1', '0', '1', '0', '1', '1', '1', '1', '1', '1' },
        { '0', '1', '1', '1', '0', '1', '1', '1', '1', '1' },
        { '1', '1', '0', '1', '1', '0', '0', '0', '0', '1' },
        { '1', '0', '1', '0', '1', '0', '0', '1', '0', '1' },
        { '1', '0', '0', '1', '1', '1', '0', '1', '0', '0' },
        { '0', '0', '1', '0', '0', '1', '1', '1', '1', '0' },
        { '1', '0', '1', '1', '1', '0', '0', '1', '1', '1' },
        { '1', '1', '1', '1', '1', '1', '1', '1', '0', '1' },
        { '1', '0', '1', '1', '1', '1', '1', '1', '1', '0' } };
    numIslands(grid);
  }

  public static int numIslands(char[][] grid) {
    int m = grid.length, n = grid[0].length;
    int res = 0;
    boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (!visited[i][j] && grid[i][j] == '1') {
          DFS(grid, visited, i, j, m, n);
          res++;
        }
      }
    }
    return res;
  }

  private static void DFS(char[][] grid, boolean[][] visited, int i, int j, int m, int n) {
    if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0' || visited[i][j])
      return;
    
    
  }

  public static int numIslands1(char[][] grid) {
    int m = grid.length, n = grid[0].length;
    boolean[][] visited = new boolean[m][n]; // record position of visited '1's
    Queue<Position> queue = new ArrayDeque<>(); // record x,y of 1
    int res = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

        if (!visited[i][j] && grid[i][j] == '1') {
          visited[i][j] = true;
          queue.offer(new Position(i, j));
          while (!queue.isEmpty()) {
            Position pos = queue.poll();
            int y = pos.getY();
            int x = pos.getX();
            // search four directions
            while (y + 1 < n && grid[x][y + 1] == '1' && !visited[x][y + 1]) {
              visited[x][y + 1] = true;
              queue.offer(new Position(x, y + 1));
              y++;
            }
            y = pos.getY();
            while (y - 1 >= 0 && grid[x][y - 1] == '1' && !visited[x][y - 1]) {
              visited[x][y - 1] = true;
              queue.offer(new Position(x, y - 1));
              y--;
            }
            y = pos.getY();
            while (x + 1 < m && grid[x + 1][y] == '1' && !visited[x + 1][y]) {
              visited[x + 1][y] = true;
              queue.offer(new Position(x + 1, y));
              x++;
            }
            while (x - 1 >= 0 && grid[x - 1][y] == '1' && !visited[x - 1][y]) {
              visited[x - 1][y] = true;
              queue.offer(new Position(x - 1, y));
              x--;
            }
          } // after this while, we visited all reachable position starting from i,j
          res++;
        }
      }
    }
    return res;
  }

  public static boolean validPath(int n, int[][] edges, int source, int destination) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
      graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    int[] distances = new int[n];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[source] = 0;

    PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    priorityQueue.offer(new int[] { 0, source });

    while (!priorityQueue.isEmpty()) {
      int[] current = priorityQueue.poll();
      int currentDistance = current[0];
      int currentNode = current[1];

      if (currentNode == destination) {
        return true;
      }

      if (currentDistance > distances[currentNode]) {
        continue;
      }

      for (int neighbor : graph.get(currentNode)) {
        int distance = currentDistance + 1;
        if (distance < distances[neighbor]) {
          distances[neighbor] = distance;
          priorityQueue.offer(new int[] { distance, neighbor });
        }
      }
    }

    return false;
  }

  /**
   * My first version, exceed time limit if input is too large.
   * 
   * @param n
   * @param edges
   * @param source
   * @param destination
   * @return
   */
  public boolean validPath1(int n, int[][] edges, int source, int destination) {
    if (n == 1)
      return true;
    if (!Arrays.stream(edges).flatMapToInt(Arrays::stream).anyMatch(i -> i == destination))
      return false;
    if (!Arrays.stream(edges).flatMapToInt(Arrays::stream).anyMatch(i -> i == source))
      return false;
    if (source == destination)
      return true;
    Queue<int[]> queue = new ArrayDeque<>();
    Queue<Integer> nodes = new ArrayDeque<>();
    // initialize queue and nodes
    nodes.offer(source);
    for (int i = 0; i < edges.length; i++) {
      if (Arrays.stream(edges[i]).anyMatch(j -> j == source)) {
        queue.offer(edges[i]);
        edges[i] = new int[] { -1, -1 };
      }
    }
    while (!queue.isEmpty()) {
      Set<Integer> set = new HashSet<>();
      while (!queue.isEmpty()) {
        int[] element = queue.poll();
        for (int value : element) {
          set.add(value);
        }
      }
      while (!nodes.isEmpty()) {
        set.remove(nodes.poll());
      }
      if (set.contains(destination))
        return true;
      for (int element : set) {
        nodes.offer(element);
      }
      for (int i = 0; i < edges.length; i++) {
        for (int element : nodes) {
          if (Arrays.stream(edges[i]).anyMatch(j -> j == element)) {
            queue.offer(edges[i]);
            edges[i] = new int[] { -1, -1 };
          }
        }
      }
    }
    return false;
  }

}

class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}
