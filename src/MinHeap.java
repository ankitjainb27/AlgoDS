import java.util.Arrays;

/**
 * Date 19/06/17
 *
 * @author Ankit Jain
 */
public class MinHeap {
    int[] nums;
    int filled = -1;
    int capacity = 0;

    public MinHeap(int length) {
        nums = new int[length];
        capacity = length;
    }

    public static void main(String[] args) {
        int[] a = {21, 1, 32, 43, 23, 65, 2, 10};
        MinHeap minHeap = new MinHeap(a.length);
        for (int i = 0; i < a.length; i++) {
            minHeap.insert(a[i]);
        }
        minHeap.print();
        System.out.println(minHeap.getMin());
        System.out.println(minHeap.extractMin());
        minHeap.print();
        System.out.println(minHeap.remove(10));
        minHeap.print();
        /*for (int i = 0; i < minHeap.size(); i++) {
            System.out.println(minHeap.extractMin());
        }*/
    }

    private int size() {
        return filled;
    }

    private int remove(int i) {
        int j = 0;
        for (j = 0; j < capacity; j++) {
            if(nums[j] == i)
                break;
        }
        int t = nums[j];
        swap(nums, j, filled );
        heapefiy(j);
        filled--;
        return t;
    }

    private int extractMin() {
        int i = nums[0];
        System.out.println(filled);
        swap(nums, 0, filled);
        heapefiy(0);
        filled--;
        return i;
    }

    private void heapefiy(int i) {
        int min = nums[i];
        int j = i;
        if (left(i)<filled && min > nums[left(i)]) {
            j = left(i);
            min = nums[j];
        }
        if (right(i)<filled && min > nums[right(i)]) {
            j = right(i);
            min = nums[j];
        }
        if (min != nums[i]) {
                swap(nums, j, parent(j));
                heapefiy(j);
        }
    }

    int left(int i) {
        return 2 * i + 1;
    }

    int right(int i) {
        return 2 * i + 2;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    void print() {
        System.out.println(Arrays.toString(nums));
    }

    private void insert(int i) {
        filled++;
        nums[filled] = i;
        int child = filled;
        while (nums[child] < nums[parent(child)]) {
            swap(nums, child, parent(child));
            child = parent(child);
        }
    }

    private void swap(int[] nums, int child, int parent) {
        int temp = nums[child];
        nums[child] = nums[parent];
        nums[parent] = temp;
    }

    public int getMin() {
        return nums[0];
    }
}
