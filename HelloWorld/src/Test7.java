import java.util.*;

class TreeNode{
    int val;
    List<TreeNode> children;
}
public class Test7 {
    public static void main(String args[]){
      Test7 test = new Test7();
      test.method2();
      //test.method1();
    }

    public void method2(){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        Map<String,Integer> map = new HashMap<>();
        sc.nextLine();
        for(int i = 0; i<m; i++){
            String input = sc.nextLine();
            map.put(input, 1);
        }
        String T = sc.nextLine();

        int ans = 0;
        int j = 0;
        for(int i = 1; i<T.length()+1; i++){
            while(j<i && i<T.length()+1){
                if(map.containsKey(T.substring(j,i))){
                    ans++;
                    j++;
                }else{
                    i++;
                }
            }
        }
        System.out.println(ans);
    }

    public int dfs(String s, ArrayList list){
        int ans = 0;
        if(list.contains(s)){
            ans++;
        }
        return ans;
    }

    public void method1(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n == 1){
            System.out.println(0);
            return;
        }
        sc.nextLine();
        Map map = new HashMap();
        for(int i = 0; i<n; i++){
            TreeNode a = new TreeNode();
            ArrayList<TreeNode> children = new ArrayList<>();
            a.children = children;
            a.val = i+1;
            map.put(i+1, a);
        }
        //建树
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] node = line.split(" ");
            if(node[0].equals(node[1]))
                continue;
            ((TreeNode)map.get(Integer.parseInt(node[1]))).children.add( (TreeNode)map.get(Integer.parseInt(node[0])));
        }

        int ans = time((TreeNode)map.get(1));
        System.out.println(ans);
        return;
    }

    public int time(TreeNode root){
        if(root == null)
            return 0;
        if(root.val == 1){
            int max = 0;
            int index = 0;
            while(index<root.children.size()){
                int temp = time(root.children.get(index));
                if(max<temp)
                    max = temp;
                index++;
            }
            return max;
        }else{
            int max = 0;
            int index = 0;
            while(index<root.children.size()){
                int temp = time(root.children.get(index));
                max+=temp;
                index++;
            }
            return max+1;
        }
    }

}
