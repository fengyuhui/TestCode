package medium;

public class Array_Medium_notComplete {
    public static void main(String[] args){
        Array_Medium_notComplete main = new Array_Medium_notComplete();
        int[] height = {1,8,6,2,5,4,8,3,7};
        main.maxArea(height);

    }

    //11. 盛最多水的容器，当然这个是有复杂度n的最优解，现在用的是暴力复杂度n^2的解
    public int maxArea(int[] height) {
        int ans = Integer.MIN_VALUE;
        int currentMax = 0;
        int temp = 0, minH = 0;
        for(int i = 0; i<height.length; i++){
            currentMax = 0;
            minH = Integer.MAX_VALUE;
            for(int j = i; j<height.length; j++){
                minH = Math.min(height[i], height[j]);
                temp = (j - i) * minH;
                if(temp>currentMax)
                    currentMax = temp;
            }
            if(ans<currentMax)
                ans = currentMax;
        }
        return ans;
    }

    //542. 01 矩阵
    private int row;
    private int col;
    //private int[][] vector = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int[][] updateMatrix_1(int[][] matrix) {
        row = matrix.length;//行数
        col = matrix[0].length;//列数
        // 第一次遍历，正向遍历，根据相邻左元素和上元素得出当前元素的对应结果
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = row + col;
                }
                if (i > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][j] + 1);
                }
                if (j > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j - 1] + 1);
                }
            }
        }
        // 第二次遍历，反向遍历，根据相邻右元素和下元素及当前元素的结果得出最终结果
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i < row - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i + 1][j] + 1);
                }
                if (j < col - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j + 1] + 1);
                }
            }
        }
        return matrix;
    }


}
