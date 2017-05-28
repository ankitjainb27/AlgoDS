import java.util.*;

/**
 * Created by ankit.ppe on 28/05/17.
 */
public class LeetcodeContest34 {
    public static void main(String[] args) {
        LeetcodeContest34 contest34 = new LeetcodeContest34();

        int[][] mat = {{2, 2}, {3, 3}};
        System.out.println(contest34.maxCount(3, 3, mat));


        String[] strings1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] strings2 = {"Piatti", "Shogun", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse"};
        strings2 = new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        strings2 = new String[]{"KFC", "Shogun", "Burger King"};
        System.out.println(Arrays.toString(contest34.findRestaurant(strings1, strings2)));

        int[] nums = {5, 4, 0, 3, 1, 6, 2};
        nums = new int[]{0, 1, 2, 3, 4, 5, 6};
        nums = new int[]{1, 2, 0, 4, 5, 3};
        nums = new int[]{};
        System.out.println(contest34.arrayNesting(nums));

        System.out.println(contest34.findIntegers(5));
    }

    /**
     * <b>Question 1</b>
     * Given an m * n matrix M initialized with all 0's and several update operations.
     * <p>
     * Operations are represented by a 2D array, and each operation is represented by an array with two positive integers a and b, which means M[i][j] should be added by one for all 0 {@literal <} = i {@literal <} a  and 0 {@literal <}= j {@literal <} b.
     * <p>
     * You need to count and return the number of maximum integers in the matrix after performing all the operations.
     * <pre>
     * <code>
     * Example 1:
     * Input:
     * m = 3, n = 3
     * operations = [[2,2],[3,3]]
     * Output: 4
     * Explanation:
     * Initially, M =
     * [[0, 0, 0],
     * [0, 0, 0],
     * [0, 0, 0]]
     *
     * After performing [2,2], M =
     * [[1, 1, 0],
     * [1, 1, 0],
     * [0, 0, 0]]
     *
     * After performing [3,3], M =
     * [[2, 2, 1],
     * [2, 2, 1],
     * [1, 1, 1]]
     *
     * So the maximum integer in M is 2, and there are four of it in M. So return 4.
     * </code></pre>
     * <p>
     * Note:
     * The range of m and n is [1,40000].
     * The range of a is [1,m], and the range of b is [1,n].
     * The range of operations size won't exceed 10,000.
     *
     * @param m   Rows
     * @param n   Cols
     * @param ops array of operations
     * @return Number of maximum integers in the matrix after performing all the operations.
     */

    public int maxCount(int m, int n, int[][] ops) {
        int minM = m;
        int minN = n;
        int count = minM * minN;
        for (int i = 0; i < ops.length; i++) {
            minM = Math.min(ops[i][0], minM);
            minN = Math.min(ops[i][1], minN);
            count = minM * minN;
        }
        return count;
    }

    /**
     * <b>Question 2</b>
     * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
     * <p>
     * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.
     * <pre>
     * Example 1:
     * Input:
     * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
     * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
     * Output: ["Shogun"]
     * Explanation: The only restaurant they both like is "Shogun".
     * Example 2:
     * Input:
     * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
     * ["KFC", "Shogun", "Burger King"]
     * Output: ["Shogun"]
     * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
     * </pre>
     * Note:
     * The length of both lists will be in the range of [1, 1000].
     * The length of strings in both lists will be in the range of [1, 30].
     * The index is starting from 0 to the list length minus 1.
     * No duplicates in both lists.
     *
     * @param list1 List of preference of Andy
     * @param list2 List of preference of Doris
     * @return all of preferred restaurants with no order requirement
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        int min = Integer.MAX_VALUE;
        ArrayList<String> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, Integer> map1 = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
            set.add(list1[i]);
        }
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            if (set.contains(s)) {
                min = Math.min(min, map.get(s) + i);
                map1.put(s, map.get(s) + i);
            }
        }
        for (Map.Entry<String, Integer> item : map1.entrySet()) {
            if (min == item.getValue())
                list.add(item.getKey());
        }

        return list.toArray(new String[list.size()]);
    }

    //Part of Array Nesting function
    int maxLen = 0;

    /**
     * <b>Quesiton 3</b>
     * A zero-indexed array A consisting of N different integers is given. The array contains all integers in the range [0, N - 1].
     * <p>
     * Sets S[K] for 0 {@literal <}= K {@literal <} N are defined as follows:
     * <p>
     * S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.
     * <p>
     * Sets S[K] are finite for each K and should NOT contain duplicates.
     * <p>
     * Write a function that given an array A consisting of N integers, return the size of the largest set S[K] for this array.
     * <pre>
     * Example 1:
     * Input: A = [5,4,0,3,1,6,2]
     * Output: 4
     * Explanation:
     * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
     * </pre>
     * One of the longest S[K]:
     * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
     * Note:
     * N is an integer within the range [1, 20,000].
     * The elements of A are all distinct.
     * Each element of array A is an integer within the range [0, N-1].
     *
     * @param nums Array for nesting
     * @return Size of the largest set S[K] for this array.
     */

    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i])
                dfsUtil(nums, visited, i);
        }
        return maxLen;
    }

    private void dfsUtil(int[] nums, boolean[] visited, int i) {
        int len = 0;
        while (true) {
            visited[i] = true;
            i = nums[i];
            len++;
            if (visited[i]) {
                maxLen = Math.max(maxLen, len);
                break;
            }
        }
    }

    /**
     * <b>Question 4</b>
     * Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.
     * <p>
     * Example 1:
     * <pre>
     * Input: 5
     * Output: 5
     * Explanation:
     * Here are the non-negative integers {@literal <}= 5 with their corresponding binary representations:
     * 0 : 0
     * 1 : 1
     * 2 : 10
     * 3 : 11
     * 4 : 100
     * 5 : 101
     * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
     * </pre>
     * Note: 1 {@literal <}= n {@literal <}= 109
     *
     * @param num Integer for counting numbers without consecutive ones
     * @return Numbers without consecutive ones
     *
     * TODO
     */

    public int findIntegers(int num) {
        int count = 0;
        int len = (int) (Math.log(num) / Math.log(2));
        System.out.println(len);
        len = len + 2;
        int[] a = new int[len + 1];
        a[0] = 0;
        a[1] = 1;
        for (int i = 2; i <= len; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        count += a[len];
        int b = (int) (num - Math.pow(2, len - 3));
        System.out.println(b);
        return count;
    }

}
