package medium;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

public class SlidingWindow_Medium_notComplete {
    public static void main(String[] args){
        SlidingWindow_Medium_notComplete main = new SlidingWindow_Medium_notComplete();
        int[] A = {1,1,1,0,1,0,1};
        main.longestOnes(A, 1);
    }

    //1004. 最大连续1的个数 III
    //滑动窗口
    public int longestOnes(int[] A, int K) {
        int ans = 0;
        int restK = K;
        int right = 0, left = 0;
        while(right<A.length){
            //还可以转换0
            if(restK>0){
                if(A[right] == 1){
                    right++;
                }else{
                    right++;
                    restK--;
                }
            }else{
                if(A[right] == 1){
                    right++;
                }else{
                    if(A[left] == 1){
                        left++;
                    }else{
                        left++;
                        restK++;
                    }
                }
            }

            if(right-left>ans)
                ans = right-left;
        }

        return ans;
    }

    //3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int ans = Integer.MIN_VALUE;

        if("".equals(s))
            return 0;

        Map<Character, Integer> map = new HashMap<>();

        char[] chars = s.toCharArray();

        for(int i = 0; i<chars.length; i++){
            if(map.containsKey(chars[i])){
                i=map.get(chars[i]);
                map.clear();
            }else{
                map.put(chars[i],i);
            }
            ans = ans>map.size()?ans:map.size();
        }

        return ans;
    }
}
