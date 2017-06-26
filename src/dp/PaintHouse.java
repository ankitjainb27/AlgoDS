package dp;

/**
 * Date 23/06/17
 *
 * @author Ankit Jain
 */
public class PaintHouse {

    public static void main(String[] args) {
        PaintHouse paintHouse = new PaintHouse();
        int[][] costs = {{3, 5, 3}, {6, 17, 6}, {7, 13, 18}, {9, 10, 18}};
        System.out.println(paintHouse.minCost(costs));
    }

    public int minCost(int[][] costs) {
        if (costs.length == 0)
            return 0;
        int[] old = costs[0];
        int col = costs[0].length;
        int[] newV = new int[col];
        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < col; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < col; k++)
                    if (j != k)
                        min = Math.min(min, old[k]);
                newV[j] = costs[i][j] + min;
            }
            old = newV;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < col; i++) {
            res = Math.min(res, old[i]);
        }
        return res;
    }

}
