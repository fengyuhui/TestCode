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
        System.out.println(main.fractionToDecimal(2,3));
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


}
