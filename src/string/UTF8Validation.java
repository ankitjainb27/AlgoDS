package string;

/**
 * Date 12/08/17
 *
 * @author Ankit Jain
 */
public class UTF8Validation {
    public static void main(String[] args) {
        UTF8Validation utf8Validation = new UTF8Validation();
//        int[] nums = {228, 189, 160, 229, 165, 189, 13, 10};
        int[] nums = {240, 162, 138, 147, 145};
//        int[] nums = {10};
        System.out.println(utf8Validation.validUtf8(nums));
    }

    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) return false;
        boolean isValid = true;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > 255) return false; // 1 after 8th digit, 100000000
            int numberOfBytes = 0;
            if ((data[i] & 128) == 0) { // 0xxxxxxx, 1 byte, 128(10000000)
                numberOfBytes = 1;
            } else if ((data[i] & 224) == 192) { // 110xxxxx, 2 bytes, 224(11100000), 192(11000000)
                numberOfBytes = 2;
            } else if ((data[i] & 240) == 224) { // 1110xxxx, 3 bytes, 240(11110000), 224(11100000)
                numberOfBytes = 3;
            } else if ((data[i] & 248) == 240) { // 11110xxx, 4 bytes, 248(11111000), 240(11110000)
                numberOfBytes = 4;
            } else {
                return false;
            }
            for (int j = 1; j < numberOfBytes; j++) { // check that the next n bytes start with 10xxxxxx
                if (i + j >= data.length) return false;
                if ((data[i + j] & 192) != 128) return false; // 192(11000000), 128(10000000)
            }
            i = i + numberOfBytes - 1;
        }
        return isValid;
    }

    public boolean validUtf81(int[] data) {
        boolean valid = true;
        for (int k = 0; k < data.length; k++) {
            String first = Integer.toBinaryString(data[k]);
            first = first.length() != 8 ? (new String(new char[8 - first.length()]).replace("\0", "0")) + first : first;
            int countBytes = 0;
            for (char ch : first.toCharArray()) {
                if (ch == '1') countBytes++;
                else break;
            }
            if (countBytes > 4 || countBytes == 1) return false;
            int count = 0;
            if (countBytes == 0) countBytes = 1;
            for (int i = 1; i < countBytes; i++) {
                if (i + k >= data.length) return false;
                String st = Integer.toBinaryString(data[i + k]);
                st = st.length() != 8 ? (new String(new char[8 - st.length()]).replace("\0", "0")) + st : st;
                if (st.charAt(0) == '1' && st.charAt(1) == '0') count++;
            }
            if ((count != countBytes - 1)) return false;
            k = k + countBytes - 1;
        }
        return valid;
    }
}
