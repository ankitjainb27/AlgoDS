package graph.topologicalsort;

import graph.graph.DirectedGraph;

import java.util.*;

/**
 * Date 24/07/17
 *
 * @author Ankit Jain
 */
public class TSKahnMethod {
    public static void main(String[] args) {
        TSKahnMethod tsKahnMethod = new TSKahnMethod();
        DirectedGraph graph = new DirectedGraph(5);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        tsKahnMethod.printTopologicalSort(graph);
    }

    private void printTopologicalSort(DirectedGraph graph) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> set = graph.getVertexes();
        HashMap<Integer, Integer> outDegree = graph.getOutDegreeMap();
        HashMap<Integer, List<Integer>> map = graph.getMap();
        for (Integer i : set)
            if (!outDegree.containsKey(i)) queue.add(i);
        int count = 0;
        while (!queue.isEmpty()) {
            Integer val = queue.poll();
            System.out.println(val);
            List<Integer> list = map.get(val);
            if(list != null){
                for (Integer j : list) {
                    if (outDegree.containsKey(j)) {
                        if (outDegree.get(j) == 1) {
                            queue.add(j);
                            outDegree.remove(j);
                        } else outDegree.put(j, outDegree.get(j) - 1);
                    }
                }
            }
            count++;
        }
        if(count != set.size()) System.out.println("Loop");
    }
}
