package graph.topologicalsort;

import java.util.*;

/**
 * Date 08/08/17
 *
 * @author Ankit Jain
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int course = prerequisites[i][0];
            graph[pre].add(course);
        }
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i])
                if (dfs(graph, visited, recStack, stack, i)) return new int[0];
        }
        int[] res = new int[numCourses];
        int k = 0;
        while (numCourses-- != 0) {
            res[k++] = stack.pop();
        }
        return res;
    }

    private boolean dfs(List<Integer>[] graph, boolean[] visited, boolean[] recStack, Stack<Integer> stack, int i) {
        visited[i] = true;
        recStack[i] = true;
        List<Integer> list = graph[i];
        for (Integer j : list) {
            if (recStack[j]) return true;
            if (!visited[j])
                if (dfs(graph, visited, recStack, stack, j)) return true;
        }
        recStack[i] = false;
        stack.push(i);
        return false;
    }

    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int course = prerequisites[i][0];
            graph[pre].add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        int count = 0;
        int[] res = new int[numCourses];
        int k = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            res[k++] = i;
            for (int j : graph[i])
                if (--indegree[j] == 0) queue.add(j);
            count++;
        }
        return count == numCourses ? res : new int[0];
    }
}
