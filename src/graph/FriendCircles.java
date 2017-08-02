package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Date 31/07/17
 *
 * @author Ankit Jain
 */
public class FriendCircles {
    public static void main(String[] args) {
        FriendCircles friendCircles = new FriendCircles();
        int[][] M = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};
        for (int j = 0; j < M[0].length; j++) {
            System.out.print((j) + " -- ");
        }
        System.out.println();
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.print(M[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println(friendCircles.findCircleNum2(M));
    }

    //DFS
    public int findCircleNum1(int[][] M) {
        int count = 0;
        int n = M.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, boolean[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, visited, j);
            }
        }
    }

    //BFS
    public int findCircleNum2(int[][] M) {
        int count = 0;
        int n = M.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                bfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private void bfs(int[][] M, boolean[] visited, int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        while (!queue.isEmpty()) {
            Integer val = queue.poll();
            for (int j = 0; j < M.length; j++) {
                if (M[val][j] == 1 && !visited[j]) {
                    visited[j] = true;
                    queue.add(j);
                }
            }
        }
    }

    //Union-Find
    public int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    public void Union(Subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        if (xRoot == yRoot) {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        } else if (xRoot < yRoot) {
            subsets[xRoot].parent = yRoot;
        } else {
            subsets[yRoot].parent = xRoot;
        }
    }

    public int findCircleNum(int[][] M) {
        int n = M.length;
        Subset[] subset = new Subset[n];
        for (int i = 0; i < n; i++) {
            subset[i] = new Subset(0, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1)
                    Union(subset, i, j);
            }
        }
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            int val = find(subset, i);
            if (!visited[val]) {
                count++;
                visited[val] = true;
            }
        }
        return count;
    }

    class Subset {
        int rank;
        int parent;

        public Subset(int rank, int parent) {
            this.rank = rank;
            this.parent = parent;
        }
    }
}
