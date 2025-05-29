package test;//package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Node {
    private String name;
    private List<Node> edges;
    private Message msg;

    public Node(String name) {
        this.name = name;
        edges = new ArrayList<Node>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Node> getEdges() {
        return edges;
    }
    public void setEdges(List<Node> edges) {
        this.edges = edges;
    }
    public Message getMsg() {
        return msg;
    }
    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public void addEdge(Node added) {
        edges.add(added);
    }
    public boolean hasCycles() {
            Set<Node> visited = new HashSet<>();
            Set<Node> path = new HashSet<>();
            return dfs(this, visited, path);
        }

        private static boolean dfs(Node node, Set<Node> visited, Set<Node> path) {
            if (path.contains(node)) {
                return true;
            }

            if (visited.contains(node)) {
                return false;
            }
            visited.add(node);
            path.add(node);

            for (Node edge : node.getEdges()) {
                if (dfs(edge, visited, path)) {
                    return true;
                }
            }
            // הסרת האובייקט מהמסלול הנוכחי
            path.remove(node);
            return false;
        }


}