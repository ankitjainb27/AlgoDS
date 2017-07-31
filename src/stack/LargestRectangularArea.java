package stack;

import java.util.Stack;

/**
 * Date 25/07/17
 *
 * @author Ankit Jain
 */
public class LargestRectangularArea {
    public static void main(String[] args) {
        LargestRectangularArea area = new LargestRectangularArea();
        int[] nums = {6, 2, 5, 4, 5, 1, 6};
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int i = 0;
        while (i < nums.length) {
            if (stack.isEmpty() || nums[stack.peek()] <= nums[i]) stack.push(i++);
            else {
                int top = stack.pop();
                maxArea = Math.max(maxArea, nums[top] * (i - (stack.isEmpty() ? 0 : stack.peek() + 1)));
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            maxArea = Math.max(maxArea, nums[top] * (i - (stack.isEmpty() ? 0 : stack.peek() + 1)));
        }
        System.out.println(maxArea);
    }
}
