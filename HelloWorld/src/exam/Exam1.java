package exam;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author fengyuhui
 * @create 2019-08-03 14:33
 */
public class Exam1 {
    public static void main(String[] args){
        Exam1 main = new Exam1();
        //main.test1();
        main.test2();
    }
    public void test1(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ops = in.nextInt();
        int[] num = new int[n];
        //初始化
        for (int i = 0; i<n; i++) {
            num[i] = in.nextInt();
        }

        //每次操作
        for(int i = 0; i<ops; i++) {
            //排序
            //Arrays.sort(num);
            int result = 0;

            int dummy = in.nextInt();

            for (int j = n-1; j>-1; j--) {
                if (dummy > num[j]) {
                    continue;
                } else {
                    num[j] = num[j] - 1;
                    result++;
                }
            }
            System.out.println(result);
        }
    }

    public void test2(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        float[] score = new float[n];
        for (int i = 0; i<n; i++) {
            score[i] = Float.parseFloat(in.next());
        }

        int q = in.nextInt();
        for (int j = 0; j<q; j++) {
            //操作
            int x = in.nextInt();
            int num = 0;
            for(int k = 0; k<n; k++) {
                if (score[x-1] < score[k]) {
                    continue;
                } else {
                    num++;
                }
            }


            float percent = 0.000000f;
            if (num == 0) {
                System.out.println(0.000000);
            } else {
                percent = (((float)((num - 1)) / (float)n));
                System.out.printf("%.6f\n",percent);
            }
        }
    }
}
