package bit;

/**
 * Date 01/08/17
 *
 * @author Ankit Jain
 */
public class TotalHammingDistance {
    //Fastest
    public int totalHammingDistance(int[] nums) {
        int total = 0;
        int n = nums.length;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++)
                count += ((nums[j] >> i) & 1);
            total += count * (n - count);
        }
        return total;
    }

    //Faster
    public int totalHammingDistance1(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length - 1; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] != nums[j])
                    total += Integer.bitCount(nums[i] ^ nums[j]);
        return total;
    }

}
