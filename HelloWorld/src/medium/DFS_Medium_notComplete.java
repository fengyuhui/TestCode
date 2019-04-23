package medium;

import com.sun.org.apache.regexp.internal.RE;

import java.lang.reflect.Array;
import java.util.*;

//二叉树，带一个next指针指向水平右侧的节点(默认是完美二叉树，即满二叉树）
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class DFS_Medium_notComplete {
    public static void main(String args[]){
        DFS_Medium_notComplete main = new DFS_Medium_notComplete();
        //System.out.println(main.isAdditiveNumber("199111992"));
        //char[][] board = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};

        //String n[] = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        //main.topKFrequent(n,4);

        int[] a1 = {1,1,2};
        int[] a2 = {1,2,3};

        main.kSmallestPairs(a1,a2,2);

        String s1 = "lrs";
        String s2 = "cxs";
        System.out.println(s1.compareTo(s2));

        int d = 1, b = 2;
        d = b = 0;
        System.out.println(d+""+b);
        int[] a = {3,5,2};
        Arrays.sort(a);
        ArrayList<Integer> c = new ArrayList<>();
        c.add(1);c.add(5);c.add(4);
        Collections.sort(c);

        String word = "AAB";
        //System.out.println(main.exist(board, word));

        String[][] tickets = {{"EZE","TIA"},{"EZE","HBA"},{"AXA","TIA"},{"JFK","AXA"},{"ANU","JFK"},{"ADL","ANU"},{"TIA","AUA"},{"ANU","AUA"},{"ADL","EZE"},{"ADL","EZE"},{"EZE","ADL"},{"AXA","EZE"},{"AUA","AXA"},{"JFK","AXA"},{"AXA","AUA"},{"AUA","ADL"},{"ANU","EZE"},{"TIA","ADL"},{"EZE","ANU"},{"AUA","ANU"}};

        //System.out.println(main.findItinerary(tickets));

        int[][] grid = {{0,1}};
        //System.out.println(main.maxAreaOfIsland(grid));
        char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
        int click[] ={3,0};
        main.updateBoard(board, click);
    }

    //306. 累加数
    public boolean isAdditiveNumber(String num) {
        boolean ans = false;
/*        数字字符串加法，DFS。其实一开始选择好两个相加的字符串就已经能确定，它们是否能够一直加到最后。

        假设对于原始的字符串num，长度是size.
                我们选择str1=num.substr(0,len1)，str2 = num.substr(len1,len2)。满足str1.size()+str2.size()<num.size()。很容易发现len1=[1,size/2],len2=[1,size/2]为两个长度的取值范围，都是闭区间。
        选择一个len1,len2组合，剩余字符串为str3 = num.substr(len1+len2,size-len1-len2)。深度搜索DFS(str1,str2,str3)。
        为了处理溢出的过大的整数，不采用转整数的方式，而是采取字符串相加的原则。add(str1,str2)为两个数字字符串相加的结果。
        如果add(str1,str2)==str3，那么return true;否则至少str3的其中一个前缀是add(str1,str2),继续搜索下一步搜索。其余都可以return false。
        如果找到一个合适的组合，return true，如果最终发现len1,len2这个组合不行，更改组合，重复3-5。如果所有组合都不行，那么最后return false。
        注意事项：

        累加项不以0开头，实质上不严格。如果只是不以0开头，那么0必然不存在，事实并不是。严格的逻辑是除0以外，不以0开头*/
        int size = num.length();
        if(size<2){
            return ans;
        }

        if(size == 3){
            if(num.charAt(2) - '0' == num.charAt(0) - '0'+num.charAt(1) - '0'){
                return true;
            }else{
                return false;
            }
        }

        String s1, s2, s3;
        for(int len1 = 1; len1<=size / 2; len1++){
            s1 = num.substring(0, len1);
            for(int len2 = 1; len2<=size / 2; len2++){
                s2 = num.substring(len1, len1+len2);
                s3 = num.substring(len1+len2, size);
                if(addDFS(s1, s2, s3)){
                    return true;
                }
            }
        }

        return ans;
    }

    boolean addDFS(String first, String second, String last){
        boolean ans = false;
        if(first.charAt(0)=='0' && !"0".equals(first)  || second.charAt(0)=='0' && !"0".equals(second)){
            return ans;
        }else{
            String temp = add(first, second);
            if(temp.equals(last))
                return true;
            if(temp.length()>last.length())
                return false;
            else {
                if(!temp.equals(last.substring(0, temp.length()))){
                    return false;
                }else{
                    //之前符合条件，再次进行深度遍历
                    first = second;
                    second = temp;
                    last = last.substring(temp.length(), last.length());
                    return addDFS(first, second, last);
                }
            }
        }
    }

    String add(String first, String second){//为了避免大数溢出，使用字符串的相加
        int sgn = 0;//进位
        //先解决两个字符串长度不一致的问题
        int size1 = first.length();
        int size2 = second.length();
        if(size1<size2){//交换位置
            String temp = first;
            first = second;
            second = temp;
            int temp1 = size1;
            size1 = size2;
            size2 = temp1;
        }
        if(size1 != size2){
            for(int i = 0; i<size1-size2; i++){
                second = "0"+second;
            }
        }
        String ans = "";
        for(int i= size1-1; i>-1;  i--){//这里size1 = size2了
            int num = sgn+first.charAt(i) - '0'+second.charAt(i) - '0';
            //考虑进位
            sgn = num / 10;
            num = num % 10;
            ans = num + ans;
        }

        if(sgn!=0){
            ans = sgn+ans;
        }
        return ans;
    }

    //79. 单词搜索
    public boolean exist(char[][] board, String word) {
        boolean ans = false;
        int size = word.length();
        int row = board.length;
        int col = board[0].length;
        int [][] visited = new int[row][col];//判断是否已经访问过
        if(size==0)
            return true;
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                if(dfsSearch(0, word,board,visited,i,j))
                    return true;
            }
        }
        return ans;
    }

    public boolean dfsSearch(int index, String word, char[][] board, int[][] visited, int row, int col){
        if(row<0 || col<0 || row>=board.length || col>=board[0].length || board[row][col] != word.charAt(index))
            return false;
        if(visited[row][col] == 0) {
            visited[row][col] = 1;
            index++;
            if (index == word.length())
                return true;

            if (dfsSearch(index, word, board, visited, row + 1, col) ||
                    dfsSearch(index, word, board, visited, row - 1, col) ||
                    dfsSearch(index, word, board, visited, row, col + 1) ||
                    dfsSearch(index, word, board, visited, row, col - 1))
                return true;
            visited[row][col] = 0;
        }
        return false;
    }

    //332. 重新安排行程
    public List<String> findItinerary(String[][] tickets) {
        List<String> ans = new ArrayList<>();
        //PS 每次矩阵都可以用for(str[]: strs)来循环，非常方便
        //建图！！！！
        Map<String,ArrayList> map = new HashMap<>();
        for(String[] ticket: tickets){
            if(!map.containsKey(ticket[0]))
                map.put(ticket[0], new ArrayList());
            map.get(ticket[0]).add(ticket[1]);
        }

        //遍历map，对路线进行字符自然排序
        for(String key:map.keySet()){
            //Collections和Arrays的sort方法都是升序排序
            Collections.sort(map.get(key));
        }

        //因为这是个有向图，所以当所有路线的城市节点都加进ans后（即结束遍历的条件），ans的节点数会=tickets.length+1，因为tickets是边
        HashSet<String> set = new HashSet<>();
        int len = tickets.length;
        dfsRoute(ans, map, len, set, "JFK", "");
        return ans;
    }

    public boolean dfsRoute(List<String> ans, Map<String, ArrayList> map, int len, HashSet<String> set, String currentPlace, String ticketNo){
        //先存下当前站的节点
        ans.add(currentPlace);
        //遍历结束条件
        if(ans.size() == len+1)
            return true;
        //如果这个站不是终点且没有票可以离开此站
        if(!map.containsKey(currentPlace)){
            int s = ans.size();
            ans.remove(s-1);//回溯，从路线中删除这个站
            set.remove(ticketNo);//回溯，将之前的票变为未使用状态。每条航线只有一张票(一笔画。站点可以重复去，但是航线不能重复)
            return false;
        }

        //遍历当前站点的所有目的地
        String dist = "";
        for(int i = 0; i<map.get(currentPlace).size(); i++){
            dist = (String)map.get(currentPlace).get(i);
            //如果票没有使用过
            if(!set.contains(currentPlace+"@"+i)){
                set.add(currentPlace+"@"+i);
                if(dfsRoute(ans, map, len, set, dist, currentPlace+"@"+i)){
                    return true;
                }
            }
        }
        int s = ans.size();
        ans.remove(s-1);//回溯，从路线中删除这个站
        set.remove(ticketNo);//回溯，将之前的票变为未使用状态。
        return false;
    }

    //695. 岛屿的最大面积
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        int row = grid.length;
        int col = grid[0].length;
        int[][] visited = new int[row][col];

        int temp = 0;
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                temp = dfsArea(row,col,grid, i, j, visited);
                ans = ans>temp?ans:temp;
            }
        }
        return ans;
    }

    public int dfsArea(int row, int col, int[][] grid, int i, int j,  int[][] visited){
        int ans = 0;
        if(i>=row || j>=col || i< 0 || j<0 || grid[i][j] == 0)
            return 0;
        if(visited[i][j] == 1)
            return 0;
        visited[i][j] = 1;
        ans = 1 +dfsArea(row, col, grid, i, j+1,visited)
        +dfsArea(row, col, grid, i+1, j, visited)
        +dfsArea(row, col, grid, i-1, j,visited)
        +dfsArea(row, col, grid, i, j-1, visited);
        return ans;
    }

    //529. 扫雷游戏
    public char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0], j = click[1];
        int row = board.length;
        int col = board[0].length;
        boolean visited[][] = new boolean[row][col];

        int count = 0;
        if(board[i][j] == 'M')
            board[i][j] = 'X';
        else  if(board[i][j] == 'E'){
            board = dfsBoard(board, i, j, visited);
        }
        return board;
    }

    public char[][] dfsBoard(char[][] board, int x, int y, boolean[][] visited){
        //八个相邻格子
        int count = 0;
        if(visited[x][y]){
            return board;
        }

        visited[x][y] = true;
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        }
        int[][] surrounder = {{0,1}, {0,-1}, {1,0}, {-1, 0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        for(int[] s:surrounder){
            if(x+s[0]>=board.length || y+s[1]>=board[0].length || x+s[0]<0 || y+s[1]<0){
                continue;
            }else{
                if(board[x+s[0]][ y+s[1]] == 'M'){
                    count++;
                }
            }
        }
        if(count!=0) {
            board[x][y] = String.valueOf(count).charAt(0);
        }else{
            board[x][y] = 'B';
            for(int[] s:surrounder){
                if(x+s[0]>=board.length || y+s[1]>=board[0].length || x+s[0]<0 || y+s[1]<0){
                    continue;
                }else{
                    dfsBoard(board, x+s[0], y+s[1], visited);
                }
            }
        }
        return board;
    }


    //547. 朋友圈
    //典型dfs
    public int findCircleNum(int[][] M) {
        int ans = 0;
        if(M.length == 0)
            return 0;
        boolean [] visited = new boolean[M.length];

        for(int i = 0; i<M.length; i++){
            if(!visited[i]){
                dfsCircle(visited, M, i);
                ans++;
            }
        }
        return ans;
    }

    public void dfsCircle(boolean[] visited, int[][] M, int i){
        for(int j = 0; j<M.length; j++){
            if(M[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfsCircle(visited,M,j);
            }
        }
    }

    //200. 岛屿的个数，经典dfs
    public int numIslands(char[][] grid) {
        int ans = 0;
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                if(grid[i][j] == '1'){
                    ans++;
                    dfsIslands(grid, i, j);
                }
            }
        }

        return ans;
    }
    public void dfsIslands(char[][] grid, int i, int j){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length)
            return;
        if(grid[i][j] == '1'){
            grid[i][j] = '0';
            dfsIslands(grid, i+1, j);
            dfsIslands(grid, i-1, j);
            dfsIslands(grid, i, j+1);
            dfsIslands(grid, i, j-1);
        }
    }

    //113. 路径总和 II 回溯法
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        if(root==null) return list;
        dfsPath(root,sum,list,li);
        return list;
    }
    public void dfsPath(TreeNode root,int sum,List<List<Integer>> list,List<Integer> li){
        li.add(root.val);
        if(root.left!=null)
            dfsPath(root.left,sum-root.val,list,li);
        if(root.right!=null)
            dfsPath(root.right,sum-root.val,list,li);
        if(root.left==null && root.right==null){
            if(sum==root.val)
                list.add(new ArrayList<Integer>(li));
        }
        //每返回一次，删掉一个节点
        li.remove(li.size()-1);
    }

    //129. 求根到叶子节点数字之和
    public int sumNumbers(TreeNode root) {
        int ans = 0;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if(root == null)
            return 0;
        dfsNumbers(root, list, temp);
        for(List<Integer> a:list){
            int all = 0;
            for(int val:a){
                all = all * 10 + val;
            }
            ans+=all;
        }
        return ans;
    }

    public void dfsNumbers(TreeNode root, List<List<Integer>> list, List<Integer> temp){
        temp.add(root.val);
        if(root.left!=null)
            dfsNumbers(root.left, list, temp);
        if(root.right!=null)
            dfsNumbers(root.right, list, temp);
        if(root.left == null && root.right == null)
            //必须是new ArrayList<Integer>(temp)而不是直接add（temp)，这样就是放了引用而已，temp.remove会删掉一切
            list.add(new ArrayList<Integer>(temp));
        temp.remove(temp.size() - 1);
    }

    //105. 从前序与中序遍历序列构造二叉树
    //preorder第一个元素为root，在inorder里面找到root，在它之前的为左子树（长l1），之后为右子树（长l2）。
    // preorder[1]到preorder[l1]为左子树,之后为右子树，分别递归。
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length<=0 || inorder.length <= 0)
            return null;
        if(preorder.length == 1)
            return new TreeNode(preorder[0]);

        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);

        for(int i = 0; i<inorder.length; i++){
            if(inorder[i] == rootVal){
                /*Arrays.copyOfRange(T[ ] original,int from,int to)

                将一个原始的数组original，从小标from开始复制，复制到小标to，生成一个新的数组。

                注意这里包括下标from，不包括上标to。*/
                root.left = buildTree(Arrays.copyOfRange(preorder, 1, i+1), Arrays.copyOfRange(inorder, 0,i));
                root.right = buildTree(Arrays.copyOfRange(preorder, i+1, preorder.length), Arrays.copyOfRange(inorder, i+1,inorder.length));
            }
        }


        return root;
    }


    //106. 从中序与后序遍历序列构造二叉树
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if(inorder.length == 0 || postorder.length == 0)
            return null;
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        if(inorder.length == 1)
            return root;
        int rootVal = postorder[postorder.length - 1];
        for(int i = 0; i<inorder.length; i++){
            if(inorder[i] == rootVal){
                root.left = buildTree2(Arrays.copyOfRange(inorder, 0, i), Arrays.copyOfRange(postorder, 0,i));
                root.right = buildTree2(Arrays.copyOfRange(inorder, i+1, inorder.length), Arrays.copyOfRange(postorder, i,postorder.length - 1));
            }
        }
        return root;
    }


    //109. 有序链表转换二叉搜索树, 类似上面两个创造树，使用快慢指针，一个单步走，一个双步走
    public TreeNode sortedListToBST(ListNode head) {
        TreeNode ans = dfsSorted(head, null);
        return ans;
    }
    public TreeNode dfsSorted(ListNode head, ListNode tail){
        if(head == tail)
            return null;
        ListNode fast = head;
        ListNode slow = head;

        //通过快慢指针找到中间节点
        while(fast!=tail && fast.next!=tail){
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = dfsSorted(head, slow);
        root.right = dfsSorted(slow.next, tail);
        return root;
    }

    //114. 二叉树展开为链表，就是个先序遍历
    //深度优先遍历
    //思想：先序遍历树，将节点插入到list中，再构造链表
    //注意：遍历返回之前要将节点的左右子节点为nul
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if(root == null)
            return;
        dfsFlatten(root, list);
        for(int i = 0; i<list.size() - 1; i++){
            list.get(i).right = list.get(i+1);
        }
    }
    public void dfsFlatten(TreeNode root, List<TreeNode> list){
        list.add(root);
        if(root.left!=null)
            dfsFlatten(root.left, list);
        if(root.right!=null)
            dfsFlatten(root.right, list);
        root.left = root.right = null;
    }


    //116. 填充每个节点的下一个右侧节点指针，要求常数空间，然而链表就是常数空间嘻嘻嘻
    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if(root == null)
            return root;
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            while (count!=0){
                Node temp = queue.poll();
                count--;
                if(count!=0){
                    temp.next = queue.peek();
                }
                if(temp.left!=null)
                    queue.offer(temp.left);
                if(temp.right!=null)
                    queue.offer(temp.right);
            }
        }
        return root;
    }

    //988. 从叶结点开始的最小字符串
    public String smallestFromLeaf(TreeNode root) {
        List<List<TreeNode>> list = new ArrayList<>();
        if(root == null)
            return "";
        List<TreeNode> node = new ArrayList<>();
        dfsSmall(root, list, node);

        List<String> str = new ArrayList<>();
        for(List<TreeNode> val: list){
            String temp = "";
            for(TreeNode s:val){
                temp+=(char)('a'+s.val);
            }
            str.add(new StringBuffer(temp).reverse().toString());
        }

        int size = str.size();
        if(size<2)
            return str.get(0);
        String ans = str.get(0);
        for(int i = 1; i<size; i++){
            if(ans.compareTo(str.get(i))>0) {
                ans = str.get(i);
            }
        }

        return ans;
    }
    public void dfsSmall(TreeNode root, List<List<TreeNode>> list, List<TreeNode> node){
        if(root!=null)
            node.add(root);
        if(root.left!=null)
            dfsSmall(root.left, list, node);
        if(root.right!=null)
            dfsSmall(root.right, list, node);
        if(root.left == null && root.right == null)
            //一定要新new一个ArrayList!!!!不然就只是一直指向同一个对象！！！！
            list.add(new ArrayList<>(node));
        node.remove(node.size() - 1);
    }


    //979. 在二叉树中分配硬币
    /*如果树的叶子仅包含 0 枚金币（与它所需相比，它的 过载量 为 -1），那么我们需要从它的父亲节点移动一枚金币到这个叶子节点上。
    如果说，一个叶子节点包含 4 枚金币（它的 过载量 为 3），那么我们需要将这个叶子节点中的 3 枚金币移动到别的地方去。
    总的来说，对于一个叶子节点，需要移动到它中或需要从它移动到它的父亲中的金币数量为 过载量 = Math.abs(num_coins - 1)。
    然后，在接下来的计算中，我们就再也不需要考虑这些已经考虑过的叶子节点了。*/
    int coins = 0;
    public int distributeCoins(TreeNode root) {
        dfsCoins(root);
        return coins;
    }
    public int dfsCoins(TreeNode root){
        if(root == null)
            return 0;
        int l = 0;
        int r = 0;
        if(root.left!=null){
            l = dfsCoins(root.left);
            coins+=Math.abs(l);
        }
        if(root.right!=null){
            r = dfsCoins(root.right);
            coins+=Math.abs(r);
        }
        //返回的是root节点的过载量，如果子树过载量是负数，需要root交硬币给子树，则root的过载量会变小，所以
        //不能用子树过载量的绝对值相加！！
        return l+r+root.val - 1;
    }


    //971. 翻转二叉树以匹配先序遍历
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> ans = new ArrayList<>();
        return ans;
    }

    //337. 打家劫舍 III
    public int rob(TreeNode root) {
        int ans = 0;
        return ans;
    }

    private class Element implements Comparable<Element>{
        int nums;
        int elemt;
        Element(int nums, int elemt){
            this.nums = nums;
            this.elemt = elemt;
        }
        //默认小顶堆，改写按照nums来排小顶堆，这是小顶堆哦！！
        @Override
        public int compareTo(Element o) {
            return this.nums - o.nums;
        }
    }


    //这是堆排序的内容，先放到这里了
    //347. 前K个高频元素
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        //这种类型的可以自己构造一个实现Comparable接口的类Element，存储值和key，并且重写compareTo方法
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Element> queue = new PriorityQueue<>();

        //把数组的频率放到map里
        for(int val:nums){
            if(map.containsKey(val)){
                map.put(val, map.get(val)+1);
            }else{
                map.put(val,1);
            }
        }

        //放入堆，大顶堆和小顶堆不要弄反了
        for(Integer key:map.keySet()){
            if(queue.size()<k){
                queue.offer(new Element(map.get(key), key));
            }else{
                if(queue.peek().nums<map.get(key)){
                    queue.poll();
                    queue.add(new Element(map.get(key), key));
                }
            }
        }

        //把堆中的数据拿到List中
        while(!queue.isEmpty()){
            ans.add(queue.poll().elemt);
        }


        //因为queue中是小顶堆（小顶堆中是前K个最大！！），所以要逆序来按从大到小排序
        Collections.reverse(ans);
        return ans;
    }


    private class StrElement implements Comparable<StrElement>{
        int nums;
        String elemt;
        StrElement(String elemt,int nums){
            this.nums = nums;
            this.elemt = elemt;
        }
        //默认小顶堆，改写按照nums来排小顶堆
        //因为还要求按照字母排序来排序，所以要考虑在频率相等时的排序l；又因为最后逆序，所以在这里要将相等的字母排序大的放在堆顶而不是排序小的
        @Override
        public int compareTo(StrElement o) {
            if(this.nums == o.nums){
                return o.elemt.compareTo(this.elemt);
            }else{
                return this.nums - o.nums;
            }
        }
    }

    //692. 前K个高频单词
    public List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<>();

        //和上面一题类似
        Map<String, Integer> map = new TreeMap<>();
        Queue<StrElement> queue = new PriorityQueue<>();

        //把String的频率放到map里
        for(String val:words){
            if(map.containsKey(val)){
                map.put(val, map.get(val)+1);
            }else{
                map.put(val,1);
            }
        }

        for(String key:map.keySet()){
            if(queue.size()<k){
                queue.offer(new StrElement(key, map.get(key)));
            }else{
                if(queue.peek().nums<map.get(key)){
                    queue.poll();
                    queue.offer(new StrElement(key, map.get(key)));
                }
            }
        }

        while(!queue.isEmpty()){
            ans.add(queue.poll().elemt);
        }

        Collections.reverse(ans);
        return ans;
    }

    //373. 查找和最小的K对数字
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> ans = new ArrayList<>();

        //这里有一种直接在PriorityQueue中传参Comparator的方法，还可以像上面两题一样实现Comparable接口
        Queue<int[]> queue = new PriorityQueue<>(k,new Comparator<int[]>() {
            @Override
            //大顶堆
            public int compare(int[] a, int[] b) {
                return b[0]+b[1] - a[0] - a[1];
            }
        });


        for(int val1:nums1){
            for(int val2:nums2){
                if(queue.size()<k){
                    queue.offer(new int[]{val1, val2});
                }else{
                    if(queue.peek()[0]+queue.peek()[1]>val1+val2){
                        queue.poll();
                        queue.offer(new int[]{val1, val2});
                    }
                }
            }
        }
        //List直接用这个方法添加容器中的元素
        ans.addAll(queue);
        return ans;
    }

    //215. 数组中的第K个最大元素，第K最大用小顶堆，第K最小用大顶堆
    //用典型堆排序即可
    public int findKthLargest(int[] nums, int k) {
        int ans = 0;
        Queue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>(){
            @Override
            public int compare(Integer a1, Integer a2){
                return a1-a2; //默认自然顺序是小顶堆
            }
        });

        for(int val:nums){
            if(queue.size()<k){
                queue.offer(val);
            }else{
                if(queue.peek()<val){
                    queue.poll();
                    queue.offer(val);
                }
            }
        }
        ans = queue.peek();
        return ans;
    }


    //973. 最接近原点的 K 个点
    public int[][] kClosest(int[][] points, int K) {
        int[][] ans = new int[K][];

        Queue<int[]> queue = new PriorityQueue<>(K, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return (int)(Math.pow(b[0],2)+Math.pow(b[1],2) - Math.pow(a[0],2) - Math.pow(a[1],2));
            }
        });

        for(int[] point:points){
            if(queue.size()<K){
                queue.offer(point);
            }else{
                if(Math.pow(queue.peek()[0],2)+Math.pow(queue.peek()[1],2) > Math.pow(point[0],2)+Math.pow(point[1],2)){
                    queue.poll();
                    queue.offer(point);
                }
            }
        }

        int index = 0;
        while(!queue.isEmpty()){
            ans[index] = queue.poll();
            index++;
        }
        return ans;
    }

    //264. 丑数 II
    //三指针解法
    public int nthUglyNumber(int n) {
        int ans = 0;
        if(n<=0)
            return 0;
        int[] ungly = new int[n];
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        ungly[0] = 1;
        for(int i = 1; i<n; i++){
            int min = Math.min(ungly[p2] * 2, Math.min(ungly[p3] * 3, ungly[p5] * 5));
            if(min == ungly[p2] * 2){
                p2++;
            }
            if(min == ungly[p3] * 3){
                p3++;
            }
            if(min == ungly[p5] * 5){
                p5++;
            }
            ungly[i] = min;
        }
        ans = ungly[n-1];
        return ans;
    }

    //313. 超级丑数
    //和上面的丑数几乎一样的思路，先求所有指针中最小，然后填充数组，最后符合的指针+1
    public int nthSuperUglyNumber(int n, int[] primes) {
        int ans = 0;

        int pointer[] = new int[primes.length];
        int ugly[] = new int[n];

        ugly[0] = 1;

        for(int i = 1; i<n; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j<primes.length; j++){
                min = Math.min(min, ugly[pointer[j]] * primes[j]);
            }
            ugly[i] = min;
            for(int j = 0; j<primes.length; j++){
                if(ugly[pointer[j]] * primes[j] == min){
                    pointer[j]++;
                }
            }
        }

        ans = ugly[n-1];
        return ans;
    }
}
