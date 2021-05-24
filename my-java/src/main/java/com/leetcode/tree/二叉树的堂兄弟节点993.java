package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author zxl
 * @date 2021/5/17 10:43
 */
public class 二叉树的堂兄弟节点993 {

    /*
    在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
    如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
    我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
    只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。

    输入：root = [1,2,3,4], x = 4, y = 3
    输出：false

    输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
    输出：true

    输入：root = [1,2,3,null,4], x = 2, y = 3
    输出：false

    二叉树的节点数介于 2 到 100 之间。
    每个节点的值都是唯一的、范围为 1 到 100 的整数。
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
//        dfs(root, 0, null);
        bfs(root);
        return xDept == yDept && xParent != yParent;
    }

    int x;
    TreeNode xParent;
    int xDept;
    boolean xFound = false;

    int y;
    TreeNode yParent;
    int yDept;
    boolean yFound = false;

    /**
     * 深度优先
     * @param node 当前节点
     * @param depth 深度
     * @param parent 父节点
     */
    private void dfs(TreeNode node, int depth,TreeNode parent){
        if(node == null){
            return;
        }

        if(node.val == x){
            xDept = depth;
            xParent = parent;
            xFound = true;
        }else if(node.val == y){
            yDept = depth;
            yParent = parent;
            yFound = true;
        }

        if(xFound && yFound){
            return;
        }

        dfs(node.left,depth+1,node);

        if(xFound && yFound){
            return;
        }
        dfs(node.right,depth+1,node);
    }

    /**
     * 利用链表特性，先入先出，广度优先
     * @param root 树
     */
    private void bfs(TreeNode root){
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        nodeQueue.offer(root);
        depthQueue.offer(0);
        update(root, null, 0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int depth = depthQueue.poll();
            if (node.left != null) {
                nodeQueue.offer(node.left);
                depthQueue.offer(depth + 1);
                update(node.left, node, depth + 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                depthQueue.offer(depth + 1);
                update(node.right, node, depth + 1);
            }
            if (xFound && yFound) {
                break;
            }
        }

    }
    // 用来判断是否遍历到 x 或 y 的辅助函数
    public void update(TreeNode node, TreeNode parent, int depth) {
        if (node.val == x) {
            xParent = parent;
            xDept = depth;
            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDept = depth;
            yFound = true;
        }
    }




    public static void main(String[] args) {

//      [1,2,4,3,8,6,5,null,7]  5  8
//      [1,2,3,null,4,null,5]
//      [1,null,2,3,null,null,4,null,5]
        Integer[] tree = {1,2,4,3,8,6,5,null,7};
        TreeNode treeNode = TreeNode.buildBinaryTree(new TreeNode(), tree, 1);
        System.out.println(new 二叉树的堂兄弟节点993().isCousins(treeNode,5,8));
    }

}
