import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Shunfeng {
    public static void main(String[] args){
        Shunfeng m = new Shunfeng();
       // m.method1();
        m.method3();
    }
    public void method1(){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] time = s.split(":");
        int minH = 2, maxH = 2, minM = 2, maxM = 2;
        boolean zeroH = false, zeroM = false;
        if(time[0].equals("00000") && time[1].equals("00000")){
            System.out.println("0");
            return;
        }
        if(time[0].equals("00000")){
            zeroH = true;
            minH = 2;
            maxH= 59;
        }
        if(time[1].equals("00000")){
            zeroM = true;
            minH = 2;
            maxH= 23;
        }
        //时，最多为23
        for(int i = 2; i<=23 && !zeroH; i++){
            int hour = 0;
            boolean flag = false;
            for(int j = time[0].length()-1; j>=0; j--){
                Character c = time[0].charAt(j);
                int temp = 0;

                if(Character.isDigit(c)){
                    temp = Integer.parseInt(c+"");
                }else if(Character.isAlphabetic(c)){
                    temp = c-'A'+10;
                }

                if(temp>=i) {
                    minH = Integer.max(minH, i+1);
                    flag = true;
                    break;
                }
                hour+=temp * Math.pow(i,time[0].length()-1-j);
            }
            if(hour<=23 && !flag){
                maxH = i;
            }
        }

        //分，最多为59
        for(int i = 2; i<=59&& !zeroM; i++){
            int minute = 0;
            boolean flag = false;
            for(int j = time[1].length()-1; j>=0; j--){
                Character c = time[1].charAt(j);
                int temp = 0;

                if(Character.isDigit(c)){
                    temp = Integer.parseInt(c+"");
                }else if(Character.isAlphabetic(c)){
                    temp = c-'A'+10;
                }

                if(temp>=i) {
                    minM = Integer.max(minM, i+1);
                    flag = true;
                    break;
                }
                minute+=temp * Math.pow(i,time[1].length()-1-j);
            }
            if(minute<=59 && !flag){
                maxM = i;
            }
        }

        int min = Math.max(minH, minM);
        int max = Math.min(maxH,maxM);
        if(min>max) {
            System.out.println("-1");
            return;
        }
        for(int i = min; i<=max; i++) {
            System.out.print(i);
            if(i<max)
                System.out.print(" ");
        }
        return;
    }

    public void method2(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] s = new int[n];
        for(int i = 0; i<n; i++){
            s[i] = in.nextInt();
            int a = s[i] / 2;
            int b = s[i] % 2;
            int ans = 2+2*a;
            if(b>0)
                ans++;
            System.out.println(ans);
        }
    }

    public void method3(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] cut = new int[n];
        ArrayList a = new ArrayList<>();
        ArrayList b = new ArrayList<>();
        for(int i = 0;i<n; i++){
            cut[i] = in.nextInt();
            if(cut[i] % 2 == 0)
                a.add(cut[i]);
            if(cut[i] % 2 != 0)
                b.add(cut[i]);
        }
        Collections.sort(a);
        Collections.sort(b);

        int indexa = 2, maxA = 0;
        int indexb = 1, maxB = 0;
        for(int i = 0; i<a.size(); i++){
            if(i == 0){
                maxA = ((Integer)a.get(i) - 2 ) / 2 -1;
            }else{
                int temp = ((Integer)a.get(i) - (Integer)a.get(i-1)) / 2 -1;
                if(temp>maxA){
                    maxA = temp;
                    indexa = (Integer)a.get(i)+2;
                }
            }
            if(i == a.size() - 1){
                int temp = (100 - (Integer)a.get(i)) / 2 -1;
                if(temp>maxA){
                    maxA = temp;
                    indexa = (Integer)a.get(i)+2;
                }
            }
        }
        for(int i = 0; i<b.size(); i++){
            if(i == 0){
                maxB = ((Integer)b.get(i) - 2 ) / 2 -1;
            }else{
                int temp = ((Integer)b.get(i) - (Integer)b.get(i-1)) / 2 -1;
                if(temp>maxB) {
                    maxB = temp;
                    indexb = (Integer) b.get(i) + 2;
                }
            }
            if(i == b.size() - 1){
                    int temp = (99 - (Integer)b.get(i)) / 2;
                    if(temp>maxB){
                        maxB = temp;
                        indexb = (Integer)b.get(i)+2;
                }
            }
        }

        if(maxA>maxB){
            System.out.print(indexa+" ");
            System.out.println(maxA);
        }else if(maxA == maxB){
            if(indexa<indexb){
                System.out.print(indexa+" ");
                System.out.println(maxA);
            }else{
                System.out.print(indexb+" ");
                System.out.println(maxB);
            }
        }
        else{
            System.out.print(indexb+" ");
            System.out.println(maxB);
        }
    }
}
