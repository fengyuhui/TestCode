/*
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int[] student = new int[100000];
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int sums = 0;
        scanner.nextLine();
        int[][] matrix= new int[n][m];
        for(int i = 0; i<n; i++){
            String str = scanner.nextLine();
            for(int j = 0; j<m; j++){
                matrix[i][j] =str.charAt(j) - '0';
            }
        }
        for(int i = 1; i<n; i++){
            for(int j = 1; j<m; j++){
                //System.out.println(matrix[i][j]);
                if(matrix[i][j] == 1&& matrix[i+1][j-1] == 1 && matrix[i+1][j] == 1 && matrix[i+1][j+1] == 1 && matrix[i+2][j] == 1 ){
                    sums++;
                }
            }
        }
        System.out.println(sums);


*/
/*        for(int i = 0; i<N; i++){
            student[i] = scanner.nextInt();
        }*//*


        */
/*while(scanner.hasNext()){
            day[index] = scanner.nextInt();
            index++;
        }*//*


    }
}*/


import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
/*        for(int i = 0; i < n; i++) {
            int nums = sc.nextInt();
            int peopleScore[] = new int[nums];
            int prize[] = new int[nums];
            //初始化分数
            for (int j = 0; j < nums; j++) {
                peopleScore[i] = sc.nextInt();
            }
            //找最小奖励的下标
            int index = 0;
            int minScore = peopleScore[0];
            for (int j = 1; j < nums; j++) {
                if (minScore > peopleScore[j]) {
                    minScore = peopleScore[j];
                    index = j;
                }
            }
            for(int k = 0; k<nums; k++){
                prize[k] = 2;
            }
            prize[index] = 1;

            for(int k = 0; k<nums; k++){
                for(int p = 0; p<nums; p++){
                    if(p+2<nums){
                        if(peopleScore[p+1] >peopleScore[p] && peopleScore[p+1]>peopleScore[p+2]){
                            prize[p+1] = prize[p]>prize[p+2]?prize[p]+1:prize[p+2]+1;
                        }else if(peopleScore[p+1] >peopleScore[p] && peopleScore[p+1]<peopleScore[p+2]){
                            prize[p+1] = prize[p]+1;
                            prize[p+2] = prize[p+1]+1;
                        }
                        else if(peopleScore[p+1] <peopleScore[p] && peopleScore[p+1]>peopleScore[p+2]){
                            prize[p+1] = prize[p+2]+1;
                        }
                    }
                }
            }

            int count = 0;
            for(int k = 0; k<nums; k++){
                count+=prize[k];
            }
            System.out.println(count);
        }*/

/*                Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int money;
        if(n == 1024)
            money = 1024;
        else
            money = 1024 - n;
        int temp = money;
        int count = 0;

            if(temp > 64 || temp ==64){
                count += temp / 64;
                temp = temp %64;
            }
            if(temp > 16 || temp ==16){
                count += temp / 16;
                temp = temp %16;
            }

            if(temp > 4 || temp ==4){
                count += temp / 4;
                temp = temp %4;
            }

            if(temp < 4) {
                count += temp;
            }
        System.out.println(count);*/

        /*Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int size = 0;
        String str[] = new String[n];
        for(int i = 0; i < n; i++){
            boolean flagAA = false;
            boolean flagAAB = false;
            StringBuffer stringBuffer = new StringBuffer();
           str[i] = sc.nextLine();
           size = str[i].length();
            if(size >2){
                stringBuffer.append(str[i].charAt(0));
                stringBuffer.append(str[i].charAt(1));
                if(str[i].charAt(0) == str[i].charAt(1)){
                    flagAA = true;
                }
            }else{
                System.out.println(str[i]);
            }

           for (int j = 2; j<size; j++){
               if(flagAA){
                   if(str[i].charAt(j) != stringBuffer.charAt(stringBuffer.length() - 1)){
                       stringBuffer.append(str[i].charAt(j));
                       flagAA = false;
                       flagAAB = true;
                       continue;
                   }else
                       continue;
               }

               if(flagAAB) {
                   if(str[i].charAt(j) != stringBuffer.charAt(stringBuffer.length() - 1)){
                       stringBuffer.append(str[i].charAt(j));
                       flagAA = false;
                       flagAAB = false;
                       continue;
                   }else
                       continue;
               }

               stringBuffer.append(str[i].charAt(j));
               if(stringBuffer.charAt(stringBuffer.length() - 2) == stringBuffer.charAt(stringBuffer.length() - 1)){
                   flagAA = true;
               }
           }
           System.out.println(stringBuffer.toString());
        }*/

        //第一题
        /*Scanner in = new Scanner(System.in);
        int n = -1;
        int m = -1;
        while (in.hasNextInt()) {//注意while处理多个case
            n = in.nextInt();
            m = in.nextInt();
        }
        int tempm = m;
        int d = -1;
        int nums = 0;
        int t = 0;
        while(tempm!=0){
            d = tempm / n;
            t = tempm % n;
            nums+=d;
            tempm = t;
            n--;
        }
        System.out.println(nums);
        */

        //第二题
/*            Scanner sc = new Scanner(System.in);
            int q = sc.nextInt();

            int l = 0, r = 0;
            int sumsl = 0;
            int sumsr = 0;

        int ln ;
        int la0;
        int lan;
        int ln20;
        int la20;
        int la2n;

        int rn;
        int ra0;
        int ran;
        int rn20;
        int ra20;
        int ra2n;
        int temp = 0;


            for(int i = 0; i < q; i++){
                for(int j = 0; j < q; j++){
                    l = sc.nextInt();
                    r = sc.nextInt();
                    if(l % 2 !=0) {
                        ln = (l / 2) + 1;
                        la0 = -1;
                        lan = (ln - 1) * (-2)+la0;
                        ln20 = (l - 1) / 2;
                        la20 = 2;
                        la2n = l-1;
                        temp = lan;
                    }else{
                        ln =l/2;
                        la0 = -1;
                        lan = (l-1) * (-1) ;
                        ln20 = l /2;
                        la20 = 2;
                        la2n = l;
                        temp = la2n;
                    }
                    if(r % 2 !=0) {
                       rn =  (r / 2)+1;
                        ra0 = -1;
                        ran = (rn-1) * (-2)+ra0;
                        rn20 = (r - 1) / 2;
                        ra20 = 2;
                        ra2n = r-1;
                    }else{
                         rn = r/2;
                         ra0 = -1;
                         ran = (r-1) * (-1) ;
                         rn20 = r/2;
                         ra20 = 2;
                         ra2n = r;
                    }


                    sumsl = (la0+lan) * ln / 2 + (la20 + la2n) * ln20 / 2;
                    sumsr = (ra0+ran) * rn / 2 + (ra20 +ra2n) * rn20 / 2;
                    System.out.println(sumsr - sumsl+temp);
                }
            }*/

        //第三题
        //Scanner sc = new Scanner(System.in);
/*        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] hash = new int[m+1];
        for(int i = 0; i<m+1; i++){
            hash[i] = 0;
        }
        int count = -1;
        for(int i = 0; i<n; i++){
            int ball = sc.nextInt();
            if(ball !=0 && hash[ball] == 0){
                hash[ball] = 1;
                count++;
            }
        }
        System.out.println(count);
        }*/
    }
}
