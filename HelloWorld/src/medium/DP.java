package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DP {
    public static void main(String[] args){

    }

    //5. ������Ӵ������ܽ�s reverse ����������У���Ӧ����reverse ֮������Ӵ������ִ���ԭʼ�±�һ�£�������abcdghcba,abc����������������Ӵ�
    public String longestPalindrome(String s) {
        return "";
    }

    //133. ��¡ͼ

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);
        for(Node neighbor: node.neighbors){
            if(map.containsKey(neighbor))
                clone.neighbors.add(map.get(neighbor));
            else{
                Node newNeighbor = cloneGraph(neighbor);
                clone.neighbors.add(newNeighbor);
            }
        }
        return clone;
    }

}
