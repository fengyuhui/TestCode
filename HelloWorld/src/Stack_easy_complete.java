import com.sun.deploy.util.StringUtils;
import sun.swing.StringUIClientPropertyKey;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

public class Stack_easy_complete {
    public static void main(String[] args) {

        //栈的简单tag：{
        //2两数相加
        //addTwoNumbers(l1, l2);

        //3无重复字符的最长子串——优化滑动窗口
        //System.out.println(lengthOfLongestSubstring("pwwkew"));

        //20有效的括号
        //System.out.println(isValid("["));

        //155最小栈
        /*MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.getMin();*/

        //225. 用队列实现栈
        /*MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.top();
        myStack.pop();*/

        //232. 用栈实现队列
        /*MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.peek();
        myQueue.pop();*/

        //496. 下一个更大元素
        /*int[] num1 = {1,3,5,2,4};
        int[] num2 = {5,4,3,2,1};
        int [] result = nextGreaterElement(num1,num2);*/

        //682. 棒球比赛
        /*String[] s = {"5","2","C","D","+"};
        calPoints(s);*/

        //844. 比较含退格的字符串

        //栈的简单tag}

   }

    //1两数之和
    public static int[] twoSum(int[] nums, int target) {
        int[] a = {0,0};
        for(int i = 0; i<nums.length; i++){
            for(int j = i; j<nums.length; j++){
                if(nums[i]+nums[j] == target && i!=j){
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        return a;
    }

    //2两数相加

      //Definition for singly-linked list.
      public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
     }

    //public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    public static ListNode addTwoNumbers() {

        //2
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(1);

        l2.val = 1;
        ListNode l22 = new ListNode(9);
        ListNode l23 = new ListNode(9);
        l2.next = l22;
        l22.next = l23;

        ListNode l24 = new ListNode(9);
        ListNode l25 = new ListNode(9);
        l23.next = l24;
        l24.next = l25;

        ListNode l26 = new ListNode(9);
        ListNode l27 = new ListNode(9);
        l25.next = l26;
        l26.next = l27;

        ListNode l28 = new ListNode(9);
        ListNode l29 = new ListNode(9);
        l27.next = l28;
        l28.next = l29;

        ListNode l210 = new ListNode(9);
        l29.next = l210;


        ListNode a = new ListNode(1);
        BigDecimal num1 = new BigDecimal(0);
        BigDecimal num2 = new BigDecimal(0);
        BigDecimal a10 = new BigDecimal(10);
        for(int i = 0; l1!=null; i++){
            BigDecimal add = new BigDecimal(l1.val).multiply(pow(a10, i));
            num1 = num1.add(add);
            l1 = l1.next;
        }
        for(int i = 0; l2!=null; i++){
            BigDecimal add = new BigDecimal(l2.val).multiply(pow(a10, i));
            num2 = num2.add(add);
            l2 = l2.next;
        }

        BigDecimal result = num1.add(num2);
        int length = String.valueOf(result).length();

        ListNode p = new ListNode(1);
        p = a;
        for(int i = 0; i<length; i++){
            ListNode b = new ListNode(1);
            p.val = (result.divideAndRemainder(new BigDecimal(10))[1]).intValue();
            result = result.divide(new BigDecimal(10));
            if(i!=length - 1) {
                p.next = b;
                p = b;
            }
        }

        return a;
    }

    //幂次方计算,a的b次方
    public static BigDecimal pow(BigDecimal a, int b){
        BigDecimal result = new BigDecimal(1);
        for(int i = 0; i<b; i++){
            result = result.multiply(a);
        }
        return result;
    }

    //3 无重复字符的最长子串——优化滑动窗口
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 0)
            return 0;
        int result = 1;
        for(int i = 0,j = 0; i<s.length() && j<s.length() + 1;){
            int max = 1;
            if(i == j)
                j++;
            if(j == s.length())
                break;
            String sub = s.substring(i,j);
            char p = s.charAt(j);
            boolean repeat = false;
            for(int k = 0; k<sub.length(); k++){
                if(p==sub.charAt(k)){
                    i = i+k+1;
                    repeat = true;
                    break;
                }
            }
            if(!repeat) {
                max = sub.length() + 1;
                result = result<max?max:result;
                j++;
            }
        }
        return result;
    }

    //20有效的括号
    public static boolean isValid(String s) {
        boolean result = true;
        if(s == null || s.length() == 0)
            return true;
        Stack a = new Stack();
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '('){
                a.push(s.charAt(i));
            }else if(s.charAt(i) == ']' || s.charAt(i) == '}' || s.charAt(i) == ')'){
                if(a.empty()){
                    return false;
                }
                    switch (s.charAt(i)){
                    case(']'):
                        if(a.pop().equals('[')){
                            continue;
                        }else
                            return false;
                    case(')'):
                        if(a.pop().equals('(')){
                            continue;
                        }else
                            return false;
                    case('}'):
                        if(a.pop().equals('{')){
                            continue;
                        }else
                            return false;
                }
            }
        }
        if(!a.empty())
            return false;
        return result;
    }

    //155最小栈(栈的题目总是能用两个栈来达到目的= =)
    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    public static class MinStack {
        public Stack stack;
        public Stack minStack;
        /** initialize your data structure here. */
        public MinStack() {
            this.stack = new Stack();
            this.minStack = new Stack();
        }

        public void push(int x) {
            this.stack.push(x);
            if(this.minStack.empty() || (int)minStack.peek()>x || (int)minStack.peek() == x){
                minStack.push(x);
            }
        }

        public void pop() {
            int pop = (int)this.stack.pop();
            if(pop == (int)this.minStack.peek())
                this.minStack.pop();
        }

        public int top() {
            return (int)this.stack.peek();
        }

        public int getMin() {
            int min = (int)this.minStack.peek();
            return min;
        }
    }

    //225. 用队列实现栈

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */
    public static class MyStack {
        public Queue queue1;
        /** Initialize your data structure here. */
        public MyStack() {
            this.queue1 = new LinkedList();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            this.queue1.offer(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            Queue queue2 = new LinkedList();
            int result = 0;
            int size = this.queue1.size();
            for(int i = 0; i<size; i++){
                int pop = (int)this.queue1.poll();
                result = pop;
                if(!this.queue1.isEmpty()){
                    queue2.offer(pop);
                }
            }
            this.queue1 = queue2;
            return result;
        }

        /** Get the top element. */
        public int top() {
            int result = 0;
            Queue queue2 = new LinkedList();
            int size = this.queue1.size();
            for(int i = 0; i<size; i++){
                int pop = (int)this.queue1.poll();
                result = pop;
                queue2.offer(pop);
            }
            this.queue1 = queue2;
            return result;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            if(this.queue1.isEmpty())
                return true;
            else
                return false;
        }
    }


    //232. 用栈实现队列

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    public static class MyQueue {

        public Stack stack1;
        /** Initialize your data structure here. */
        public MyQueue() {
            this.stack1 = new Stack();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            this.stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            Stack stack2 = new Stack();
            int size = this.stack1.size();
            int pop = 0;
            for(int i = 0; i<size; i++){
                pop = (int)this.stack1.pop();
                if(!this.stack1.empty()){
                    stack2.push(pop);
                }
            }
            for(int i = 0;i<size - 1; i++){
                this.stack1.push(stack2.pop());
            }
            return pop;
        }

        /** Get the front element. */
        public int peek() {
            Stack stack2 = new Stack();
            int size = this.stack1.size();
            int pop = 0;
            for(int i = 0; i<size; i++){
                pop = (int)this.stack1.pop();
                stack2.push(pop);
            }
            for(int i = 0;i<size; i++){
                this.stack1.push(stack2.pop());
            }
            return pop;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            if(this.stack1.empty())
                return true;
            else
                return false;
        }
    }

    //496. 下一个更大元素
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        List<Integer> list=new ArrayList();//**须定义时就进行转化**
        int size1 = nums1.length;
        int size2 = nums2.length;
        for(int i = 0 ;i<size1; i++){
            boolean isFind = false;
            int index = 0;
            Queue stack = new LinkedList();
            for(int j = 0; j<size2; j++){
                if(nums1[i] == nums2[j]){
                    isFind = true;
                    index = j+1;
                    break;
                }
            }
            if(isFind){
                for(int j = index; j<size2; j++){
                    stack.offer(nums2[j]);
                }
            }

            int r = -1;
            while(!stack.isEmpty()){
                 r = (int)stack.poll();
                if(r>nums1[i]){
                    break;
                }
                else{
                    r = -1;
                }
            }
            list.add(r);
        }

        int resultSize = list.size();
        int[] result = new int[resultSize];
        for(int i = 0; i<resultSize; i++){
            result[i] = list.get(i);
        }
        return result;
    }

    //682. 棒球比赛
    public static int calPoints(String[] ops) {
        int result = 0;
        Stack stack = new Stack();
        for(int i = 0; i<ops.length; i++){
            String element = ops[i];
            switch (element){
                case "D":
                    stack.push((int)stack.peek() * 2);
                    break;
                case "+":
                    stack.push((int)stack.peek() + (int)stack.get(stack.size() - 2));
                    break;
                case "C":
                    stack.pop();
                    break;
                default:
                    stack.push(Integer.valueOf(ops[i]));
            }
        }
        while(!stack.empty()){
            result = result + (int)stack.pop();
        }
        return result;
    }

    //844. 比较含退格的字符串
    public boolean backspaceCompare(String S, String T) {
        boolean result = true;
        Stack stack1 = new Stack();
        Stack stack2 = new Stack();
        for(int i = 0; i<S.length();i++){
            char element = S.charAt(i);
            switch (element){
                case '#':
                    if(stack1.empty()){
                        break;
                    }
                    stack1.pop();
                    break;
                default:
                    stack1.push(element);
                    break;
            }
        }

        for(int i = 0; i<T.length();i++){
            char element = T.charAt(i);
            switch (element){
                case '#':
                    if(stack2.empty()){
                        break;
                    }
                    stack2.pop();
                    break;
                default:
                    stack2.push(element);
                    break;
            }
        }

        if(stack1.empty() && stack2.empty())
            return true;
        if(stack1.size()!=stack2.size())
            return false;
        while(!stack1.empty()){
            if(!stack1.pop().equals(stack2.pop()))
                return false;
        }
        return result;
    }



    public static void poemLine(){
        Scanner cin = new Scanner(System.in);
        System.out.println("Hello World!");
        boolean flag = false;//是否达到第三行
        int number = cin.nextInt();
        String space = cin.nextLine();
        for(int i = 0; i<number; i++){
            //接收3行
            String s1 = new String();
            s1 = cin.nextLine();

            String s2 = new String();
            s2 = cin.nextLine();

            String s3 = new String();
            s3 = cin.nextLine();

            String[] array1 = formatStr(s1).split("\\s+|'");

            String[] array2 = formatStr(s2).split("\\s+|'");

            String[] array3 = formatStr(s3).split("\\s+|'");

            int[] results = new int[array1.length];

            System.out.println("array1.length: "+array1.length);
            for(int j = 0; j<array1.length; j++){
                int length = array1[j].length();
                //递归
                results[j] = getSearchResult(0, array1[j].length(), array1, array2, true);
                System.out.println("results["+j+"]: "+array3[results[j]]);
            }

        }
    }

    public static int getSearchResult(int index, int steps, String[] array1, String[] array2, boolean inFirst){
        int result = 0;
        //仍然在第一行
        if((array1.length - index>steps || steps + index == array1.length) && inFirst){
            index = index + steps;
            steps = array1[index-1].length();
            inFirst = true;
            result = getSearchResult(index, steps, array1, array2, inFirst);
        }
        else{//跳出第一行
            if(inFirst) {
                index = steps - array1.length + index;
            }else{
                index = index + steps;
            }
            inFirst = false;
            if(index < array2.length || index == array2.length){
                steps = array2[index-1].length();
                result = getSearchResult(index, steps, array1, array2, inFirst);
            }else{//跳到第三行，结束
                index = index - array2.length;
                result = index - 1;
            }
        }
        return result;
    }

    //分割大小写单词
    public static String formatStr(String str){
        StringBuffer  sbf=new StringBuffer("");
        for(int i=0;i<str.length();i++){
            char tempChr=str.charAt(i);
            if(Character.isUpperCase(tempChr) && i!=0){
                sbf.append(" ");//如果是大写字母，则在字符前面插入一个空格
            }
            //转换大写字母为小写字母
            sbf.append(Character.toLowerCase(tempChr));
        }
        return sbf.toString();
    }
}
