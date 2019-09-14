import java.util.*;

public class Main {
    public static void main(String[] args){
        Main test = new Main();
        test.method4();
    }
    public void method1(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        Set set = new HashSet<>();
        boolean[][] visited = new boolean[n][m];
        int[][] board = new int[n][m];
        //初始化
        for (int i = 0; i < k; i++) {
            int indexi = in.nextInt();
            int indexj = in.nextInt();
            board[indexi][indexj] = 1;
        }
        dfsRoute(set,visited,board,0,n,m,0,0);
        if(set.isEmpty()){
            System.out.println(0);
            return;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < set.size(); i++) {
            if(set.iterator().hasNext()){
                int value = (int)set.iterator().next();
                min = min>value?value:min;
            }
        }
        System.out.println(min+1);
    }

    public boolean dfsRoute(Set set, boolean[][] visited,int[][] board, int level ,int n, int m, int indexi, int indexj) {
        if (indexi >= n || indexj >= m || indexi < 0 || indexj < 0 || board[indexi][indexj] == 1)
            return false;
        if (visited[indexi][indexj])
            return false;
        level++;
        visited[indexi][indexj] = true;
        if (indexi == n - 1 && indexj == m - 1) {
            set.add(level);
            return true;
        }
       return dfsRoute(set, visited, board, level, n, m, indexi + 1, indexj) || dfsRoute(set, visited, board, level, n, m, indexi - 1, indexj) || dfsRoute(set,visited,board,level,n,m,indexi,indexj+1)||  dfsRoute(set,visited,board,level,n,m,indexi,indexj-1);
    }

    public void method2(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Stack stack = new Stack();
        int leftComment = 0,rightComment = 0;
        StringBuffer stringBuffer = new StringBuffer("");
        for(int i = 0; i<str.length(); i++){
            if('<'==str.charAt(i)){
                if(!stack.isEmpty() && leftComment==0 && rightComment == 0 ){
                    stack.pop();
                }
            }
            else if('(' == str.charAt(i)){
                leftComment++;
            }
            else if(')'==str.charAt(i)){
                rightComment++;
                leftComment -=rightComment;
                rightComment = 0;
            }
            else if(rightComment==0 && leftComment == 0){
                stack.push(str.charAt(i));
            }
        }

        while(!stack.isEmpty()){
            stringBuffer.append(stack.pop());
        }
        System.out.println(stringBuffer.reverse().toString());
    }

    public void method3(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int starti = 0, startj = 0;
        int endi = 0, endj = 0;
        Set set = new HashSet<>();
        boolean[][] visited = new boolean[n][n];
        int[][] board= new int[n][n];
        //初始化
        for(int i = 0; i<n; i++){
            String s = in.nextLine();
            for(int j = 0; j<s.length(); j++){
                if('.'==s.charAt(j)){
                    board[i][j] = 0;
                }else if('#'==s.charAt(j)){
                    board[i][j] = 1;
                }else if('S'==s.charAt(j)){
                    starti = i;
                    startj = j;
                }else if('E'==s.charAt(j)){
                    endi = i;
                    endj = j;
                }
            }
        }

        dfs(set,visited,board,-1,n,n,0,0,endi,endj);
        if(set.isEmpty()){
            System.out.println(-1);
            return;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < set.size(); i++) {
            if(set.iterator().hasNext()){
                int value = (int)set.iterator().next();
                min = min>value?value:min;
            }
        }
        System.out.println(min);
    }

    public boolean dfs(Set set, boolean[][] visited,int[][] board, int level ,int n, int m, int indexi, int indexj,int endi, int endj) {
        if (board[indexi][indexj] == 1)
            return false;
        if (visited[indexi][indexj])
            return false;
        if(indexi==n)
            indexi = 0;
        if(indexi == -1)
            indexi = n-1;
        if(indexj==n)
            indexj = 0;
        if(indexj == -1)
            indexj = n-1;
        visited[indexi][indexj] = true;
        level++;
        if (indexi == endi && indexj == endj) {
            set.add(level);
            return true;
        }
        if(dfs(set, visited, board, level, n, m, indexi + 1, indexj,endi,endj) || dfs(set, visited, board, level, n, m, indexi - 1, indexj,endi,endj)||dfs(set,visited,board,level,n,m,indexi,indexj+1,endi,endj)||dfs(set,visited,board,level,n,m,indexi,indexj-1,endi,endj))
            return true;
        visited[indexi][indexj] = false;
        return false;
    }

    public void method4(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p[][] = new int[n][2];
        int ans = 0;
        //初始化
        for(int i = 0;i<n; i++){
            int x = in.nextInt();
            int h = in.nextInt();
            p[i][0] = x;
            p[i][1] = h;
        }

        int maxx = Integer.MAX_VALUE;
        int maxh = Integer.MAX_VALUE;
        for (int i = 0; i < p.length; i++) {
            maxx = Integer.MAX_VALUE;
            maxh = Integer.MAX_VALUE;
            int minIndex = -1;
            for(int j = 0; j<p.length; j++){
                if(p[j][0]<=maxx && p[j][1]<=maxh&&p[j][0]!=Integer.MAX_VALUE&&p[j][1]!=Integer.MAX_VALUE){
                    maxx = p[j][0];
                    maxh = p[j][1];
                    minIndex = j;
                }
            }
            if(minIndex!=-1) {
                p[minIndex][0] = Integer.MAX_VALUE;
                p[minIndex][1] = Integer.MAX_VALUE;
                ans++;
            }
        }
        System.out.println(ans);
    }
}
