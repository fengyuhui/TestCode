package medium;


import java.util.*;

//Definition for singly-linked list.
 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }


public class List_Medium_notComplete {
    public static void main(String args[]){
        List_Medium_notComplete main = new List_Medium_notComplete();

        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> l = main.threeSum(nums);
    }

    //19. 删除链表的倒数第N个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        ListNode ans = head;
        ListNode next = head;
        ListNode pre = head;
        if(n==1 && head.next == null)
            return null;
        for(int i = 0; i<n-1; i++){
            if(p!=null) {
                p = p.next;
            }
            else
                return ans;
        }
        if(p.next!=null) {
            while (p.next != null) {
                pre = next;
                p = p.next;
                next = next.next;
            }
        }else{
            return ans.next;
        }
        pre.next = next.next;
        next = null;
        return ans;
    }


    //15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        List<Integer> list = new ArrayList<>();

        Arrays.sort(nums);
        if(nums.length == 3){
            if(nums[0] + nums[1] + nums[2] == 0){
                list.add(nums[0]);
                list.add(nums[1]);
                list.add(nums[2]);
                set.add(list);
            }
        }
        for(int i = 0; i<nums.length - 3; i++){
            int left = i;
            int right = nums.length - 1;
            int mid = i+1;

            while(mid<right){
                int temp = nums[left]+nums[mid]+nums[right];
                if(temp == 0){
                    list.add(nums[left]);
                    list.add(nums[mid]);
                    list.add(nums[right]);
                    set.add(list);
                    list = new ArrayList<>();
                    mid++;
                    right--;
                }else if(temp<0){
                    mid++;
                }else if(temp>0)
                    right--;
            }
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            ans.add((List<Integer>) iterator.next());
        }
        return ans;
    }


    //
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode ans = head;


        return ans;
    }

}

