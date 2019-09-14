import java.util.Scanner;

public class KuaiShou {
    public static void main(String[] args) {
        KuaiShou main = new KuaiShou();
        /*Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        sc.nextLine();
        String version1 = "", version2 = "";
        for(int i = 0; i < m ;i++) {
            String line = sc.nextLine();
            String[] v = line.split(" ");
            System.out.println(main.compareVersion(v[0],v[1]));
        }*/
        main.test();
    }

    public boolean compareVersion(String v1, String v2) {
        String[] tmp = v1.split("\\.");
        String[] tmp2 = v2.split("\\.");
        int num1 = 0;
        int num2 = 0;
        int idx1 = 0;
        int idx2 = 0;
        while(idx1 < tmp.length || idx2 < tmp2.length){
            num1 *= 10;
            num2 *= 10;
            if(num1 > num2)
                return true;
            if(num2 > num1)
                return false;

            if(idx1 < tmp.length){
                String cur = tmp[idx1];
                num1 += Integer.parseInt(cur);
            }
            if(idx2 < tmp2.length){
                String cur2 = tmp2[idx2];
                num2 += Integer.parseInt(cur2);
            }

            idx1++;
            idx2++;
        }
        if(num1 > num2)
            return true;
        if(num1 == num2)
            return true;
        else
            return false;
    }


    double a=3,b=6,c=3;
    double solve(double x){
        return a*x*x-b*x-c;
    }
    int test(){
        double l = -1000.0, r=1000.0;
        double mid1,mid2;
        int t = 100;
        while(t-->0){
            mid1 = (l+r) / 2.0;
            mid2 = (mid1+r) / 2.0;
            if(solve(mid1)<solve(mid2)){
                r=mid2;
            }else{
                l = mid1;
            }
        }
        System.out.println(l);
        return 0;
    }

}