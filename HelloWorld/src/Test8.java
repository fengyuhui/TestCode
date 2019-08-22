import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class Test8 {
    public static void main(String[] args){
        Test8 main = new Test8();
        //main.metohd2();
        int a[][] = {{4,8},{4,9},{2,4},{0,2},{5,7}};
        int r = main.method3(a);
        System.out.println(r);
    }
    public void metohd1(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i<n; i++){

            long temp = sc.nextLong();
            long k = temp;
            boolean can = false;
            boolean not = false;
            if(k==0){
                System.out.println("G");
                continue;
            }
            while(k>0){
                long a = k%10;
                if(a == 0){
                    can = true;
                    k = k/10;
                    continue;
                }
                if(temp % a == 0)
                    can = true;
                else{
                    not = true;
                }
                k = k/10;
            }
            if(can && not){
                System.out.println("H");
            }else if(!can&&not){
                System.out.println("S");
            }else if(can && !not){
                System.out.println("G");
            }
        }
    }

    public void metohd2(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //sc.nextLine();
        int[] money = new int[n];
        //String s = sc.nextLine();
        //s = s.replace(" ","");
        //char a[] = s.toCharArray();
        for(int i = 0; i<n; i++) {
            money[i] = sc.nextInt();
        }

        int ans = 0;
        int times = 0;
        for(int i = 0; i<n-1; i++){
            if(money[i]<money[i+1]){
                ans+=money[i+1]-money[i];
                times+=2;
                i=i+1;
            }
        }
        System.out.println(ans+" "+times);
    }

    public int method3(int[][] p) {
        int count = 1;
        Stack<Integer> f = new Stack();
        Stack l = new Stack();
        Arrays.sort(p, new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2) {
                return p1[0] - p2[0];
            }
        });

        if (p[0][0] >0) {
            return -1;
        }

        int first = p[0][0];
        int last = p[0][1];
        f.push(first);
        l.push(last);

        for (int i = 1; i < p.length-1; i++) {
            if (p[i][0]<=(Integer)l.peek() && p[i][1]>(Integer)l.peek()) {
                if (p[i][0] == (Integer)l.peek()) {
                    f.push(p[i][0]);
                    l.push(p[i][1]);
                } else {
                    //遍历右下标栈，如果小于就弹出当前栈
                    while (p[i][0] < (Integer)l.peek()) {
                        f.pop();
                        l.pop();
                    }
                    f.push(p[i][0]);
                    l.push(p[i][1]);
                }
            }
        }

        count = f.size() < l.size()?f.size():l.size();
        return count;
    }
}
