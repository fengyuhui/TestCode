package medium;

import java.util.*;

public class DFS_Medium_notComplete {
    public static void main(String args[]){
        DFS_Medium_notComplete main = new DFS_Medium_notComplete();
        //System.out.println(main.isAdditiveNumber("199111992"));
        //char[][] board = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
        int[] a = {3,5,2};
        Arrays.sort(a);
        ArrayList<Integer> c = new ArrayList<>();
        c.add(1);c.add(5);c.add(4);
        Collections.sort(c);

        String word = "AAB";
        //System.out.println(main.exist(board, word));

        String[][] tickets = {{"EZE","TIA"},{"EZE","HBA"},{"AXA","TIA"},{"JFK","AXA"},{"ANU","JFK"},{"ADL","ANU"},{"TIA","AUA"},{"ANU","AUA"},{"ADL","EZE"},{"ADL","EZE"},{"EZE","ADL"},{"AXA","EZE"},{"AUA","AXA"},{"JFK","AXA"},{"AXA","AUA"},{"AUA","ADL"},{"ANU","EZE"},{"TIA","ADL"},{"EZE","ANU"},{"AUA","ANU"}};

        //System.out.println(main.findItinerary(tickets));

        int[][] grid = {{0,1}};
        //System.out.println(main.maxAreaOfIsland(grid));
        char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
        int click[] ={3,0};
        main.updateBoard(board, click);
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

    //79. 单词搜索
    public boolean exist(char[][] board, String word) {
        boolean ans = false;
        int size = word.length();
        int row = board.length;
        int col = board[0].length;
        int [][] visited = new int[row][col];//判断是否已经访问过
        if(size==0)
            return true;
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                if(dfsSearch(0, word,board,visited,i,j))
                    return true;
            }
        }
        return ans;
    }

    public boolean dfsSearch(int index, String word, char[][] board, int[][] visited, int row, int col){
        if(row<0 || col<0 || row>=board.length || col>=board[0].length || board[row][col] != word.charAt(index))
            return false;
        if(visited[row][col] == 0) {
            visited[row][col] = 1;
            index++;
            if (index == word.length())
                return true;

            if (dfsSearch(index, word, board, visited, row + 1, col) ||
                    dfsSearch(index, word, board, visited, row - 1, col) ||
                    dfsSearch(index, word, board, visited, row, col + 1) ||
                    dfsSearch(index, word, board, visited, row, col - 1))
                return true;
            visited[row][col] = 0;
        }
        return false;
    }

    //332. 重新安排行程
    public List<String> findItinerary(String[][] tickets) {
        List<String> ans = new ArrayList<>();
        //PS 每次矩阵都可以用for(str[]: strs)来循环，非常方便
        //建图！！！！
        Map<String,ArrayList> map = new HashMap<>();
        for(String[] ticket: tickets){
            if(!map.containsKey(ticket[0]))
                map.put(ticket[0], new ArrayList());
            map.get(ticket[0]).add(ticket[1]);
        }

        //遍历map，对路线进行字符自然排序
        for(String key:map.keySet()){
            //Collections和Arrays的sort方法都是升序排序
            Collections.sort(map.get(key));
        }

        //因为这是个有向图，所以当所有路线的城市节点都加进ans后（即结束遍历的条件），ans的节点数会=tickets.length+1，因为tickets是边
        HashSet<String> set = new HashSet<>();
        int len = tickets.length;
        dfsRoute(ans, map, len, set, "JFK", "");
        return ans;
    }

    public boolean dfsRoute(List<String> ans, Map<String, ArrayList> map, int len, HashSet<String> set, String currentPlace, String ticketNo){
        //先存下当前站的节点
        ans.add(currentPlace);
        //遍历结束条件
        if(ans.size() == len+1)
            return true;
        //如果这个站不是终点且没有票可以离开此站
        if(!map.containsKey(currentPlace)){
            int s = ans.size();
            ans.remove(s-1);//回溯，从路线中删除这个站
            set.remove(ticketNo);//回溯，将之前的票变为未使用状态。每条航线只有一张票(一笔画。站点可以重复去，但是航线不能重复)
            return false;
        }

        //遍历当前站点的所有目的地
        String dist = "";
        for(int i = 0; i<map.get(currentPlace).size(); i++){
            dist = (String)map.get(currentPlace).get(i);
            //如果票没有使用过
            if(!set.contains(currentPlace+"@"+i)){
                set.add(currentPlace+"@"+i);
                if(dfsRoute(ans, map, len, set, dist, currentPlace+"@"+i)){
                    return true;
                }
            }
        }
        int s = ans.size();
        ans.remove(s-1);//回溯，从路线中删除这个站
        set.remove(ticketNo);//回溯，将之前的票变为未使用状态。
        return false;
    }

    //695. 岛屿的最大面积
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        int row = grid.length;
        int col = grid[0].length;
        int[][] visited = new int[row][col];

        int temp = 0;
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                temp = dfsArea(row,col,grid, i, j, visited);
                ans = ans>temp?ans:temp;
            }
        }
        return ans;
    }

    public int dfsArea(int row, int col, int[][] grid, int i, int j,  int[][] visited){
        int ans = 0;
        if(i>=row || j>=col || i< 0 || j<0 || grid[i][j] == 0)
            return 0;
        if(visited[i][j] == 1)
            return 0;
        visited[i][j] = 1;
        ans = 1 +dfsArea(row, col, grid, i, j+1,visited)
        +dfsArea(row, col, grid, i+1, j, visited)
        +dfsArea(row, col, grid, i-1, j,visited)
        +dfsArea(row, col, grid, i, j-1, visited);
        return ans;
    }

    //529. 扫雷游戏
    public char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0], j = click[1];
        int row = board.length;
        int col = board[0].length;
        boolean visited[][] = new boolean[row][col];

        int count = 0;
        if(board[i][j] == 'M')
            board[i][j] = 'X';
        else  if(board[i][j] == 'E'){
            board = dfsBoard(board, i, j, visited);
        }
        return board;
    }

    public char[][] dfsBoard(char[][] board, int x, int y, boolean[][] visited){
        //八个相邻格子
        int count = 0;
        if(visited[x][y]){
            return board;
        }
        visited[x][y] = true;
        int[][] surrounder = {{0,1}, {0,-1}, {1,0}, {-1, 0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        if(board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        for(int[] s:surrounder){
            if(x+s[0]>=board.length || y+s[1]>=board[0].length || x+s[0]<0 || y+s[1]<0){
                return board;
            }else{
                board = dfsBoard(board, x+s[0], y+s[1], visited);
                if(board[x+s[0]][ y+s[1]] == 'M'){
                    count++;
                }
            }
        }
        if(count!=0) {
            board[x][y] = (char) count;
        }else{
            board[x][y] = 'B';
        }
        return board;
    }


}
