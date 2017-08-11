package string;

import java.util.Arrays;

/**
 * Date 03/08/17
 *
 * @author Ankit Jain
 */
public class ReconstructOriginalDigitsEnglish {
    public static void main(String[] args) {
        System.out.println(new ReconstructOriginalDigitsEnglish().originalDigits("seven"));
    }

    public String originalDigits(String s) {
        int[] count = new int[10];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'z') count[0]++;
            if (c == 'w') count[2]++;
            if (c == 'x') count[6]++;
            if (c == 's') count[7]++; //7-6
            if (c == 'g') count[8]++;
            if (c == 'u') count[4]++;
            if (c == 'f') count[5]++; //5-4
            if (c == 'h') count[3]++; //3-8
            if (c == 'i') count[9]++; //9-8-5-6
            if (c == 'o') count[1]++; //1-0-2-4
        }
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] = count[9] - count[8] - count[5] - count[6];
        count[1] = count[1] - count[0] - count[2] - count[4];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j < count[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    public String originalDigits1(String s) {
        int[] map = new int[26];
        for (char ch : s.toCharArray()) {
            map[ch - 'a']++;
        }
        String[] sb = new String[10];

        sb[0] = (getStringValue("0", map['z' - 'a']));
        reduce(map, "zero", sb[0].length());
        sb[2] = (getStringValue("2", map['w' - 'a']));
        reduce(map, "two", sb[2].length());
        sb[6] = (getStringValue("6", map['x' - 'a']));
        reduce(map, "six", sb[6].length());
        sb[7] = getStringValue("7", map['s' - 'a']);
        reduce(map, "seven", sb[7].length());
        sb[8] = getStringValue("8", map['g' - 'a']);
        reduce(map, "eight", sb[8].length());
        sb[5] = getStringValue("5", map['v' - 'a']);
        reduce(map, "five", sb[5].length());
        sb[4] = getStringValue("4", map['f' - 'a']);
        reduce(map, "four", sb[4].length());
        sb[3] = getStringValue("3", map['r' - 'a']);
        reduce(map, "three", sb[3].length());
        sb[1] = getStringValue("1", map['o' - 'a']);
        reduce(map, "one", sb[1].length());
        sb[9] = getStringValue("9", map['i' - 'a']);
        reduce(map, "nine", sb[9].length());
        Arrays.sort(sb);
        return String.join("", sb);
    }

    private void reduce(int[] map, String st, int count) {
        if (count > 0) {
            for (char ch : st.toCharArray()) {
                map[ch - 'a'] -= count;
            }
        }
    }

    private String getStringValue(String val, int count) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < count; i++)
            sb.append(val);
        return sb.toString();
    }
}
