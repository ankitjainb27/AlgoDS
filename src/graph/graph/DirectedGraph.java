package graph.graph;

import java.util.*;

/**
 * Date 24/07/17
 *
 * @author Ankit Jain
 */
public class DirectedGraph {

    int vertex;
    HashMap<Integer, List<Integer>> map;
    HashMap<Integer, Integer> outDegreeMap;
    HashSet<Integer> vertexes;

    public DirectedGraph(int vertex) {
        this.vertex = vertex;
        map = new HashMap<>();
        outDegreeMap = new HashMap<>();
        vertexes = new HashSet<>();
    }

    public void addEdge(int start, int finish) {
        vertexes.add(start);
        vertexes.add(finish);
        if(outDegreeMap.containsKey(finish))
            outDegreeMap.put(finish,outDegreeMap.get(finish) + 1);
        else outDegreeMap.put(finish,1);
        if (!map.containsKey(start))
            map.put(start, new LinkedList<>());
        map.get(start).add(finish);
    }

    public Set<Integer> keySet() {
        return map.keySet();
    }

    public HashMap<Integer, List<Integer>> getMap() {
        return map;
    }

    public HashMap<Integer, Integer> getOutDegreeMap() {
        return outDegreeMap;
    }

    public HashSet<Integer> getVertexes() {
        return vertexes;
    }
}
