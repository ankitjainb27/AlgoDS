package greedy;

/**
 * Date 12/08/17
 *
 * @author Ankit Jain
 */
public class WiggleSubsequence {
    public static void main(String[] args) {
        String st = Integer.toBinaryString(128);
        System.out.println(st);
        System.out.println(new String(new char[3]).replace("\0", "0"));
    }
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;
        int sign = nums[1] - nums[0];
        int count = nums[1] != nums[0] ? 1 : 0;
        for (int i = 2; i < n; i++) {
            int val = nums[i] - nums[i - 1];
            if (val * sign <= 0) {
                if (val * sign < 0) {
                    if (count == 0) count++;
                    count++;
                }
                if (val != 0)
                    sign = val;
            }
        }
        return count + 1;
    }
}
