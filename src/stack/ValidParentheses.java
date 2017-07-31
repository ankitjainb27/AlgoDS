package stack;

import java.util.Stack;

/**
 * Date 13/07/17
 *
 * @author Ankit Jain
 */
public class ValidParentheses {
    public static void main(String[] args) {
        String[] strings = {"{}{}()", "{", "}", "", "{[}]", "[{)]", "{[}]}", "[{()}]", "{}([])"};
        for (String str :
                strings) {
            System.out.println(str + " - " + isValid2(str));

        }
        System.out.println();
    }

    public static boolean isValid2(String s) {
        char[] stack = new char[s.length()];
        int head = 0;
        for (Character ch :
                s.toCharArray()) {
            if (ch == '(')
                stack[head++] = ')';
            else if (ch == '{')
                stack[head++] = '}';
            else if (ch == '[')
                stack[head++] = ']';
            else {
                if (head == 0 || stack[--head] != ch)
                    return false;
            }
        }
        return head == 0;
    }


    public static boolean isValid1(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character ch :
                s.toCharArray()) {
            if (ch == '(')
                stack.push(')');
            else if (ch == '{')
                stack.push('}');
            else if (ch == '[')
                stack.push(']');
            else {
                if (stack.isEmpty() || !stack.pop().equals(ch))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    //Method 1
    public static boolean isValid(String value) {
        Stack<Character> stack = new Stack<>();
        int j = 0;
        while (j < value.length()) {
            Character ch = value.charAt(j);
            if (ch == '(' || ch == '{' || ch == '[')
                stack.push(ch);
            if (ch == ')' || ch == '}' || ch == ']')
                if (stack.empty() || !isMatch(stack.pop(), ch))
                    return false;
            j++;
        }
        return stack.empty();
    }

    private static boolean isMatch(Character popCh, Character ch) {
        return ((popCh == '(' && ch == ')') || (popCh == '{' && ch == '}') || (popCh == '[' && ch == ']'));
    }
}
