package general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by ankit.ppe on 25/05/17.
 */
public class IO {

    public static void main(String[] args) throws IOException {
        IO io = new IO();
//        io.scanner();
        io.bufferedReader();
    }

    private void bufferedReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
        str = br.readLine();
        System.out.println(str);
        br.close();
    }

    private void scanner() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        double b = scanner.nextDouble();
        String c = scanner.nextLine();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        scanner.close();
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
