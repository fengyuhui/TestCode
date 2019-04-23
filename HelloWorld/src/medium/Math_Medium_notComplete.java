package medium;

public class Math_Medium_notComplete {
    public static void main(String[] args){
        Math_Medium_notComplete main = new Math_Medium_notComplete();
        main.myAtoi("-91283472332");
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
