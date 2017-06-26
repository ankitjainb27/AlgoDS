package string;

import java.util.Arrays;

/**
 * Date 26/06/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/find-permutation/#/description
 */
public class FindPermutation {
    public static void main(String[] args) {
        FindPermutation findPermutation = new FindPermutation();
        System.out.println(Arrays.toString(findPermutation.findPermutation("DDD")));
        System.out.println(Arrays.toString(findPermutation.findPermutation("DDDD")));
        System.out.println(Arrays.toString(findPermutation.findPermutation("IIII")));
        System.out.println(Arrays.toString(findPermutation.findPermutation("IDDD")));
        System.out.println(Arrays.toString(findPermutation.findPermutation("IIDD")));
        System.out.println(Arrays.toString(findPermutation.findPermutation("IDID")));
        System.out.println(Arrays.toString(findPermutation.findPermutation("IDDI")));
    }

    public int[] findPermutation(String st) {
        int[] val = new int[st.length() + 1];
        for (int i = 0; i < val.length; i++) {
            val[i] = i + 1;
        }
        for (int j = 0; j < st.length(); j++) {
            if (st.charAt(j) == 'D') {
                int k = j;
                while (j < st.length() && st.charAt(j) == 'D')
                    j++;
                reverse(val, k, j);
                j--;
            }
        }
        return val;
    }

    private void reverse(int[] val, int k, int i) {
        while (k < i) {
            int temp = val[i];
            val[i] = val[k];
            val[k] = temp;
            k++;
            i--;
        }
    }
}
