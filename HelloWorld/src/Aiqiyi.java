import java.util.*;

public class Aiqiyi {
    public static void main(String[] args){
        Aiqiyi aiqiyi = new Aiqiyi();
        aiqiyi.method1();
    }
    public void method1(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];

        //初始化A数组
        for (int i = 0; i < a.length-1; i++) {


            a[i] = in.nextInt();
        }

        //如果A全为1则递增，全为0则递减
        int a1 = 0;
        int a0 = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i] == 1)
                a1++;
            if(a[i] == 0)
                a0++;
        }
        if(a1 == n || a0 == 0){
            System.out.println(1);
            return;
        }

        //求全排列
        System.out.println(fib(n));

    }

    public int fib(int n){
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        else
            return fib(n-1)+fib(n-2);
    }

    public void method2(){

    }
}
