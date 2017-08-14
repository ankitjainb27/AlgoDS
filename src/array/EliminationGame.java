package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date 13/08/17
 *
 * @author Ankit Jain
 */
public class EliminationGame {
    public static void main(String[] args) {
        System.out.println(new EliminationGame().lastRemaining(9));
    }

    public int lastRemaining3(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(i);
        boolean left = true;
        while (list.size() > 1) {
            if (left) {
                for (int i = 0; i < list.size(); i++)
                    list.remove(i);
            } else {
                for (int i = list.size() - 1; i >= 0; i = i - 2)
                    list.remove(i);
            }
            left = !left;
        }
        return list.get(0);
    }

    public int lastRemaining(int n) {
        boolean[] alive = new boolean[n];
        Arrays.fill(alive, true);
        boolean left = true;
        int count = 0;
        while (count < n - 1) {
            boolean delete = true;
            if (left) {
                for (int i = 0; i < n; i++)
                    if (alive[i]) {
                        if (delete) {
                            alive[i] = false;
                            count++;
                        }
                        delete = !delete;
                    }
            } else {
                for (int i = n - 1; i >= 0; i--)
                    if (alive[i]) {
                        if (delete) {
                            alive[i] = false;
                            count++;
                        }
                        delete = !delete;
                    }
            }
            left = !left;
        }
        int i;
        for (i = 0; i < n; i++) {
            if (alive[i]) break;
        }
        return i + 1;
    }

    public int lastRemaining2(int n) {
        int size = n;
        int step = 2;
        boolean left = true;
        int start = 1;
        while (size > 1) {
            boolean odd = size % 2 == 1;
            if (left || odd)
                start = start + step / 2;
            size = size / 2;
            left = !left;
            step *= 2;
        }
        return start;
    }

    public int lastRemaining1(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(i);
        boolean left = true;
        while (list.size() > 1) {
            int l = 0;
            if (!left || (left && list.size() % 2 == 1))
                l = list.size() - 1;
            else
                l = list.size() - 2;
            for (int i = l; i >= 0; i = i - 2)
                list.remove(i);
            left = !left;
        }
        return list.get(0);
    }
}
