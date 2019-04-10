package medium;

import java.util.*;

public class Stack_medium_notComplete {
    public static void main(String[] args) {
    //栈的中等tag{
        //71. 简化路径
        //simplifyPath("s");

        //394. 字符串解码
        //String a = decodeString("3[a2[c]]");

        //503. 下一个更大元素 II 进阶版
        //int[] a = {1,10,8};
        //nextGreaterElements(a);

        ////856. 括号的分数
        //scoreOfParentheses("(()(()))");

    //栈的中等tag}

        Stack_medium_notComplete main = new Stack_medium_notComplete();
        int[] a = {-2,1,-1,-2};
        main.asteroidCollision(a);

     }

     //71. 简化路径
    public static String simplifyPath(String path) {
        String result = "";
        Stack stack = new Stack();
        Stack stack1 = new Stack();
        String[] p = path.split("/");
        for(int i = 0; i<p.length; i++){
            String element = p[i];
            switch (element){
                case ".":
                    break;
                case "":
                    break;
                case "..":
                    if(!stack.empty())
                        stack.pop();
                    break;
                default:
                    stack.push(element);
            }
        }
        if(stack.empty())
            result = "/";
        while(!stack.empty()){
            stack1.push(stack.pop());
        }
        while(!stack1.empty()){
            result = result + "/"+ stack1.pop();
        }
        return result;
    }


    //二叉树
     //Definition for a binary tree node.
     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    public static class Solution {
        //public Stack stack = new Stack();

        //94. 二叉树的中序遍历
        public  List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if(root == null)
                return list;
            if(root.left!=null){
                list.addAll(inorderTraversal(root.left));
            }
            list.add(root.val);
            if(root.right!=null){
                list.addAll(inorderTraversal(root.right));
            }
            return list;
        }

        //103. 二叉树的锯齿形层次遍历
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            Stack stack1 = new Stack();
            Stack stack2 = new Stack();
            if(root!=null) {
                stack1.push(root);
            }

            while(!stack1.empty() || !stack2.empty()){
                List<Integer> list1 = new ArrayList<>();
                List<Integer> list2 = new ArrayList<>();
                while(!stack1.empty()){//从右往左
                    TreeNode element = (TreeNode)stack1.pop();
                    list1.add(element.val);
                    if(element.left!=null){
                        stack2.push(element.left);
                    }
                    if(element.right!=null){
                        stack2.push(element.right);
                    }
                }
                if(list1.size()!=0){
                    list.add(list1);
                }

                while(!stack2.empty()){//从左往右
                    TreeNode element = (TreeNode)stack2.pop();
                    list2.add(element.val);
                    if(element.right!=null){
                        stack1.push(element.right);
                    }
                    if(element.left!=null){
                        stack1.push(element.left);
                    }
                }
                if(list2.size()!=0){
                    list.add(list2);
                }
            }
            return list;
        }

        //144. 二叉树的前序遍历
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if(root!=null){
                list.add(root.val);
                if(root.left!=null){
                    list.addAll(preorderTraversal(root.left));
                }
                if(root.right!=null){
                    list.addAll(preorderTraversal(root.right));
                }
            }
            return list;
        }

        //150. 逆波兰表达式求值
        public int evalRPN(String[] tokens) {
            int result = 0;
            Stack stack = new Stack();
            int size = tokens.length;
            for(int i = 0; i<size; i++){
                String element = tokens[i];
                switch (element){
                    case("+"):
                        int a1 = (int)stack.pop();
                        int b1 = (int)stack.pop();
                        stack.push(a1+b1);
                        break;
                    case("-"):
                        int a2 = (int)stack.pop();
                        int b2 = (int)stack.pop();
                        stack.push(b2-a2);
                        break;
                    case("*"):
                        int a3 = (int)stack.pop();
                        int b3 = (int)stack.pop();
                        stack.push(a3*b3);
                        break;
                    case("/"):
                        int a4 = (int)stack.pop();
                        int b4 = (int)stack.pop();
                        stack.push(b4/a4);
                        break;
                    default:
                        stack.push(Integer.valueOf(element));
                }
            }
            if(stack!=null)
                result = (int)stack.pop();
            return result;
        }

        //331. 验证二叉树的前序序列化，因为空节点用#表示，所以这是判断是否为满二叉树的前序序列化
        //这个好难，先放着吧= =
        public boolean isValidSerialization(String preorder) {
            boolean result = false;
            String[] p = preorder.split(",");
            for(int i = 0; i<p.length; i++){

            }
            return result;
        }
    }

    //173. 二叉搜索树迭代器，这道题的题意十分难理解了，，，，可以好好看看怎么用栈来实现非递归的中序遍历
    /**
     * Your BSTIterator will be called like this:
     * BSTIterator i = new BSTIterator(root);
     * while (i.hasNext()) v[f()] = i.next();
     */
    public static class BSTIterator {
        public Queue queue  = new LinkedList();
        public BSTIterator(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if(root!=null) {
                list = inorderTraversal(root);
                for(int i = 0; i<list.size(); i++)
                    this.queue.offer(list.get(i));
            }

        }

        public  List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if(root == null)
                return list;
            if(root.left!=null){
                list.addAll(inorderTraversal(root.left));
            }
            list.add(root.val);
            if(root.right!=null){
                list.addAll(inorderTraversal(root.right));
            }
            return list;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            if(hasNext()) {
                return (int) this.queue.poll();
            }else
                return 0;
        }
    }

    //用栈来实现非递归的中序遍历
        /*public class BSTIterator {
        private Stack<easy.TreeNode> stack = new Stack<>();

        public BSTIterator(easy.TreeNode root) {
            easy.TreeNode current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            easy.TreeNode next = stack.pop();
            easy.TreeNode current = next.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            return next.val;
        }
    }*/

    //907. 子数组的最小值之和
    public int sumSubarrayMins(int[] A) {
        int result = 0;

        return result;
    }


    //385. 迷你语法分析器

    //394. 字符串解码
    public static String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int index = 0;
        while(index < s.length()){
            if(Character.isDigit(s.charAt(index))){
                int count = 0; while(Character.isDigit(s.charAt(index))){
                    count = 10 * count + (s.charAt(index) - '0');
                    index++;
                }
                countStack.push(count);
            }
            else if(s.charAt(index) == '['){
                resStack.push(res);
                res = "";
                index++;
            }else if(s.charAt(index) == ']'){
                StringBuilder stringBuilder = new StringBuilder(resStack.pop());
                int nums = countStack.pop();
                for(int i = 0; i<nums; i++){
                    stringBuilder.append(res);
                }
                res = stringBuilder.toString();
                index++;
            }else{
                res += s.charAt(index++);
            }
        }
        return res;
    }


    //739. 每日温度 递减栈
    public int[] dailyTemperatures(int[] T) {
        Stack stack = new Stack();
        Stack stackIndex = new Stack();
        int size = T.length;
        int[] result = new int[size];
        for(int i = 0; i<size; i++){
            if(i == 0){
                stack.push(T[i]);
                stackIndex.push(i);
            }else{
                while(T[i]>(int)stack.peek()){
                    stack.pop();
                    int ind = (int)stackIndex.pop();
                    result[ind] = i - ind;
                    if(stack.empty())
                        break;
                }
                stack.push(T[i]);
                stackIndex.push(i);
            }
        }
        while(!stack.empty()){
            stack.pop();
            int ind = (int)stackIndex.pop();
            result[ind] = 0;
        }
        return result;
    }


    //503. 下一个更大元素 II 进阶版
    public static int[] nextGreaterElements(int[] nums) {
        int size = nums.length;
        Stack stack = new Stack();
        Stack stackIndex = new Stack();
        int[] result = new int[size];
        for(int i = 0; i<size; i++){
            result[i] = -1;
        }
        for(int j = 0; j<2 * size; j++){
            int i = j % size;
            if(j == 0){
                stack.push(nums[i]);
                stackIndex.push(i);
            }else{
                while(nums[i]>(int)stack.peek()){
                    stack.pop();
                    int ind = (int)stackIndex.pop();
                    result[ind] = nums[i];
                    if(stack.empty())
                        break;
                }
                stack.push(nums[i]);
                stackIndex.push(i);
            }
        }
        return result;
    }

    //856. 括号的分数
    public static int scoreOfParentheses(String S) {
        int result = 0;
        Stack<String> stack = new Stack<String>();
        int index = 0;
        int size = S.length();
        while(index < size){
            if(S.charAt(index) == '('){
                stack.push("(");
                index++;
            }else if(S.charAt(index) == ')'){
                if(stack.peek() == "("){
                    stack.pop();
                    if(!stack.empty()){
                        if(stack.peek() == "("){
                            stack.push("1");
                        }else{
                            int temp = Integer.parseInt(stack.pop());
                            temp++;
                            stack.push(String.valueOf(temp));
                        }
                    }
                    else{
                        stack.push("1");
                    }
                }else{
                    int temp = Integer.parseInt(stack.pop());
                    if(!stack.empty()){
                        stack.pop();
                    }
                    temp = 2 * temp;
                    if(!stack.empty()) {
                        if (!stack.peek().equals("(")) {
                            int temp1 = Integer.parseInt(stack.pop()) + temp;
                            stack.push(String.valueOf(temp1));
                        }else{
                            stack.push(String.valueOf(temp));
                        }
                    }else{
                        stack.push(String.valueOf(temp));
                    }
                }
                index++;
            }
        }
        if(!stack.empty()){
            result = Integer.parseInt(stack.pop());
        }
        return result;
    }

    //行星碰撞
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        boolean isleft = false;
        for(int asteroid: asteroids){
            if(stack.empty()){
                stack.push(asteroid);
                if(asteroid<0){
                    isleft = true;
                }else{
                    isleft = false;
                }
            }else{
                if(asteroid<0){
                    if(isleft){
                        stack.push(asteroid);
                    }else{
                        if(Math.abs(asteroid)>Math.abs(stack.peek())){
                            stack.pop();
                            while(!stack.empty()){
                                if(stack.peek()<0){
                                    isleft = true;
                                }else
                                    isleft = false;
                                if(!isleft) {
                                    if (Math.abs(asteroid) > Math.abs(stack.peek())) {
                                        stack.pop();
                                    } else if (Math.abs(asteroid) == Math.abs(stack.peek())) {
                                        stack.pop();
                                        asteroid = 0;
                                        break;
                                    } else
                                        break;
                                }
                                if(isleft && asteroid!=0){
                                    stack.push(asteroid);
                                    break;
                                }
                            }
                            if(stack.empty() && asteroid!=0){
                                stack.push(asteroid);
                                isleft = false;
                            }
                        }else if(Math.abs(asteroid) == Math.abs(stack.peek())){
                            stack.pop();
                        }
                    }
                }else if(asteroid>0){
                    if(isleft){
                        stack.push(asteroid);
                        isleft = false;
                    }else{
                        stack.push(asteroid);
                    }

                }
            }
            if(!stack.empty()) {
                if (stack.peek() < 0) {
                    isleft = true;
                } else
                    isleft = false;
            }
        }
        int size = stack.size();
        int[] ans = new int[size];
        int i = size-1;
        while(!stack.empty()){
            ans[i] = stack.pop();
            i--;
        }
        return ans;
    }
}
