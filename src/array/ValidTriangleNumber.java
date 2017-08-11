package array;

import java.util.Arrays;

/**
 * Date 05/08/17
 *
 * @author Ankit Jain
 */
public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 2; i < nums.length; i++) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    count += r - l;
                    r--;
                } else l++;
            }
        }
        return count;
    }


    public int triangleNumber1(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                k = binarySearch(k, nums.length - 1, nums[i] + nums[j], nums);
                //while(k<nums.length && >nums[k]) k++;
                count += k - j - 1;
            }
        }
        return count;
    }

    private int binarySearch(int l, int r, int sum, int[] nums) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= sum && nums[mid - 1] < sum) return mid;
            else if (nums[mid] < sum) l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }

    public int triangleNumber2(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                k = binarySearch2(nums, k, nums.length - 1, nums[i] + nums[j]);
                count += k - j - 1;
            }
        }
        return count;
    }

    int binarySearch2(int nums[], int l, int r, int x) {
        while (r >= l && r < nums.length) {
            int mid = (l + r) / 2;
            if (nums[mid] >= x)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return l;
    }

    public int triangleNumber3(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k])
                    k++;
                count += k - j - 1;
            }
        }
        return count;
    }

}
