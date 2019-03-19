import java.util.Arrays;

public class Greedy_easy_notComplete {
    public static void main(String[] args){

        ////122. 买卖股票的最佳时机 II
        int[] p = {1, 7, 2, 3, 6, 7, 6, 7};
        maxProfit(p);
    }

    //122. 买卖股票的最佳时机 II
    public static int maxProfit(int[] prices) {
        int max = 0;
        for(int i = 1; i<prices.length; i++){
            if(prices[i] > prices[i - 1]){
                max+=prices[i] - prices[i-1];
            }
        }
        return max;
    }


    //455. 分发饼干
    public int findContentChildren(int[] g, int[] s) {
        int result = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int cookies = 0, child = 0;
        while(child<g.length && cookies < s.length ){
            if(g[child] < s[cookies] || g[child] == s[cookies]){
                child++;
                cookies++;
            }
            else{
                cookies++;
            }
        }
        return child;
    }

    //860. 柠檬水找零
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for(int i = 0; i<bills.length; i++){
            if(bills[i] == 5){
                five++;
            }else if(bills[i] == 10){
                ten++;
                if(five>0){
                    five--;
                }else{
                    return false;
                }
            }else{
                if(ten > 0 && five > 0){
                    ten--;
                    five--;
                }else if(five > 2){
                    five = five - 3;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    //984. 不含 AAA 或 BBB 的字符串,//需要保证A > B
    public String strWithout3a3b(int A, int B) {
        String str = "";
        char a = 'a', b = 'b';
        if (A < B) {
            int temp = A;
            A = B;
            B = temp;
            char tempc = a;
            a = b;
            b = tempc;
        }
        while (A > 0 || B > 0) {
            if (A > 0) {
                str = str + a;
                A --;
            }
            if (A > B) {
                str = str + a;
                A --;
            }
            if (B > 0) {
                str = str + b;
                B --;
            }
        }
        return str;
    }

    //944. 删列造序，转化为找出二维数组中 所有不是 “非降序” 排列的列的数量
    public int minDeletionSize(String[] A) {
        int count = 0;
        for(int i = 0; i<A[0].length(); i++){
            char left = A[0].charAt(i);
            for(int j = 1; j<A.length; j++){
                char cur  = A[j].charAt(i);
                if(cur<left){
                    count++;
                    break;
                }else{
                    left = cur;
                }
            }
        }
        return count;
    }
}
