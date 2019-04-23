import java.util.*;

class TreeNode{
    int val;
    List<TreeNode> children;
    boolean isBlack;
}

public class Test7 {
    public static void main(String args[]){
      Test7 test = new Test7();
      test.method2();
      //test.method1();
    }

    public void method3() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n<=0){
            System.out.println(0);
            return;
        }
        if(n == 1){
            System.out.println(1);
            return;
        }
        TreeNode tree = new TreeNode();

        String root = sc.nextLine();
        String[] r = root.split(" ");
        String color = sc.nextLine();
        String[] c = color.split(" ");
        Map<Integer,TreeNode> map = new TreeMap<>();

        //建树
        for(int i = 0; i<n; i++){
            TreeNode temp = new TreeNode();
            temp.val = i;
            temp.isBlack = c[i] == "1"?true:false;
            map.put(i, temp);
        }

        for(int i = 0; i<n; i++){
            if(map.get(Integer.parseInt(r[i])).children == null){
                List<TreeNode> children = new ArrayList<>();
                children.add(map.get(i));
                map.get(Integer.parseInt(r[i])).children = children;
            }else{
                map.get(Integer.parseInt(r[i])).children.add(map.get(i));
            }
        }

        int ans = preOrder(map.get(0));
        //前序遍历
        System.out.println(ans);
        return;
    }

    public int preOrder(TreeNode root){
        int ans = 1;
        List<TreeNode> children = root.children;
        if(children == null)
            return 1;
        if(root.isBlack){
            for(int i = 0; i<children.size(); i++){
                int temp = 0;
                if(!children.get(i).isBlack){
                    ans++;
                    ans = ans+preOrder(children.get(i));
                }
            }
        }
        return ans;
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
