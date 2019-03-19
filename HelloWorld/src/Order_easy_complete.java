import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Order_easy_complete {
    public static void main(String[] args){
        //
        int[] a = {3,4};
        sortArrayByParityII(a);
    }

    //242. 有效的字母异位词，可以假设字符串s和t都只包含小写字母
    public boolean isAnagram(String s, String t) {
        boolean isAnagram = true;
        int[] s1 = new int[26];
        int[] t1 = new int[26];

        int size1 = s.length();
        int size2 = t.length();
        if(size1!=size2)
            return false;
        for(int i = 0; i<size1; i++){
            s1[s.charAt(i) - 'a']++;
            t1[t.charAt(i) - 'a']++;
        }

        for(int i= 0; i<26; i++){
            if(s1[i]!=t1[i])
                return false;
        }
        return isAnagram;
    }

    //349. 两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i<nums1.length; i++){
            for(int j = 0; j<nums2.length; j++){
                if(nums1[i] == nums2[j]){
                    if(!list.contains(nums1[i]))
                        list.add(nums1[i]);
                }
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //350. 两个数组的交集 II
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //922. 按奇偶排序数组 II
    public static int[] sortArrayByParityII(int[] A) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        for(int i = 0; i<A.length; i++){
            if(i % 2 == 0){
                if(A[i] % 2 != 0){
                    stack1.push(i);
                }
            }else{
                if(A[i] % 2 == 0){
                    stack2.push(i);
                }
            }
        }
        int temp = 0;
        int i = 0, j = 0;
        while(!stack1.empty()){
            i = stack1.pop();
            j = stack2.pop();
            temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
        return A;
    }

}
