package stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created on 21/08/17 at 11:05 AM
 *
 * @author Ankit Jain
 */
public class SimplifyPath {
    public static void main(String[] args) {
        System.out.println(new SimplifyPath().simplifyPath("/a/./b/../../c/"));
        System.out.println(new SimplifyPath().simplifyPath("/home/"));
        System.out.println(new SimplifyPath().simplifyPath("/home//foo"));
        System.out.println(new SimplifyPath().simplifyPath("/../"));
        System.out.println(new SimplifyPath().simplifyPath("/"));
        System.out.println(new SimplifyPath().simplifyPath("/abc/..."));
    }

    public String simplifyPath(String path) {
        String[] strings = path.split("/");
        System.out.println(Arrays.toString(strings));
        LinkedList<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (String st : strings) {
            if (st.equals("/") || st.equals(".") || st.length() == 0)
                continue;
            else if (st.equals("..")) {
                if (!stack.isEmpty())
                    stack.removeLast();
            } else stack.addLast(st);
        }
        while (!stack.isEmpty()) sb.append("/" + stack.removeFirst());
        return sb.toString().length() == 0 ? "/" : sb.toString();
    }

    public String simplifyPath1(String path) {
        final int l = path.length();
        int p = 0;
        final StringBuffer sb = new StringBuffer();
        while (p < l) {
            while (p < l && path.charAt(p) == '/') p++;
            int q = p;
            while (q < l && path.charAt(q) != '/') q++;
            String s = path.substring(p, q);
            p = q;
            if (s.equals("..")) {
                int end = sb.length() - 1;
                while (end >= 0 && sb.charAt(end) != '/') end--;
                if (end >= 0) sb.setLength(end);
            } else if (s.length() > 0 && !s.equals(".")) {
                sb.append('/');
                sb.append(s);
            }
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
