public class Greedy_Meiun_notComplete {
    public static void main(String[] args){
        int[] nums = {3,2,1,0,4};
        Greedy_Meiun_notComplete object = new Greedy_Meiun_notComplete();
        object.canJump(nums);
    }

    //55. 跳跃游戏
    public boolean canJump(int[] nums) {
        if(nums!=null) {
            int max = nums[0];
            for(int i = 0; i<nums.length; i++){
                if(i>max)
                    return false;
                if(nums[i]+i >max) {
                    max = nums[i] + i;
                }
            }
            if(max < nums.length - 1)
                return false;
            else
                return true;
        }
        return false;
    }

}
