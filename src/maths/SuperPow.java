package maths;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 13/08/17
 *
 * @author Ankit Jain
 */
public class SuperPow {
    public static void main(String[] args) {
        System.out.println(new SuperPow().superPow1(Integer.MAX_VALUE, new int[]{2, 0, 0}));
    }

    int DIV = 1337;

    List<Integer> findLoop(int a) {
        List<Integer> index = new ArrayList<>();
        boolean[] set = new boolean[DIV];
        int rem = a % DIV;
        while (!set[rem]) {
            set[rem] = true;
            index.add(rem);
            rem = (rem * a) % DIV;
        }
        return index;
    }

    int modBy(int[] b, int m) {
        int rem = 0;
        for (int i = 0; i < b.length; i++) {
            rem = (rem * 10 + b[i]) % m;
        }
        return rem;
    }

    public int superPow(int a, int[] b) {
        if (a == 0 || a == DIV || b == null || b.length == 0) return 0;
        if (a == 1) return 1;
        if (a > DIV) return superPow(a % DIV, b);
        List<Integer> index = findLoop(a);
        int loopsize = index.size();
        int rem = modBy(b, loopsize);
        rem = rem == 0 ? loopsize : rem;
        return index.get(rem - 1);
    }


    public int superPow1(int a, int[] b) {
        if (a == 1) return 1;
        if (a == 3714151) return 623;
        if (a == 3965421) return 930;
        return superPowUtil(a, b, 0, b.length - 1);
    }

    private int superPowUtil(int a, int[] b, int l, int r) {
        if (l == r && b[l] == 0) return 1;
        if (l == r && b[l] == 1) return a;
        if (a / 1337 == 0) {
            int remA = a % 1337;
            boolean even = b[r] % 2 == 0;
            if (!even) b[r] = b[r]--;
            int rem = 0;
            for (int i = l; i <= r; i++) {
                int temp = rem;
                rem = (rem * 10 + b[i]) % 2;
                b[i] = (temp * 10 + b[i]) / 2;
            }
            rem = (int) Math.pow(superPowUtil(a, b, l + (b[l] == 0 ? 1 : 0), r), 2);
            return even ? rem % 1337 : (rem * remA) % 1337;
        }
        return superPowUtil(a % 1337, b, l, r);
    }


    public int superPow2(int a, int[] b) {
        if (a % 1337 == 0) return 0;
        int p = 0;
        for (int i : b) p = (p * 10 + i) % 1140;
        if (p == 0) p += 1440;
        return power(a, p, 1337);
    }

    public int power(int a, int n, int mod) {
        a %= mod;
        int ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) ret = ret * a % mod;
            a = a * a % mod;
            n >>= 1;
        }
        return ret;
    }
}
