package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created on 15/08/17 at 3:22 PM
 *
 * @author Ankit Jain
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i] + "";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String i, String j) {
                String s1 = i + j;
                String s2 = j + i;
                return s1.compareTo(s2);
            }
        });
        if (strs[strs.length - 1].charAt(0) == '0') return "0";
        String res = new String();
        for (int i = 0; i < strs.length; i++) {
            res = strs[i] + res;
        }
        return res;
    }

    public String largestNumber1(int[] nums) {
        Integer[] arr = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ("" + o2 + o1).compareTo("" + o1 + o2);
            }
        });

        if (arr[0] == 0) return "0";

        StringBuilder sb = new StringBuilder();
        for (Integer i : arr) {
            sb.append(String.valueOf(i));
        }
        return sb.toString();
    }
}
