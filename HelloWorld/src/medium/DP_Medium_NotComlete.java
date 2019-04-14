package medium;

import java.util.*;

class Node2 {
    public int val;
    public Node2 prev;
    public Node2 next;
    public Node2 child;

    public Node2() {}

    public Node2(int _val,Node2 _prev,Node2 _next,Node2 _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
}

public class DP_Medium_NotComlete {
    public static void main(String[] args){
        DP_Medium_NotComlete main = new DP_Medium_NotComlete();
        int p[][] = {{1,0}};
        main.canFinish(2,p);
        int[] a = {10,9,2,5,3,7,101,18};
        //main.lengthOfLIS(a);
        String s = "leetcode";
        List list = new ArrayList();
        list.add("leet");
        list.add("code");
        main.wordBreak(s,list);
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

    //650. ֻ���������ļ���
    //n>1ʱ ��ʵ���ǽ�n�ֽ�Ϊm�����ֵĳ˻� ��m�����ֵĺ���С ����һ�����ֽ�Ϊn�������ĺ� ��С�����ȥ��̽
   /* �������������� �������ֻ��һ��һ���ĸ��Ƶõ� ��������������������� ����������� ������ɸ���֮ǰ�õ�
    ����20������10���Ƶõ� 10������5���Ƶõ� ��5������ ֻ��һ��һ������ ����minStep ��20�� = 9���൱������������
    ����20�Ĺ���������2 2 5����Ӽ�Ϊ9*/
    //������DP�������õľ���DP
    public int minSteps(int n) {
        int ans = 0;
        int[] dp = new int[n+1];
        dp[1] = 0;
        for(int i = 2; i<=n; i++){
            //���ķ�������1��ʼ����copy������dp[i]���Ϊi
            dp[i] = i;
            for(int j = 2; j<i; j++){
                if(i % j == 0){
                    //i-j����˼�������Ѿ���j�ˣ�����Ҫ��ȥ����Ҫ��ճ����i-j��/j
                    //��j��Ȼ��+1�Ǽ���copyAll����һ��
                    dp[i] = Integer.min(dp[i], dp[j]+(i-j) / j +1);
                }
            }
        }
        ans = dp[n];
        return ans;
    }

    //139. ���ʲ��
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean ans;
        //dp[i]��ʾ��i-1Ϊ�±���ַ�Ϊ��β��ǰi���ַ��Ƿ����������ҵ�
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;//boolean�����Ĭ��ֵ��false
        for(int i = 1; i<=s.length(); i++){
            for(int j = 0; j<i; j++){
                if(dp[j] && wordDict.contains(s.substring(j, i))){//��Ϊs.substring(i,j)�Ǹ��߾���= =
                    dp[i] = true;
                }
            }
        }
        ans = dp[s.length()];
        return ans;
    }

    //98. ��֤���������� dfs
    int last =Integer.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        Integer max = null;
        Integer min = null;
        if(root == null)
            return true;
        return ds(root, min, max);

    }
    public boolean ds(TreeNode root,Integer min, Integer max){
        if(root == null ){
            return true;
        }
        if(min != null && min >= root.val){
            return false;
        }
        if(max != null && max <= root.val ){
            return false;
        }
        return ds(root.left, min, root.val) && ds(root.right, root.val, max);
    }

    //430. ��ƽ���༶˫������ dfs
    public Node2 flatten(Node2 head) {
        if(head == null){
            return head;
        }
        dfsFlattenHelper(head);
        return head;
    }

    public Node2 dfsFlattenHelper(Node2 head){
        Node2 pt = head;
        Node2 tail;
        while(pt!=null){
            if(pt.child!=null) {
                tail = dfsFlattenHelper(pt.child);
                if(pt.next!=null) {
                    pt.next.prev = tail;
                    tail.next = pt.next;
                    pt.next = pt.child;
                    pt.child.prev = pt;
                    pt.child = null;
                    pt = tail;
                }else{
                    pt.next = pt.child;
                    pt.child.prev = pt;
                    pt.child = null;
                    pt = pt.next;
                }
            }else if(pt.next!=null){
                pt = pt.next;
            }else
                break;
        }
        return pt;
    }


    //375. �����ִ�С II
    public int getMoneyAmount(int n) {
        int ans = 0;
        //dp[i][j]��ʾ������i��j֮���������һ������������Ҫ���ѵ�Ǯ��
        int[][] dp = new int[n+1][n+1];
        for(int i = 2; i<n+1; i++){
            for(int j = i-1; j>=0; j--){

                int localMax = Integer.MAX_VALUE;
                if(j == i-1){
                    dp[i][j] = j;
                    continue;
                }
                for(int k = j+1; k<i; k++){

                }
            }
        }
        return ans;
    }


    //96. ��ͬ�Ķ���������
    public int numTrees(int n) {
        int ans = 0;
        if(n<=1)
            return 1;
        //dp[i]���� 1 ... i-1 Ϊ�ڵ���ɵĶ���������������
/*        ����n���ڵ���ڶ����������ĸ�����G(n)��
        1Ϊ���ڵ㣬2Ϊ���ڵ㣬...��nΪ���ڵ㣬
        ��1Ϊ���ڵ�ʱ�����������ڵ����Ϊ0���������ڵ����Ϊn-1��
        ͬ��2Ϊ���ڵ�ʱ�����������ڵ����Ϊ1���������ڵ�Ϊn-2��
        ���Կɵ�G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)*/
        //���Գ�ʼ��dp[0] = 1��������Ϊ�����������Ϊ0ʱ��ֻ��Ҫ����������dp[n-1]�������ܵ�һ�����
        //dp[n] = dp[0]*dp[n-1]+dp[1]*dp[n-2]+...+dp[n-1]*dp[0]
        int dp[] = new int[n+1];
        //��ʼ��
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 1; i<=n; i++){
            int count = 0;
            for(int j = 1; j<=i; j++){
                count += dp[j-1] * dp[i-j];
            }
            dp[i] = count;
        }
        ans = dp[n];
        return ans;
    }


    //207. �γ̱�
    //��������
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int count = 0;
        if(numCourses == 0)
            return true;
        //�Ȱ����пγ̵���ȳ�ʼ����
        int[] inDegree = new int[numCourses];
        for(int[] prerequisite: prerequisites){
            inDegree[prerequisite[1]]++;
        }
        //�����Ϊ0�Ľ����ջ
        Stack stack = new Stack();
        for(int i = 0; i<numCourses; i++){
            if(inDegree[i]==0)
                stack.push(i);
        }

        //���ΰ����Ϊ0�Ľ�����γ̱��γ���+1
        while(!stack.empty()){
            int course = (int)stack.pop();
            count++;
            //���ν��ý���Ӧ��ͷ���ĳ���-1
            for(int[] prerequisite: prerequisites){
                if(prerequisite[0] == course){
                    inDegree[prerequisite[1]] -- ;
                    //�����ͷ�������Ϊ0����֮����ջ
                    if(inDegree[prerequisite[1]] == 0)
                        stack.push(prerequisite[1]);
                }
            }
        }

        return count==numCourses;
    }
}
