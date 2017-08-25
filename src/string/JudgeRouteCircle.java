package string;

/**
 * Created on 24/08/17 at 12:40 PM
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/judge-route-circle/description/
 */
public class JudgeRouteCircle {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
            }
        }
        return x == 0 && y == 0;
    }

    public boolean judgeCircle1(String moves) {
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'U') y++;
            if (ch == 'D') y--;
            if (ch == 'R') x++;
            if (ch == 'L') x--;
        }
        return x == 0 && y == 0;
    }

    public boolean judgeCircle2(String moves) {
        moves=" " + moves + " ";
        return moves.split("L").length==moves.split("R").length && moves.split("U").length == moves.split("D").length;
    }
}
