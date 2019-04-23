package medium;

public class Math_Medium_notComplete {
    public static void main(String[] args){
        Math_Medium_notComplete main = new Math_Medium_notComplete();
        main.myAtoi("-91283472332");
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

}
