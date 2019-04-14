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

    //516. 最长回文子序列
    public int longestPalindromeSubseq(String s) {
        int ans = 0;
        int size = s.length();
        String s2 = new StringBuffer(s).reverse().toString();

        //dp[i+1][j+1]表示s的从0开始到下标i截止 与s2从0开始到下标j截止，最长子序列个数
        //如果不等，则dp[i]ij] = max(dp[i-1][j], dp[i][j-1])；如果相等则dp[i][j] = dp[i-1][j-1]
        int[][] dp = new int[size+1][size+1];//留了第一行和第一列全为0

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

    //300. 最长上升子序列
    public int lengthOfLIS(int[] nums) {
        int size = nums.length;
        int ans = 0;
        /*动态规划的思路：将 dp 数组定义为：以 nums[i] 结尾的最长上升子序列的长度
        那么题目要求的，就是这个 dp 数组中的最大者
        以数组  [10, 9, 2, 5, 3, 7, 101, 18] 为例：
        dp 的值： 1  1  1  2  2  3  4    4*/

    /*    1、子序列：不要求连续子序列，只要保证元素前后顺序一致即可；

        2、上升：这里的“上升”是“严格上升”，类似于 [2, 3, 3, 6, 7] 这样的子序列是不符合要求的；

        一个序列可能有多个最长上升子序列，题目中只要我们求这个最长的长度。如果使用回溯搜索，选择所有的子序列进行判断，时间复杂度为 $O( (2^n) * n )$。

        定义状态：LIS(i) 表示以第 i 个数字为结尾的最长上升子序列的长度。即在 [0, ..., i] 的范围内，选择以数字 nums[i] 结尾可以获得的最长上升子序列的长度。关键字是：以第 i 个数字为结尾，即我们要求 nums[i] 必须被选取。反正一个子序列一定要以一个数字结尾，那我就将状态这么定义，这一点是重要且常见的。

        状态转移方程：遍历到索引是 i 的数的时候，我们应该把索引是 [0, ... ,i - 1] 的 LIS 都看一遍，如果当前的数 nums[i] 大于之前的某个数，那么 nums[i] 就可以接在这个数后面形成一个更长的 LIS 。把前面的 i 个数都看了， LIS[i] 就是它们的最大值加 $1$。即比当前数要小的那些里头，找最大的，然后加 $1$ 。

        状态转移方程即：LIS(i) = max( 1 + LIS(j) if j < i and nums[i] > nums[j])
        最后不要忘了，应该扫描一遍这个 LIS[i] 数组，其中最大的就是我们所求的。因为这个并不是最后一个就是最大的！！
        比如[1,3,6,7,9,4,10,5,6]，dp[4] = 5就大于dp[5] = 3*/
        int[] dp = new int[size];
        //初始化，无论如何只要数组长度>0，子序列至少为1
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

    //712. 两个字符串的最小ASCII删除和
    public int minimumDeleteSum(String s1, String s2) {
        int ans = 0;
        int row = s1.length();
        int col = s2.length();

        //使用dp数组存储数组。行存储s2，列存储s1。
        //第一行代表s2和空字符串比，需要去除的最小ASCII值
        //第一列代表s1和空字符串比，需要去除的最小ASCII值。
        //dp[i+1][j+1]代表s1[0,i]和s2[0,j]需要删除的ASCII和
        int[][] dp = new int[row+1][col+1];

        //第一行和第一列是留出来的，既然为0就不用管了，因为默认初始化为0。真正的计算从下标1开始，先初始化
        for(int i = 1; i<row+1; i++){
            dp[i][0] = dp[i-1][0]+(int)s1.charAt(i-1); //因为是ASCII码，且字符有大小写，所以最好不用减去大小写'a' 'A'的方法
        }

        //因为s1和s2的长度可能不一样，所以不能放在一个循环里初始化
        for(int j = 1; j<col+1; j++){
            dp[0][j] = dp[0][j-1]+(int)s2.charAt(j-1);
        }


        for(int i = 1; i<row+1; i++){
            for(int j = 1; j<col+1; j++){
                if(s1.charAt(i-1)!=s2.charAt(j-1)){
                    //从两个删除方案（删掉s1的字符或s2的字符）中找最小的那一个
                    dp[i][j] = Integer.min(dp[i-1][j]+(int)s1.charAt(i-1), dp[i][j-1]+(int)s2.charAt(j-1));
                }else{
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        ans = dp[row][col];
        return ans;
    }

    //650. 只有两个键的键盘
    //n>1时 其实就是将n分解为m个数字的乘积 且m个数字的和最小 即把一个数分解为n个质数的和 从小到大的去试探
   /* 如果这个数是质数 则这个数只能一个一个的复制得到 操作步数就是这个数本身 如果不是质数 则可以由复制之前得到
    例如20可以由10复制得到 10可以由5复制得到 而5是质数 只能一个一个复制 所以minStep （20） = 9，相当于是求公因数。
    比如20的公因数就是2 2 5，相加即为9*/
    //可以用DP，这里用的就是DP
    public int minSteps(int n) {
        int ans = 0;
        int[] dp = new int[n+1];
        dp[1] = 0;
        for(int i = 2; i<=n; i++){
            //最多的方法：从1开始依次copy，所以dp[i]最大为i
            dp[i] = i;
            for(int j = 2; j<i; j++){
                if(i % j == 0){
                    //i-j的意思是现在已经有j了，所以要减去，需要再粘贴（i-j）/j
                    //个j，然后+1是加上copyAll的这一次
                    dp[i] = Integer.min(dp[i], dp[j]+(i-j) / j +1);
                }
            }
        }
        ans = dp[n];
        return ans;
    }

    //139. 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean ans;
        //dp[i]表示以i-1为下标的字符为结尾的前i个字符是否能在其中找到
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;//boolean数组的默认值是false
        for(int i = 1; i<=s.length(); i++){
            for(int j = 0; j<i; j++){
                if(dp[j] && wordDict.contains(s.substring(j, i))){//因为s.substring(i,j)是个蛇精病= =
                    dp[i] = true;
                }
            }
        }
        ans = dp[s.length()];
        return ans;
    }

    //98. 验证二叉搜索树 dfs
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

    //430. 扁平化多级双向链表 dfs
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


    //375. 猜数字大小 II
    public int getMoneyAmount(int n) {
        int ans = 0;
        //dp[i][j]表示从数字i到j之间猜中任意一个数字最少需要花费的钱数
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


    //96. 不同的二叉搜索树
    public int numTrees(int n) {
        int ans = 0;
        if(n<=1)
            return 1;
        //dp[i]代表 1 ... i-1 为节点组成的二叉搜索树的数量
/*        假设n个节点存在二叉排序树的个数是G(n)，
        1为根节点，2为根节点，...，n为根节点，
        当1为根节点时，其左子树节点个数为0，右子树节点个数为n-1，
        同理当2为根节点时，其左子树节点个数为1，右子树节点为n-2，
        所以可得G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)*/
        //所以初始化dp[0] = 1，就是因为左子树结点数为0时，只需要算右子树的dp[n-1]，算是总的一种情况
        //dp[n] = dp[0]*dp[n-1]+dp[1]*dp[n-2]+...+dp[n-1]*dp[0]
        int dp[] = new int[n+1];
        //初始化
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


    //207. 课程表
    //拓扑排序
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int count = 0;
        if(numCourses == 0)
            return true;
        //先把所有课程的入度初始化好
        int[] inDegree = new int[numCourses];
        for(int[] prerequisite: prerequisites){
            inDegree[prerequisite[1]]++;
        }
        //把入度为0的结点入栈
        Stack stack = new Stack();
        for(int i = 0; i<numCourses; i++){
            if(inDegree[i]==0)
                stack.push(i);
        }

        //依次把入度为0的结点加入课程表，课程数+1
        while(!stack.empty()){
            int course = (int)stack.pop();
            count++;
            //依次将该结点对应的头结点的出度-1
            for(int[] prerequisite: prerequisites){
                if(prerequisite[0] == course){
                    inDegree[prerequisite[1]] -- ;
                    //如果该头结点的入度为0，则将之加入栈
                    if(inDegree[prerequisite[1]] == 0)
                        stack.push(prerequisite[1]);
                }
            }
        }

        return count==numCourses;
    }
}
