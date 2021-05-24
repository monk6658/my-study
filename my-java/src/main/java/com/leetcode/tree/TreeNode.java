package com.leetcode.tree;

import java.util.Stack;

/**
 * 
 * @author zxl
 * @date 2021/5/17 10:43
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

//    [1,null,2,3,null,null,4,null,5]
    public TreeNode getTreeNode(Integer[] tree){
        if (tree.length == 0) {
            return new TreeNode(0);
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(tree[0]);
        stack.push(root);

        for(int i = 1; i < tree.length; i += 2){
            TreeNode curTreeNode = stack.pop();
            curTreeNode.left = new TreeNode(tree[i]);
            if(i+1 == tree.length){
                break;
            }

            curTreeNode.right = new TreeNode(tree[i+1]);
            stack.push(curTreeNode);
        }


        return null;
    }

    /**
     * 根据数组，创建传统二叉树
     * @param root 根部
     * @param tree 数组
     * @param index 下标
     * @return 二叉树
     */
    public static TreeNode buildBinaryTree(TreeNode root, Integer[] tree, int index){
        if(index == 1){
            root = new TreeNode(tree[0]);
        }
        if(2*index - 1 >= tree.length){
            return root;
        }
        root.left = tree[2*index - 1] == null ? null : new TreeNode(tree[2*index - 1]);
        if(2*index >= tree.length){
            return root;
        }
        root.right = tree[2*index] == null ? null : new TreeNode(tree[2*index]);

        if(root.left != null){
            buildBinaryTree(root.left,tree,++index);
        }
        if(root.right != null){
            buildBinaryTree(root.right,tree,++index);
        }
        return root;
    }




    public static void main(String[] args) {
        Integer[] tree = {1,null,2,3,null,null,4,null,5};
        buildBinaryTree(new TreeNode(),tree,1);
    }

}
