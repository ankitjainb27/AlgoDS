package greedy;

/**
 * Date 25/07/17
 *
 * @author Ankit Jain
 */
public class DijkstraShotestPath {
    public static void main(String[] args) {
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        DijkstraShotestPath t = new DijkstraShotestPath();
        t.dijkstra(graph, 0);
    }

    private void dijkstra(int[][] graph, int i) {
        int[] dist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        for (int j = 0; j < dist.length; j++) {
            dist[j] = Integer.MAX_VALUE;
        }
        dist[i] = 0;
        int e = 0;
        while (e < graph.length - 1) {
            int index = findVertex(graph, dist, visited, i);
            visited[index] = true;
            for (int j = 0; j < graph.length; j++) {
                if (!visited[j] && graph[index][j] != 0 && dist[index] != Integer.MAX_VALUE && dist[index] + graph[index][j] < dist[j])
                    dist[j] = dist[index] + graph[index][j];
            }
            e++;
        }
        for (int j = 0; j < graph.length; j++) {
            System.out.println(dist[j]);
        }
    }

    private int findVertex(int[][] graph, int[] dist, boolean[] visited, int i) {
        int val = 0;
        int mDist = Integer.MAX_VALUE;
        for (int j = 0; j < graph.length; j++) {
            if (!visited[j] && dist[j] <= mDist) {
                mDist = dist[j];
                val = j;
            }
        }
        return val;
    }
}
