package medium;

import java.util.Stack;

public class String_Medium_notComplete {
    public static void main(String[] args){
        String_Medium_notComplete test = new String_Medium_notComplete();
        test.decodeString("3[a]2[bc]");
    }

    //227. 基本计算器 II 主要是减法的话，就把后面的数字变为负数。并且小心数字>10的情况
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int temp = 0;
        char simple = '+';
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                temp = temp * 10 + c - '0';
            }
            if ((c != ' ' && !(c >= '0' && c <= '9')) || count == s.length() - 1) { // 最后一个数需要算
                if (simple == '+') {
                    stack.push(temp);
                } else if (simple == '-') {
                    stack.push(-temp);
                } else if (simple == '*') {
                    stack.push(stack.pop() * temp);
                } else {
                    stack.push(stack.pop() / temp);
                }
                temp = 0;
                simple = c;
            }
            count++;
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    //1003. 检查替换后的词是否有效，实际上就是abc整体弹出，看最后栈里有没有剩下的，如果没有整体弹出就false
    public boolean isValid(String S) {
        Stack<Character> stack = new Stack<>();
        char[] c = S.toCharArray();
        for(char aC : c){
            if( aC == 'c'){
                if (stack.isEmpty() || stack.pop() != 'b') return false;
                if (stack.isEmpty() || stack.pop() != 'a') return false;
            }
            else{
                stack.push(aC);
            }

        }
        return stack.isEmpty();
    }

    //165. 比较版本号，其实就是一个根据规则分割字符串求int值之间的大小而已
    public int compareVersion(String version1, String version2) {
        String[] tmp = version1.split("\\."); //注意分割方法
        String[] tmp2 = version2.split("\\.");
        int num1 = 0;
        int num2 = 0;
        int idx1 = 0;
        int idx2 = 0;
        while(idx1 < tmp.length || idx2 < tmp2.length){
            num1 *= 10;
            num2 *= 10;
            if(num1 > num2)
                return 1;
            if(num2 > num1)
                return -1;

            if(idx1 < tmp.length){
                String cur = tmp[idx1];
                num1 += Integer.parseInt(cur);
            }
            if(idx2 < tmp2.length){
                String cur2 = tmp2[idx2];
                num2 += Integer.parseInt(cur2);
            }

            idx1++;
            idx2++;
        }
        if(num1 > num2)
            return 1;
        else if (num1 < num2)
            return -1;
        else
            return 0;
    }

    //537. 复数乘法，substring真是一个神奇的东西啊,substring(0,1)是下标为0的字符串，只有一个，不包含下标为1的字符！！
    public String complexNumberMultiply(String a, String b) {
        String[] stra = a.split("\\+");
        String[] strb = b.split("\\+");
        int a1 = Integer.parseInt(stra[0]);
        int b1 = Integer.parseInt(strb[0]);
        int a2 = Integer.parseInt(stra[1].substring(0,stra[1].length()-1));
        int b2 = Integer.parseInt(strb[1].substring(0,strb[1].length()-1));
        int x = a1*b1 - a2*b2;
        int y = a1*b2 + a2*b1;
        return x + "+" + y +"i";
    }

    //394. 字符串解码
    int indexForDecode = 0; //标识字符串的下标，用来递归
    public String decodeString(String s) {
        StringBuffer sb = new StringBuffer();
        for(;indexForDecode<s.length() && s.charAt(indexForDecode)!=']'; indexForDecode++) {
            int num = 0;
            if ((s.charAt(indexForDecode) > 'a' || s.charAt(indexForDecode) == 'a') && (s.charAt(indexForDecode) < 'z' || s.charAt(indexForDecode) == 'z') ||
                    (s.charAt(indexForDecode) > 'A' || s.charAt(indexForDecode) == 'A') && (s.charAt(indexForDecode) < 'Z' || s.charAt(indexForDecode) == 'Z')) {
                sb.append(s.charAt(indexForDecode));
            }else {
                while((s.charAt(indexForDecode) > '0' || s.charAt(indexForDecode) == '0') && (s.charAt(indexForDecode) < '9' || s.charAt(indexForDecode) == '9')) {
                    num = num * 10 + s.charAt(indexForDecode) - '0';
                    indexForDecode++;
                }
                indexForDecode++;
                String temp = decodeString(s);
                for(int i = 0; i<num; i++){
                    sb.append(temp);
                }
            }
        }
        return sb.toString();
    }




}
