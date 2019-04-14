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
}
