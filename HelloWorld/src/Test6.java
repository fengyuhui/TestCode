import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Test6 {

/** 请完成下面这个函数，实现题目要求的功能 **/
    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    static String calculate(int m, int k) {
        String ans="";
        //code[i]代表第i只的编号
        int[] code = new int[m];
        //第一只编号为2
        code[0]=2;
        code[1]=3;
        code[2]=4;
        //求m的编号
        if(m == 3){
            ans=ans+1+",";
        }else if(m == 4){
            ans=ans+2+",";
        }else{
            for(int i = 3; i<m; i++){
                code[i]=code[i-2]+code[i-3];
            }
            ans=ans+code[m-1]+",";
        }

        //求m出生年份
        //m-2<=n+(n-1)+(n-3)+...+2+1， n为第一只母猪所生除去第一只的小猪数量，也是2019+n年；
        // n-2是第二只小猪所生，第二只小猪在第三年生下第一个小猪。
        // m个中包括第一只母猪和第一只小猪，所以m-2
        //m-2<=(1+n)*n / 2
        int year = 0;
        if(m<=2){
            year = 0;
        }else {
            while ((1 + year) * year / 2  <m-2) {
                year++;
            }
        }
        year+=2019;
        ans+=year+",";



        //翻转code[i]的编号
        for(int i = 0; i<m; i++){
            int temp = code[i];
            int reverse = 0;
            while(temp!=0){
                reverse = reverse*10+(temp%10);
                temp=temp/10;
            }
            code[i]=reverse;
        }

        //排序
        Arrays.sort(code);
        //第k大，k最小为1，最大为m
        ans+=code[m-k];
        return ans;

    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(",");
        int m = Integer.valueOf(line[0]);
        int k = Integer.valueOf(line[1]);
        System.out.println(calculate(m, k));

    }
}