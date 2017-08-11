package bit;

/**
 * Date 04/08/17
 *
 * @author Ankit Jain
 */
public class SingleNumberII {
    public static void main(String[] args) {
        System.out.println(new SingleNumberII().singleNumber2(new int[]{1, 2, 2, 2, 3, 3, 3}));
    }

    public int singleNumber1(int A[], int n) {
        int ones = 0, twos = 0;
        int common_bit_mask;
        for (int i = 0; i < n; i++) {
                 /* The expression "one & arr[i]" gives the bits that are
               there in both 'ones' and new element from arr[].  We
               add these bits to 'twos' using bitwise OR

               Value of 'twos' will be set as 0, 3, 3 and 1 after 1st,
               2nd, 3rd and 4th iterations respectively */

            twos = twos | (ones & A[i]);
                /* XOR the new bits with previous 'ones' to get all bits
               appearing odd number of times

               Value of 'ones' will be set as 3, 0, 2 and 3 after 1st,
               2nd, 3rd and 4th iterations respectively */
            ones = ones ^ A[i];
                 /* The common bits are those bits which appear third time
               So these bits should not be there in both 'ones' and 'twos'.
               common_bit_mask contains all these bits as 0, so that the bits can
               be removed from 'ones' and 'twos'

               Value of 'common_bit_mask' will be set as 00, 00, 01 and 10
               after 1st, 2nd, 3rd and 4th iterations respectively */
            common_bit_mask = ~(ones & twos);
                /* Remove common bits (the bits that appear third time) from 'ones'

               Value of 'ones' will be set as 3, 0, 0 and 2 after 1st,
               2nd, 3rd and 4th iterations respectively */
            ones &= common_bit_mask;
                /* Remove common bits (the bits that appear third time) from 'twos'

               Value of 'twos' will be set as 0, 3, 1 and 0 after 1st,
               2nd, 3rd and 4th itearations respectively */
            twos &= common_bit_mask;
        }
        return ones;
    }

    public int singleNumber2(int[] A) {
        int ones = 0, twos = 0;
        for (int i = 0; i < A.length; i++) {
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;
    }
    public int singleNumber3(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((nums[j] >> i) & 1) == 1) {
                    sum++;
                    sum %= 3;
                }
            }
            if (sum != 0) {
                ans |= sum << i;
            }
        }
        return ans;
    }
}
