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

        //�����һλ�Ƿ���λ������±�1��ʼ
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

        //����е���С�������Ĳ���
        for(Integer key:map.keySet()){
            if(map.get(key)<2){
                return 0;
            }
        }

        //�������������װ�˼�����
        int min = Integer.MAX_VALUE;
        for(Integer key:map.keySet()){
            if(min>map.get(key))
                min = map.get(key);
        }

        //����������ܱ�2��������ֻ��ÿ�����ӷ�min���򣬲������򷵻�0
        if(min % 2!=0){
            for(Integer key:map.keySet()){
                if(map.get(key) % min == 0){
                    ans+=map.get(key) / min;
                }else
                    return 0;
            }
            return ans;
        }


        //�����������Ա�2�������ȳ���ÿ�����ӷ�min��������ÿ�����ӷ�min / 2��һֱ��ÿ�����ӷ�2����һֱ���оͷ���0
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

    //8. �ַ���ת������ (atoi)
    public int myAtoi(String str) {
        int ans = 0;
        if(str == null || "".equals(str) || "".equals(str.trim())){
            return 0;
        }

        char[] c = str.trim().toCharArray();

        boolean isPositive = c[0] == '+' || c[0]!='-';

        //�����һλ�Ƿ���λ������±�1��ʼ
        int index = c[0] == '+'|| c[0] == '-'?1:0;

        while(index<c.length && Character.isDigit(c[index])){
            int next = c[index] - '0';

            //�жϰ�������ּӽ���֮���Ƿ�����
            if(ans>(Integer.MAX_VALUE - next) / 10){
                //ֱ�ӷ������򸺵������߽�ֵ
                return isPositive?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }else{
                ans = ans*10+next;
            }
            index++;
        }
        //���ǵ��ж�����
        return isPositive?ans:-ans;
    }

    //12. ����ת��������
    //�����ֿ��԰����������¼�����ģ�������ָ��ģ�ֱ��Ūһ�������ÿ���������������
    public String intToRoman(int num) {
        StringBuffer ans = new StringBuffer("");
        int[] nums = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] str = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        //һ��13�����
        for(int i = 0; i<13; i++){
            while(num>=nums[i]){
                num = num - nums[i];
                ans.append(str[i]);
            }
        }
        return ans.toString();
    }

    //43. �ַ�����ˣ�emmm�����ƴ������
    public String multiply(String num1, String num2) {
        StringBuffer ans = new StringBuffer("");

        //result[i+j]��result[i+j+1]��num1�ĵ�iλ * num2�ĵ�jλ
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

    //60. ��k�����У���������������У�����պ�����ѭ������˳�����listԪ��
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
    //��׳�
    public int permutation(int n){
        if(n == 1 || n == 0)
            return 1;
        else return n*permutation(n-1);
    }


    //166. ������С��
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuffer ans = new StringBuffer("");
        //�����Ƿ�Ϊ��������Ҫ�������= =
        //�ÿӣ�����ʹ��abs������Ĳ�������= =  Ҫ��Long
        Long dummyNumerator = new Long(numerator);
        Long dummyDenominator = new Long(denominator);

        if(dummyNumerator*dummyDenominator<0){
            ans.append("-");
        }

        dummyDenominator = Math.abs(dummyDenominator);
        dummyNumerator = Math.abs(dummyNumerator);

        //������������
        if(dummyNumerator/dummyDenominator!=0)
            ans.append(String.valueOf(dummyNumerator/dummyDenominator));
        else
            ans.append(String.valueOf("0"));

        dummyNumerator = dummyNumerator%dummyDenominator;
        if(dummyNumerator == 0)
            return ans.toString();

        ans.append(String.valueOf("."));
        //�������������������10���������³����õ�������С����������
        //ֻҪ����һ�����ֹ��������Ϳ��Է����ˡ�ע���ǳ���һ����������������С���е����һλ������
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
