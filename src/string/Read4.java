package string;

/**
 * Date 25/06/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/read-n-characters-given-read4/#/description
 */
public class Read4 {
    public int read(char[] buf, int n) {
        int index = -1;
        char[] chs = new char[4];
        while (index+1 < n) {
            int val = read4(chs);
            int i = 0;
            while (val-- > 0 && index+1 < n) {
                buf[++index] = chs[i++];
            }
            if (i < 3)
                break;
        }
        return index + 1;
    }

    private int read4(char[] chs) {
        return 0;
    }
}
