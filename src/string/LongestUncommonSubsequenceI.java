package string;

import java.util.HashMap;

/**
 * Date 09/08/17
 *
 * @author Ankit Jain
 */
public class LongestUncommonSubsequenceI {
    public static void main(String[] args) {
        LongestUncommonSubsequenceI longestUncommonSubsequenceI = new LongestUncommonSubsequenceI();
        System.out.println(longestUncommonSubsequenceI.findLUSlength("abc","adc"));
    }

    public int findLUSlength(String a, String b) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : new String[]{a, b}) {
            for (int i = 0; i < (1 << s.length()); i++) {
                String t = "";
                for (int j = 0; j < s.length(); j++) {
                    if (((i >> j) & 1) != 0)
                        t += s.charAt(j);
                }
                System.out.println(t);
                if (map.containsKey(t))
                    map.put(t, map.get(t) + 1);
                else
                    map.put(t, 1);
                System.out.println(map);
            }
        }
        int res = -1;
        for (String s : map.keySet()) {
            if (map.get(s) == 1)
            {
                System.out.println(s);
                res = Math.max(res, s.length());
            }
        }
        return res;
    }
}
