import java.util.Scanner;

class ListNode{
    public int val;
    public ListNode next;
}
public class Test5 {
    public static void main(String[] args){

        Test5 main = new Test5();
        //main.method3();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int size = n;
        if(m == 1){
            for(int i = 1; i<size; i++){
                System.out.print(i+" ");
            }
            System.out.println();
            System.out.println(size);
            return;
        }

        ListNode p = new ListNode();
        p.val = 1;
        ListNode temp;
        if(n>1){
            temp = new ListNode();
            temp.val = 2;
            p.next = temp;
        }else{
            System.out.print(1);
            return;
        }
        for(int i = 3; i<n+1; i++){
            ListNode temp2 = new ListNode();
            temp2.val = i;
            temp.next = temp2;
            temp = temp.next;
        }
        temp.next = p;

        ListNode pt = p.next;
        ListNode pre = p;
        boolean first = true;
        while(size>1) {
            if (first) {
                for (int i = 1; i < m - 1 && pt != null; i++) {
                    pt = pt.next;
                    pre = pre.next;
                }
                first = false;
                pre.next = pt.next;
                System.out.print(pt.val+" ");
                size--;
                pt = pre.next;
            } else {
                for (int i = 0; i < m - 1 && pt != null; i++) {
                    pt = pt.next;
                    pre = pre.next;
                }
                pre.next = pt.next;
                System.out.print(pt.val+" ");
                size--;
                pt = pre.next;
            }
        }
        System.out.println();
        System.out.print(pre.val);
    }

    public void method3(){
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int[] n = new int[T];
        for(int i = 0; i<T; i++){
            n[i] = in.nextInt();
        }

        for(int i = 0; i<T; i++){
            int nums = 0;
            int step = n[i];
            if(step == 1){
                System.out.println(0);
                continue;
            }
            //偶数
            if(n[i] % 2 == 0){
                step = n[i] / 2;
                nums++;
                while(step!=1 ){
                    if(step% 2 == 0){
                        step = step / 2;
                        nums++;
                        if(nums>10000){
                            nums = -1;
                            break;
                        }
                    }else{
                        if(step == 1){
                            break;
                        }else {
                            step = 3 * step + 1;
                            nums++;
                            if(nums>10000){
                                nums = -1;
                                break;
                            }
                        }
                    }
                }
            }else{//奇数
                step = 3 * step + 1;
                nums++;
                if(step == 1){
                    break;
                }else{
                    while(step!=1){
                        if(step% 2 == 0){
                            step = step / 2;
                            nums++;
                            if(nums>10000){
                                nums = -1;
                                break;
                            }
                        }else{
                            if(step == 1){
                                break;
                            }else {
                                step = 3 * step + 1;
                                nums++;
                                if(nums>10000){
                                    nums = -1;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(nums);
        }
    }

    public void method2(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();
        int ans = 0;
        if(n<=m){
            ans = k;
            System.out.println(ans);
            return;
        }else{
            int add = n % m;
            if(add!=0){
                add = 1;
            }else{
                add = 0;
            }
            int times = n / m;
            ans = (times+add);
            System.out.println(ans);
            return;
        }
    }
}
