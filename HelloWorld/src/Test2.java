import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Test2 {
    //合并数组
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        ArrayList ans = new ArrayList();
        int len = 0;
        int all = 0;
        while(sc.hasNext()){
            len++;
            ArrayList tempL = new ArrayList();
            String s = sc.nextLine();
            String[] temp = s.split(",");
            for(int i = 0; i<temp.length; i++){
                tempL.add(Integer.parseInt(temp[i]));
                all++;
            }
            list.add(tempL);
        }

        int index = 0;
        int time = 0;
        while(time<all){
            index = index%len;
            Iterator temp = list.get(index).iterator();
            int times = 0;
            while(temp.hasNext() && times<n){
                ans.add(temp.next());
                times++;
                temp.remove();
                time++;
            }
            index++;
        }

        for(int i = 0; i<ans.size(); i++){
            System.out.print(ans.get(i));
            if(i!=ans.size() - 1){
                System.out.print(",");
            }
        }
    }
}
