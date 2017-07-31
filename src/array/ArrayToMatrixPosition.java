package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Date 13/07/17
13
4 98 20 -26 88 75 4 -18 86 23 45 61 95
12
1 1
2 1
3 1
1 2
2 2
3 2
1 3
2 3
3 3
1 4
2 4
3 4
 *
 * @author Ankit Jain
 */
public class ArrayToMatrixPosition {
    public static void main(String[] args) throws IOException {
//        inputUsingScanner();
        inputUsingBufferedReader();
    }

    private static void inputUsingScanner()
    {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        int c = arr[0];
        int T = scanner.nextInt();
        System.out.println();
        for (int i = 0; i < T; i++) {
            System.out.println(arr[(scanner.nextInt() - 1) * c + scanner.nextInt()]);
        }
        scanner.close();
    }

    private static void inputUsingBufferedReader() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String arrayString = br.readLine();
        int c = Integer.parseInt(arrayString.substring(0,arrayString.indexOf(" ")));
        String[] strings = arrayString.substring(arrayString.indexOf(" ")+1,arrayString.length()).split(" ");
        int T = Integer.parseInt(br.readLine());
        System.out.println(c);
        System.out.println(T);
        System.out.println(N);
        System.out.println(Arrays.toString(strings));
        System.out.println(arrayString);
        String[] val;
        for (int i = 0; i < T; i++) {
            val = br.readLine().split(" ");
            System.out.println(strings[(Integer.parseInt(val[0]) - 1) * c + Integer.parseInt(val[1])-1]);
        }
    }
}
