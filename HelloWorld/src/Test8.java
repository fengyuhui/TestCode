import java.util.Scanner;

public class Test8 {
    public static void main(String[] args){
        Test8 main = new Test8();
        main.metohd2();
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
}
