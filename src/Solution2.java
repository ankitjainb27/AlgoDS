import java.util.*;

/**
 * Date 16/06/17
 *
 * @author Ankit Jain
 *         <p>
          10
          4 3 7 5 10
          5 1 5 10 6 4
          4 7 5 9 4
          0
          2 9 4
          2 9 4
          2 5 4
          6 1 3 7 10 6 4
          1 4
          1 9
 */


public class Solution2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        ArrayList<HashSet<Integer>> list = new ArrayList<>(N + 1);
        int[] result = new int[N+1];
        boolean[] visited = new boolean[N+1];
        int countFilled = 0;
        for (int i = 0; i <= N; i++) {
            HashSet<Integer> set = new HashSet<>();
            list.add(set);
        }
        for (int i = 1; i <= N; i++) {
            int val = scanner.nextInt();
            for (int j = 0; j < val; j++) {
                int a = scanner.nextInt();
                list.get(a).add(i);
            }
        }
        int noToFill = 1;
        ArrayList<Integer> done = new ArrayList<>();
        while (countFilled < N) {
            for (int i = 1; i <= N; i++) {
                if (list.get(i).size() == 0 && !visited[i]) {
                    result[i] = noToFill;
                    done.add(i);
                    countFilled++;
                    visited[i] = true;
                }
            }
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < done.size(); j++) {
                    if(list.get(i).contains(done.get(j)))
                        list.get(i).remove(done.get(j));
                }
            }
            noToFill++;
            done.clear();
        }
        for (int i = 0; i < N; i++) {
            System.out.print(result[i+1] + " ");
        }
    }
}
