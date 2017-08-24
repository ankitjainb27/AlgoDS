package array;

/**
 * Created on 15/08/17 at 3:03 PM
 *
 * @author Ankit Jain
 */
public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        int[] num = new int[10];
        int r = 9;
        while (n != 0) {
            num[r--] = n % 10;
            n = n / 10;
        }
        int l = r + 1;
        r = 9;
        int i;
        for (i = r; i >= l + 1; i--)
            if (num[i] > num[i - 1]) break;
        if (i == l) return -1;
        i--;
        int j = binarySearch(num, i + 1, r, num[i]);
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
        reverse(num, i + 1, r);
        StringBuilder sb = new StringBuilder();
        for (i = l; i <= r; i++)
            sb.append(num[i]);
        if (sb.toString().length() == 10 && sb.toString().compareTo(String.valueOf(Integer.MAX_VALUE)) > 1) return -1;
        /*  Two other ways to handle the corner case.
        long val = Long.parseLong(new String(num));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;

        try {
            return Integer.parseInt(new String(a));
        } catch (Exception e) {
            return -1;
        }
        */
        return Integer.parseInt(sb.toString());
    }

    private int binarySearch(int[] num, int l, int r, int x) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (num[mid] > x && num[mid + 1] <= x) return mid;
            else if (num[mid] > x)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return l;
    }

    private void reverse(int[] num, int l, int r) {
        while (l < r) {
            int temp = num[l];
            num[l++] = num[r];
            num[r--] = temp;
        }
    }
}
