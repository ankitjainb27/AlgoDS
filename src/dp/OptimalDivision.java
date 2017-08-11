package dp;

/**
 * Date 09/08/17
 *
 * @author Ankit Jain
 */
public class OptimalDivision {
    public static void main(String[] args) {
        OptimalDivision optimalDivision = new OptimalDivision();
        System.out.println(optimalDivision.optimalDivision(new int[]{1000, 100, 10, 2}));
    }

    public String optimalDivision(int[] nums) {
        int n = nums.length;
        int[][] dpMin = new int[n][n];
        int[][] dpMax = new int[n][n];
        for (int i = 0; i < n; i++) {
            dpMin[i][i] = nums[i];
            dpMax[i][i] = nums[i];
        }
        for (int i = 0; i < n - 1; i++) {
            dpMin[i][i + 1] = nums[i] / nums[i + 1];
            dpMax[i][i + 1] = nums[i] / nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dpMax[i][j] + " -- ");
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dpMin[i][j] + " -- ");
            }
            System.out.println();
        }
        for (int L = 3; L <= n; L++) {
            for (int i = 0; i < n - L + 1; i++) {
                int j = i + L - 1;
//                dpMin[i][j] = Integer.MAX_VALUE;
//                dpMax[i][j] = Integer.MIN_VALUE;
                for (int k = i; k < j - 1; k++) {
                    dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] / dpMax[i][k + 1]);
                    dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] / dpMin[i][k + 1]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dpMax[i][j] + " -- ");
            }
            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dpMin[i][j] + " -- ");
            }
            System.out.println();
        }
        return "";
    }
}
