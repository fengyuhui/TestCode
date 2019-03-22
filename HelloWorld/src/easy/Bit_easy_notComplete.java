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

        //nextInt�ǻ�Ͽ��ո�ģ��������һ������2 3����ôa = 2, b = 3
        for(int i = 0; i<a; i++) {
            int b = in.nextInt();
            System.out.println(b);
        }

        //�пӣ�readInt��֮������س��ǻᱻ���յ�nextLine��ֵ�ģ���
        //����ֻ����nextInt��������һ��nextLine���������س�
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

    //136. ֻ����һ�ε����� �������
    public int singleNumber(int[] nums) {
        int result = nums[0];
        for(int i = 1; i<nums.length; i++){
            result = result ^ nums[i];
        }
        return result;
    }

    //169. ������ Ħ��ͶƱ
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

    //389. �Ҳ�ͬ ͬ����λ������������
    public char findTheDifference(String s, String t) {
        char result = t.charAt(0);
        for(int i = 0, j = 1; i<s.length() && j<t.length(); i++, j++){
            result ^= s.charAt(i);
            result ^= t.charAt(j);
        }
        return result;
    }


    //231. 2���� ����ͨ��whileѭ��������n���ж���1;��Ϊ2����һ��ֻ��һ��1����0��1
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

    //268. ȱʧ���� λ����
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

    //371. ������֮�� a ^ b���޽�λ����ӣ� a&b�õ�ÿһλ�Ľ�λ;�õ���λ����Ҫ����һλ��������Ӿ��൱����Ӧ�ý�1��λ���ϼ���1��Ȼ��ݹ����ֱ��û�н�λ���Ͳ���Ҫ���ڽ�1λ���ϼ�1
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

    //342. 4���� һ��ֻ��һ��1�������1������λ��
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

    //191. λ1�ĸ���
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
