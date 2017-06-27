package hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date 26/06/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/group-shifted-strings/#/description
 */
public class GroupShiftedStrings {
    public static void main(String[] args) {
        GroupShiftedStrings groupShiftedStrings = new GroupShiftedStrings();
        String[] strs = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        System.out.println(groupShiftedStrings.groupStrings1(strs));
    }

    //My Solutions
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();
        String str;
        for (String string : strings) {
            str = "";
            for (int i = 0; i < string.length() - 1; i++) {
                int l = (string.charAt(i + 1) - 'a') - (string.charAt(i) - 'a');
                if (l < 0) l += 26;
                if (l < 10) str += ("0");
                str += (String.valueOf(l));
            }
            if (string.length() == 1)
                str += "0";
            String st = str;
            if (!map.containsKey(st)) {
                map.put(st, new ArrayList<String>());
            }
            map.get(st).add(string);

        }
        List<List<String>> list = new ArrayList<>();
        list.addAll(map.values());
        return list;
    }

    public List<List<String>> groupStrings1(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();
        System.out.println((int) 'a');
        for (String string : strings) {
            int n = string.charAt(0) - 'a';
            char[] ch = string.toCharArray();
            ch[0] = 'a';
            for (int i = 1; i < ch.length; i++) {

                int val = (ch[i] - n);
                char cht = (char) val;
                if (cht < 'a') cht += 26;
                ch[i] = cht;
            }
            String st = String.valueOf(ch);
            if (!map.containsKey(st)) {
                map.put(st, new ArrayList<String>());
            }
            List<String> lis1 = map.get(st);
            lis1.add(string);
            map.put(st, lis1);

        }
        List<List<String>> list = new ArrayList<>();
        list.addAll(map.values());
        return list;
    }

    public List<List<String>> groupStrings2(String[] strings) {
        return new ArrayList
                        (
                                Stream.of(strings).
                                        collect(Collectors.groupingBy(s -> s.chars().mapToObj(c -> (c - s.charAt(0) + 26) % 26)
                                                .collect(Collectors.toList())
                                        )).values());
    }

}
