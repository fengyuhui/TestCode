package medium;

import java.util.*;

public class BFS_Medium_notComplete {
    public  static void main(String[] args){

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

}
