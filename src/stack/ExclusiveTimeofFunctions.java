package stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created on 15/08/17 at 6:57 PM
 *
 * @author Ankit Jain
 */
public class ExclusiveTimeofFunctions {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ExclusiveTimeofFunctions().exclusiveTime(3, Arrays.asList("0:start:0",  "1:start:1", "1:end:1", "2:start:3", "2:end:3","0:end:5", "2:start:7", "2:end:10"))));
        System.out.println(Arrays.toString(new ExclusiveTimeofFunctions().exclusiveTime1(3, Arrays.asList("0:start:0",  "1:start:1", "1:end:2", "2:start:3", "2:end:3","0:end:5", "2:start:7", "2:end:10"))));
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;
        for (String log : logs) {
            String[] parts = log.split(":");
            if (!stack.isEmpty()) res[stack.peek()] += Integer.parseInt(parts[2]) - prevTime;
            prevTime = Integer.parseInt(parts[2]);
            if (parts[1].equals("start")) stack.push(Integer.parseInt(parts[0]));
            else {
                res[stack.pop()]++;
                prevTime++;
            }
            System.out.println(stack);
            System.out.println(Arrays.toString(res));
        }
        return res;
    }

    public int[] exclusiveTime1(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<int[]> stack = new Stack();
        int prevIndex = -1;
        int prevId = -1;
        int[] prevStrings = new int[0];
        for (int i = 0; i < logs.size(); i++) {
            String[] next1 = logs.get(i).split(":");
            int[] next = new int[]{Integer.parseInt(next1[0]), Integer.parseInt(next1[2]), i, next1[1].equals("start") ? 0 : 1};
            if (!stack.isEmpty()) {
                prevIndex = stack.peek()[2];
                prevId = Integer.parseInt(logs.get(prevIndex).split(":")[0]);
            }
            if (next[3] == 0) {
                if (prevStrings.length > 0 && prevId != -1) {
                    if (prevStrings[3] == 0)
                        res[prevId] += next[1] - prevStrings[1];
                    else res[prevId] += next[1] - prevStrings[1] - 1;
                }
                stack.push(next);
            } else {
                if (i - prevIndex == 1) {
                    res[next[0]] += next[1] - prevStrings[1] + 1;
                } else {
                    res[next[0]] += next[1] - prevStrings[1];
                }
                stack.pop();
            }
            prevStrings = next;
        }
        return res;
    }

}
