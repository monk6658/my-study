package com.leetcode.array;

public class 二进制矩阵的最短路径1091 {


    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {0,1},{1,0}
        };
        
        int[][] ints = new int[][]{
                {0,0,0},
                {1,1,0},
                {1,1,0}
        };
        
        System.out.println(shortestPathBinaryMatrix(ints));
        
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {
        
        
        if(grid[0][0] == 1){
            return -1;
        }
        
        int i = 0, j = 0;
        int l = 1;
        int n = grid.length - 1;
        
        while (i < n && j < n){
            if(i+1 <= n && j+1 <= n && grid[i+1][j+1] == 0){
                i++;
                j++;
                l++;
            }
            else if(j+1 <= n && grid[i][j+1] == 0){
                j++;
                l++;
            }
            else if(i+1 <= n && grid[i+1][j] == 0){
                i++;
                l++;
            }else{
                return -1;
            }
            
        }
        
        return l;
    }
    
    
    
}
