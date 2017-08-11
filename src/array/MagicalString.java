package array;

/**
 * Date 02/08/17
 *
 * @author Ankit Jain
 */
public class MagicalString {
    public static void main(String[] args) {
        System.out.println(new MagicalString().magicalString(6));
    }

    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;

        int[] a = new int[n + 1];
        a[0] = 1;
        a[1] = 2;
        a[2] = 2;
        int head = 2, tail = 3, num = 1, result = 1;

        while (tail < n) {
            for (int i = 0; i < a[head]; i++) {
                a[tail] = num;
                if (num == 1 && tail < n) result++;
                tail++;
            }
            num = num ^ 3;
            head++;
        }

        return result;
    }

    public int magicalString1(int n) {
        if (n < 2) return n;
        if (n < 4) return 1;
        int[] prev = new int[n];
        prev[0] = 1;
        prev[1] = 2;
        prev[2] = 2;
        int[] next = new int[n];
        int count = 0;
        while (next[n - 1] == 0) {
            int j = 0;
            count = 0;
            for (int i = 0; i < n && prev[i] != 0; i++) {
                int k = 0;
                while (j < n && k < prev[i]) {
                    if ((i & 1) != 1) {
                        count++;
                        next[j++] = 1;
                    } else {
                        next[j++] = 2;
                    }
                    k++;
                }
            }
            prev = next;
        }
        return count;
    }
}
