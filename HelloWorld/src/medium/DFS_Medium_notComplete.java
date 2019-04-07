package medium;

public class DFS_Medium_notComplete {
    public static void main(String args[]){
        DFS_Medium_notComplete main = new DFS_Medium_notComplete();
        System.out.println(main.isAdditiveNumber("199111992"));
    }

    //306. 累加数
    public boolean isAdditiveNumber(String num) {
        boolean ans = false;
/*        数字字符串加法，DFS。其实一开始选择好两个相加的字符串就已经能确定，它们是否能够一直加到最后。

        假设对于原始的字符串num，长度是size.
                我们选择str1=num.substr(0,len1)，str2 = num.substr(len1,len2)。满足str1.size()+str2.size()<num.size()。很容易发现len1=[1,size/2],len2=[1,size/2]为两个长度的取值范围，都是闭区间。
        选择一个len1,len2组合，剩余字符串为str3 = num.substr(len1+len2,size-len1-len2)。深度搜索DFS(str1,str2,str3)。
        为了处理溢出的过大的整数，不采用转整数的方式，而是采取字符串相加的原则。add(str1,str2)为两个数字字符串相加的结果。
        如果add(str1,str2)==str3，那么return true;否则至少str3的其中一个前缀是add(str1,str2),继续搜索下一步搜索。其余都可以return false。
        如果找到一个合适的组合，return true，如果最终发现len1,len2这个组合不行，更改组合，重复3-5。如果所有组合都不行，那么最后return false。
        注意事项：

        累加项不以0开头，实质上不严格。如果只是不以0开头，那么0必然不存在，事实并不是。严格的逻辑是除0以外，不以0开头*/
        int size = num.length();
        if(size<2){
            return ans;
        }

        if(size == 3){
            if(num.charAt(2) - '0' == num.charAt(0) - '0'+num.charAt(1) - '0'){
                return true;
            }else{
                return false;
            }
        }

        String s1, s2, s3;
        for(int len1 = 1; len1<=size / 2; len1++){
            s1 = num.substring(0, len1);
            for(int len2 = 1; len2<=size / 2; len2++){
                s2 = num.substring(len1, len1+len2);
                s3 = num.substring(len1+len2, size);
                if(addDFS(s1, s2, s3)){
                    return true;
                }
            }
        }

        return ans;
    }

    boolean addDFS(String first, String second, String last){
        boolean ans = false;
        if(first.charAt(0)=='0' && !"0".equals(first)  || second.charAt(0)=='0' && !"0".equals(second)){
            return ans;
        }else{
            String temp = add(first, second);
            if(temp.equals(last))
                return true;
            if(temp.length()>last.length())
                return false;
            else {
                if(!temp.equals(last.substring(0, temp.length()))){
                    return false;
                }else{
                    //之前符合条件，再次进行深度遍历
                    first = second;
                    second = temp;
                    last = last.substring(temp.length(), last.length());
                    return addDFS(first, second, last);
                }
            }
        }
    }

    String add(String first, String second){//为了避免大数溢出，使用字符串的相加
        int sgn = 0;//进位
        //先解决两个字符串长度不一致的问题
        int size1 = first.length();
        int size2 = second.length();
        if(size1<size2){//交换位置
            String temp = first;
            first = second;
            second = temp;
            int temp1 = size1;
            size1 = size2;
            size2 = temp1;
        }
        if(size1 != size2){
            for(int i = 0; i<size1-size2; i++){
                second = "0"+second;
            }
        }
        String ans = "";
        for(int i= size1-1; i>-1;  i--){//这里size1 = size2了
            int num = sgn+first.charAt(i) - '0'+second.charAt(i) - '0';
            //考虑进位
            sgn = num / 10;
            num = num % 10;
            ans = num + ans;
        }

        if(sgn!=0){
            ans = sgn+ans;
        }
        return ans;
    }
}
