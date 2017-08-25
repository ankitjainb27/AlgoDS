package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created on 25/08/17 at 12:06 PM
 *
 * @author Ankit Jain
 */
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++)
            map.put(nums1[i], i);
        Stack<Integer> stack = new Stack<>();
        for (int i : nums2) {
            while (!stack.empty() && stack.peek() < i) {
                int peek = stack.pop();
                if (map.containsKey(peek))
                    res[map.get(peek)] = i;
            }
            stack.push(i);
        }
        return res;
    }
}
