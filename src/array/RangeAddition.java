package array;

import java.util.Arrays;

/**
 * Date 25/06/17
 * @author Ankit Jain
 * https://leetcode.com/problems/range-addition/
 */
public class RangeAddition {
    public static void main(String[] args) {
        RangeAddition rangeAddition = new RangeAddition();
        int[][] updates = {
                {1, 3, 2},
                {2, 4, 3},
                {0, 2, -2}
        };
        System.out.println(Arrays.toString(rangeAddition.getModifiedArray(5, updates)));
    }

    //Inefficient n*k solution
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update :
                updates) {
            for (int i = update[0]; i <= update[1]; i++) {
                res[i] += update[2];
            }
        }
        return res;
    }

}
