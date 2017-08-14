package greedy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Date 12/08/17
 *
 * @author Ankit Jain
 */
public class Dota2Senate {
    public String predictPartyVictory1(String senate) {
        int n = senate.length();
        Queue<Integer> queueR = new LinkedList<Integer>();
        Queue<Integer> queueD = new LinkedList<Integer>();
        for (int i = 0; i < senate.length(); i++) {
            char ch = senate.charAt(i);
            if (ch == 'R') queueR.add(i);
            else queueD.add(i);
        }
        while (!queueR.isEmpty() && !queueD.isEmpty()) {
            int indexR = queueR.poll();
            int indexD = queueD.poll();
            if (indexR < indexD) queueR.add(indexR + n);
            else queueD.add(indexD + n);
        }
        return queueD.size() == 0 ? "Radiant" : "Dire";
    }

    public String predictPartyVictory(String senate) {
        char[] chars = senate.toCharArray();
        int Rcount = 0;
        int Dcount = 0;
        while (true) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                if (temp == 'R') {
                    if (Dcount > 0) Dcount--;
                    else {
                        Rcount++;
                        sb.append('R');
                    }
                } else {
                    if (Rcount > 0) Rcount--;
                    else {
                        Dcount++;
                        sb.append('D');
                    }
                }
            }
            int lastArrLen = chars.length;
            chars = sb.toString().toCharArray();
            if (chars.length == 1 || chars.length == lastArrLen) {
                return chars[0] == 'R' ? "Radiant" : "Dire";
            }
        }
    }

    public static String predictPartyVictory2(String senate) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('D', 0);
        map.put('R', 1);
        int[] nextRound = new int[2], eliminate = new int[2];
        while (true) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < senate.length(); i++) {
                if (eliminate[map.get(senate.charAt(i))] > 0) {
                    eliminate[map.get(senate.charAt(i))]--;
                } else {
                    nextRound[map.get(senate.charAt(i))]++;
                    eliminate[map.get(senate.charAt(i)) ^ 1]++;
                    sb.append(senate.charAt(i));
                }
            }

            if (nextRound[0] - eliminate[0] > 0 && nextRound[1] - eliminate[1] > 0) {
                nextRound = new int[2];
                senate = sb.toString();
            } else {
                return nextRound[0] - eliminate[0] > 0 ? "Dire" : "Radiant";
            }
        }
    }

    public String predictPartyVictory3(String senate) {
        int r = 0, d = 0, start = 0;
        char[] arr = senate.toCharArray();
        for (char c : arr) {
            if (c == 'R') r++;
            else d++;
        }

        while (r > 0 && d > 0) {
            while (arr[start] != 'R' && arr[start] != 'D') {
                start = (start + 1) % arr.length;
            }

            char ban = 'R';
            if (arr[start] == 'R') {
                ban = 'D';
                d--;
            } else {
                r--;
            }
            int idx = (start + 1) % arr.length;
            while (arr[idx] != ban) {
                idx = (idx + 1) % arr.length;
            }

            arr[idx] = ' ';
            start = (start + 1) % arr.length;
        }

        return d == 0 ? "Radiant" : "Dire";
    }
}
