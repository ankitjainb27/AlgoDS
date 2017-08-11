package array;

import java.util.*;

/**
 * Date 09/08/17
 * [1,7,11]
 * [2,4,6]
 * 9
 *
 * @author Ankit Jain
 */
public class FindKPairsSmallestSums {
    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        FindKPairsSmallestSums findKPairsSmallestSums = new FindKPairsSmallestSums();
        List<int[]> list = findKPairsSmallestSums.kSmallestPairs(nums1, nums2, 9);
        for (int[] val : list) {
            System.out.println(Arrays.toString(val));
        }
    }

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue(new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                return e1[0] + e1[1] - (e2[0] + e2[1]);
            }
        });
        List<int[]> list = new ArrayList<int[]>();
        if (k == 0 || nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return list;
        for (int i = 0; i < nums1.length && i < k; i++) {
            queue.add(new int[]{nums1[i], nums2[0], 0});
        }
        while (!queue.isEmpty() && k-- != 0) {
            int[] val = queue.poll();
            list.add(new int[]{val[0], val[1]});
            if (val[2] == nums2.length - 1) continue;
            queue.add(new int[]{val[0], nums2[val[2] + 1], val[2] + 1});
        }
        return list;
    }

    public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Ele> queue = new PriorityQueue(new Comparator<Ele>() {
            @Override
            public int compare(Ele e1, Ele e2) {
                return e1.sum - e2.sum;
            }
        });
        List<int[]> list = new ArrayList<int[]>();
        int len = Math.max(nums1.length, nums2.length);
        for (int i = 0; i < len; i++) {
            int index2 = i < nums2.length ? i : nums2.length - 1;
            int index1 = i < nums1.length ? i : nums1.length - 1;
            for (int j = index1; j < nums1.length; j++) {
                queue.add(new Ele(nums1[j] + nums2[index2], new int[]{nums1[j], nums2[index2]}));
            }
            for (int l = index2 + 1; l < nums2.length; l++) {
                queue.add(new Ele(nums1[index1] + nums2[l], new int[]{nums1[index1], nums2[l]}));
            }
            System.out.println(queue);
            while (!queue.isEmpty() && k-- != 0) {
                list.add(queue.poll().indexes);
            }
        }
        return list;
    }

    class Ele {
        int sum;
        int[] indexes;

        public Ele(int sum, int[] val) {
            this.sum = sum;
            indexes = val;
        }

        @Override
        public String toString() {
            return "  sum: " + sum + "indexex: " + Arrays.toString(indexes);
        }
    }


    public List<int[]> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        List<int[]> list = new ArrayList<int[]>();
        int start1 = 0, start2 = 0, end1 = 0, end2 = 0;
        while (k != 0 && start1 < nums1.length && start2 < nums2.length) {
            if (start1 == start2 && end1 == end2 && start1 == end1) {
                list.add(new int[]{nums1[start1], nums2[start2]});
                end1++;
                end2++;
            } else if (nums1[start1] + nums2[end2] >= nums2[start2] + nums1[end1]) {
                list.add(new int[]{nums2[start2], nums1[end1]});
                end1++;
            } else {
                list.add(new int[]{nums1[start1], nums2[end2]});
                end2++;
            }
            if (end1 == nums1.length) {
                start2++;
                end1 = start2 < nums1.length ? start2 : nums1.length - 1;
            }
            if (end2 == nums2.length) {
                start1++;
                end2 = start1 < nums2.length ? start1 : nums2.length - 1;
            }
            k--;
        }
        return list;
    }
}
