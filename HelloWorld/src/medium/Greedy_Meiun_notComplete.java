package medium;

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

    //134. 加油站 分段不重要，只要全部油>消耗油，就一定能回到起点。所以重点就是找出起点而已
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, start = 0, currentSums = 0;
        for(int i = 0; i<gas.length; i++){
            total+=gas[i] - cost[i];
            currentSums+=gas[i] - cost[i];
            if(currentSums<0){
                currentSums = 0;
                start = i+1;
            }
        }
        if(total<0)
            return -1;
        else
            return start;
    }
}
