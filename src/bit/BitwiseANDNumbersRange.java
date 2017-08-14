package bit;

/**
 * Date 13/08/17
 *
 * @author Ankit Jain
 */
public class BitwiseANDNumbersRange {
    public static void main(String[] args) {
//        System.out.println(new BitwiseANDNumbersRange().rangeBitwiseAnd(700000000, 2147483641));
        System.out.println(new BitwiseANDNumbersRange().rangeBitwiseAnd(1, 1));
        System.out.println(new BitwiseANDNumbersRange().rangeBitwiseAnd(2147483647, 2147483647));
    }

    public int rangeBitwiseAnd5(int m, int n) {
        if (m == 0) {
            return 0;
        }
        int moveFactor = 1;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }

    public int rangeBitwiseAnd6(int m, int n) {
        while (m < n) n = n & (n - 1);
        return n;
    }

    public int rangeBitwiseAnd4(int m, int n) {
        int r = Integer.MAX_VALUE;
        while ((m & r) != (n & r)) r = r << 1;
        return n & r;
    }


    public int rangeBitwiseAnd(int m, int n) {
        int lb = 0;
        int rb = 0;
        int tempM = m;
        int tempN = n;
        while (tempM != 0) {
            lb++;
            tempM = tempM >> 1;
        }
        while (tempN != 0) {
            rb++;
            tempN = tempN >> 1;
        }
        System.out.println(lb);
        System.out.println(rb);
        if (lb != rb) return 0;
        int num = n;
        int l = m;
        int r = n;
        while (l >= 0 && l <= r) {
            num = ((num & l) & r);
            l++;
            r--;
            if (num == 0) break;
        }
        return num;
    }

    public int rangeBitwiseAnd3(int m, int n) {
        int num = n;
        int l = m;
        int r = n;
        while (l <= r) {
            System.out.println("num: " + num + "   l: " + l + "     r: " + r);
            num = ((num & r) & l);
            if (num < l) {
                r = l;
                l = num;
            } else {
                l++;
                r--;
            }
            if (num == 0) break;
        }
        return num;
    }
}
