package graph;

import java.util.*;

/**
 * Created on 21/08/17 at 1:08 PM
 *
 * @author Ankit Jain
 */
public class CloneGraph {
    public UndirectedGraphNode cloneGraph1(UndirectedGraphNode node) {
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
        if (node == null) return null;
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(node);
        UndirectedGraphNode node2 = new UndirectedGraphNode(node.label);
        UndirectedGraphNode node1;
        map.put(node.label, node2);
        while (!queue.isEmpty()) {
            node = queue.poll();
            node1 = map.get(node.label);
            for (UndirectedGraphNode ugn : node.neighbors) {
                if (!map.containsKey(ugn.label)) {
                    map.put(ugn.label, new UndirectedGraphNode(ugn.label));
                    queue.add(ugn);
                }
                node1.neighbors.add(map.get(ugn.label));
            }
        }
        return node2;
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
        if (node == null) return null;
        UndirectedGraphNode node2 = new UndirectedGraphNode(node.label);
        map.put(node.label, node2);
        dfs(map, node);
        return node2;
    }

    private void dfs(HashMap<Integer, UndirectedGraphNode> map, UndirectedGraphNode node) {
        UndirectedGraphNode node1 = map.get(node.label);
        for (UndirectedGraphNode ugn : node.neighbors) {
            if (!map.containsKey(ugn.label)) {
                map.put(ugn.label, new UndirectedGraphNode(ugn.label));
                dfs(map, ugn);
            }
            node1.neighbors.add(map.get(ugn.label));
        }
    }
}

class UndirectedGraphNode {
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  }
