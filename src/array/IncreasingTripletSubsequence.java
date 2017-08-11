package array;

/**
 * Date 05/08/17
 *
 * @author Ankit Jain
 */
public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) {
                small = n;
            } // update small if n is smaller than both
            else if (n <= big) {
                big = n;
            } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }

    public boolean increasingTriplet2(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) first = num;
            else if (num <= second) second = num;
            if (num > second) return true;
        }
        return false;
    }

    public boolean increasingTriplet1(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > first && num > second) return true;
            else if (num > first && num < second) second = num;
            if (first > num) first = num;
        }
        return false;
    }
}
