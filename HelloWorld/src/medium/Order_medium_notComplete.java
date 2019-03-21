package medium;

import java.util.*;

public class Order_medium_notComplete {
    public static void main(String[] args){

        //
        int[] a = {1,1,2,0,2,1,0,0,2,2};
        sortColors(a);
    }

    //56. �ϲ�����

    public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
    public List<Interval> merge(List<Interval> intervals) {
        /**
         ������start����, �����ж�������ÿ������, ������������е����һ�������end
         ���ڵ��ڵ�ǰ�����start, �������һ�������end, ����ֱ������뷵��ֵ��.
         **/
        LinkedList<Interval> ret = new LinkedList<>();
        Collections.sort(intervals, (a, b) -> (a.start - b.start));
        for(Interval temp : intervals) {
            if(ret.isEmpty() || ret.peekLast().end < temp.start)
                ret.add(temp);
            else
                ret.peekLast().end = Math.max(ret.peekLast().end, temp.end);
        }
        return ret;
    }

    //75. ��ɫ����
    public static void sortColors(int[] nums) {
        int size = nums.length;
        for(int i = 0, j = 0, k = size - 1, p = size - 1; (i<size&&j<size)||(k>-1&&p>-1);){
            if(nums[i]!=0){
                while(j<size) {
                    if(nums[j]==0) {
                        swap(nums, i, j);
                        break;

                    }
                    else {
                        j++;
                    }
                }
            }else{
                i++;
            }

            if(j<i)
                j = i;

            if(nums[k]!=2){
                while(p>-1) {
                    if(nums[p]==2){
                        swap(nums, k, p);
                        break;
                    }else{
                        p--;

                    }
                }
            }else{
                k--;
            }
            if(p>k)
                p = k;
        }
        return;
    }
    public static void swap(int[] nums, int k, int j){
        int temp = nums[k];
        nums[k] = nums[j];
        nums[j] = temp;
    }
}
