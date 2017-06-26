package maths;

/**
 * Date 23/06/17
 *
 * @author Ankit Jain
 */
public class Strobogrammatic {
    public static void main(String[] args) {
        Strobogrammatic strobogrammatic = new Strobogrammatic();
        System.out.println(strobogrammatic.isStrobogrammatic("69"));
        System.out.println(strobogrammatic.isStrobogrammatic("818"));
        System.out.println(strobogrammatic.isStrobogrammatic("88"));
        System.out.println(strobogrammatic.isStrobogrammatic("8832"));
        System.out.println(strobogrammatic.isStrobogrammatic("2"));
        System.out.println(strobogrammatic.isStrobogrammatic("1"));
    }

    public boolean isStrobogrammatic(String num) {
        int l = 0;
        int r = num.length() - 1;
        while (l <= r) {
            if ((num.charAt(l) == num.charAt(r) && (num.charAt(r) == '0' || num.charAt(r) == '8' || num.charAt(r) == '1')) || (num.charAt(l) != num.charAt(r) && ((num.charAt(l) - '0') * (num.charAt(r) - '0') == 54))) {
                l++;
                r--;
            } else return false;
        }
        return true;
    }
}
