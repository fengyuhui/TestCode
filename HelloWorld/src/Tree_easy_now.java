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
}
