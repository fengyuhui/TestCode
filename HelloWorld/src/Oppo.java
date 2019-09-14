import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Welcome to vivo !
 */

public class Oppo {

    /*public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int output = solution(inputStr );
        System.out.println(output);
    }

    private static int solution(String str) {

        // TODO Write your code here 

        int ans = 0;
        int size = str.length();
        Stack stack = new Stack();
        int total = 0, fake = 0;
        for (int i = 0; i < size; i++) {
            if(str.charAt(i) == '('){
                stack.push(str.charAt(i));
                total++;
            }else if(str.charAt(i) == ')'){
                if(!stack.isEmpty()){
                    stack.pop();
                    fake++;
                }

            }else if(str.charAt(i) == '0'){
                ans = total-fake;
            }
        }
        return ans;

    }*/
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int input[] = parseInts(inputStr.split(" "));
        int output = solution(input);
        System.out.println(output);
    }

    private static int[] parseInts(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new int[0];
        }
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    private static int solution(int[] input) {

        // TODO Write your code here
        int ans = 0;
        int[] color = new int[9];
        int times[] = new int[9];
        for (int i = 0; i < input.length; i++) {
            color[input[i]]++;
        }

        Stack stack = new Stack();
        for (int i = 0; i < input.length; i++) {
            if(stack.isEmpty()){
                stack.push(input[i]);
                times[input[i]]++;
            }else{
                if(times[input[i]] == color[input[i]] - 1){
                    int a = 1;
                    while(!stack.isEmpty() && (Integer)stack.peek() == input[i]){
                        stack.pop();
                        times[input[i]] -- ;
                        color[input[i]]--;
                        a++;
                    }
                    ans+=a *a;
                }else{
                    stack.push(input[i]);
                    times[input[i]]++;
                }
            }
        }

        int temp = 0;
        int pre = 0;
        while(!stack.isEmpty()){
           if(pre == 0){
               pre = (Integer)stack.pop();
               temp = 1;
           }else{
               if((Integer)stack.peek() == pre){
                   stack.pop();
                   temp++;
               }else{
                   ans+=temp*temp;
                   temp = 0;
                   pre = 0;
               }
           }
        }
        return ans;
    }
}