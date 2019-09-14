import java.util.*;

public class Qianxin {
    public static void main(String[] args){
        Qianxin qianxin = new Qianxin();
        //qianxin.terminate();
        qianxin.terminate();
    }
    public int terminate(){
        boolean flag = false;
        int ans = 0;
        Scanner in = new Scanner(System.in);
        String child  = in.nextLine();
        String father = in.nextLine();
        int target = in.nextInt();
        String[] c = child.split(" ");
        String[] f = father.split(" ");
        Map map = new HashMap<String, Set<String>>();
        if(c.length == 0){
            System.out.println(0);
            return c.length;
        }

        for (int i = 0; i < c.length; i++) {
            if(Integer.parseInt(c[i]) == target || Integer.parseInt(f[i]) == target){
                flag = true;
            }
            //如果删除根节点
            if(Integer.parseInt(c[i]) == target && f[i].equals("0") ){
                System.out.println(c.length);
                return c.length;
            }

            if(map.containsKey(f[i])){
                Set s = (Set)map.get(f[i]);
                s.add(c[i]);
                //遍历所有结点，如果子节点包括其父节点，则也加入
                for(Object key : map.keySet()){
                    Set set = (Set)map.get(key);
                    if(set.contains(f[i])){
                        set.add(c[i]);
                    }
                }

            }else{
                if(f[i].equals("0"))
                    continue;
                else{
                    map.put(f[i], new HashSet());
                    Set s = (Set)map.get(f[i]);
                    s.add(c[i]);
                    //遍历所有结点，如果子节点包括其父节点，则也加入
                    for(Object key : map.keySet()){
                        Set set = (Set)map.get(key);
                        if(set.contains(f[i])){
                            set.add(c[i]);
                        }
                    }
                }
            }
        }
        Set set = (Set)map.get(target+"");
        if(set!=null && set.size()!=0){
            ans = set.size();
        }

        if(set.contains(target+""))
            ans--;
        if(flag) {
            ans += 1;//子结点加上本身
        }
        System.out.println(ans);
        return ans;
    }

    public int find(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int num = 1<<n;
        num--;
        int ans = 0;
        int[] node = new int[num];
        for (int i = 0; i < num; i++) {
            node[i] = in.nextInt();
        }
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        System.out.println(node[0]);
        return node[0];
    }
}
