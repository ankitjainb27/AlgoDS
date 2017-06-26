package maths;

/**
 * Date 25/06/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/paint-fence/#/description
 */
public class PaintFence {
    public static void main(String[] args) {
        PaintFence paintFence = new PaintFence();
        System.out.println(paintFence.numWays(3, 3));
        System.out.println(paintFence.numWays(2, 3));
        System.out.println(paintFence.numWays(1, 1));
        System.out.println(paintFence.numWays(0, 1));
    }

    public int numWays(int n, int k) {
        if (n == 0)
            return 0;
        return (k * (int) Math.pow(k - 1, n - 1));
    }
}
