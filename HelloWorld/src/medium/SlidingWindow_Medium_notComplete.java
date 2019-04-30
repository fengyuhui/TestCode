package medium;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

public class SlidingWindow_Medium_notComplete {
    public static void main(String[] args){
        SlidingWindow_Medium_notComplete main = new SlidingWindow_Medium_notComplete();
        //int[] A = {1,1,1,0,1,0,1};
        //main.longestOnes(A, 1);
        //main.characterReplacement("ABAB",2);
        //int[] A = {1,2,3,4,5};
        //main.minSubArrayLen(11,A);
        //main.checkInclusion("hello","ooolleoooleh");
        //System.out.println(main.minWindow("ABAB", "AB"));
        int[] a = {1,3,1,2,0,5};
        System.out.println(main.maxSlidingWindow(a,3));
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

    //424. 替换后的最长重复字符，第一种方法，遍历26个字母的情况，看最长，复杂度n^2
    public int characterReplacement(String s, int k) {
        int ans = 0;

        for(char c = 'A'; c<='Z'; c++){
            int left = 0;
            int max = 0, restK = k;
            for(int right = 0; right<s.length(); right++){
                if(s.charAt(right)!=c){
                    restK--;
                }
                if(restK<0){
                    if(s.charAt(left)!=c){
                        left++;
                        restK++;
                    }else{
                        left++;
                    }
                }
                max = Math.max(right-left+1, max);
            }
            ans = Math.max(max,ans);
        }

        return ans;
    }

    //424. 替换后的最长重复字符，第二种方法，复杂度n，滑动窗口，借助额外空间
    public int characterReplacement2(String s, int k) {
        int ans = 0;

        int[] count = new int[26];
        int curMax = 0, left = 0;

        for(int right = 0; right<s.length(); right++){
            count[s.charAt(right)-'A']++;
            curMax = Math.max(curMax, count[s.charAt(right)-'A']);

            //最多字符需要的替换次数大于所提供的，需要缩小窗口
            if(right-left+1>curMax+k){
                count[s.charAt(left)-'A']--;
                left++;
            }
            ans = Math.max(ans, right-left+1);
        }

        return ans;
    }

    //209. 长度最小的子数组，典型滑动窗口
    public int minSubArrayLen(int s, int[] nums) {
        int ans = 0;
        int curSums = 0, length = Integer.MAX_VALUE, left = 0;
        for(int right = 0; right<nums.length; right++){
            curSums+=nums[right];
            while(curSums>=s){
                length = Math.min(length, right-left+1);
                curSums-=nums[left];
                left++;
            }
        }
        ans = length==Integer.MAX_VALUE?0:length;
        return ans;
    }

    //567. 字符串的排列
    public boolean checkInclusion(String s1, String s2) {
        boolean ans = false;

        if("".equals(s1))
            return true;
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i<s1.length(); i++){
            if(map.containsKey(s1.charAt(i))){
                map.put(s1.charAt(i),map.get(s1.charAt(i))+1);
            }else{
                map.put(s1.charAt(i),1);
            }
        }
        Map<Character, Integer> map2 = new HashMap<>(map);
        int left = 0;

        for(int right = 0;right<s2.length(); right++){
            char temp = s2.charAt(right);
            if(right-left+1<s1.length()){
                if(map2.containsKey(temp)){
                    map2.put(temp,map2.get(temp)-1);
                    while(map2.get(temp)<0&&left<=right){
                        map2.put(s2.charAt(left),map2.get(s2.charAt(left))+1);
                        left++;
                    }
                }else{
                    left = right+1;
                    map2 = new HashMap<>(map);
                }
            }else{
                if(map2.containsKey(temp)){
                    map2.put(temp,map2.get(temp)-1);
                    for(Map.Entry entry:map2.entrySet()){
                        if((int)entry.getValue() != 0){
                            map2.put(s2.charAt(left), map2.get(s2.charAt(left))+1);
                            left++;
                            ans = false;
                            break;
                        }else{
                            ans = true;
                        }
                    }
                    if(ans)
                        return ans;
                }else{
                    left = right+1;
                    map2 = new HashMap<>(map);
                }
            }
        }
        return ans;
    }


    //76. 最小覆盖子串
    /*于是，可以开辟一个大小为64的数组，来存放数组中字母的频率(Frequency)。准确的说，
    通过字母的ASCII码作为数组的索引，开辟空间的大小为26+6+26=58：26个大写字母，26个小写字母，话说为什么我总以为字母是24个啊= =
    还有中间的6个非字母  A~Z[65~90]  非字母[91~96]  a~z[97~122]*/
    //以后这种题可以不用map了，map是真的烦
    public String minWindow(String s, String t) {
        int[] nums = new int[64];
        int[] pNums = new int[64];
        for(int i = 0; i<t.length(); i++){
            nums[t.charAt(i)-'A']++;
        }

        if(s.length()<t.length())
            return "";
        int left = 0,right = -1;
        int resultL = 0, resultR = s.length()+1;

        while(left<=s.length()-t.length()){
            //当窗口小于t.length时，右指针向右滑动
            if(right-left+1<t.length()){
                //要判断数组越界
                if(right<s.length()-1){
                    right++;
                    pNums[s.charAt(right)-'A']++;
                    continue;
                }else{
                    break;
                }
            }else{//当窗口大小等于t.length()时，判断是否符合条件，是就返回
                int i = 0;
                for(; i<64; i++){
                    if(nums[i]>pNums[i]) {
                        break;
                    }
                }
                //不符合条件
                if(i<64){
                    //右指针没有到最右端时，右指针滑动
                    if(right<s.length()-1){
                        right++;
                        pNums[s.charAt(right)-'A']++;
                        continue;
                    }else{
                        //右指针到最右端时，左指针滑动
                        pNums[s.charAt(left)-'A']--;
                        left++;
                    }
                }else{
                    //符合条件且滑窗大小等于t.length，直接返回
                    if(right-left+1==t.length()){
                        return s.substring(left, right+1);
                    }else{
                        if(resultR-resultL>right-left){
                            resultL = left;
                            resultR = right;
                        }

                        //若是符合条件下，滑动窗口大于t.length，一定是优先移动左指针，然后再查看长度是否还有更小的
                        //因为一旦是符合条件，最后符合条件的那个字符一定是右指针目前所指
                        pNums[s.charAt(left)-'A']--;
                        left++;
                    }
                }
            }
        }

        return resultR==s.length()+1?"":s.substring(resultL, resultR+1);
    }

    //978. 最长湍流子数组
    //题解真是令人拍案叫绝
    /*显然，我们只需要关注相邻两个数字之间的符号就可以了。 如果用 -1, 0, 1 代表比较符的话（分别对应 <、 =、 >），
    那么我们的目标就是在符号序列中找到一个最长的元素交替子序列 1, -1, 1, -1, ...（从 1 或者 -1 开始都可以）。
    这些交替的比较符会形成若干个连续的块 。我们知道何时一个块会结束：当已经到符号序列末尾的时候或者当序列元素不再交替的时候。
    举一个例子，假设给定数组为 A = [9,4,2,10,7,8,8,1,9]。那么符号序列就是 [1,1,-1,1,-1,0,-1,1]。它可以被划分成的块为 [1], [1,-1,1,-1], [0], [-1,1]。*/
    //非常巧妙地用两个compare方法解决了
    public int maxTurbulenceSize(int[] A) {
        int ans = 1;
        int flag = 0;

        for(int i = 1; i<A.length; i++){
            int temp = Integer.compare(A[i-1], A[i]);
            if(i == A.length-1 || temp * Integer.compare(A[i], A[i+1]) != -1){
                if(temp!=0){
                    //防止一直都是相等值，这样ans会等于2。只要前后不同，则length最小是2。但是如果前后相同，则length只能是1
                    ans = Math.max(ans, i-flag+1);
                }
                flag = i;

            }
        }

        return ans;
    }

    //239. 滑动窗口最大值
    //双向队列实现
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length <= 1)
            return nums;

        int[] ans = new int[nums.length-k+1];
        //双向队列
        int left = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i<nums.length; i++){
            //已出窗口的值，弹出
            if(i>=k && deque.getFirst()<=i-k)
                deque.pollFirst();
            //当窗内元素不为空且队首元素小于当前元素，弹出队首
            while(!deque.isEmpty() && nums[deque.peekLast()]<=nums[i])
                deque.pollLast();

            //压入最新元素，比它小的都被弹出去了，剩下的队首是比它大的
            deque.offerLast(i);
            if(i>=k-1){
                ans[i-k+1] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }

    //480. 滑动窗口中位数
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] ans = new double[nums.length-k+1];



        return ans;
    }


    //992. K 个不同整数的子数组
    public int subarraysWithKDistinct(int[] A, int K) {
        int ans = 0;
        Window window1 = new Window();
        Window window2 = new Window();
        int left1 = 0, left2 = 0;

        for(int i = 0; i<A.length; i++){
            int temp = A[i];
            window1.add(temp);
            window2.add(temp);

            while(window1.getDiff()>K){
                window1.remove(A[left1]);
                left1++;
            }

            //这个大于等于就是用来计算和window1不同的个数的
            //window1代表最多K-1个不同
            //window2代表最多K个不同
            //则刚好K个不同就是window2-window1
            while(window2.getDiff()>=K){
                window2.remove(A[left2]);
                left2++;
            }

            ans+=left2-left1;
        }
        return ans;
    }

    //992的辅助类——滑动窗口
    class Window{
        int diff;
        Map<Integer, Integer> count;
        Window(){
            diff = 0;
            count = new HashMap<>();
        }

        void add(int a){
            //喜大普奔！！jdk1.8提供map的一个put方法，如果key不存在则put(key, defaultValue)
            //再也不用苦兮兮地每次做判断了！
            count.put(a, count.getOrDefault(a,0)+1);
            if(count.get(a) == 1){
                diff++;
            }
        }

        void remove(int a){
            count.put(a, count.get(a)-1);
            if(count.get(a) == 0){
                diff--;
            }
        }

        int getDiff(){
            return diff;
        }
    }


    //995. K 连续位的最小翻转次数
    public int minKBitFlips(int[] A, int K) {
        int ans = 0;



        return ans;
    }
}
