package string;

/**
 * Date 26/06/17
 * @author Ankit Jain
 * https://leetcode.com/problems/ternary-expression-parser/#/description
 */
public class TernaryExpressionParser {
    public static void main(String[] args) {
        TernaryExpressionParser ternaryExpressionParser = new TernaryExpressionParser();
        System.out.println(ternaryExpressionParser.parseTernary("T?2:3"));
        System.out.println(ternaryExpressionParser.parseTernary("F?1:T?4:5"));
        System.out.println(ternaryExpressionParser.parseTernary("T?T?F:5:3"));
        System.out.println(ternaryExpressionParser.parseTernary("F?T?F?7:F?F?F?3:F?F?0:1:0:6:1:0:5"));
    }

    // My Solution
    public String parseTernary(String expression) {
        return parseTernaryUtil(expression, 0, expression.length() - 1);
    }

    private String parseTernaryUtil(String expression, int l, int r) {
        int count = 0;
        int i;
        for (i = l; i <= r; i++) {
            if (expression.charAt(i) == '?') count++;
            else if (expression.charAt(i) == ':') {
                count--;
                if (count == 0) break;
            }
        }
        if (expression.charAt(l) == 'T') {
            if (i - (l + 2) == 1)
                return String.valueOf(expression.charAt(l + 2));
            else return parseTernaryUtil(expression, l + 2, i - 1);
        } else {
            if (r - i == 1)
                return String.valueOf(expression.charAt(i + 1));
            else return parseTernaryUtil(expression, i + 1, r);
        }
    }
}
