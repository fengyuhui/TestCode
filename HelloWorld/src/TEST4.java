import java.util.HashMap;
import java.util.Scanner;

public class TEST4 {
    public static void main(String[] args) {
/*        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] str = s.split(",");
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i<str.length; i++){
            if(map.containsKey(str[i])){
                int times = map.get(str[i]);
                times++;
                map.put(str[i], times);
                System.out.println("true");
                return;
            }else{
                map.put(str[i], 1);
            }
        }
        System.out.println("false");*/

        TEST4 main = new TEST4();
        main.method2();
    }

    public void method2(){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int k = in.nextInt();
        int size = s.length();
        s = s.substring(1, size-1);
        String str[] = s.split(",");
        if(k == 0){
            System.out.print("[");
            for(int i = 0; i<str.length; i++){
                System.out.print(str[i]);
                if(i<str.length - 1)
                    System.out.print(",");
                if(i == str.length - 1){
                    System.out.print("]");
                }
            }
            return;
        }
        int times = str.length / k;
        int first=0, last=first+k-1;
        while(times>0){
            times--;
            if(last - first<=1){
                String temp = str[first];
                str[first] = str[last];
                str[last] = temp;
            }else{
                for(int i = first, j = last; i<=(first+k) / 2 && i<j && j<s.length(); i++, j--){
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }

            first = first+k;
            last=first+k-1;
        }

        System.out.print("[");
        for(int i = 0; i<str.length; i++){
            System.out.print(str[i]);
            if(i<str.length - 1)
                System.out.print(",");
            if(i == str.length - 1){
                System.out.print("]");
            }
        }
    }
}
