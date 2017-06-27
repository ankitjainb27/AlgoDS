package matrix;

/**
 * Date 26/06/17
 *
 * @author Ankit Jain
 */
public class SquirrelSimulation {
    public static void main(String[] args) {
        SquirrelSimulation squirrelSimulation = new SquirrelSimulation();
        System.out.println(squirrelSimulation.minDistance(5,
                7,
                new int[]{7, 2},
                new int[]{7, 1},
                new int[][]{{3, 0}, {7, 7}, {8, 8}, {0, 8}, {2, 4}, {4, 5}, {3, 5}, {4, 2}, {1, 8}}));
        System.out.println(squirrelSimulation.minDistance(5,
                7,
                new int[]{2, 2},
                new int[]{4, 4},
                new int[][]{{3, 0}, {2, 5}}));
    }

    //My Solution
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int diff = Integer.MAX_VALUE;
        int dist = 0;
        for (int i = 0; i < nuts.length; i++) {
            int val = distance(nuts[i], squirrel);
            int val1 = distance(nuts[i], tree);
            if (val - val1 <= diff) {
                diff = val-val1;
            }
            dist += 2 * val1;
        }
        dist += diff;
        return dist;
    }

    private int distance(int[] nut, int[] squirrel) {
        return Math.abs(nut[0] - squirrel[0]) + Math.abs(nut[1] - squirrel[1]);
    }
}
