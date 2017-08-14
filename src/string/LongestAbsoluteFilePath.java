package string;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Date 12/08/17
 *
 * @author Ankit Jain
 */
public class LongestAbsoluteFilePath {
    public static void main(String[] args) {
        LongestAbsoluteFilePath longestAbsoluteFilePath = new LongestAbsoluteFilePath();
        System.out.println((longestAbsoluteFilePath.lengthLongestPath("a\n\tb.txt\na2\n\tb2.txt")));
        System.out.println(longestAbsoluteFilePath.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
        System.out.println(longestAbsoluteFilePath.lengthLongestPath("dir\n    file.txt"));
        System.out.println(longestAbsoluteFilePath.lengthLongestPath("dir\n        file.txt"));
        System.out.println(longestAbsoluteFilePath.lengthLongestPath("subdir2\n\tfile.ext"));
    }

    public int lengthLongestPath2(String input) {
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length+1];
        int maxLen = 0;
        for(String s:paths){
            int lev = s.lastIndexOf("\t")+1, curLen = stack[lev+1] = stack[lev]+s.length()-lev+1;
            if(s.contains(".")) maxLen = Math.max(maxLen, curLen-1);
        }
        return maxLen;
    }

    public int lengthLongestPath1(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for(String s:input.split("\n")){
            int lev = s.lastIndexOf("\t")+1; // number of "\t"
            while(lev+1<stack.size()) stack.pop(); // find parent
            int len = stack.peek()+s.length()-lev+1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if(s.contains(".")) maxLen = Math.max(maxLen, len-1);
        }
        return maxLen;
    }

    public int lengthLongestPath(String input) {
        System.out.println(lengthLongestPath2(input));
        Integer max = Integer.MIN_VALUE;
        input = input.replace("\n\t", "+");
        String[] vals = input.split("\n");
        for (String val : vals) {
            max = Math.max(max, dfs(val.replace("+", "\n\t"), "\n\t"));
        }
        return max;
    }

    private int dfs(String input, String sep) {
        int res = 0;
        int sepIndex = input.indexOf(sep);
        int dotIndex = input.indexOf(".");
        if (dotIndex == -1) return 0;
        if (sepIndex == -1) return input.length();
        if (sepIndex < dotIndex) res = sepIndex;
        int start = sepIndex + sep.length();
        int max = 0;
        input = input + sep + " ";
        while ((sepIndex = input.indexOf(sep, sepIndex + sep.length())) != -1) {
            if (input.charAt(sepIndex + sep.length()) != '\t') {
                int res1 = dfs(input.substring(start, sepIndex), sep + "\t");
                if (res1 != 0)
                    max = Math.max(max, res1);
                start = sepIndex + sep.length();
            }
        }
        return max == 0 ? 0 : res + max + 1;
    }
}
