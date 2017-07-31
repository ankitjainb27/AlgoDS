package contest;

/**
 * Date 02/07/17
 *
 * @author Ankit Jain
 */
public class LeetcodeContest38 {
    public static void main(String[] args) {
        LeetcodeContest38 contest38 = new LeetcodeContest38();
        System.out.println(contest38.judgeSquareSum(1));
        System.out.println(contest38.judgeSquareSum(2));
        System.out.println(contest38.judgeSquareSum(3));
        System.out.println(contest38.judgeSquareSum(4));
        System.out.println(contest38.judgeSquareSum(5));
        System.out.println(contest38.judgeSquareSum(100));
        System.out.println(contest38.judgeSquareSum(1000000));
        System.out.println(contest38.judgeSquareSum(999999999));
        System.out.println(contest38.judgeSquareSum(6));
        System.out.println(contest38.judgeSquareSum(0));
    }

    public boolean judgeSquareSum(int c) {
        int l = 0;
        int r = (int)Math.sqrt(c);
        while (l <= r) {
            int val = l * l + r * r;
            if (val == c)
                return true;
            else if (val < c)
                l++;
            else r--;

        }
        return false;
    }
}
