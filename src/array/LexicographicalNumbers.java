package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 14/08/17 at 3:14 PM
 *
 * @author Ankit Jain
 */
public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n);
        loUtil(n, 0, list, 1);
        return list;
    }

    private void loUtil(int n, int start, List<Integer> list, int init) {
        if (start > n) return;
        for (int i = init; i <= 9 && (i + start) <= n; i++) {
            list.add(start + i);
            loUtil(n, (start + i) * 10, list, 0);
        }
    }

    public List<Integer> lexicalOrder1(int n) {
        List<Integer> list = new ArrayList<>(n);
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            list.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }
        return list;
    }
}
