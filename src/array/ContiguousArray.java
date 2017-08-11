package array;

import java.util.Arrays;

/**
 * Date 07/08/17
 *
 * @author Ankit Jain
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        int[] map = new int[2 * n + 1];
        int sum = 0;
        Arrays.fill(map, -1);
        for (int i = 0; i < n; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (sum == 0) maxLen = Math.max(maxLen, i + 1);
            else {
                if (map[sum + n] != -1) {
                    maxLen = Math.max(maxLen, i - map[sum + n]);
                } else {
                    map[sum + n] = i;
                }
            }
        }
        return maxLen;
    }
}
