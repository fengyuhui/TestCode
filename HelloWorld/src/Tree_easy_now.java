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
        //b += a; // b=-2����Ϊȡֵ��Χ��-128��127��127+1��-128��
        b=b++;//����a = a++����Ҷ�֪����a++��������Ļ�����a�Ȳ������㣬��ִ��a�������������������ε��ǣ�++�����ȼ��Ǹ���=�ģ���ˣ�java�Ĵ������ǣ������ڴ����ȿ���һ����ʱ�ռ䣬���a��ֵ��Ȼ��a������֮���ٴ���ʱ�ռ��ó���a������a��
        int i = 0;
        int[] s = {0,1};
        System.out.println(s[i++]);
        int k = 2;
        i = k++;//i-2
        i+=k++;

        System.out.println(b);
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


    //104��������������
    public int maxDepth(TreeNode root) {
        if(root!=null){
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }else
            return 0;
    }


    //107. �������Ĳ�α��� II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        return list;
    }
    public void levelSub(TreeNode root, int level, ArrayList list){
    }


}
