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

    //274. H指数
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for(int i =citations.length-1; i>=0; i--){
            if(citations[i]>h){
                h=citations.length - i;
            }else if(citations[i]==h){
                return h;
            }else{
                return h;
            }
        }
        return h;
    }

    private class Element implements Comparable<Element>{
        int nums;
        int elemt;
        Element(int nums, int elemt){
            this.nums = nums;
            this.elemt = elemt;
        }
        //默认小顶堆，改写按照nums来排小顶堆，这是小顶堆哦！！
        @Override
        public int compareTo(Element o) {
            return this.nums - o.nums;
        }
    }


    //这是堆排序的内容，先放到这里了
    //347. 前K个高频元素
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        //这种类型的可以自己构造一个实现Comparable接口的类Element，存储值和key，并且重写compareTo方法
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Element> queue = new PriorityQueue<>();

        //把数组的频率放到map里
        for(int val:nums){
            if(map.containsKey(val)){
                map.put(val, map.get(val)+1);
            }else{
                map.put(val,1);
            }
        }

        //放入堆，大顶堆和小顶堆不要弄反了
        for(Integer key:map.keySet()){
            if(queue.size()<k){
                queue.offer(new Element(map.get(key), key));
            }else{
                if(queue.peek().nums<map.get(key)){
                    queue.poll();
                    queue.add(new Element(map.get(key), key));
                }
            }
        }

        //把堆中的数据拿到List中
        while(!queue.isEmpty()){
            ans.add(queue.poll().elemt);
        }


        //因为queue中是小顶堆（小顶堆中是前K个最大！！），所以要逆序来按从大到小排序
        Collections.reverse(ans);
        return ans;
    }


    private class StrElement implements Comparable<StrElement>{
        int nums;
        String elemt;
        StrElement(String elemt,int nums){
            this.nums = nums;
            this.elemt = elemt;
        }
        //默认小顶堆，改写按照nums来排小顶堆
        //因为还要求按照字母排序来排序，所以要考虑在频率相等时的排序l；又因为最后逆序，所以在这里要将相等的字母排序大的放在堆顶而不是排序小的
        @Override
        public int compareTo(StrElement o) {
            if(this.nums == o.nums){
                return o.elemt.compareTo(this.elemt);
            }else{
                return this.nums - o.nums;
            }
        }
    }

    //692. 前K个高频单词
    public List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<>();

        //和上面一题类似
        Map<String, Integer> map = new TreeMap<>();
        Queue<StrElement> queue = new PriorityQueue<>();

        //把String的频率放到map里
        for(String val:words){
            if(map.containsKey(val)){
                map.put(val, map.get(val)+1);
            }else{
                map.put(val,1);
            }
        }

        for(String key:map.keySet()){
            if(queue.size()<k){
                queue.offer(new StrElement(key, map.get(key)));
            }else{
                if(queue.peek().nums<map.get(key)){
                    queue.poll();
                    queue.offer(new StrElement(key, map.get(key)));
                }
            }
        }

        while(!queue.isEmpty()){
            ans.add(queue.poll().elemt);
        }

        Collections.reverse(ans);
        return ans;
    }

    //373. 查找和最小的K对数字
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> ans = new ArrayList<>();

        //这里有一种直接在PriorityQueue中传参Comparator的方法，还可以像上面两题一样实现Comparable接口
        Queue<int[]> queue = new PriorityQueue<>(k,new Comparator<int[]>() {
            @Override
            //大顶堆
            public int compare(int[] a, int[] b) {
                return b[0]+b[1] - a[0] - a[1];
            }
        });


        for(int val1:nums1){
            for(int val2:nums2){
                if(queue.size()<k){
                    queue.offer(new int[]{val1, val2});
                }else{
                    if(queue.peek()[0]+queue.peek()[1]>val1+val2){
                        queue.poll();
                        queue.offer(new int[]{val1, val2});
                    }
                }
            }
        }
        //List直接用这个方法添加容器中的元素
        ans.addAll(queue);
        return ans;
    }

    //215. 数组中的第K个最大元素，第K最大用小顶堆，第K最小用大顶堆
    //用典型堆排序即可
    public int findKthLargest(int[] nums, int k) {
        int ans = 0;
        Queue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>(){
            @Override
            public int compare(Integer a1, Integer a2){
                return a1-a2; //默认自然顺序是小顶堆
            }
        });

        for(int val:nums){
            if(queue.size()<k){
                queue.offer(val);
            }else{
                if(queue.peek()<val){
                    queue.poll();
                    queue.offer(val);
                }
            }
        }
        ans = queue.peek();
        return ans;
    }


    //973. 最接近原点的 K 个点
    public int[][] kClosest(int[][] points, int K) {
        int[][] ans = new int[K][];

        Queue<int[]> queue = new PriorityQueue<>(K, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return (int)(Math.pow(b[0],2)+Math.pow(b[1],2) - Math.pow(a[0],2) - Math.pow(a[1],2));
            }
        });

        for(int[] point:points){
            if(queue.size()<K){
                queue.offer(point);
            }else{
                if(Math.pow(queue.peek()[0],2)+Math.pow(queue.peek()[1],2) > Math.pow(point[0],2)+Math.pow(point[1],2)){
                    queue.poll();
                    queue.offer(point);
                }
            }
        }

        int index = 0;
        while(!queue.isEmpty()){
            ans[index] = queue.poll();
            index++;
        }
        return ans;
    }

    //264. 丑数 II
    //三指针解法
    public int nthUglyNumber(int n) {
        int ans = 0;
        if(n<=0)
            return 0;
        int[] ungly = new int[n];
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        ungly[0] = 1;
        for(int i = 1; i<n; i++){
            int min = Math.min(ungly[p2] * 2, Math.min(ungly[p3] * 3, ungly[p5] * 5));
            if(min == ungly[p2] * 2){
                p2++;
            }
            if(min == ungly[p3] * 3){
                p3++;
            }
            if(min == ungly[p5] * 5){
                p5++;
            }
            ungly[i] = min;
        }
        ans = ungly[n-1];
        return ans;
    }

    //313. 超级丑数
    //和上面的丑数几乎一样的思路，先求所有指针中最小，然后填充数组，最后符合的指针+1
    public int nthSuperUglyNumber(int n, int[] primes) {
        int ans = 0;

        int pointer[] = new int[primes.length];
        int ugly[] = new int[n];

        ugly[0] = 1;

        for(int i = 1; i<n; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j<primes.length; j++){
                min = Math.min(min, ugly[pointer[j]] * primes[j]);
            }
            ugly[i] = min;
            for(int j = 0; j<primes.length; j++){
                if(ugly[pointer[j]] * primes[j] == min){
                    pointer[j]++;
                }
            }
        }

        ans = ugly[n-1];
        return ans;
    }


    //147. 对链表进行插入排序
    public ListNode insertionSortList(ListNode head) {
        ListNode ans = head;
        return ans;
    }
}
