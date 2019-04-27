package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Math_Medium_notComplete {
    public static void main(String[] args){
        Math_Medium_notComplete main = new Math_Medium_notComplete();
        //main.myAtoi("-91283472332");
        //main.method2();
        //System.out.println(main.method1("  -9"));

    }

    public long method1(String str){
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

    public int method2(){
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

}
