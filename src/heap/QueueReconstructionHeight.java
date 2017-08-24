package heap;

import java.math.BigInteger;
import java.util.*;

/**
 * Created on 16/08/17 at 4:30 PM
 *
 * @author Ankit Jain
 */
public class QueueReconstructionHeight {
    public static void main(String[] args) {
//        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] people = {{2, 4}, {3, 4}, {9, 0}, {0, 6}, {7, 1}, {6, 0}, {7, 3}, {2, 5}, {1, 1}, {8, 0}};
        people = new QueueReconstructionHeight().reconstructQueue(people);
        for (int[] val : people) System.out.println(Arrays.toString(val));
        System.out.println(Integer.toString(Integer.parseInt(String.valueOf(Integer.MAX_VALUE), 10), 36));
        System.out.println(
                new BigInteger("12345678901234567890123456789", 10).toString(36));
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] pep : people) {
            list.add(pep[1],pep);
        }
        return list.toArray(new int[people.length][2]);
    }

    public int[][] reconstructQueue3(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0)
            return new int[0][0];

        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (b[0] == a[0]) return a[1] - b[1];
                return b[0] - a[0];
            }
        });

        int n = people.length;
        for (int[] val : people) System.out.println(Arrays.toString(val));
        System.out.println();
        ArrayList<int[]> tmp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tmp.add(people[i][1], new int[]{people[i][0], people[i][1]});
            for (int[] val : tmp) System.out.println(Arrays.toString(val));
            System.out.println();
        }
        System.out.println();
        int[][] res = new int[people.length][2];
        int i = 0;
        for (int[] k : tmp) {
            res[i][0] = k[0];
            res[i++][1] = k[1];
        }

        return res;
    }

    public int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] val1, int[] val2) {
                return val1[0] == val2[0] ? -val1[1] + val2[1] : val1[0] - val2[0];
            }
        });

        TreeMap<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int[] val1 = people[o1];
                int[] val2 = people[o2];
                return val1[1] == val2[1] ? val1[0] - val2[0] : val1[1] - val2[1];
            }
        });
        for (int i = 0; i < people.length; i++) {
            map.put(i, 1);
        }

        int[][] res = new int[people.length][2];
        int[][] people1 = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            people1[i] = Arrays.copyOf(people[i], 2);
        }

        for (int i = 0; i < people.length; i++) {
            int index = map.firstKey();
            map.remove(index);
            res[i] = Arrays.copyOf(people1[index], 2);
            for (int j = index - 1; j >= 0; j--) {
                if (people[j][1] - 1 >= 0) {
                    map.remove(j);
                    people[j][1]--;
                    map.put(j, 1);
                }
            }
        }
        return res;
    }

    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] val1, int[] val2) {
                return val1[0] == val2[0] ? -val1[1] + val2[1] : val1[0] - val2[0];
            }
        });
//        for (int[] val : people) System.out.println(Arrays.toString(val));
        TreeMap<int[], Integer> map = new TreeMap<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] val1, int[] val2) {
                return val1[1] == val2[1] ? val1[0] - val2[0] : val1[1] - val2[1];
            }
        });
        for (int i = 0; i < people.length; i++) {
            int[] temp = new int[]{people[i][0], people[i][1], i};
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
/*
        for (Map.Entry<int[], Integer> me : map.entrySet()) {
            System.out.println(Arrays.toString(me.getKey()));
        }
*/
//        System.out.println();
        int[][] res = new int[people.length][2];
        int[][] people1 = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            people1[i] = Arrays.copyOf(people[i], 2);
        }
        for (int i = 0; i < people.length; i++) {
            int[] val = map.firstKey();
            map.remove(val);
            int index = val[2];
            res[i] = Arrays.copyOf(people1[index], 2);
            for (int j = index; j >= 0; j--) {
                int[] val1 = Arrays.copyOf(people[j], 2);
                people[j][1]--;
                if (people[j][1] >= 0) {

                    map.put(new int[]{people[j][0], people[j][1], j}, map.get(val1));
                    map.remove(val1);

//                    val1[1] = people[j][1];
                }
            }

            /*for (Map.Entry<int[], Integer> me : map.entrySet()) {
                System.out.println(Arrays.toString(me.getKey()));
            }
            System.out.println();
*/
        }
        return res;
    }
}
