package com.itheima.algorithms.剑指offer.树.JZ55二叉树的深度;

import com.itheima.algorithms.剑指offer.树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
 * 最长路径的长度为树的深度，根节点的深度视为 1 。
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)。
 */
public class Solution {
    //采用递归方法进行求解
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
//        int leftDepth = TreeDepth(root.left);
//        int rightDepth = TreeDepth(root.right);
//        int depth = leftDepth >= rightDepth ? leftDepth : rightDepth;
        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }

    //采用层次遍历
    public int TreeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            while (curSize > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                curSize--;
            }
            level++;
        }
        return level;
    }
}
