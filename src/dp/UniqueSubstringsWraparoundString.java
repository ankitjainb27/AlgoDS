package dp;

import java.util.HashSet;

/**
 * Created on 19/08/17 at 4:18 PM
 *
 * @author Ankit Jain
 */
public class UniqueSubstringsWraparoundString {
    public static void main(String[] args) {
        System.out.println(new UniqueSubstringsWraparoundString().findSubstringInWraproundString("cdefghefghijklmnopqrstuvwxmnijklmnopqrstuvbcdefghijklmnopqrstuvwabcddefghijklfghijklmabcdefghijklmnopqrstuvwxymnopqrstuvwxyz"));
//        System.out.println(new UniqueSubstringsWraparoundString().findSubstringInWraproundString("zababcd"));
    }

    public int findSubstringInWraproundString4(String p) {
        // count[i] is the maximum unique substring end with ith letter.
        // 0 - 'a', 1 - 'b', ..., 25 - 'z'.
        int[] count = new int[26];

        // store longest contiguous substring ends at current position.
        int maxLengthCur = 0;

        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25))) {
                maxLengthCur++;
            } else {
                maxLengthCur = 1;
            }

            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }

        // Sum to get result
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
        }
        return sum;
    }

    public int findSubstringInWraproundString(String p) {
        int[] maxLen = new int[26];
        if (p.length() == 0) return 0;
        int count = 0;
        int steakLen = 1;
        int l = 0;
        for (int i = 1; i < p.length(); i++) {
            char ch = p.charAt(i);
            if ((ch - p.charAt(i - 1) + 26) % 26 == 1) {
                steakLen++;
            } else {
                if (maxLen[p.charAt(l) - 'a'] < steakLen) {
                    int r = steakLen;
                    for (int j = 0; j < steakLen; j++) {
                        int val = p.charAt(l + j) - 'a';
                        if (maxLen[p.charAt(l + j) - 'a'] < r) {
                            count += r - maxLen[p.charAt(l + j) - 'a'];
                        }
                        maxLen[val] = Math.max(maxLen[val], r--);
                    }
                }
                l = i;
                steakLen = 1;
            }
        }
        int r = steakLen;
        for (int j = 0; j < steakLen; j++) {
            int val = p.charAt(l + j) - 'a';
            if (maxLen[p.charAt(l + j) - 'a'] < r) {
                count += r - maxLen[p.charAt(l + j) - 'a'];
            }
            maxLen[val] = Math.max(maxLen[val], r--);
        }
        return count;
    }

    private int fact(int i) {
        if (i == 0) return 0;
        return (i * (i + 1)) / 2;
    }


    public int findSubstringInWraproundString2(String p) {
        HashSet<String> set = new HashSet<>();
        if (p.length() == 0) return 0;
        String prev = p.charAt(0) + "";
        set.add(prev);
        for (int i = 1; i < p.length(); i++) {
            char ch = p.charAt(i);
            if ((ch - p.charAt(i - 1) + 26) % 26 == 1) {
                for (int j = 0; j < prev.length(); j++) {
                    String st = prev.substring(j, prev.length());
                    if (set.contains(st + ch)) break;
                    else
                        set.add(st + ch);
                }
                set.add(ch + "");
                prev += ch;
            } else {
                prev = ch + "";
                set.add(prev);
            }
        }
        System.out.println(set);
        return set.size();
    }

    public int findSubstringInWraproundString1(String p) {
        HashSet<String> set = new HashSet<>();
        boolean[][] dp = new boolean[p.length()][p.length()];
        if (p.length() == 0) return 0;
        dp[0][0] = true;
        set.add(p.charAt(0) + "");
        for (int i = 1; i < dp.length; i++) {
            dp[i][i] = true;
            set.add(p.charAt(i) + "");
            if ((p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                dp[i - 1][i] = true;
                set.add(p.substring(i - 1, i + 1));
            }
        }
        /*for (int i = 0; i < p.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                System.out.print(dp[i][j] + " -- ");
            }
            System.out.println();
        }
        System.out.println();*/
        for (int i = 3; i <= p.length(); i++) {
            for (int j = 0; j < p.length() - i + 1; j++) {
                int r = j + i - 1;
                dp[j][r] = dp[j][r - 1] && dp[j + 1][r];
                if (dp[j][r])
                    set.add(p.substring(j, r + 1));
            }
        }
        /*for (int i = 0; i < p.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                System.out.print(dp[i][j] + " -- ");
            }
            System.out.println();
        }*/
        return set.size();
    }

}
