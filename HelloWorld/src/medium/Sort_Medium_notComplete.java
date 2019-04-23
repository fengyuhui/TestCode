package medium;

import java.util.*;

public class Sort_Medium_notComplete {
    //451. 根据字符出现频率排序
    public String frequencySort(String s) {
        StringBuffer ans = new StringBuffer("");
        Map<Character, Integer> map = new HashMap<>();
        char[] temp = s.toCharArray();
        for(Character a:temp){
            if(map.containsKey(a)){
                map.put(a, map.get(a)+1);
            }else{
                map.put(a,1);
            }
        }

        for(int i = s.length(); i>0; i--) {
            for (Map.Entry entry : map.entrySet()) {
                if(i == (int)entry.getValue()){
                    for(int j=0; j<i; j++) {
                        ans.append(entry.getKey());
                    }
                }
            }
        }

        return ans.toString();
    }

    //378. 有序矩阵中第K小的元素
    public int kthSmallest(int[][] matrix, int k) {
        int ans = 0;

        //默认是小顶堆，但是可以通过传入自定义的Comparator函数来实现大顶堆
        Queue<Integer> heap = new PriorityQueue<>(k, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                //就刚好反过来
                return o2.compareTo(o1);
            }
        });

        for(int[] m:matrix){
           for(int value:m){
               if(heap.size()<k){
                   heap.add(value);
               }else{
                   if(value<heap.peek()){
                       heap.poll();
                       heap.add(value);
                   }
               }
           }
        }

        ans = heap.peek();
        return ans;
    }
}
