package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created on 14/08/17 at 7:01 PM
 *
 * @author Ankit Jain
 */
public class FindKClosestElements {
    public static void main(String[] args) {
        System.out.println(String.valueOf(Integer.MAX_VALUE).length());
        System.out.println(String.valueOf(Integer.MIN_VALUE));
        System.out.println(new FindKClosestElements().findClosestElements1(Arrays.asList(0, 0, 1, 2, 3, 3, 4, 7, 7, 8), 3, 5));
    }

    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        List<Integer> res = new ArrayList<>(k);
        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, arr.get(i) - x);
        }
        int l = 0;
        int r = k - 1;
        int minSum;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += Math.abs(arr.get(i));
        }
        minSum = sum;
        for (int i = k; i < arr.size(); i++) {
            sum += Math.abs(arr.get(i)) - Math.abs(arr.get(i - k));
            if (sum < minSum) {
                l = i - k + 1;
                r = i;
                minSum = sum;
            }
        }
        for (int i = l; i <= r; i++) {
            res.add(arr.get(i) + x);
        }
        return res;
    }

    public List<Integer> findClosestElements1(List<Integer> arr, int k, int x) {
        List<Integer> res = new ArrayList<>(k);
        int floor;
        int ceiling;
        if (x < arr.get(0)) {
            floor = -1;
            ceiling = 0;
        } else {
            floor = binarySearch(arr, x);
            ceiling = floor + 1;
        }
        int l = 0;
        int r = arr.size() - 1;
        for (int i = 0; i < k; i++) {
            if (floor < l)
                res.add(arr.get(ceiling++));
            else if (ceiling > r)
                res.add(arr.get(floor--));
            else {
                if (x - arr.get(floor) <= arr.get(ceiling) - x)
                    res.add(arr.get(floor--));
                else
                    res.add(arr.get(ceiling++));
            }
        }
        Collections.sort(res);
        return res;
    }

    public int binarySearch(List<Integer> arr, int x) {
        int l = 0;
        int r = arr.size() - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) == x || (arr.get(mid) < x && arr.get(mid + 1) > x)) return mid;
            else if (arr.get(mid) < x)
                l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }
}
