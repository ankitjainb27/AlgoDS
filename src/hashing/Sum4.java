package hashing;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Date 01/08/17
 *
 * @author Ankit Jain
 */
public class Sum4 {
    public static void main(String[] args) {
        Sum4 sum4 = new Sum4();
        System.out.println(sum4.fourSumCount(
                new int[]{1, 2},
                new int[]{-2, -1},
                new int[]{-1, 2},
                new int[]{0, 2}
        ));
    }

    //Fastest on Leetcode, as it doesn't use HashMap, but Time Complexity is (n^2)logn
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int N = A.length;
        int[] sum1 = new int[N * N];
        int[] sum2 = new int[N * N];

        int k = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum1[k] = A[i] + B[j];
                sum2[k++] = C[i] + D[j];
            }
        }
        int i = 0;
        int j = N * N - 1;
        int count = 0;
        Arrays.sort(sum1);
        Arrays.sort(sum2);
        while (i < N * N && j >= 0) {
            int sum = sum1[i] + sum2[j];
            if (sum < 0)
                i++;
            else if (sum > 0)
                j--;
            else {
                int a = 1;
                int b = 1;
                i++;
                j--;
                while (i < N * N && sum1[i] == sum1[i - 1]) {
                    i++;
                    a++;
                }
                while (j >= 0 && sum2[j] == sum2[j + 1]) {
                    j--;
                    b++;
                }
                count += a * b;
            }
        }
        return count;
    }

    //Uses HashMap
    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        int N = A.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = C[i] + D[j];
                count += map.getOrDefault(-1 * sum, 0);
            }
        }

        return count;
    }
}
