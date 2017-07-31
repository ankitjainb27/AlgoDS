package maths;

import java.util.Arrays;
import java.util.Random;

/**
 * Date 28/07/17
 *
 * @author Ankit Jain
 */
public class ShuffleArray {
    public static void main(String[] args) {
        ShuffleArray obj = new ShuffleArray(new int[]{1, 2, 3});
        int[] param_1 = obj.reset();
        int[] param_2 = obj.shuffle();
        System.out.println(Arrays.toString(param_1));
        System.out.println(Arrays.toString(param_2));
    }

    int[] val;
    Random rand;
    int[] shuff;

    public ShuffleArray(int[] nums) {
        val = nums;
        rand = new Random();
        shuff = val.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return val;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = shuff.length - 1; i > 0; i--) {
            int r = rand.nextInt(i + 1);
            int temp = shuff[i];
            shuff[i] = shuff[r];
            shuff[r] = temp;
        }
        return shuff;
    }
}
