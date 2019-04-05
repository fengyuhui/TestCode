package medium;

import java.util.*;

public class DP_Medium_NotComlete {
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

    //787. K վ��ת������˵ĺ��࣬�����תkվ��ֱ�ﲻ����תӴ~ ���͵Ķ�̬�滮��
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int ans = 0;

        //�ȳ�ʼ��dp����
        int[][] dp = new int[n][K+1];
        for(int i = 0; i<n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        //Ŀ�ĵ���Դ��ַ�Ͳ���ת����
        Arrays.fill(dp[src],0);

        //��ֱ���ֱ�����ȥ
        for(int[] flight: flights){
            int s = flight[0];
            int d = flight[1];
            int f = flight[2];
            if(s == src)
                dp[d][0] = f;
        }

        for(int i = 1; i<K+1; i++){
            for(int[] flight: flights){//����ѭ����ʽ��ͦ�����
                int s = flight[0];
                int d = flight[1];
                int f = flight[2];
                if(dp[s][i-1]!=Integer.MAX_VALUE) {
                    dp[d][i] = Math.min(dp[s][i - 1] + f, dp[d][i]);
                }
            }
        }

        ans = dp[dst][K] == Integer.MAX_VALUE?-1:dp[dst][K];
        return ans;
    }

    //873. ���쳲����������еĳ��ȣ�������Ŷ�������Ӵ�
    /*���Ǽ���dp[i][j]����A��i����A��j����β�����У���ΪĬ����Ai<Aj����
    ���Զ�������......A[i],A[j]������������A[k]==A[i]+A[j]����ô�����оͱ����.....A[i],A[j],A[k].
    dp[j][k]=dp[i][j]+1
    ����ʽ�ӱ���ת�Ʒ��̡�
    ���ǿ��ǳ�ʼ�������������dp[i][i]��˼��������ͬ����������β���ǲ����ڵģ����dp[i][i]=0
    �������������ֻҪ����������ͬ��ʽ�ӣ���ô���Ⱦ���2.����dp��i����j��=2*/
    public int lenLongestFibSubseq(int[] A) {
        int ans = 0;
        int[][] dp = new int[A.length][A.length];

        //��ʼ��dp[i][j]Ϊ2
        for(int i = 0; i<A.length; i++){
            Arrays.fill(dp[i], 2);
        }

        //��ʼ��dp[i][i]
        for(int i = 0; i<A.length; i++){
            dp[i][i] = 0;
        }

        //��ʼ��map
        Map map = new HashMap<Integer, Integer>();
        for(int i = 0; i<A.length; i++){
            map.put(A[i], i);
        }

        for(int j = 1; j<A.length; j++){
            for(int i = j-1; i>-1; i--){
                if(map.get(A[i]+A[j])!=null){
                    dp[j][(Integer)map.get(A[i]+A[j])] = Math.max(dp[j][(Integer)map.get(A[i]+A[j])], dp[i][j]+1);
                    ans = Math.max(ans, dp[j][(Integer)map.get(A[i]+A[j])]);
                    int temp = (Integer)map.get(A[i]+A[j]);
                    i = j;
                   j = temp;
                }
            }
        }

        return ans;
    }

}
