package com.leetcode.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author zxl
 * @date 2021/5/10 9:18
 */
public class 叶子相似的树 {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        if(root1 != null){
            findVal(root1,list1);
        }
        List<Integer> list2 = new ArrayList<>();
        if(root2 != null){
            findVal(root2,list2);
        }
        return Objects.equals(list1,list2);
    }

//    /**
//     * 递归
//     * @param root1 树
//     * @param nums 值
//     */
//    public void findVal(TreeNode root1, List<Integer> nums){
//        TreeNode left = root1.left;
//        TreeNode right = root1.right;
//        if(left == null && right == null){
//            nums.add(root1.val);
//        }else{
//            if(left != null){
//                findVal(left,nums);
//            }
//            if(right != null){
//                findVal(right,nums);
//            }
//        }
//    }

    /**
     * 迭代/非递
     * @param root1 树
     * @param nums 值
     */
    private void findVal(TreeNode root1, List<Integer> nums){

        Stack<TreeNode> stack = new Stack<>();

        while (root1 != null || !stack.empty()){
            while (root1 != null){
                stack.push(root1.left);
                root1 = root1.left;
            }
            TreeNode pop = stack.pop();
            if(pop.left == null && pop.right == null){
                nums.add(pop.val);
            }

            root1 = pop.right;
        }

    }


}
