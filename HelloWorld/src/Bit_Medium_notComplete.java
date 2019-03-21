public class Bit_Medium_notComplete {
    public static void main(String[] args){

    }

    //137. 只出现一次的数字 II
    public int singleNumber(int[] nums) {
        int ans = 0;
        int bitNums[] = new int[32];
        //初始化所有位数上1的个数
        for(int i = 0; i<32; i++){
            for(int j = 0; j<nums.length; j++){
                bitNums[i]+=(nums[j]>>i)&1;
            }
        }

        //每一位上1的个数除以3，如果不为0则该位上的1是属于只出现一次的数字
        for(int i = 0; i<32; i++){
            if(bitNums[i]%3!=0){
                ans+=1<<i;
            }
        }
        return ans;
    }
}
