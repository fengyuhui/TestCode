package medium;

import java.util.*;

public class BFS_Medium_notComplete {
    public  static void main(String[] args){
        BFS_Medium_notComplete main = new BFS_Medium_notComplete();
        int[][] p = {{1, 0}, {1, 2}, {1, 3}};
        //main.findMinHeightTrees(4,p);
        int times[][] = {{1,3,68},{1,4,20},{4,1,65},{3,2,74},{2,1,44},{3,4,61},{4,3,68},{3,1,26},{5,1,60},{5,3,3},{4,5,5},{2,5,36},{2,3,94},{1,2,0},{3,5,90},{2,4,28},{4,2,12},{5,4,52},{5,2,85},{1,5,42}};
        //main.networkDelayTime(times, 5,4);

        int p1[][] = {{1,0},{0,1}};
        //main.findOrder(2,p1);

        int color[][] = {{3,4,6},{3,6},{3,6},{0,1,2,5},{0,7,8},{3},{0,1,2,7},{4,6},{4},{}};
        main.isBipartite(color);


    }

    //515. 在每个树行中找最大值，感觉我这写的不是bfs而是dfs= =
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        Map<Integer, Integer> maxMap = new HashMap<>();
        if(root == null){
            return list;
        }
        map.put(root, 1);
        maxMap.put(1,root.val);
        if(root.left!=null) {
            linkedList.add(root.left);
            map.put(root.left, 2);
            maxMap.put(2, root.left.val);
        }
        if(root.right!=null){
            linkedList.add(root.right);
            map.put(root.right, 2);
            if(maxMap.containsKey(2)){
                if(maxMap.get(2)<root.right.val)
                    maxMap.put(2,root.right.val);
            }else{
                maxMap.put(2,root.right.val);
            }
        }

        bfsLargest(linkedList,map,maxMap);

        for(Integer value:maxMap.values()){
            list.add(value);
        }
        return list;
    }
    public void bfsLargest(LinkedList<TreeNode> linkedList, Map<TreeNode, Integer> map, Map<Integer, Integer> maxMap){
        if(linkedList.isEmpty())
            return;
        TreeNode root = linkedList.poll();
        //获取层数
        int h = map.get(root);
        int tempMax = root.val;
        if(maxMap.containsKey(h)){
            tempMax = maxMap.get(h)>tempMax?maxMap.get(h):tempMax;
            maxMap.put(h,tempMax);
        }else{
            maxMap.put(h,tempMax);
        }

        if(root.left!=null) {
            linkedList.add(root.left);
            map.put(root.left, h+1);
        }
        if(root.right!=null){
            linkedList.add(root.right);
            map.put(root.right, h+1);
        }
        bfsLargest(linkedList,map,maxMap);
    }

    //909. 蛇梯棋


    //310. 最小高度树
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> ans = new ArrayList<>();
        if(n == 1) {
            ans.add(0);
            return ans;
        }
        //可以把每个节点都当作root, 然后dfs来查找每棵树的高度，选最小的
        //还可以挨个删除叶节点，也就是度为1的节点(因为这个不是有向图，所以度既是出度也是入度)，然后再将依赖的头节点的入度-1，最后剩下的一个或两个节点就是所求
        //初始化度的数组

        int[] degree = new int[n];
        for(int[] edge:edges){
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        //存放度为1的节点
        int nums = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i<n; i++){
            if(degree[i] == 1) {
                queue.add(i);
                nums++;
            }
        }
        while(!queue.isEmpty() && nums<n) {
            int size = queue.size();
            while (size > 0) {
                int temp = queue.poll();
                size--;

                //删除对应的依赖度
                for (int[] edge : edges) {
                    if (edge[0] == temp) {
                        degree[edge[1]]--;
                        if (degree[edge[1]] == 1) {
                            queue.add(edge[1]);
                            nums++;
                        }
                    }
                    if (edge[1] == temp) {
                        degree[edge[0]]--;
                        if (degree[edge[0]] == 1) {
                            queue.add(edge[0]);
                            nums++;
                        }
                    }
                }
            }
        }
        while(!queue.isEmpty()){
            ans.add(queue.poll());
        }
        return ans;
    }



    //934. 最短的桥
    public int shortestBridge(int[][] A) {
        int ans = 0;
        return ans;
    }


    //102. 二叉树的层次遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            List tempList = new ArrayList();
            while(count!=0){
                TreeNode temp = queue.poll();
                tempList.add(temp.val);
                if(temp.left!=null)
                    queue.add(temp.left);
                if(temp.right!=null)
                    queue.add(temp.right);
                count--;
            }
            ans.add(tempList);
        }
        return ans;
    }

    //743. 网络延迟时间，dijkstra算法，最后是否有节点剩下，如果有就不能达到
    public int networkDelayTime(int[][] times, int N, int K) {
        Set<Integer> visited = new HashSet<>();
        //value[i]表示K到i的最小时间
        int[] value = new int[N+1];
        //初始化value
        for(int i = 0; i<N+1; i++){
            value[i] = 1000000;
        }
        //自身节点时间为0
        value[K] = 0;
        //waste[i][j]代表节点i到节点j的直接时间
        int[][] waste = new int[N+1][N+1];
        //初始化waste
        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=N; j++){
                if(i!=j)
                    waste[i][j] = 1000000;
            }
        }
        for(int[] time:times){
            waste[time[0]][time[1]] = time[2];
            if(time[0] == K){
                value[time[1]] = time[2];
            }
        }


        while(true){
            int index = findMinNext(visited, N, value);
            for(int i = 1; i<=N; i++){
                if(value[i]>value[index]+waste[index][i])
                    value[i] = value[index]+waste[index][i];
            }
            if(value[index] == value[0])
                break;
            visited.add(index);
        }

        int max = 0;
        if(visited.size() == N){
            for(int i = 1; i<=N; i++){
                max = max<value[i]?value[i]:max;
            }
            return max;
        }
        return -1;
    }
    public int findMinNext(Set<Integer> visited,int N, int[] value){
        int index = 0;
        int min = value[0];
        for(int i = 1; i<=N; i++){
            if(visited.contains(i))
                continue;
            if(min>=value[i]) {
                min = value[i];
                index = i;
            }
        }
        return index;
    }

    //513. 找树左下角的值
    public int findBottomLeftValue(TreeNode root) {
        int ans = 0;
        if(root == null)
            return 0;
        Map<Integer, ArrayList> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int h = 0;
        while(!queue.isEmpty()){
            int count = queue.size();
            h++;
            ArrayList temp = new ArrayList();
            map.put(h,temp);
            while(count!=0){
                TreeNode head = queue.poll();
                temp.add(head);
                count--;
                if(head.left!=null)
                    queue.offer(head.left);
                if(head.right!=null)
                    queue.offer(head.right);
            }
        }
        TreeNode last = (TreeNode)(map.get(h).get(0));
        ans = last.val;
        return ans;
    }


    //207. 课程表
    //拓扑排序 其实这是个BFS
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

    //210. 课程表 II
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        if(numCourses == 1) {
            ans[0] = 0;
            return ans;
        }

        Stack stack = new Stack();

        //初始化入度数组, inDgreee[i]代表i课程需要依赖多少门课
        int[] inDegree = new int[numCourses];
        for(int i = 0; i<numCourses; i++) {
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[0] == i){
                    inDegree[i]++;
                }
            }
        }

        //把入度为0的课程加入栈
        for(int i = 0; i<numCourses; i++){
            if(inDegree[i] == 0)
                stack.push(i);
        }

        //将依赖入度为0的课程的入度-1，如果-1后入度变为0，则加入栈
        int count = 0;
        while(!stack.isEmpty()){
            int course = (int)stack.pop();
            ans[count] = course;
            count++;
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == course){
                    inDegree[prerequisite[0]]--;
                    if(inDegree[prerequisite[0]] == 0)
                        stack.push(prerequisite[0]);
                }
            }
        }
        int[] notFound = {};
        if(count == numCourses)
            return ans;
        else
            return notFound;
    }

    //199. 二叉树的右视图，就是求每一层的最右节点
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer, ArrayList> map = new HashMap<>();
        if(root == null)
            return ans;
        queue.offer(root);
        int h = 0;
        while(!queue.isEmpty()){
            int count = queue.size();
            ArrayList<TreeNode> list = new ArrayList<>();
            map.put(h, list);
            h++;
            while(count!=0){
                TreeNode temp = queue.poll();
                list.add(temp);
                count--;
                if(temp.left!=null)
                    queue.add(temp.left);
                if(temp.right!=null)
                    queue.add(temp.right);
            }
        }
        ArrayList list2;
        for(int i = 0; i<h; i++){
            list2 = map.get(i);
            int size = list2.size();
            TreeNode last = (TreeNode)list2.get(size-1);
            ans.add(last.val);
        }
        return ans;
    }

    //785. 判断二分图,就是上色问题，如果相邻节点颜色相同，就不是二分图
    public boolean isBipartite(int[][] graph) {
        //1为蓝色，2为红色，0为未上色
        int[] color = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        Queue<Integer>queue = new LinkedList<>();
        for(int i = 0; i<graph.length; i++){
            if(color[i] == 0){
                //给自己和相邻节点上色
                color[i] = 1;
                queue.add(i);
                dfsColor(graph,color,queue,visited);
            }
        }

        //验证是否有相邻颜色相同
        for(int i = 0; i<graph.length; i++){
            int temp = color[i];
            int g[] = graph[i];
            for(int next:g){
                if(temp == 1){
                    if(color[next] == 1)
                        return false;
                }
                if(temp == 2){
                    if(color[next] == 2)
                        return false;
                }
            }
        }
        return true;
    }
    public boolean dfsColor(int[][] graph, int[] color, Queue<Integer> queue,boolean[] visited){
        while(!queue.isEmpty()){
            int temp = queue.poll();
            visited[temp] = true;
            int g[] = graph[temp];
            int val = color[temp] == 1?2:1;
            for(int next:g){
                if(!visited[next]){
                    color[next] = val;
                    queue.add(next);
                }
            }
        }
        return true;
    }


    //863. 二叉树中所有距离为 K 的结点
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();

        //建图

        return ans;
    }

}
