/*
// Definition for a Node.
class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

이거 이미 있다고 생각하고 clone 만 구현하는 문제 dfs로 deepcopy
*/
import java.util.*;

class Solution {
    Map<Node, Node> m = new HashMap<>();

    public Node cloneGraph(Node n) {
        if(n == null)
            return null;
        
        if(m.containsKey(n))
            return m.get(n);
        
        Node cpy = new Node(n.val);
        m.put(n, cpy);

        for(Node next : n.neighbors){
            cpy.neighbors.add(cloneGraph(next));
        }

        return cpy;
    }
}