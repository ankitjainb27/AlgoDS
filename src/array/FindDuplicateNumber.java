package array;

/**
 * Date 10/08/17
 *
 * @author Ankit Jain
 */
public class FindDuplicateNumber {
    public static void main(String[] args) {
        FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
        System.out.println(findDuplicateNumber.findDuplicate1(new int[]{4, 3, 4, 2, 1}));
    }

    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        do {
            slow = nums[slow];
            fast = nums[fast];
        } while (slow != fast);
        return slow;
    }

    public int findDuplicate1(int[] nums) {
        int low = 1;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            System.out.println("low: " + low + "  mid: " + mid + "  high:  " + high);
            int count = 0;
            for (int i : nums) {
                if (i <= mid)
                    count++;
            }
            if (count <= mid)
                low = mid + 1;
            else high = mid;
        }
        return low;
    }
}
