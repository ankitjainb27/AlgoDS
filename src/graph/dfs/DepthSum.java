package graph.dfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date 22/06/17
 * @author Ankit Jain
 *
 * https://leetcode.com/problems/nested-list-weight-sum/#/description
 */
public class DepthSum {
    public static void main(String[] args) {

    }

    public int depthSum(List<NestedInteger> nestedList) {
        return depthSumUtil(nestedList, 1);
    }

    private int depthSumRecursive(List<NestedInteger> nestedList, int depth)
    {
        int sum = 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);
        while (!queue.isEmpty())
        {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger ni = queue.poll();
                if(ni.isInteger())
                    sum+=ni.getInteger()*depth;
                else
                    queue.addAll(ni.getList());
            }
            depth++;
        }
        return sum;
    }

    private int depthSumUtil(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger i : nestedList)
            sum += i.isInteger() ? i.getInteger() * depth : depthSumUtil(i.getList(), depth + 1);
        return sum;
    }

    // This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

}
