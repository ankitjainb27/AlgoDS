package dp;

import java.util.HashMap;

/**
 * Date 27/07/17
 *
 * @author Ankit Jain
 */
public class BurstBalloons {

    public static void main(String[] args) {
        BurstBalloons burstBalloons = new BurstBalloons();
        burstBalloons.maxCoins(new int[]{3, 1, 5, 8});
    }

    public void maxCoins(int[] nums) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] visited = new String[nums.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = "0";
        }
        System.out.println(maxCoinsUtil(nums, map, visited));
    }

    private int maxCoinsUtil(int[] nums, HashMap<String, Integer> map, String[] visited) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i].equals("0")) {
                visited[i] = "1";
                int val;
                String key = String.join(",", visited);
                if (map.containsKey(key))
                    val = map.get(key);
                else {
                    val = nums[i] * findLeft(nums, visited, i) * findRight(nums, visited, i);
                    val += maxCoinsUtil(nums, map, visited);
                }
                max = Math.max(max, val);
                visited[i] = "0";
            }
        }
        System.out.println(String.join(",", visited) + "--" + max);
        map.put(String.join(",", visited), max);
        return max;
    }

    private int findLeft(int[] nums, String[] visited, int i) {
        for (int j = i - 1; j >= 0; j--) {
            if (visited[j].equals("0"))
                return nums[j];
        }
        return 1;
    }

    private int findRight(int[] nums, String[] visited, int i) {
        for (int j = i + 1; j < nums.length; j++) {
            if (visited[j].equals("0"))
                return nums[j];
        }
        return 1;
    }
}
