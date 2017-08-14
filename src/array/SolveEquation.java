package array;

/**
 * Date 11/08/17
 *
 * @author Ankit Jain
 */
public class SolveEquation {
    public static void main(String[] args) {
        SolveEquation solveEquation = new SolveEquation();
        System.out.println(solveEquation.solveEquation("x+5-3+x=6+x-2"));
        System.out.println(solveEquation.solveEquation("x=x"));
        System.out.println(solveEquation.solveEquation("2x=x"));
        System.out.println(solveEquation.solveEquation("2x+3x-6x=x+2"));
        System.out.println(solveEquation.solveEquation("x=x+2"));
        System.out.println(solveEquation.solveEquation("-x=1"));
        System.out.println(solveEquation.solveEquation("-x=-1"));
        System.out.println(solveEquation.solveEquation("0x=0"));
    }

    public String solveEquation(String equation) {
        int isEqualFound = 1;
        int left = 0;
        int right = 0;
        int val = 0;
        int sign = 1;
        equation += ' ';
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);
            if (ch >= '0' && ch <= '9')
                val = val * 10 + (ch - '0');
            else {
                if (ch == 'x') {
                    if (val == 0)
                        val = (i > 0 && equation.charAt(i - 1) == '0') ? 0 : 1;
                    left += sign * val * isEqualFound;
                } else
                    right += sign * val * -1*isEqualFound;
                sign = ch == '-' ? -1 : 1;
                val = 0;
                if (ch == '=') {
                    isEqualFound = -1;
                }
            }
        }
        if (left == 0 && right != 0) return "No solution";
        if (left == right && left == 0) return "Infinite solutions";
        return "x=" + right / left;
    }

}
