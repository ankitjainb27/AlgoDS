package sort;

import java.util.Arrays;

/**
 * Date 25/07/17
 *
 * @author Ankit Jain
 */
public class QuickSort {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {32, 12, 435, 4123, 123, 135, 24, 24};
        quickSort.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private void sort(int[] arr, int l, int r) {
        if (l < r) {
            int partition = partition(arr, l, r);
            sort(arr, l, partition - 1);
            sort(arr, partition + 1, r);
        }
    }

    private int partition(int[] arr, int l, int r) {
        int start = l;
        for (int i = l; i < r; i++) {
            if (arr[r] >= arr[i]) {
                swap(arr, i, start);
                start++;
            }
        }
        swap(arr, start, r);
        return start;
    }

    private void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
