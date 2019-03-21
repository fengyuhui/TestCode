package easy;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Bit_easy_notComplete {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        System.out.println(a);

        //nextInt是会断开空格的，所以如果一行输入2 3，那么a = 2, b = 3
        for(int i = 0; i<a; i++) {
            int b = in.nextInt();
            System.out.println(b);
        }

        //有坑！readInt了之后，如果回车是会被接收到nextLine的值的！！
        //所以只能在nextInt后面再来一次nextLine，来消除回车
        in.nextLine();
        String test = in.nextLine();
        System.out.println(test);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String str = bufferedReader.readLine();
            System.out.println(str);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    //136. 只出现一次的数字 异或运算
    public int singleNumber(int[] nums) {
        int result = nums[0];
        for(int i = 1; i<nums.length; i++){
            result = result ^ nums[i];
        }
        return result;
    }

    //169. 求众数 摩尔投票
    public int majorityElement(int[] nums) {
        int result = nums[0];
        int count = 1;
        for(int i = 1; i<nums.length; i++){
            if(result == nums[i])
                count++;
            else{
                if(count > 0)
                    count --;
                else {
                    count = 1;
                    result = nums[i];
                }
            }
        }
        return result;
    }

    //389. 找不同 同样是位运算的异或运算
    public char findTheDifference(String s, String t) {
        char result = t.charAt(0);
        for(int i = 0, j = 1; i<s.length() && j<t.length(); i++, j++){
            result ^= s.charAt(i);
            result ^= t.charAt(j);
        }
        return result;
    }


    //231. 2的幂 可以通过while循环来查找n里有多少1;因为2的幂一定只有一个1或者0个1
    public boolean isPowerOfTwo(int n) {
        if(n<0){
            return false;
        }
        if(n == 0)
            return false;
        n = n&(n-1);
        if(n == 0)
            return true;
        else
            return false;
    }

    //268. 缺失数字 位运算
    public int missingNumber(int[] nums) {

        int[] nums2 = new int[nums.length + 1];
        for(int i = 0; i<nums.length + 1; i++){
            nums2[i] = i;
        }
        int result = nums2[0];
        for(int i = 0; i<nums.length; i++){
            result^=nums[i];
            result^=nums2[i+1];
        }
        return result;
    }

    //371. 两整数之和 a ^ b是无进位的相加； a&b得到每一位的进位;得到进位后还需要左移一位；两者相加就相当于在应该进1的位置上加了1；然后递归相加直到没有进位，就不需要再在进1位置上加1
    public int getSum(int a, int b) {
        int result = 0;
        int sum = a^b;
        int addOne = (a&b)<<1;
        if(addOne == 0)
            return sum;
        else{
            result = getSum(sum, addOne);
            return result;
        }
    }

    //342. 4的幂 一定只有一个1，且这个1在奇数位上
    public boolean isPowerOfFour(int num) {
        if(num<0 || num == 0){
            return false;
        }
        if((num&(num-1)) == 0){
            if((num&0x55555555)!=0)
                return true;
            else
                return false;
        }else
            return false;
    }

    //191. 位1的个数
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        while(n!=0){
            n = n&(n-1);
            result++;
        }
        return result;
    }


}
