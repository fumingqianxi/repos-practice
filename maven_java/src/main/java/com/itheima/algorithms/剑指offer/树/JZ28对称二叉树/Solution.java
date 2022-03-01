package com.itheima.algorithms.剑指offer.树.JZ28对称二叉树;

import com.itheima.algorithms.剑指offer.树.TreeNode;

/**
 * 给定一棵二叉树，判断其是否是自身的镜像（即：是否对称）
 */
public class Solution {
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return verify(pRoot.left, pRoot.right);
    }

    public boolean verify(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2== null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return verify(root1.left, root2.right) && verify(root1.right, root2.left);
    }
}
