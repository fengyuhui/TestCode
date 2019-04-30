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

    //1004. �������1�ĸ��� III
    //��������
    public int longestOnes(int[] A, int K) {
        int ans = 0;
        int restK = K;
        int right = 0, left = 0;
        while(right<A.length){
            //������ת��0
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

    //3. ���ظ��ַ�����Ӵ�
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

    //424. �滻�����ظ��ַ�����һ�ַ���������26����ĸ���������������Ӷ�n^2
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

    //424. �滻�����ظ��ַ����ڶ��ַ��������Ӷ�n���������ڣ���������ռ�
    public int characterReplacement2(String s, int k) {
        int ans = 0;

        int[] count = new int[26];
        int curMax = 0, left = 0;

        for(int right = 0; right<s.length(); right++){
            count[s.charAt(right)-'A']++;
            curMax = Math.max(curMax, count[s.charAt(right)-'A']);

            //����ַ���Ҫ���滻�����������ṩ�ģ���Ҫ��С����
            if(right-left+1>curMax+k){
                count[s.charAt(left)-'A']--;
                left++;
            }
            ans = Math.max(ans, right-left+1);
        }

        return ans;
    }

    //209. ������С�������飬���ͻ�������
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

    //567. �ַ���������
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


    //76. ��С�����Ӵ�
    /*���ǣ����Կ���һ����СΪ64�����飬�������������ĸ��Ƶ��(Frequency)��׼ȷ��˵��
    ͨ����ĸ��ASCII����Ϊ��������������ٿռ�Ĵ�СΪ26+6+26=58��26����д��ĸ��26��Сд��ĸ����˵Ϊʲô������Ϊ��ĸ��24����= =
    �����м��6������ĸ  A~Z[65~90]  ����ĸ[91~96]  a~z[97~122]*/
    //�Ժ���������Բ���map�ˣ�map����ķ�
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
            //������С��t.lengthʱ����ָ�����һ���
            if(right-left+1<t.length()){
                //Ҫ�ж�����Խ��
                if(right<s.length()-1){
                    right++;
                    pNums[s.charAt(right)-'A']++;
                    continue;
                }else{
                    break;
                }
            }else{//�����ڴ�С����t.length()ʱ���ж��Ƿ�����������Ǿͷ���
                int i = 0;
                for(; i<64; i++){
                    if(nums[i]>pNums[i]) {
                        break;
                    }
                }
                //����������
                if(i<64){
                    //��ָ��û�е����Ҷ�ʱ����ָ�뻬��
                    if(right<s.length()-1){
                        right++;
                        pNums[s.charAt(right)-'A']++;
                        continue;
                    }else{
                        //��ָ�뵽���Ҷ�ʱ����ָ�뻬��
                        pNums[s.charAt(left)-'A']--;
                        left++;
                    }
                }else{
                    //���������һ�����С����t.length��ֱ�ӷ���
                    if(right-left+1==t.length()){
                        return s.substring(left, right+1);
                    }else{
                        if(resultR-resultL>right-left){
                            resultL = left;
                            resultR = right;
                        }

                        //���Ƿ��������£��������ڴ���t.length��һ���������ƶ���ָ�룬Ȼ���ٲ鿴�����Ƿ��и�С��
                        //��Ϊһ���Ƿ����������������������Ǹ��ַ�һ������ָ��Ŀǰ��ָ
                        pNums[s.charAt(left)-'A']--;
                        left++;
                    }
                }
            }
        }

        return resultR==s.length()+1?"":s.substring(resultL, resultR+1);
    }

    //978. �����������
    //������������İ��о�
    /*��Ȼ������ֻ��Ҫ��ע������������֮��ķ��žͿ����ˡ� ����� -1, 0, 1 ����ȽϷ��Ļ����ֱ��Ӧ <�� =�� >����
    ��ô���ǵ�Ŀ������ڷ����������ҵ�һ�����Ԫ�ؽ��������� 1, -1, 1, -1, ...���� 1 ���� -1 ��ʼ�����ԣ���
    ��Щ����ıȽϷ����γ����ɸ������Ŀ� ������֪����ʱһ�������������Ѿ�����������ĩβ��ʱ����ߵ�����Ԫ�ز��ٽ����ʱ��
    ��һ�����ӣ������������Ϊ A = [9,4,2,10,7,8,8,1,9]����ô�������о��� [1,1,-1,1,-1,0,-1,1]�������Ա����ֳɵĿ�Ϊ [1], [1,-1,1,-1], [0], [-1,1]��*/
    //�ǳ������������compare���������
    public int maxTurbulenceSize(int[] A) {
        int ans = 1;
        int flag = 0;

        for(int i = 1; i<A.length; i++){
            int temp = Integer.compare(A[i-1], A[i]);
            if(i == A.length-1 || temp * Integer.compare(A[i], A[i+1]) != -1){
                if(temp!=0){
                    //��ֹһֱ�������ֵ������ans�����2��ֻҪǰ��ͬ����length��С��2���������ǰ����ͬ����lengthֻ����1
                    ans = Math.max(ans, i-flag+1);
                }
                flag = i;

            }
        }

        return ans;
    }

    //239. �����������ֵ
    //˫�����ʵ��
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length <= 1)
            return nums;

        int[] ans = new int[nums.length-k+1];
        //˫�����
        int left = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i<nums.length; i++){
            //�ѳ����ڵ�ֵ������
            if(i>=k && deque.getFirst()<=i-k)
                deque.pollFirst();
            //������Ԫ�ز�Ϊ���Ҷ���Ԫ��С�ڵ�ǰԪ�أ���������
            while(!deque.isEmpty() && nums[deque.peekLast()]<=nums[i])
                deque.pollLast();

            //ѹ������Ԫ�أ�����С�Ķ�������ȥ�ˣ�ʣ�µĶ����Ǳ������
            deque.offerLast(i);
            if(i>=k-1){
                ans[i-k+1] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }

    //480. ����������λ��
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] ans = new double[nums.length-k+1];



        return ans;
    }


    //992. K ����ͬ������������
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

            //������ڵ��ھ������������window1��ͬ�ĸ�����
            //window1�������K-1����ͬ
            //window2�������K����ͬ
            //��պ�K����ͬ����window2-window1
            while(window2.getDiff()>=K){
                window2.remove(A[left2]);
                left2++;
            }

            ans+=left2-left1;
        }
        return ans;
    }

    //992�ĸ����ࡪ����������
    class Window{
        int diff;
        Map<Integer, Integer> count;
        Window(){
            diff = 0;
            count = new HashMap<>();
        }

        void add(int a){
            //ϲ���ձ�����jdk1.8�ṩmap��һ��put���������key��������put(key, defaultValue)
            //��Ҳ���ÿ������ÿ�����ж��ˣ�
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


    //995. K ����λ����С��ת����
    public int minKBitFlips(int[] A, int K) {
        int ans = 0;



        return ans;
    }
}
