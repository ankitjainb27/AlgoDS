package contest;

import java.util.*;

/**
 * Created by ankit.ppe on 14/05/17.
 */
public class LeetcodeContest32 {
    public static void main(String[] args) {
        LeetcodeContest32 leetcodeContest32 = new LeetcodeContest32();
        Point[] points = {new Point(1, 1), new Point(2, 2), new Point(2, 0), new Point(2, 4), new Point(3, 3), new Point(4, 2)};
        System.out.println(leetcodeContest32.outerTrees(points));

        //        System.out.println(leetcodeContest32.minDistance("a", "ab"));


//        Integer[] pidA = {1, 3, 10, 5};
//        Integer[] ppidA = {3, 0, 5, 3};
//        List<Integer> pid = Arrays.asList(pidA);
//        List<Integer> ppid = Arrays.asList(ppidA);
//        System.out.println(leetcodeContest32.killProcess(pid, ppid, 5));

//        int[] arr = {2, 6, 4, 8, 10, 9, 15};
//        System.out.println(leetcodeContest32.findUnsortedSubarray(arr));
    }

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }

        @Override
        public String toString() {
            return x + "  " + y;
        }
    }

    public List<Point> outerTrees(Point[] points) {
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.x - o2.x;
            }
        });
//        System.out.println(Arrays.toString(points));
        List<Point> points1 = new ArrayList<>();
        points1.add(points[0]);
        if (!points1.contains(points[points.length - 1]))
            points1.add(points[points.length - 1]);
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.y - o2.y;
            }
        });
        if (!points1.contains(points[0]))
            points1.add(points[0]);
        if (!points1.contains(points[points.length - 1]))
            points1.add(points[points.length - 1]);
        return points1;
    }

    public int minDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }
        int r = word1.length() + 1;
        int c = word2.length() + 1;
        int[][] mat = new int[r][c];
        for (int i = 0; i < r; i++) {
            mat[i][0] = i;
        }
        for (int i = 0; i < c; i++) {
            mat[0][i] = i;
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                Character ch1 = word1.charAt(i - 1);
                Character ch2 = word2.charAt(j - 1);
                if (ch1.equals(ch2))
                    mat[i][j] = mat[i - 1][j - 1];
                else
                    mat[i][j] = 1 + Math.min(mat[i - 1][j], mat[i][j - 1]);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(mat[i][j] + "  ");
            }
            System.out.println();
        }
        return mat[r - 1][c - 1];
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        int root = 0;
        for (int i = 0; i < ppid.size(); i++) {
            int j = ppid.get(i);
            if (j != 0) {
                if (hashMap.containsKey(j)) {
                    hashMap.get(j).add(pid.get(i));
                } else {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(pid.get(i));
                    hashMap.put(j, list1);
                }
            } else {
                root = pid.get(i);
            }
        }
        if (kill == root)
            return pid;
        list.add(kill);
        killProcessUtil(kill, list, hashMap);
        return list;
    }

    private void killProcessUtil(int kill, List<Integer> list, HashMap<Integer, List<Integer>> hashMap) {
        if (hashMap.containsKey(kill)) {
            List<Integer> list1 = hashMap.get(kill);
            list.addAll(list1);
            for (int i = 0; i < list1.size(); i++) {
                killProcessUtil(list1.get(i), list, hashMap);
            }
        }
        return;
    }

    public int findUnsortedSubarray(int[] nums) {

        int[] num2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        int l = 0;
        int r = 0;
        for (int i = 0; i < num2.length; i++) {
            if (nums[i] != num2[i])

            {
                l = i;
                break;
            }
        }
        for (int i = num2.length - 1; i >= 0; i--) {
            if (nums[i] != num2[i]) {
                r = i;
                break;
            }
        }
        if (l != r)
            return r - l + 1;
        return 0;
    }
}
