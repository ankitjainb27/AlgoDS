package dp;

import java.util.Arrays;

/**
 * Created on 21/08/17 at 2:17 PM
 *
 * @author Ankit Jain
 */
public class CanIWin {
    public static void main(String[] args) {
        System.out.println(new CanIWin().canIWin(10, 0));
        System.out.println(new CanIWin().canIWin(10, 1));
        System.out.println(new CanIWin().canIWin(10, 11));
        System.out.println(new CanIWin().canIWin(10, 40));
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) return true;
        if (desiredTotal > maxChoosableInteger * (maxChoosableInteger + 1) / 2) return false;
        boolean[] visited = new boolean[maxChoosableInteger + 1];
        visited[0] = true;
        return ciwUtil(visited, desiredTotal);
    }

    private boolean ciwUtil(boolean[] visited, int desiredTotal) {
        if (desiredTotal <= visited.length) {
            for (int i = desiredTotal; i < visited.length; i++) {
                if (!visited[i]) return true;
            }
        }
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (!ciwUtil(visited, desiredTotal - i)) {
                    System.out.println(Arrays.toString(visited) + " -- " + String.valueOf(i));
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
