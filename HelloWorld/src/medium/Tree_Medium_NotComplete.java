package medium;

import java.util.LinkedList;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
      TreeNode(int x) { val = x; }
 }

public class Tree_Medium_NotComplete {
    public static void main(String args[]){
        Integer[] tree = {1,null, 0,0,1};
        Tree_Medium_NotComplete main = new Tree_Medium_NotComplete();
        TreeNode root = main.buildTree(tree);

    }

    //构建树，为空子树用-1结点和null结点表示
    public LinkedList<TreeNode> linkedList = new LinkedList<>();
    public TreeNode buildTree(Integer[] tree){
        TreeNode root = new TreeNode(tree[0]);
        TreeNode left = new TreeNode(-1);
        TreeNode right = new TreeNode(-1);
        root.left = left;
        root.right = right;
        linkedList.add(left);
        linkedList.add(right);

        int index = 1;
        TreeNode templ =new TreeNode(-1);
        TreeNode tempr =new TreeNode(-1);
        while(index<tree.length){
            if(tree[index]!=null){
                TreeNode l =new TreeNode(-1);
                TreeNode r = new TreeNode(-1);
                templ = linkedList.poll();
                templ.val = tree[index];
                templ.right = r;
                templ.left = l;
                linkedList.add(l);
                linkedList.add(r);
            }else{
                linkedList.poll();
            }

            index++;
            if(tree[index]!=null){
                TreeNode l =new TreeNode(-1);
                TreeNode r = new TreeNode(-1);
                tempr = linkedList.poll();
                tempr.val = tree[index];
                tempr.right = r;
                tempr.left = l;
                linkedList.add(l);
                linkedList.add(r);
            }else{
                linkedList.poll();
            }
            index++;
        }
        return root;
    }


    //814. 二叉树剪枝，先修减左右子树，再判断当前节点是否已经变为叶子节点且值为0
    public TreeNode pruneTree(TreeNode root) {
        if(root == null){
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if(root.left == null && root.right == null && root.val == 0){
            return null;
        }

        return root;
    }
}
