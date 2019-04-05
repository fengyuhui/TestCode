package medium;

import java.util.*;

public class DP_Medium_NotComlete {
    public static void main(String[] args){

    }

    //5. 最长回文子串，不能将s reverse 再求最长子序列！！应该是reverse 之后求最长子串，且字串的原始下标一致！！比如abcdghcba,abc并不是它的最长回文子串
    public String longestPalindrome(String s) {
        return "";
    }

    //133. 克隆图

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

    //787. K 站中转内最便宜的航班，最多中转k站，直达不算中转哟~ 典型的动态规划了
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int ans = 0;

        //先初始化dp数组
        int[][] dp = new int[n][K+1];
        for(int i = 0; i<n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        //目的地是源地址就不用转机了
        Arrays.fill(dp[src],0);

        //把直达的直接填进去
        for(int[] flight: flights){
            int s = flight[0];
            int d = flight[1];
            int f = flight[2];
            if(s == src)
                dp[d][0] = f;
        }

        for(int i = 1; i<K+1; i++){
            for(int[] flight: flights){//这种循环方式还挺方便的
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

    //873. 最长的斐波那契子序列的长度，子序列哦，不是子串
    /*我们假设dp[i][j]是以A【i】与A【j】结尾的数列（因为默认了Ai<Aj）。
    所以对于类似......A[i],A[j]这个数列如果有A[k]==A[i]+A[j]，那么这数列就变成了.....A[i],A[j],A[k].
    dp[j][k]=dp[i][j]+1
    上述式子便是转移方程。
    我们考虑初始化的情况：对于dp[i][i]意思是两个相同的数字做结尾，是不存在的！因此dp[i][i]=0
    而其他的情况，只要处于两个不同的式子，那么长度就是2.所以dp【i】【j】=2*/
    public int lenLongestFibSubseq(int[] A) {
        int ans = 0;
        int[][] dp = new int[A.length][A.length];

        //初始化dp[i][j]为2
        for(int i = 0; i<A.length; i++){
            Arrays.fill(dp[i], 2);
        }

        //初始化dp[i][i]
        for(int i = 0; i<A.length; i++){
            dp[i][i] = 0;
        }

        //初始化map
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
