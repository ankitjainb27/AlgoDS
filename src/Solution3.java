import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit.ppe on 19/03/17.
 */
public class Solution3 {
    public static void main(String[] args) {
        List<List<Integer>> m = new ArrayList<>();
        List<Integer> m1 = new ArrayList<>();
        m1.add(0);
        m1.add(0);
        m1.add(0);
        m.add(m1);
        List<Integer> m2 = new ArrayList<>();
        m2.add(0);
        m2.add(1);
        m2.add(0);
        m.add(m2);
        List<Integer> m3 = new ArrayList<>();
        m3.add(1);
        m3.add(1);
        m3.add(1);
        m.add(m3);
        Solution3 solution = new Solution3();
        solution.updateMatrix(m);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print((m.get(i)).get(j));
            }
            System.out.println();
        }
    }

    public List<List<Integer>> updateMatrix(List<List<Integer>> m) {
        int rows = m.size();
        if(rows == 0)
            return m;
        int cols = m.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        for(int i = 0 ;i<rows;i++)
        {
            for(int j = 0 ;j<cols;j++)
            {
                if(!visited[i][j])
                {
                    if( (m.get(i)).get(j) != 0)
                    {
                        int a = dfs(m,i,j, rows, cols, visited);
                    }
                    else
                    {
                        visited[i][j] = true;
                    }
                }
            }
        }
        return m;
    }

    public int dfs(List<List<Integer>> m, int i, int j, int rows, int cols, boolean[][] visited)
    {
        if(visited[i][j])
            return (m.get(i)).get(j);
        if((m.get(i)).get(j) == 0)
        {
            visited[i][j] = true;
            return 0;
        }
        visited[i][j] = true;
        int val = Integer.MAX_VALUE;
        if(!(i-1<0 || i-1>=rows || j<0 || j>=cols))
            val = Math.min(val,1+dfs(m,i-1,j,rows,cols,visited));
        if(!(i+1<0 || i+1>=rows || j<0 || j>=cols))
            val = Math.min(val,1+dfs(m,i+1,j,rows,cols,visited));
        if(!(i<0 || i>=rows || j-1<0 || j-1>=cols))
            val = Math.min(val,1+dfs(m,i,j-1,rows,cols,visited));
        if(!(i<0 || i>=rows || j+1<0 || j+1>=cols))
            val = Math.min(val,1+dfs(m,i,j+1,rows,cols,visited));
        (m.get(i)).set(j,val);
        return val;
    }
}
