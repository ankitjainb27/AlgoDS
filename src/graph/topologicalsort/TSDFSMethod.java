package graph.topologicalsort;

import graph.graph.DirectedGraph;

import java.util.*;

/**
 * Date 24/07/17
 *
 * @author Ankit Jain
 */
public class TSDFSMethod {
    public static void main(String[] args) {
        TSDFSMethod tsdfsMethod = new TSDFSMethod();
        DirectedGraph graph = new DirectedGraph(5);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        tsdfsMethod.printTopologicalSort(graph);
    }

    private void printTopologicalSort(DirectedGraph graph) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        for (Integer i : graph.getMap().keySet()) {
            if (!visited.contains(i)) {
                tsUtil(graph.getMap(), stack, visited, i);
            }
        }
        while (!stack.isEmpty()) System.out.println(stack.pop());
    }

    private void tsUtil(HashMap<Integer, List<Integer>> map, Stack<Integer> stack, Set<Integer> visited, Integer i) {
        visited.add(i);
        if (map.containsKey(i)) {
            for (Integer j : map.get(i)) {
                if (!visited.contains(j))
                    tsUtil(map, stack, visited, j);
            }
        }
        stack.add(i);
    }
}
