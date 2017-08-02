package stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Date 31/07/17
 *
 * @author Ankit Jain
 */
public class NextGreaterElementII {
    public static void main(String[] args) {
        NextGreaterElementII nextGreaterElementII = new NextGreaterElementII();
        System.out.println(Arrays.toString(nextGreaterElementII.nextGreaterElements4(new int[]{3, 8, 4, 1, 2})));
    }

    /**
     * The idea is to use the array to be returned to store information rather than an extra Stack.
     * We use the array called result to store index of next larger element and replace with actual values before returning it.
     * In first iteration, we move from right to left and find next greater element assuming array to be non-cyclical.
     * Then we do another iteration from right to left. This time, we assume array is cyclical and find next larger element for the elements that do not have next larger element if array is not cyclical.
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements4(int[] nums) {

        //Case when array is empty
        if (nums.length == 0) return nums;

        int[] result = new int[nums.length];

        //Assuming array to be non-cyclical, last element does not have next larger element
        result[nums.length - 1] = -1;

        //Case when only one element is there in array
        if (result.length == 1) return result;

        for (int i = nums.length - 2; i >= 0; i--) {
            //Starting from next element
            int k = i + 1;

            //Keep tracking next larger element until you find it or you find element with no next larger element
            while (nums[i] >= nums[k] && result[k] != -1) {
                k = result[k];
            }

            //If next larger element is found, store index
            if (nums[k] > nums[i]) result[i] = k;
                //If not found, it doesn't have next larger element
            else result[i] = -1;
        }

        //Second iteration assuming cyclical array, last element can also have next larger element
        for (int i = nums.length - 1; i >= 0; i--) {

            //If next larger element assuming non-cyclical array already exists, continue
            if (result[i] != -1) continue;

            //Case when i + 1 is greater than length of array: when on last element
            int k = (i + 1) % nums.length;

            //Keep tracking next larger element until you find it or you find element with no next larger element
            while (nums[i] >= nums[k] && result[k] != -1) {
                k = result[k];
            }

            //If next larger element is found, store it's index
            if (nums[k] > nums[i]) result[i] = k;
                //If not found, it doesn't have next larger element
            else result[i] = -1;
        }

        //Replace indices with actual values
        for (int i = 0; i < nums.length; i++) {
            if (result[i] != -1) result[i] = nums[result[i]];
        }

        return result;
    }

    public int[] nextGreaterElements3(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n) stack.push(i);
        }
        return next;
    }

    //Best Method
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }

    //My Method, more optimized
    public int[] nextGreaterElements1(int[] nums) {
        int[] nge = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 2 * n; i++) {
            max = Math.max(max, nums[i % n]);
            if (stack.empty())
                stack.add(i % n);
            else {
                while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                    nge[stack.peek()] = nums[i % n];
                    stack.pop();
                }
                if (i >= n && max == nums[i % n]) break;
                stack.push(i % n);
            }
        }
        while (!stack.empty()) {
            nge[stack.pop()] = -1;
        }
        return nge;
    }

    //My Method, not well optimized
    public int[] nextGreaterElements2(int[] nums) {
        Integer[] nge = new Integer[nums.length];
        Stack<Integer> stack = new Stack<>();
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            if (stack.empty())
                stack.add(i);
            else {
                int peek = stack.peek();
                while (nums[i] > nums[peek] && !stack.isEmpty()) {
                    if (nge[peek] == null)
                        nge[peek] = nums[i];
                    stack.pop();
                    if (!stack.isEmpty())
                        peek = stack.peek();
                }
                if (stack.isEmpty() || (!stack.isEmpty() && stack.peek() != i))
                    stack.push(i);

            }
            if (i == nums.length - 1 && !flag) {
                i = -1;
                flag = true;
            }
        }
        while (!stack.empty()) {
            int index = stack.pop();
            if (nge[index] == null)
                nge[index] = -1;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nge[i];
        }
        return nums;
    }
}
