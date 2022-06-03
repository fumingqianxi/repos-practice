package com.itheima.algorithms.剑指offer.树.JZ79判断是不是平衡二叉树;

import com.itheima.algorithms.剑指offer.树.TreeNode;

/**
 * 输入一棵节点数为 n 二叉树，判断该二叉树是否是平衡二叉树。
 */
public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        int diff = Math.abs(depth(root.left) - depth(root.right));
        if (diff > 1) {
            return false;
        }
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
