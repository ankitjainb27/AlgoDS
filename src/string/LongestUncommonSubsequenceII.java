package string;

import java.util.*;

/**
 * Date 09/08/17
 *
 * @author Ankit Jain
 */
public class LongestUncommonSubsequenceII {
    public static void main(String[] args) {
        LongestUncommonSubsequenceII longestUncommonSubsequenceII = new LongestUncommonSubsequenceII();
        System.out.println(longestUncommonSubsequenceII.findLUSlength(new String[]{"abc", "adc"}));
    }

    public int findLUSlength(String[] strs) {
        int res = -1;
        int j = 0;
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i == j) continue;
                if (subcheck(strs[i], strs[j])) break;
            }
            if (j == n) res = Math.max(res, (int) strs[i].length());
        }
        return res;
    }

    boolean subcheck(String a, String b) {
        int i = 0;
        char[] arr = a.toCharArray();
        char[] brr = b.toCharArray();
        for (char c : brr) {
            if (c == arr[i]) i++;
            if (i == arr.length) break;
        }
        return i == arr.length;
    }

    public int findLUSlength2(String[] strs) {
        Map<String, Integer> subseqFreq = new HashMap<>();
        for (String s : strs)
            for (String subSeq : getSubseqs(s))
                subseqFreq.put(subSeq, subseqFreq.getOrDefault(subSeq, 0) + 1);
        int longest = -1;
        for (Map.Entry<String, Integer> entry : subseqFreq.entrySet())
            if (entry.getValue() == 1) longest = Math.max(longest, entry.getKey().length());
        return longest;
    }

    public static Set<String> getSubseqs(String s) {
        Set<String> res = new HashSet<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        Set<String> subRes = getSubseqs(s.substring(1));
        res.addAll(subRes);
        for (String seq : subRes) res.add(s.charAt(0) + seq);
        return res;
    }

    public int findLUSlength1(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.length() - a.length();
            }
        });
        Set<String> dup = findDup(strs);
        for (int i = 0; i < strs.length; i++) {
            if (!dup.contains(strs[i])) {
                if (i == 0) return strs[i].length();
                for (int j = 0; j < i; j++) {
                    if (isSubseq(strs[j], strs[i])) break;
                    if (i - 1 == j) return strs[i].length();
                }
            }
        }
        return -1;
    }

    private boolean isSubseq(String st1, String st2) {
        int i = 0, j = 0;
        while (i < st1.length() && j < st2.length()) {
            if (st1.charAt(i) == st2.charAt(j)) j++;
            i++;
        }
        return j == st2.length();
    }

    private Set<String> findDup(String[] strs) {
        Set<String> dup = new HashSet<>();
        Set<String> val = new HashSet<>();
        for (String st : strs) {
            if (val.contains(st))
                dup.add(st);
            val.add(st);
        }
        return dup;
    }
}
