package com.leetcode.array;

import java.util.*;

import static com.sxm.common.util.PrintUtil.printArrays;


public class Array1 {

    public static void main(String[] args) {

        // 123698745
//        int[][] matrix = {
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12}
//        };
//
//        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
//            System.out.println("无效数组");
//        }
//
//        //行长度
//        int m = matrix.length;
//        //列长度
//        int n = matrix[0].length;
//        System.out.println(m + " " + n);
//        int i = 0,j = 0;
//        int result[] = new int[m * n];
//        int l = 0;
//        int z = m,y = n;
//
//        while( l< m*n ){
//
//
//
//            while(j < y){
//                result[l++] = matrix[i][j++];
//            }
//            j--;
//            i++;
//            while(i < z){
//                result[l++] = matrix[i++][j];
//            }
//            i--;
//            j--;
//            while(j >= n-y){
//                result[l++] = matrix[i][j--];
//            }
//            i--;
//            j++;
//            y--;
//            z--;
//            while(i >= m-z){
//                result[l++] = matrix[i--][j];
//            }
//            i++;
//            j++;
//            z--;
//        }
//
//        for (int a: result) {
//            System.out.print(a + " ");
//        }
//        System.out.println();

//        int[] nums = {0,0,1,1,1,2,2,3,3,4};
//        // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//        int len = removeDuplicates(nums);
//        System.out.println(len);
//        // 在函数里修改输入数组对于调用者是可见的。
//        // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
//        for (int i = 0; i < len; i++) {
//            System.out.print(nums[i]);
//        }

//        //合并重叠空间测试
//        int[][] intervals = {{1,4},{2,3}};
//        intervals = new int[][]{{1,4},{0,3}};
//        int[][] re = 合并重叠空间(intervals);
//        printArrays(re);

        TreeSet treeSet = new TreeSet();
        treeSet.add("sd");

//        //旋转矩阵
//        int[][] matrix = {
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,16}
//        };
//        matrix = new int[][]{
//                {1,2,3},
//                {5,6,7},
//                {9,10,11},
//        };
//        旋转矩阵(matrix);


//        //零矩阵
//        int[][] matrix = {
//                {0,1,2,0},
//                {3,4,5,2},
//                {1,3,1,5}
//        };
//        matrix = new int[][]{{1},{0}};
//        零矩阵(matrix);


        //对角线遍历矩阵
        int[][] matrix = {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };
        对角线遍历矩阵(matrix);

    }

    /**
     * 移出重复参数，返回数组实际长度
     * @author zxl
     * @time 2020/1/15 16:24
     */
    public static int removeDuplicates(int[] nums) {

        if(nums == null || nums.length == 0){
            return 0;
        }
        int max = nums[nums.length - 1];
        int maxC = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;

            if(j < nums.length && nums[j] > nums[i]){
                break;
            }

            while ( j < nums.length){
                if(maxC == 1){
                    break;
                }
                if(max == nums[j]){
                    maxC++;
                }
                if(nums[j] > nums[i] && j != i+1){
                    nums[j - 1] = nums[j];
                    break;
                }
                j++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if(max == nums[i]){
                return i+1;
            }
        }

        return 0;
    }

    /**
     * 杨辉三角
     * @author zxl
     * @time 2020/1/15 14:49
     */
    public List<List<Integer>> generate(int numRows) {




        return null;
    }

    /***
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * @method spiralOrder
     * @param matrix
     * @return java.util.List<java.lang.Integer>
     * @Author zxl
     * @Date 2019/11/6 15:21
     **/
    public List<Integer> spiralOrder(int[][] matrix) {

        return null;
    }


    /**
     * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，
     *
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)return new int[0];
        int m = matrix.length;//几个一维数组
        int n = matrix[0].length;//每一个数组长度
        int[] result = new int[m * n];//结果集
        for(int l = 0; l < m + n - 1; l++) {//遍历次数
            if (l % 2 != 0) {
                int i = (l < n ? 0 : (l - n + 1));
                int j = (l < n ? l : (n - 1));
                while (i < m && j >= 0) {
                    result[l] = matrix[i][j];
                    System.out.print(matrix[i][j]);
                    i++;
                    j--;
                }
            } else {
                int i = (l < m ? l : (m - 1));
                int j = (l < m ? 0 : (l - m + 1));
                while(i >= 0 && j < n){
                    result[l] = matrix[i][j];
                    System.out.print(matrix[i][j]);
                    i--;
                    j++;
                }
            }
        }
        return result;
    }
    
    /***
     *  根据奇偶规则优化
     *                 (00)
     * 1:            (01)(10)
     * 2:          (20)(11)(02)
     * 3:        (03)(12)(21)(30)
     * 4:      (40)(31)(22)(13)(04)
     * 5:    (05)(14)(23)(32)(41)(50)
     * 6:  (60)(51)................(06)
     * **/
    public int[] findDiagonalOrder1(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)return new int[0];
        int m = matrix.length;//几个一维数组
        int n = matrix[0].length;//每一个数组长度
        int[] result = new int[m * n];//结果集
        int i = 0, j = 0;
        for(int l = 0; l < m*n; l++) {//遍历次数
            result[l] = matrix[i][j];
            if ((i + j) % 2 == 0) {//上走
                if(j == n -1){//边界
                    i++;
                }else if(i == 0){//第一行
                    j++;
                }else{//正常走
                    i--;j++;
                }
            }else{//下走
                if(i == m - 1){//边界
                    j++;
                }else if(j == 0){//第一列
                    i++;
                } else{//正常
                    i++;j--;
                }
            }
        }
        return result;
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1]++;
            return digits;
        }
        int i = digits.length - 1;
        while (digits[i] == 9) {
            digits[i] = 0;
            if (--i < 0) {
                int[] result = new int[digits.length + 1];
                result[0] = 1;
                return result;
            }
        }
        digits[i]++;
        return digits;

    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     * 如果是，则返回最大元素的索引，否则返回-1
     *
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        /** 方法一：数组赋值方法 */
//        if(nums.length == 1){
//            return 0;
//        }
//        int[] newn = new int[nums.length];
//        System.arraycopy(nums,0,newn,0,nums.length);
//        Arrays.sort(newn);
//        if(newn[newn.length -1] >= newn[newn.length - 2] * 2 ){
//            for (int i = 0;i<nums.length ;i++){
//                if(nums[i] == newn[newn.length -1])return i;
//            }
//        }
//        return -1;

        /** 方法一：数组赋值方法 */
        if (nums.length == 1) return 0;
        int min = 0, max = nums.length - 1;
        while (min < max) {

        }

        return -1;
    }

    /**
     * 寻找中心索引（数组中存在一个数使得索引左边和等于右边和，不存在返回-1）
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int leftSum = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (sum - nums[i] == leftSum * 2) {
                return i;
            } else {
                leftSum += nums[i];
            }
        }
        return -1;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     *
     *
     * 示例 1:
     * 输入: [1,3,5,6], 5
     * 输出: 2
     *
     * 输入: [1,3,5,6], 2
     * 输出: 1
     *
     * 输入: [1,3,5,6], 7
     * 输出: 4
     *
     * 输入: [1,3,5,6], 0
     * 输出: 0
     *
     * @param nums 有序数组
     * @param target 插入值
     * @return 插入索引
     */
    public int searchInsert(int[] nums, int target) {
        return searchInsert二分法(nums,target);
    }
    public int searchInsert基本搜索(int[] nums, int target) {
        if(nums[0] > target){
            return 0;
        }
        int i = 0;
        for (i = 0; i < nums.length; i++){
            if(nums[i] == target){
                return i;
            }

            if(nums[i] < target && i+1 < nums.length && nums[i+1] >target){
                return i + 1;
            }

        }
        return i;
    }
    public int searchInsert二分法(int[] nums, int target) {
        int pre = 0, last = nums.length - 1;
        while (pre <= last){
            // <<:左移运算符，num << 1,相当于num乘以2
            // >>:右移运算符，num >> 1,相当于num除以2
            // >>>:无符号右移，忽略符号位，空位都以0补齐
            int mid = (last + pre) >> 1;
            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] > target){
                last = mid - 1;
            }else if(nums[mid] < target){
                pre = mid + 1;
            }
        }
        return last + 1;
    }


    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * 输入: intervals = [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * 提示：
     * intervals[i][0] <= intervals[i][1]
     *
     * @param intervals 二维数据
     * @return 返回重叠之后数组
     */
    public static int[][] 合并重叠空间(int[][] intervals) {

        // 非空判断
        if (intervals.length == 0) {
            return new int[0][2];
        }

        //排序 根据第一列第二列排序
        Arrays.sort(intervals, (o1, o2) -> !Objects.equals(o1[0],o2[0])?o1[0]-o2[0]:o2[1]-o1[1]);
        List<int[]> temp = new ArrayList<>();
        for(int i =0;i<intervals.length;i++){
            int l = intervals[i][0];//索引0的值
            int r = intervals[i][1];//索引1的值
            int lr;//上一个索引1的值
            //如果是第一组区间或上一组区间的值小于当前区间的索引0的值，直接放置进去。
            if(i==0||(lr = temp.get(temp.size()-1)[1])<l){
                temp.add(new int[]{l,r});
            }else{
                // 上一组区间的值大于当前区间的索引0的值，使用三元表达式取最大值进行替换
                temp.get(temp.size()-1)[1]=Math.max(lr, r);
            }
        }
        return temp.toArray(new int[temp.size()][]);
    }


    /**
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     * 不占用额外内存空间能否做到？
     *
     * 示例 1:
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     *
     * 示例 2:
     * 给定 matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     *
     *
     * @param matrix 原始矩阵
     */
    public static void 旋转矩阵(int[][] matrix) {
        if(matrix.length == 0){
            return;
        }
//        旋转矩阵_对角线(matrix);
//        旋转矩阵_自旋转(matrix);
//        旋转矩阵_辅助数组规则替换(matrix);
        旋转矩阵_水平_对角(matrix);
        printArrays(matrix);
    }
    /**
     * 方法一：对角线——>分组倒序
     * @param matrix 参数
     */
    private static void 旋转矩阵_对角线(int[][] matrix) {

        //对角线旋转
        for(int i=0;i<matrix.length;i++){
            for(int j = matrix[i].length-1;j>i;j--){
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        //每组倒序
        for(int i = 0; i<matrix.length;i++){
            for(int j=0;j<matrix[i].length/2;j++){
                int t = matrix[i][matrix[i].length-j-1];
                matrix[i][matrix[i].length-j-1] = matrix[i][j];
                matrix[i][j] = t;
            }
        }
    }
    /**
     * 方法二： 每个数字旋转
     * @param matrix 二维数组
     */
    private static void 旋转矩阵_自旋转(int[][] matrix) {
        int n = matrix.length;
        for(int i=0;i<n/2;i++){
            for(int j=0;j<(n+1)/2;j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i]=matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = t;
            }
        }
    }
    /**
     * 方法三：辅助数组替换
     * @param matrix 二维数组
     */
    private static void 旋转矩阵_辅助数组规则替换(int[][] matrix) {
        int n = matrix.length;
        int[][] temp = new int[n][n];
        //规则替换
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                temp[j][n-i-1] = matrix[i][j];
            }
        }
        //还给原数组
        for(int i=0;i<n;i++){
            System.arraycopy(temp[i], 0, matrix[i], 0, n);//原数组 原数组开始位置 目标数组 目标开始位置 个数
        }
    }
    /**
     * 方法四：水平——>对角线
     * @param matrix 参数
     */
    private static void 旋转矩阵_水平_对角(int[][] matrix) {
        int n = matrix.length;
        //水平换位
        for (int i=0;i<n/2;i++){
            for (int j=0;j<n;j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[n-i-1][j];
                matrix[n-i-1][j] = t;
            }
        }

        //对角线旋转
        for(int i=0;i<n;i++){
            for(int j = n-1;j>i;j--){
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }

    }


    /**
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     * 输入：
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出：
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     *
     * @param matrix 矩阵
     */
    private static void 零矩阵(int[][] matrix){

        printArrays(matrix);

//        零矩阵_记录下标值替换法(matrix);
        零矩阵_首行列坐标标记法(matrix);

        System.out.println("原矩阵");
        printArrays(matrix);
    }
    /**
     * 记录0所在坐标 ———> 根据坐标一一替换
     * @param matrix 二维数组
     */
    private static void 零矩阵_记录下标值替换法(int[][] matrix){
        if(matrix.length == 0){
            return;
        }
        //1. 记录0所在坐标
        List<int[]> temp = new ArrayList<>();
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == 0){
                    temp.add(new int[]{i,j});
                }
            }
        }

        //2. 根据坐标一一替换
        for (int[] tem : temp){
            for(int j = 0; j < matrix[tem[0]].length; j++){
                matrix[tem[0]][j] = 0;
            }
            for(int i = 0; i < n;i++){
                matrix[i][tem[1]] = 0;
            }
        }
    }
    private static void 零矩阵_首行列坐标标记法(int[][] matrix){
        if(matrix.length == 0){
            return;
        }
        boolean firstCol = false;
        boolean firstRow = false;
        int n = matrix.length;
        int c = matrix[0].length;
        //1. 判断首行首列是否有0
        for (int[] ints : matrix) {
            if (ints[0] == 0) {//首行
                firstRow = true;
            }
        }
        for(int i = 0; i < c; i++){
            if (matrix[0][i] == 0){//首列
                firstCol = true;
            }
        }

        //2. 判断除首行首列是否存在0，存在则把首行首列置0
        for (int i = 1; i < n; i++) {
            for (int j = 1; j<c;j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //3. 判断首行是否存在0，存在，把该行置0
        for (int i = 1;i <n ;i++){
            if(matrix[i][0]==0){
                matrix[i] = new int[c];
            }
        }

        //4. 判断首列是否有0，存在0，把该列置为0
        for (int j =1;j<c;j++){
            if(matrix[0][j]==0){
                for(int i =1;i<n;i++){
                    matrix[i][j] = 0;
                }
            }
        }

        //5. 若首行首列有值，把首行或首列置0
        if(firstCol){
            matrix[0] = new int[c];
        }
        if(firstRow){
            for(int i =0;i<n;i++){
                matrix[i][0] = 0;
            }
        }

    }


    /**
     * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     *
     * 输出:  [1,2,4,7,5,3,6,8,9]
     * @param matrix 矩阵
     */
    private static int[] 对角线遍历矩阵(int[][] matrix){
        printArrays(matrix);
        System.out.println("结束");

        if(matrix.length == 0){
            return new int[0];
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[] result = new int[m*n];

        //1. 单行单列处理
        if(n == 1){//只有一行的情况下
            return matrix[0];
        }else if(m == 1){//只有一列的情况下
            for(int i=0;i<m;i++){
                result[i] = matrix[i][0];
            }
            return result;
        }

        //2. 多行多列处理
        result[0] = matrix[0][0];
        int r = 1;
        int i=0,j=1,k=0;
        while(k <= m+n-2){
            k++;
            //1. 向下遍历
            while (i+j==k&&i<n&&j<m&&i>=0&&j>=0){
                result[r++] = matrix[i][j];
                j--;i++;
            }
            if(j<0){
                j++;
            }
            if(j==0){
                j=m-1;
            }
            if(i==n){
                i--;
            }
            k++;
            //2. 向上遍历
            while (i+j==k&&i<n&&j<m&&i>=0&&j>=0){
                result[r++] = matrix[i][j];
                j++;i--;
            }
            if(i<0){
                i++;
            }
            if(j==m){
                j--;
            }
            if(i==0){
                i=n-1;
            }
        }

        printArrays(result);

        return result;
    }




}
