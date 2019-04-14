package medium;

public class Arithmetic_Medium_Complete {
    public static void main(String[] args){
        Arithmetic_Medium_Complete main = new Arithmetic_Medium_Complete();
        main.canTransform("XXXLXXXXXX", "XXXLXXXXXX");
    }

    //319. 灯泡开关
/*    灯泡i会被按几次？这其实相当于求i有几个因子 比如灯泡8,一共会被按4次，分别是第一轮 第二轮 第四轮 第八轮
    假如x*y=z，显然z%y==0且z%x==0。 也就是说你只要需要枚举j从到1..根号i，count += i%j==0? 2:0;
    通过枚举，count最终的结果都是偶数，当且仅当 i可以被开根号时，count才会是奇数！
    在1..n中，假设n等于100，1*1 2*2 3*3 4*4 ... 10*10的结果都小于等于100，换句话说，1*1 ... 根号n*根号n 都<= n，
    所以求1..n里有几个开根号能开尽的数，其实就是求根号n向下取整等于几。*/
    public int bulbSwitch(int n) {
        return (int)Math.floor(Math.sqrt(n));//大概是我见过的最短的leetcode了= =
    }

    //777. 在LR字符串中交换相邻字符
    public boolean canTransform(String start, String end) {
        //可以略过X，然后对比两个字符串，start的L不能比end的更左，start的R不能比end的更右
        //先判断去掉X之后两个字符串是否相等
        String s1 = start.replace("X","");
        String s2 = end.replace("X", "");
        if(!s1.equals(s2))
            return false;
        int i = 0, j = 0;
        while(i<start.length() && j<end.length()){
            while(i<start.length() &&start.charAt(i) == 'X')
                i++;
            while(j<end.length() && end.charAt(j) == 'X')
                j++;
            //判断是否已经读到了末尾
            if(i>=start.length() || j>=end.length())
                return true;
            if(start.charAt(i)!=end.charAt(j))
                return false;
            else{
                if(start.charAt(i) == 'L' && i<j)
                    return false;
                if(start.charAt(i) == 'R' && i>j)
                    return false;
            }
            i++;
            j++;
        }
        return true;
    }

    //292. Nim游戏
    //巴什博弈，两个顶尖聪明的人在玩游戏，有n个石子，每人可以随便拿1-m个石子，不能拿的人为败者，问谁会胜利
    //n%(m+1)!=0时，先手总是会赢的，n是总数，m是每次最多拿走的数量
    //因为面临m+1的人一定会失败，这样的话两个人的最优策略一定是通过拿走石子，使得对方拿石子时还剩下m+1个
    public boolean canWinNim(int n) {
        if(n % 4 ==0)
            return false;
        return true;
    }

}
