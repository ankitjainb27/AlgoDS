package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created on 17/08/17 at 12:28 PM
 *
 * @author Ankit Jain
 */
public class MinimumNumberArrowsBurstBalloons {
    public static void main(String[] args) {
        System.out.println(new MinimumNumberArrowsBurstBalloons().findMinArrowShots(new int[][]{{0, 9}, {1, 8}, {7, 8}, {1, 6}, {9, 16}, {7, 13}, {7, 10}, {6, 11}, {6, 9}, {9, 13}}));
        System.out.println(new MinimumNumberArrowsBurstBalloons().findMinArrowShots(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}));
    }

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for(int[] val:points) System.out.println(Arrays.toString(val));
        int count = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                count++;
                end = points[i][1];
            }
        }
        return count;
    }

    public int findMinArrowShots2(int[][] points) {
        if(points == null || points.length < 1) return 0;
        Arrays.sort(points, (a, b)->(a[0]-b[0]));
        int result = 1;
        int end = points[0][1];
        for(int i = 1; i < points.length; i ++) {
            if(points[i][0] > end) {
                result ++;
                end = points[i][1];
            } else {
                end = Math.min(end, points[i][1]);
            }
        }
        return result;
    }

    public int findMinArrowShots1(int[][] points) {
        if (points.length == 0) return 0;
        int[] start = new int[points.length];
        int[] end = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            start[i] = points[i][0];
            end[i] = points[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        System.out.println(Arrays.toString(start));
        System.out.println(Arrays.toString(end));
        int count = 1;
        int endV = end[0];
        for (int i = 1; i < start.length; i++) {
            if (endV < start[i]) {
                endV = end[i];
                count++;
            }
        }
        return count;
    }
}
