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


    //92. 反转链表 II 重点是只能遍历一次链表
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode ans = head;
        ListNode pre = head;
        ListNode rear = head;
        m--;
        n--;
        if(m == n)
            return ans;
        if(m==0){
            for (int i = 0; i < n; i++) {
                if (rear.next != null) {
                    rear = rear.next;
                }
            }

            ans = rear;
            for(int i = 0; i<n; i++){
                ListNode temp = pre.next;
                ListNode temp2 = rear.next;

                pre.next = temp2;
                rear.next = pre;

                pre = temp;
            }
        }else {
            for (int i = 0; i < n - m + 1; i++) {
                if (rear.next != null) {
                    rear = rear.next;
                }
            }

            for (int i = 0; i < m - 1; i++) {
                rear = rear.next;
                pre = pre.next;
            }

            if (pre.next == rear) {
                int temp = pre.val;
                pre.val = rear.val;
                rear.val = temp;
                return ans;
            }

            //将pre的next逐一插到rear的后面
            while (pre.next != rear) {
                ListNode temp = pre.next;
                ListNode temp2 = rear.next;

                pre.next = temp.next;
                rear.next = temp;
                temp.next = temp2;
            }
        }

        return ans;
    }

    //142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode ans = head;

        //先判断有没有环，没有就直接返回null
        //使用快慢指针
        ListNode fast = head, slow = head;
        ListNode inter = null;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                inter = fast;
                break;
            }
        }
        //无环
        if(inter == null)
            return null;

        //查找环起点
        ListNode cur = head;
        while(cur!=inter){
            cur = cur.next;
            inter = inter.next;
        }

        return cur;
    }

    //206. 反转链表
    public ListNode reverseList(ListNode head) {
        //设置一个虚假头节点
        if(head == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = head.next, pre = head;

        while(cur!=null){
            pre.next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = pre.next;
        }
        return dummy.next;
    }
}

