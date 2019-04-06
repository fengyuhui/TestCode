package medium;

import java.util.*;

public class DP_Medium_NotComlete {
    public static void main(String[] args){
        DP_Medium_NotComlete main = new DP_Medium_NotComlete();
        int[] a = {10,9,2,5,3,7,101,18};
        main.lengthOfLIS(a);
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

    //516. �����������
    public int longestPalindromeSubseq(String s) {
        int ans = 0;
        int size = s.length();
        String s2 = new StringBuffer(s).reverse().toString();

        //dp[i+1][j+1]��ʾs�Ĵ�0��ʼ���±�i��ֹ ��s2��0��ʼ���±�j��ֹ��������и���
        //������ȣ���dp[i]ij] = max(dp[i-1][j], dp[i][j-1])����������dp[i][j] = dp[i-1][j-1]
        int[][] dp = new int[size+1][size+1];//���˵�һ�к͵�һ��ȫΪ0

        for(int i = 1; i<size+1; i++){
            for(int j = 1; j<size+1; j++){
                if(s.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Integer.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        ans = dp[size][size];
        return ans;
    }

    //300. �����������
    public int lengthOfLIS(int[] nums) {
        int size = nums.length;
        int ans = 0;
        /*��̬�滮��˼·���� dp ���鶨��Ϊ���� nums[i] ��β������������еĳ���
        ��ô��ĿҪ��ģ�������� dp �����е������
        ������  [10, 9, 2, 5, 3, 7, 101, 18] Ϊ����
        dp ��ֵ�� 1  1  1  2  2  3  4    4*/

    /*    1�������У���Ҫ�����������У�ֻҪ��֤Ԫ��ǰ��˳��һ�¼��ɣ�

        2������������ġ��������ǡ��ϸ��������������� [2, 3, 3, 6, 7] �������������ǲ�����Ҫ��ģ�

        һ�����п����ж������������У���Ŀ��ֻҪ�����������ĳ��ȡ����ʹ�û���������ѡ�����е������н����жϣ�ʱ�临�Ӷ�Ϊ $O( (2^n) * n )$��

        ����״̬��LIS(i) ��ʾ�Ե� i ������Ϊ��β������������еĳ��ȡ����� [0, ..., i] �ķ�Χ�ڣ�ѡ�������� nums[i] ��β���Ի�õ�����������еĳ��ȡ��ؼ����ǣ��Ե� i ������Ϊ��β��������Ҫ�� nums[i] ���뱻ѡȡ������һ��������һ��Ҫ��һ�����ֽ�β�����Ҿͽ�״̬��ô���壬��һ������Ҫ�ҳ����ġ�

        ״̬ת�Ʒ��̣������������� i ������ʱ������Ӧ�ð������� [0, ... ,i - 1] �� LIS ����һ�飬�����ǰ���� nums[i] ����֮ǰ��ĳ��������ô nums[i] �Ϳ��Խ�������������γ�һ�������� LIS ����ǰ��� i ���������ˣ� LIS[i] �������ǵ����ֵ�� $1$�����ȵ�ǰ��ҪС����Щ��ͷ�������ģ�Ȼ��� $1$ ��

        ״̬ת�Ʒ��̼���LIS(i) = max( 1 + LIS(j) if j < i and nums[i] > nums[j])
        ���Ҫ���ˣ�Ӧ��ɨ��һ����� LIS[i] ���飬�������ľ�����������ġ���Ϊ������������һ���������ģ���
        ����[1,3,6,7,9,4,10,5,6]��dp[4] = 5�ʹ���dp[5] = 3*/
        int[] dp = new int[size];
        //��ʼ�����������ֻҪ���鳤��>0������������Ϊ1
        Arrays.fill(dp, 1);
        if(size<2)
            return size;

        for(int i = 1; i<size; i++){
            for(int j = 0; j<i; j++){
                if(nums[i]>nums[j]){
                    dp[i] = Integer.max(dp[i], dp[j]+1);
                }
            }
        }
        ans = dp[0];
        for(int i = 0; i<size; i++){
            if(ans<dp[i])
                ans = dp[i];
        }
        return ans;
    }

    //712. �����ַ�������СASCIIɾ����
    public int minimumDeleteSum(String s1, String s2) {
        int ans = 0;
        int row = s1.length();
        int col = s2.length();

        //ʹ��dp����洢���顣�д洢s2���д洢s1��
        //��һ�д���s2�Ϳ��ַ����ȣ���Ҫȥ������СASCIIֵ
        //��һ�д���s1�Ϳ��ַ����ȣ���Ҫȥ������СASCIIֵ��
        //dp[i+1][j+1]����s1[0,i]��s2[0,j]��Ҫɾ����ASCII��
        int[][] dp = new int[row+1][col+1];

        //��һ�к͵�һ�����������ģ���ȻΪ0�Ͳ��ù��ˣ���ΪĬ�ϳ�ʼ��Ϊ0�������ļ�����±�1��ʼ���ȳ�ʼ��
        for(int i = 1; i<row+1; i++){
            dp[i][0] = dp[i-1][0]+(int)s1.charAt(i-1); //��Ϊ��ASCII�룬���ַ��д�Сд��������ò��ü�ȥ��Сд'a' 'A'�ķ���
        }

        //��Ϊs1��s2�ĳ��ȿ��ܲ�һ�������Բ��ܷ���һ��ѭ�����ʼ��
        for(int j = 1; j<col+1; j++){
            dp[0][j] = dp[0][j-1]+(int)s2.charAt(j-1);
        }


        for(int i = 1; i<row+1; i++){
            for(int j = 1; j<col+1; j++){
                if(s1.charAt(i-1)!=s2.charAt(j-1)){
                    //������ɾ��������ɾ��s1���ַ���s2���ַ���������С����һ��
                    dp[i][j] = Integer.min(dp[i-1][j]+(int)s1.charAt(i-1), dp[i][j-1]+(int)s2.charAt(j-1));
                }else{
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        ans = dp[row][col];
        return ans;
    }
}
