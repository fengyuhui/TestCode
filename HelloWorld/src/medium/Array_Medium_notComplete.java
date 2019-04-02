package medium;

public class Array_Medium_notComplete {
    public static void main(String[] args){
        Array_Medium_notComplete main = new Array_Medium_notComplete();
        int[] height = {1,8,6,2,5,4,8,3,7};
        main.maxArea(height);

        int[] sor = {0,6,1,2,7,9,3,4,5,10,8};
        main.quickSort(sor);
        System.out.println(sor);

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

    //快排
    /*******************************************************
     *快速排序 (比较排序类)
     *每次排序将待排记录分割两部分,一部分都比关键字小,一部分都比关键字大。这个快排是要预留第一位为0的！！！！
     ********/
    public void quickSort(int[] L) {
        Qsort(L,1,L.length-1);
    }

    public void Qsort(int[] L,int low,int high) {
        int pivot;
        if(low<high) {
            //将L[low,high]一分为二,算出枢轴值pivot,该值得位置固定,不用再变化
            pivot=partition0(L,low,high);

            //对两边的数组分别排序
            Qsort(L,low,pivot-1);
            Qsort(L,pivot+1,high);
        }
    }

    //  选择一个枢轴值(关键字) 把它放到某个位置 使其左边的值都比它小 右边的值都比它大
    public int partition0(int[] L,int low,int high) {
        int pivotkey;
        pivotkey=L[low];
        //顺序很重要，要先从右边找
        while(low<high) {
            while(low<high && L[high]>=pivotkey) {  //从后往前找到比key小的放到前面去
                high--;
            }
            swap(L,low,high);
            while(low<high && L[low]<=pivotkey) {  //从前往后找到比key大的 放到后面去
                low++;
            }
            swap(L,low,high);
        } //遍历所有记录  low的位置即为 key所在位置, 且固定,不用再改变
        return low;
    }
    //交换数组的两个位置
    public void swap(int[] L,int i,int j) {

        int temp=L[i];
        L[i]=L[j];
        L[j]=temp;

    }


}
