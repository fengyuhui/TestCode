import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }


public class Tree_easy_now {
    public static void main(String args[]){
        return;
    }

    //100 相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null){
            if(p == null && q == null)
                return true;
            else
                return false;
        }else{
            if(p.val == q.val){
                return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
            }else
                return false;
        }
    }

    //101对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        if(root.left == null || root.right == null){
            if(root.left == null && root.right == null) {
                return true;
            }
            else
                return false;
        }else{
            return isSymmetricSub(root.left, root.right);
        }
    }

    public boolean isSymmetricSub(TreeNode l, TreeNode r){
        if(l == null || r == null){
            if(l == null && r == null) {
                return true;
            }
            else
                return false;
        }else{
            if(l.val!=r.val)
                return false;
            else {
                return isSymmetricSub(l.left, r.right)&&isSymmetricSub(l.right, r.left);
            }
        }
    }


    //104二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if(root!=null){
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }else
            return 0;
    }


    //107. 二叉树的层次遍历 II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        return list;
    }
    public void levelSub(TreeNode root, int level, ArrayList list){

    }


}
