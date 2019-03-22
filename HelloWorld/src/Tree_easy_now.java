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
        byte a = 127;
        byte b = 127;
        // b = a + b; // error : cannot convert from int to byte
        //b += a; // b=-2，因为取值范围是-128到127。127+1是-128；
        b=b++;//对于a = a++，大家都知道，a++参与运算的话，是a先参与运算，再执行a自增操作。但这里尴尬的是，++的优先级是高于=的，因此，java的处理方法是，会在内存里先开辟一个临时空间，存放a的值，然后a自增，之后再从临时空间拿出来a，赋给a。
        int i = 0;
        int[] s = {0,1};
        System.out.println(s[i++]);
        int k = 2;
        i = k++;//i-2
        i+=k++;

        System.out.println(b);
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
