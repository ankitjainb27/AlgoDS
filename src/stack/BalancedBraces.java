package stack;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by ankit.ppe on 26/05/17.
 */

/**
 * 13
 * 4 98 20 -26 88 75 4 -18 86 23 45 61 95
 * 12
 * 1 1
 * 2 1
 * 3 1
 * 1 2
 * 2 2
 * 3 2
 * 1 3
 * 2 3
 * 3 3
 * 1 4
 * 2 4
 * 3 4
 */
public class BalancedBraces {
    public static void main(String[] args) {
        String[] strings = {"{}{}()","{","}","","{[}]","[{)]","{[}]}"};
        System.out.println(Arrays.toString(braces(strings)));
        /*Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        int c = arr[0];
        int T = scanner.nextInt();
        scanner.nextLine();
        int[] element = new int[T];
        for (int i = 0; i < T; i++) {
            element[i] = arr[(scanner.nextInt() - 1) * c + scanner.nextInt()];
        }
        scanner.close();
        for (int i = 0; i < T; i++) {
            System.out.println(element[i]);
        }*/
    }

    static String[] braces(String[] values) {
        Stack<Character> stack = new Stack<>();
        String[] strings = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            isBalanced(values[i],stack, strings, i);
            stack.clear();
        }
        return strings;
    }

    private static void isBalanced(String value, Stack<Character> stack, String[] result, int i) {
        int j = 0;
        while (j < value.length()) {
            Character ch = value.charAt(j);
            if (ch == '(' || ch == '{' || ch == '[')
                stack.push(ch);
            if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.empty()) {
                    result[i] = "NO";
                    return;
                }
                Character popCh = stack.pop();
                if (!isMatch(popCh, ch)) {
                    result[i] = "NO";
                    return;
                }
            }
            j++;
        }
        if (stack.empty()) {
            result[i] = "YES";
        } else {
            result[i] = "NO";
        }
        return;
    }

    private static boolean isMatch(Character popCh, Character ch) {
        if ((popCh == '(' && ch == ')') || (popCh == '{' && ch == '}') || (popCh == '[' && ch == ']'))
            return true;
        else
            return false;
    }
}
