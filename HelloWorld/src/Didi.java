import java.util.*;

public class Didi {
    public static void main(String[] args){
        Didi didi = new Didi();
        didi.method1();
    }
    void method1(){
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        String string = in.nextLine();
        String[] s = string.split(" ");
        //偶数位为数字，奇数位为运算符
        List<Integer> a = new ArrayList<>();
        List<Integer> m = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer("");
        int level = 0;
        for(int j = 0;j<s.length;  j+=2){
            if(j>1 && j<s.length-1){
                if(("+".equals(s[j-1]) || "-".equals(s[j-1])) && ("+".equals(s[j+1]) || "-".equals(s[j+1])))
                    level = 1;
                else
                    level = 2;
            }
            if(j==0&&"+".equals(s[j+1])) {
                a.add(Integer.parseInt(s[0]));
                continue;
            }
            if(j==0&&"*".equals(s[j+1])) {
                m.add(Integer.parseInt(s[0]));
                continue;
            }
            //连续的加号可以排序
            if("+".equals(s[j-1])) {
                if (j<s.length-1&& "+".equals(s[j+1]) || j==s.length-1)
                    a.add(Integer.parseInt(s[j]));
                else
                    continue;
            } else{
                if(a.size()!=0) {
                    Collections.sort(a);
                    stringBuffer.append(a.get(0));
                    for (int i = 1; i < a.size(); i++) {
                        stringBuffer.append(" + " + a.get(i));
                    }
                    a = new ArrayList<>();
                }

                //连续的乘号可以排序
                if("*".equals(s[j-1]) || level == 1) {
                    if (j<s.length-1&& "*".equals(s[j+1]) || j==s.length-1)
                        m.add(Integer.parseInt(s[j]));
                }else{
                    if(m.size()!=0) {
                        Collections.sort(m);
                        stringBuffer.append(m.get(0));
                        for (int i = 1; i < m.size(); i++) {
                            stringBuffer.append(" * " + m.get(i));
                        }
                        m = new ArrayList<>();
                    }
                    //其他符号直接加到式子里
                    stringBuffer.append(" "+s[j-1]+" "+s[j]);
                }
            }
        }
        System.out.println(stringBuffer);
    }
}
