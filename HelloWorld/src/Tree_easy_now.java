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

    //100 ��ͬ����
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

    //101�Գƶ�����
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
}