package maths;

/**
 * Date 02/08/17
 *
 * @author Ankit Jain
 */
public class CountNumbersWithUniqueDigits {
    /**
     * Following the hint. Let f(n) = count of number with unique digits of length n.
     * <p>
     * f(1) = 10. (0, 1, 2, 3, ...., 9)
     * <p>
     * f(2) = 9 * 9. Because for each number i from 1, ..., 9, we can pick j to form a 2-digit number ij and there are 9 numbers that are different from i for j to choose from.
     * <p>
     * f(3) = f(2) * 8 = 9 * 9 * 8. Because for each number with unique digits of length 2, say ij, we can pick k to form a 3 digit number ijk and there are 8 numbers that are different from i and j for k to choose from.
     * <p>
     * Similarly f(4) = f(3) * 7 = 9 * 9 * 8 * 7....
     * <p>
     * ...
     * <p>
     * f(10) = 9 * 9 * 8 * 7 * 6 * ... * 1
     * <p>
     * f(11) = 0 = f(12) = f(13)....
     * <p>
     * any number with length > 10 couldn't be unique digits number.
     * <p>
     * The problem is asking for numbers from 0 to 10^n. Hence return f(1) + f(2) + .. + f(n)
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;

        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }

    public static int countNumbersWithUniqueDigits1(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 10, base = 9;
        for (int i = 2; i <= n && i <= 10; i++) {
            base = base * (9 - i + 2);
            ans += base;
        }
        return ans;
    }

    public static int countNumbersWithUniqueDigits2(int n) {
        if (n > 10) {
            return countNumbersWithUniqueDigits2(10);
        }
        int count = 1; // x == 0
        long max = (long) Math.pow(10, n);

        boolean[] used = new boolean[10];

        for (int i = 1; i < 10; i++) {
            used[i] = true;
            count += search(i, max, used);
            used[i] = false;
        }

        return count;
    }

    private static int search(long prev, long max, boolean[] used) {
        int count = 0;
        if (prev < max) {
            count += 1;
        } else {
            return count;
        }

        for (int i = 0; i < 10; i++) {
            if (!used[i]) {
                used[i] = true;
                long cur = 10 * prev + i;
                count += search(cur, max, used);
                used[i] = false;
            }
        }

        return count;
    }
}
