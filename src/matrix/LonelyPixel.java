package matrix;

/**
 * Date 25/06/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/lonely-pixel-i/#/description
 */
public class LonelyPixel {
    public static void main(String[] args) {
        LonelyPixel lonelyPixel = new LonelyPixel();
        System.out.println(lonelyPixel.findLonelyPixel(new char[][]{{'W', 'W', 'B'},
                {'W', 'B', 'W'},
                {'B', 'W', 'W'}}));

    }

    public int findLonelyPixel(char[][] picture) {
        if(picture == null || picture.length == 0) return 0;
        int[] row = new int[picture.length];
        int[] col = new int[picture[0].length];
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B' && row[i] * col[j] == 1)
                    count++;
            }
        }
        return count;
    }
}
