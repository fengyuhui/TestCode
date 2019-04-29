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
        main.minWindow("AA", "AA");
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
        String ans="";
        int[] nums = new int[64];
        int[] pNums = new int[64];
        for(int i = 0; i<t.length(); i++){
            nums[t.charAt(i)-'A']++;
        }

        if(s.length()<t.length())
            return "";
        int left = 0,right = 0;

        while(left<=s.length()-t.length()){
            //������С��t.lengthʱ����ָ�����һ���
            if(right-left+1<t.length()){
                //Ҫ�ж�����Խ��
                if(right-left<t.length()){
                    pNums[s.charAt(right)-'A']++;
                    right++;
                    continue;
                }else{
                    break;
                }
            }else if(right-left+1==t.length()){//�����ڴ�С����t.length()ʱ���ж��Ƿ�����������Ǿͷ���
                int i = 0;
                for(; i<64; i++){
                    if(nums[i]!=pNums[i]) {
                        break;
                    }
                }
                //����Ҫ�ǵ��ж�����Խ��
                if(i<64){
                    //��������������ָ�뻬��
                }
            }
        }

        return ans;
    }

}
