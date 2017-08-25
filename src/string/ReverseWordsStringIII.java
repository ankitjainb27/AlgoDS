package string;

/**
 * Created on 24/08/17 at 4:25 PM
 *
 * @author Ankit Jain
 */
public class ReverseWordsStringIII {
    public static void main(String[] args) {
        System.out.println(new ReverseWordsStringIII().reverseWords1("Let's take LeetCode contest"));
    }
    public String reverseWords1(String s) {
        s += ' ';
        int l = 0;
        int r;
        char[] chars = s.toCharArray();
        while ((r = s.indexOf(' ', l)) != -1) {
            reverse(chars, l, r-1);
            l = r+1;
        }
        return new String(chars).substring(0, chars.length - 1);
    }

    private void reverse(char[] chars, int l, int r) {
        while (l < r) {
            char temp = chars[l];
            chars[l++] = chars[r];
            chars[r--] = temp;
        }
    }

    public String reverseWords(String s) {
        String[] st = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : st) {
            sb.append(reverse(str.toCharArray()));
            sb.append(" ");
        }
        sb.setLength(sb.toString().length() - 1);
        return sb.toString();
    }

    private String reverse(char[] chars) {
        int l = 0;
        int r = chars.length - 1;
        while (l < r) {
            char temp = chars[l];
            chars[l++] = chars[r];
            chars[r--] = temp;
        }
        return new String(chars);
    }
}
