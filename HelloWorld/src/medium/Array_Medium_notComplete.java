package medium;

public class Array_Medium_notComplete {
    public static void main(String[] args){
        Array_Medium_notComplete main = new Array_Medium_notComplete();
        int[] height = {1,8,6,2,5,4,8,3,7};
        main.maxArea(height);

    }

    //11. 盛最多水的容器，当然这个是有复杂度n的最优解，现在用的是暴力复杂度n^2的解
    public int maxArea(int[] height) {
        int ans = Integer.MIN_VALUE;
        int currentMax = 0;
        int temp = 0, minH = 0;
        for(int i = 0; i<height.length; i++){
            currentMax = 0;
            minH = Integer.MAX_VALUE;
            for(int j = i; j<height.length; j++){
                minH = Math.min(height[i], height[j]);
                temp = (j - i) * minH;
                if(temp>currentMax)
                    currentMax = temp;
            }
            if(ans<currentMax)
                ans = currentMax;
        }
        return ans;
    }
}
