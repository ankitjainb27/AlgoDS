package graph.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date 07/08/17
 *
 * @author Ankit Jain
 */
public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int[][] prerequisites = {{0, 1}};
        System.out.println(courseSchedule.canFinish(2, prerequisites));
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[][] mat = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int[] val = prerequisites[i];
            mat[val[1]][val[0]] = 1;
            indegree[val[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.add(i);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (int j = 0; j < numCourses; j++) {
                if (mat[i][j] != 0 && --indegree[j] == 0) queue.add(j);
            }
            count++;
        }
        return numCourses == count;
    }


    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count = 0;

        for (int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList();

        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                count++;
            }
        }

        while (queue.size() != 0) {
            int course = (int) queue.poll();
            for (int i = 0; i < graph[course].size(); i++) {
                int pointer = (int) graph[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if (count == numCourses)
            return true;
        else
            return false;
    }


    public boolean canFinish4(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList();

        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, i))
                return false;
        }
        return true;
    }

    private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
        if (visited[course])
            return false;
        else
            visited[course] = true;
        ;

        for (int i = 0; i < graph[course].size(); i++) {
            if (!dfs(graph, visited, (int) graph[course].get(i)))
                return false;
        }
        visited[course] = false;
        return true;
    }


    public static boolean canFinish5(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0)
            return false;
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][1]]++;
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int i = 0; i < prerequisites.length; i++) {
                if (x == prerequisites[i][0]) {
                    inDegree[prerequisites[i][1]]--;
                    if (inDegree[prerequisites[i][1]] == 0)
                        queue.offer(prerequisites[i][1]);
                }
            }
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] != 0)
                return false;
        }
        return true;
    }

    //My Solutions
    class Node {
        int val;
        List<Node> list;
        int indegree = 0;

        public Node(int val) {
            this.val = val;
            list = new ArrayList<Node>();
        }
    }

    // Kahn's Algo - BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Node[] graph = new Node[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new Node(i);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int[] val = prerequisites[i];
            graph[val[1]].list.add(graph[val[0]]);
            graph[val[0]].indegree++;
        }
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (graph[i].indegree == 0) queue.add(graph[i]);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            List<Node> list = node.list;
            for (Node node1 : list) {
                if (--node1.indegree == 0)
                    queue.add(node1);
            }
            count++;
        }
        return numCourses == count;
    }

    //DFS, detect a cycle in directed graph
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        Node[] graph = new Node[numCourses];
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new Node(i);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int[] val = prerequisites[i];
            graph[val[1]].list.add(graph[val[0]]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i])
                if (isCyclicUtil(graph, visited, recStack, i)) return false;
        }
        return true;
    }

    public boolean isCyclicUtil(Node[] graph, boolean[] visited, boolean[] recStack, int j) {
        visited[j] = true;
        recStack[j] = true;
        List<Node> list = graph[j].list;
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if (recStack[node.val]) return true;
            if (!visited[node.val]) {
                if (isCyclicUtil(graph, visited, recStack, node.val)) return true;
            }
        }
        recStack[j] = false;
        return false;
    }
}
