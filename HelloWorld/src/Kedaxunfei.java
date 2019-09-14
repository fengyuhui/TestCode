import java.util.Scanner;

public class Kedaxunfei {
    public static void main(String[] args){
        Kedaxunfei m = new Kedaxunfei();
        m.method1();
    }
    public void method1(){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        StringBuffer stringBuffer1 = new StringBuffer("");
        StringBuffer stringBuffer2 = new StringBuffer("");
        boolean flag = false;
        for(int i = 0; i<s.length(); i++){
           if(s.charAt(i)!=' ' && !flag){
                stringBuffer1.append(s.charAt(i));
            }
            if(s.charAt(i) == ' '){
                flag = true;
            }
            if(s.charAt(i)!=' ' && flag){
                stringBuffer2.append(s.charAt(i));
            }
        }
        String s2 = s.replaceFirst(" ", "-");
        s2.trim();
        String[] str = s.split(" ");
        //String str1 = str[0].trim();
        //String str2 = str[1].trim();
        String str1 = stringBuffer1.toString();
        String str2 = stringBuffer2.toString();
        int size1 = str1.length();
        int size2 = str2.length();
        int add = 0;
        if(size1 == 0){
            System.out.println(str2);
            return;
        }
        if(size2 == 0){
            System.out.println(str1);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("");
        int i1 = size1 - 1, j1 = size2 - 1;
        for(int i = size1 - 1, j = size2 - 1; i>=0 && j>=0;i--, j--){
            int temp1 = Integer.parseInt(str1.charAt(i)+"");
            int temp2 = Integer.parseInt(str2.charAt(j)+"");
            int result = temp1+temp2+add;
            add = result / 10;
            result = result % 10;
            stringBuffer.append(result);
            i1--;
            j1--;
        }
        if(i1>=0){
            for(int k = i1; k>=0; k--){
                int temp1 = Integer.parseInt(str1.charAt(k)+"");
                int result = temp1+add;
                add = result / 10;
                result = result % 10;
                stringBuffer.append(result);
            }
        }
        else if(j1>=0){
            for(int k = j1; k>=0; k--){
                int temp1 = Integer.parseInt(str2.charAt(k)+"");
                int result = temp1+add;
                add = result / 10;
                result = result % 10;
                stringBuffer.append(result);
            }
        }else if(add!=0){
            stringBuffer.append(add);
        }
        System.out.println(stringBuffer.reverse().toString());
        return;
    }

    public void method2(){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println("2a2b");
    }
}
