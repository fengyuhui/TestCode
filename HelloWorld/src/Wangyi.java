import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

class Tree{
    int val;
    int key;
    Tree left;
    Tree right;
}
public class Wangyi {
    public static void main(String args[]){
        Wangyi wangyi = new Wangyi();
        wangyi.method3();
    }

    public void method1(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            String s = Integer.toBinaryString(k)+"";
            StringBuffer stringBuffer = new StringBuffer(s);
            String s1 = stringBuffer.reverse().toString();
            if(s1.equals(s))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    public void method2(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for (int j = 0; j < n; j++) {
            boolean flag = false;
            int k = in.nextInt();
            if(k == 1){
                System.out.println("YES");
                return;
            }
            Map map = new HashMap<>();
            Tree root = new Tree();
            boolean visited[] = new boolean[k];
            //建树
            for (int i = 0; i < k; i++) {
                Tree t = new Tree();
                if(map.containsKey(i)){
                    t = (Tree)map.get(i);
                    t.val = in.nextInt();
                    t.key = i;
                }else{
                    t = new Tree();
                    t.key = i;
                    t.val = in.nextInt();
                    map.put(i,t);
                }
                int l = in.nextInt();
                int r = in.nextInt();
                if(l != -1){
                    if(map.containsKey(l)){
                        t.left = (Tree)map.get(l);
                        t.left.key = l;
                    }else{
                        Tree left = new Tree();
                        left.key = l;
                        map.put(l,left);
                        t.left = left;
                    }
                    visited[l] = true;
                }

                if(r != -1){
                    if(map.containsKey(r)){
                        t.right = (Tree)map.get(r);
                        t.right.key = r;
                    }else{
                        Tree right = new Tree();
                        right.key = r;
                        map.put(r,right);
                        t.right = right;
                    }
                    visited[r] = true;
                }
            }

            //查找root
            for (int p = 0; p < visited.length; p++) {
                if(!visited[p]){
                    root =(Tree) map.get(p);
                }
            }
            //遍历
            LinkedList<Tree> linkedList = new LinkedList<>();
            linkedList.add(root);
            int pre = 0;
            int level = 0;
            int[] values = new int[k];
            while(!linkedList.isEmpty()){
                int size = linkedList.size();
                while(size!=0){
                    size--;
                    Tree t1 = linkedList.poll();
                    pre+=t1.val;
                    if(t1.left!=null){
                        linkedList.offer(t1.left);
                    }
                    if(t1.right!=null){
                        linkedList.offer(t1.right);
                    }
                }

                if(size == 0){
                    values[level] = pre;
                    if(level>0){
                        if(values[level-1]>=pre){
                            System.out.println("NO");
                            flag = true;
                            break;
                        }
                    }
                    pre = 0;
                    level++;
                }

            }
            if(!flag)
                System.out.println("YES");
        }
    }

    public void method3(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int j = 0; j < n; j++) {
            int gap = in.nextInt();
            int days = in.nextInt();
            int max = days;
            int date[] = new int[days];
            for (int i = 0; i < days; i++) {
                date[i] = in.nextInt();
            }

            //每天都喝
            if(gap == 0){
                System.out.println(30);
                continue;
            }
            //不限制日期
            if(days == 0){
                System.out.println((30 / (gap+1)));
                continue;
            }

            //挑选日期
            //处理前半段
            int m = max;
            int d = date[0] - gap;
            while(d>=1){
                m++;
                d = d - gap - 1;
            }

            //处理中间段
            for(int k = 0; k<days - 1; k++){
                int g = date[k+1] - date[k] - 1 - 2*gap;
                int num = g / (gap+1);
                if(num>0)
                    m+=num;
            }

            //处理后半段
            int end = date[days-1] + gap +1;
            while(end<=30){
                m++;
                end = end + gap+1;
            }
            System.out.println(m);
        }
    }
}
