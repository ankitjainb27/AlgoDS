package bit;

/**
 * Created on 24/08/17 at 3:26 PM
 *
 * @author Ankit Jain
 */
public class NumberComplement {
    public int findComplement(int num) {
        int res = 0;
        int i = 0;
        while (num != 0) {
            if ((num & 1) == 0)
                res |= (1 << i);
            num >>= 1;
            i++;
        }
        return res;
    }

    /**
     *
     * @param num
     * @return
     */
    public int findComplement1(int num) {
        return ((~num) & (Integer.highestOneBit(num) - 1));
    }

    public int findComplement2(int num) {
        int mask = num;
        mask |= mask >> 1;
        mask |= mask >> 2;
        mask |= mask >> 4;
        mask |= mask >> 8;
        mask |= mask >> 16;
        return num ^ mask;
    }
}
