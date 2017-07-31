package maths;

/**
 * Date 28/07/17
 *
 * @author Ankit Jain
 */
public class ArithmeticSlices {
    public static void main(String[] args) {
        ArithmeticSlices arithmeticSlices = new ArithmeticSlices();
        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
    }

    public int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        if (A.length < 3) return 0;
        int currCount = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] + A[i - 2] == 2 * A[i - 1]) {
                currCount++;
            } else {
                count += (currCount) * (currCount + 1) / 2;
                currCount = 0;
            }
        }
        count += (currCount) * (currCount + 1) / 2;
        return count;
    }
}
