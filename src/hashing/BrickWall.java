package hashing;

import java.util.*;

/**
 * Date 03/08/17
 *
 * @author Ankit Jain
 */
public class BrickWall {
    public static void main(String[] args) {
        List list = new ArrayList();
//        list.add(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
//        list.add(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
//        list.add(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        list.add(Arrays.asList(1, 2, 2, 1));
        list.add(Arrays.asList(3, 1, 2));
        list.add(Arrays.asList(1, 3, 2));
        list.add(Arrays.asList(2, 4));
        list.add(Arrays.asList(3, 1, 2));
        list.add(Arrays.asList(1, 3, 1, 1));
        BrickWall brickWall = new BrickWall();
        System.out.println(brickWall.leastBricks2(list));
    }

    public int leastBricks(List<List<Integer>> wall) {
        int max = 0;
        HashMap<Integer, Integer> index = new HashMap<>();
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                index.put(sum, index.getOrDefault(sum, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> me : index.entrySet()) max = Math.max(max, me.getValue());
        return wall.size() - max;
    }

    public int leastBricks1(List<List<Integer>> wall) {
        int min = Integer.MIN_VALUE;
        int total = 0;
        for (int i = 0; i < wall.get(0).size(); i++) {
            total += wall.get(0).get(i);
        }
        if (total == 1) return wall.size();
        int[] index = new int[total - 1];
        for (List<Integer> row : wall) {
            int sum = 0;
            if (row.size() == 1) total++;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                index[sum - 1]++;
            }
        }
        for (int i : index) min = Math.max(min, i);
        return min == 0 ? wall.size() : total - min;
    }

    public int leastBricks2(List<List<Integer>> wall) {
        int R = wall.size(), min = R;
        if (R == 1 && wall.get(0).size() > 1) return 0;

        // [0: end, 1: row, 2: col]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));

        for (int i = 0; i < R; i++) {
            pq.add(new int[]{wall.get(i).get(0), i, 0});
        }

        while (!pq.isEmpty()) {
            int end = pq.peek()[0], count = 0;

            while (!pq.isEmpty() && pq.peek()[0] == end) {
                count++;
                int[] brick = pq.poll();
                if (brick[2] < wall.get(brick[1]).size() - 1) {
                    pq.add(new int[]{end + wall.get(brick[1]).get(brick[2] + 1), brick[1], brick[2] + 1});
                }
            }

            if (!pq.isEmpty()) {
                min = Math.min(min, R - count);
            }
        }

        return min;
    }
}
