import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        Test3 main = new Test3();
        main.method1();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String s = in.nextLine();

        if(!s.contains("01") && !s.contains("10") ){
            System.out.println(n);
            return;
        }
        if(n<2){
            System.out.println(n);
            return;
        }

        int l = 0, r = 1;
        int size = n;
        while (s.contains("01") || s.contains("10")){
            if(r>size){
                break;
            }
            if(s.charAt(l) != s.charAt(r)){
                size = size-2;
                String temp1 = s;
                String temp2 = s;
                temp1 = s.substring(0,l);
                temp2 = s.substring(r+1,size+2);
                s=temp1+temp2;

                if(l<2)
                    l = 0;
                else{
                    l--;
                }

                r =l+1;
            }else{
                l++;
                r++;
            }
        }
        System.out.println(size);

    }

    public void method3(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int power[] = new int[n];
        int money[] = new int[n];
        int ans = 0;
        for(int j = 0; j < n; j++){
            power[j] = sc.nextInt();
        }

        for(int i = 0; i < n; i++){
            money[i] = sc.nextInt();
        }

        if(n<2){
            System.out.println(money[0]);
            return;
        }
        //初始化
        //走到第i个怪兽共耗费多少金币
        int dp[] = new int[n];
        dp[0] =money[0];

        //走到第i个怪兽还有多少武力值
        int dp2[] = new int[n];
        dp2[0] = power[0];

        for(int i = 0; i<n-1; i++){
            if(dp2[i]>power[i+1]){
                dp2[i+1] = dp2[i];
                dp[i+1] = dp[i];
            }else{
                dp2[i+1] = dp2[i]+power[i+1];
                dp[i+1] = dp[i]+money[i+1];
            }
        }
        ans = dp[n-1];
        System.out.println(ans);
    }

    public void method1(){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] money = new int[m];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<m; i++){
            money[i] = sc.nextInt();
            map.put(money[i], 0);
        }


        for(int j = 1; j<n+1; j++) {
            int temp = j;
            int i = m-1;
            while(i>-1 && temp!=0){
                if(temp>=money[i]) {
                    int num = temp / money[i];
                    temp = temp % money[i];
                    if (map.get(money[i]) < num) {
                        map.put((money[i]), num);
                    }
                    i--;
                }else{
                    if(i == 0) {
                        System.out.println(-1);
                        return;
                    }
                    i--;
                }
            }
        }

        int ans = 0;
        for(int i = 0; i<m; i++){
            ans+=map.get(money[i]);
        }
        System.out.println(ans);
    }


    //改良版method3
    public void method3_2(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int power[] = new int[n];
        int money[] = new int[n];
        int ans = 0;
        for(int j = 0; j < n; j++){
            power[j] = sc.nextInt();
        }

        for(int i = 0; i < n; i++){
            money[i] = sc.nextInt();
        }

        if(n<2){
            System.out.println(money[0]);
            return;
        }
        //初始化
        //从i走到第j个怪兽共耗费多少金币
        int dp[][] = new int[n][n];
        dp[0][1] =money[0];
        for(int i = 0; i<n; i++){
            dp[i][i] = 0;
        }

        //从i走到第j个怪兽还有多少武力值
        int dp2[][] = new int[n][n];
        dp2[0][1] = power[0];

/*        for(int i = 0; i<n-1; i++){
            if(dp2[i]>power[i+1]){
                dp2[i+1] = dp2[i];
                dp[i+1] = dp[i];
            }else{
                dp2[i+1] = dp2[i]+power[i+1];
                dp[i+1] = dp[i]+money[i+1];
            }
        }
        ans = dp[n-1];*/
        System.out.println(ans);
    }
}
