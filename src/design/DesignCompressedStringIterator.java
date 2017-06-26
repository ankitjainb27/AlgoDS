package design;

/**
 * Date 25/06/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/design-compressed-string-iterator/#/description
 */
public class DesignCompressedStringIterator {
    public static void main(String[] args) {
        StringIterator iterator = new StringIterator("x6");

        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());

    }

    public static class StringIterator {

        int index;
        int count;
        int len;
        String string;
        int charInInt;

        public StringIterator(String compressedString) {
            index = 0;
            string = compressedString;
            len = compressedString.length();
            if (len > 0) {
                count = 0;
                int index1 = index + 1;
                while (index1<len && !Character.isLetter(compressedString.charAt(index1))) {
                    count = count * 10 + compressedString.charAt(index1) - '0';
                    index1++;
                    charInInt++;
                }
            } else
                count = 0;

        }

        public char next() {
            if (hasNext()) {
                char ch = string.charAt(index);
                if (--count == 0) {
                    index += charInInt+1;
                    if (index + 1 < len) {
                        int index1 = index + 1;
                        charInInt = 0;
                        while (index1 < len && !Character.isLetter(string.charAt(index1))) {
                            count = count * 10 + string.charAt(index1) - '0';
                            index1++;
                            charInInt++;
                        }
                    }
                }
                return ch;
            }
            return ' ';
        }

        public boolean hasNext() {
            return index < len;
        }
    }

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
