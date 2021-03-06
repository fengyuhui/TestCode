package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class Math_Medium_notComplete {
    public static void main(String[] args){
        Math_Medium_notComplete main = new Math_Medium_notComplete();
        //main.myAtoi("-91283472332");
        //main.method2();
        //System.out.println(main.method1("  -9"));


        //System.out.println(main.myantoni2("  -9"));
        //System.out.println(main.getPermutation(4,2));
        System.out.println(main.divide(-2147483648,-1));
    }

    public long myantoni2(String str){
        long ans = 0;
        if(str == null || "".equals(str) || "".equals(str.trim())){
            return 0;
        }
        char[] c = str.trim().toCharArray();

        boolean isPositive = c[0] == '+' || c[0]!='-';

        //如果第一位是符号位，则从下标1开始
        int index = c[0] == '+'|| c[0] == '-'?1:0;

        if(c.length == 1){
            if(Character.isDigit(c[0]))
                return c[0] = '0';
            else
                return 0;
        }

        boolean stop = false;
        while(index<c.length) {
            if(!Character.isDigit(c[index]) && c[index] != '.')
                return 0;
            if( Character.isDigit(c[index])){
                if(stop) {
                    index++;
                    continue;
                }
            int next = c[index] - '0';
            ans = ans*10+next;
            index++;
            }else if(c[index] == '.'){
                if(index == c.length - 1)
                    return 0;
                if(stop)
                    return 0;
                stop = true;
                index++;
            }else {
                return 0;
            }
        }
            return isPositive?ans:-ans;
    }

    public int ballsAndBaskets(){
        int ans = 0;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[] balls = new int[n];
        for(int i = 0; i<n; i++){
            balls[i] = in.nextInt();
        }

        Map<Integer, Integer> map = new HashMap<>();

        for(int ball:balls){
            int b = ball;
            if(map.containsKey(b)){
                map.put(b, map.get(b)+1);
            }else{
                map.put(b, 1);
            }
        }

        //如果有单独小于两个的彩球
        for(Integer key:map.keySet()){
            if(map.get(key)<2){
                return 0;
            }
        }

        //看最少球的篮子装了几个球
        int min = Integer.MAX_VALUE;
        for(Integer key:map.keySet()){
            if(min>map.get(key))
                min = map.get(key);
        }

        //如果最少球不能被2整除，则只能每个篮子放min个球，不符合则返回0
        if(min % 2!=0){
            for(Integer key:map.keySet()){
                if(map.get(key) % min == 0){
                    ans+=map.get(key) / min;
                }else
                    return 0;
            }
            return ans;
        }


        //如果最少球可以被2整除，先尝试每个篮子放min，不行则每个篮子放min / 2，一直到每个篮子放2个，一直不行就返回0
        int capacity = min;
        while(capacity>=2){
            for(Integer key:map.keySet()){
                if(map.get(key) % capacity == 0){
                    ans+=map.get(key) / capacity;
                }else {
                    ans = 0;
                    capacity = capacity / 2;
                    break;
                }
            }
            return ans;
        }

        return 0;
    }

    //8. 字符串转换整数 (atoi)
    public int myAtoi(String str) {
        int ans = 0;
        if(str == null || "".equals(str) || "".equals(str.trim())){
            return 0;
        }

        char[] c = str.trim().toCharArray();

        boolean isPositive = c[0] == '+' || c[0]!='-';

        //如果第一位是符号位，则从下标1开始
        int index = c[0] == '+'|| c[0] == '-'?1:0;

        while(index<c.length && Character.isDigit(c[index])){
            int next = c[index] - '0';

            //判断把这个数字加进来之后是否会溢出
            if(ans>(Integer.MAX_VALUE - next) / 10){
                //直接返回正或负的整数边界值
                return isPositive?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }else{
                ans = ans*10+next;
            }
            index++;
        }
        //最后记得判断正负
        return isPositive?ans:-ans;
    }

    //12. 整数转罗马数字
    //像这种可以把所有情况记录下来的，类似三指针的，直接弄一个数组放每种情况，遍历即可
    public String intToRoman(int num) {
        StringBuffer ans = new StringBuffer("");
        int[] nums = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] str = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        //一共13种情况
        for(int i = 0; i<13; i++){
            while(num>=nums[i]){
                num = num - nums[i];
                ans.append(str[i]);
            }
        }
        return ans.toString();
    }

    //43. 字符串相乘，emmm，类似大数相乘
    public String multiply(String num1, String num2) {
        StringBuffer ans = new StringBuffer("");

        //result[i+j]到result[i+j+1]是num1的第i位 * num2的第j位
        int[] result = new int[num1.length()+num2.length()];

        for(int i = num1.length() - 1; i>=0; i--){
            for(int j = num2.length() - 1; j>=0; j--){
                int multi = (num1.charAt(i)-'0') *(num2.charAt(j)-'0');
                multi+=result[i+j+1];
                int add = multi / 10;
                result[i+j+1] = multi % 10;
                result[i+j]+=add;
            }
        }

        boolean isZero = true;
        for(int i = 0; i<num1.length()+num2.length(); i++){
            if(isZero && result[i] == 0)
                continue;
            ans.append(result[i]);
            isZero = false;
        }
        if(isZero)
            return "0";
        return ans.toString();
    }

    //60. 第k个排列，不用求出所有序列，如果刚好落在循环内则顺序添加list元素
    public String getPermutation(int n, int k) {
        StringBuffer ans = new StringBuffer("");


        List<Integer> list = new ArrayList<>();
        for(int i = 0; i<n; i++){
            list.add(i+1);
        }

        k--;

        while(list.size()!=0){
            int index = k / permutation(n-1);
            ans.append(list.get(index));
            list.remove(index);
            k = k % permutation(n-1);
            n--;
        }
        return ans.toString();
    }
    //求阶乘
    public int permutation(int n){
        if(n == 1 || n == 0)
            return 1;
        else return n*permutation(n-1);
    }


    //166. 分数到小数
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuffer ans = new StringBuffer("");
        //考虑是否为负数，还要考虑溢出= =
        //好坑，存在使用abs会溢出的测试用例= =  要用Long
        Long dummyNumerator = new Long(numerator);
        Long dummyDenominator = new Long(denominator);

        if(dummyNumerator*dummyDenominator<0){
            ans.append("-");
        }

        dummyDenominator = Math.abs(dummyDenominator);
        dummyNumerator = Math.abs(dummyNumerator);

        //先算整数部分
        if(dummyNumerator/dummyDenominator!=0)
            ans.append(String.valueOf(dummyNumerator/dummyDenominator));
        else
            ans.append(String.valueOf("0"));

        dummyNumerator = dummyNumerator%dummyDenominator;
        if(dummyNumerator == 0)
            return ans.toString();

        ans.append(String.valueOf("."));
        //如果有余数，则余数乘10，接着往下除，得到的商是小数点后的数字
        //只要出现一个出现过的余数就可以返回了。注意是出现一个余数！！而不是小数中的随便一位！！！
        Map<Long, Integer> map = new HashMap<>();
        while(dummyNumerator!=0){
            dummyNumerator*=10;
            Long value = dummyNumerator / dummyDenominator;
            if(map.containsKey(dummyNumerator)){
                int index = map.get(dummyNumerator);
                ans.insert(index,"(");
                ans.append(")");
                return ans.toString();
            }else{
                map.put(dummyNumerator,ans.length());
                ans.append(String.valueOf(value));
                dummyNumerator = dummyNumerator%dummyDenominator;
            }
        }
        return ans.toString();
    }

    /**
     * 解题思路：这题是除法，所以先普及下除法术语
     * 商，公式是：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，是一种数学术语。
     * 在一个除法算式里，被除数、余数、除数和商的关系为：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，
     * 进而推导得出：商×除数+余数=被除数。
     *
     * 要求商，我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
     *
     * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
     *
     * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
     *
     * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
     *
     * 我们可以以100/3为例
     *
     * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
     *
     * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
     *
     * 所以一共是减去了33个3，所以商就是33
     *
     * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
     *
     */
    //29. 两数相除
    public int divide(int dividend, int divisor) {//被除数 dividend 和除数 divisor
        int ans = 0;
        //要考虑溢出的问题、除数为0等等情况
        if(divisor == 0)
            return 0;
        if(dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }
        if(dividend == Integer.MIN_VALUE && divisor == 1)
            return Integer.MIN_VALUE;
        //考虑负数问题，使用异或
        boolean isNegative = (dividend^divisor)<0;

        long dir = Math.abs((long)divisor);
        long did = Math.abs((long)dividend);



        for(int i = 31; i>=0; i--){
            if(did>>i >=dir){
                ans+=(1<<i);
                did = did - (dir<<i);
            }
        }

        return isNegative?-ans:ans;
    }


    //799. 香槟塔
    public double champagneTower(int poured, int query_row, int query_glass) {
        double ans = 0;

        double[][] champagne = new double[query_row+2][query_glass+2];
        champagne[0][0] = poured;

        for(int i = 0; i<query_row+1; i++){
            for(int j = 0; j<query_glass+1; j++){
                if(champagne[i][j]>1){
                    double left = champagne[i][j] - 1;
                    champagne[i][j] = 1;
                    champagne[i+1][j] +=left/2;
                    champagne[i+1][j+1] +=left/2;
                }
            }
        }
        ans = champagne[query_row][query_glass];
        return ans;
    }

    //1015. 可被 K 整除的最小整数
    //尾数为0,2,4,5,6,8的数乘以整数所得的结果尾数一定不为1；
    // 或者反过来说尾数为1,3,7,9(不被2、5整除)一定会有结果
    // (1,3,7,9乘以整数乘出的数的尾数包含0-9所有整数所以一定会凑出一个数，相乘结果全为1)
    //其实就是个数学定理= =
    //而且不能用循环1...11111来判断是否整除，会超时间。用除法的位除来判断，返回位数即可
    public int smallestRepunitDivByK(int K) {
        if(K%2 == 0 || K%5 == 0)
            return -1;
        int i = 1,n=1;
        while(n%K!=0){
            n = n%K;//除法的位除
            n = n*10 +1;
            i++;
        }
        return i;
    }

}
