package bit;

/**
 * Date 04/08/17
 *
 * @author Ankit Jain
 */
public class FindDuplicateNumber {
    public static void main(String[] args) {
        System.out.println(new FindDuplicateNumber().findDuplicate(new int[]{13, 46, 8, 11, 20, 17, 40}));
    }

    public int findDuplicate(int[] nums) {
        long num = 0;
        for (int i = 0; i < nums.length; i++) {
            long val = 1 << (nums[i] - 1);
            System.out.println(val + " - " + Long.toBinaryString(val).length());
            if ((num & val) == val) return nums[i];
            else num |= val;
        }
        return 0;
    }
}
