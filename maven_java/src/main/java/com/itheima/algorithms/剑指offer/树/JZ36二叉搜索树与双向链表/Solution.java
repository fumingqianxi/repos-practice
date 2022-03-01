package com.itheima.algorithms.剑指offer.树.JZ36二叉搜索树与双向链表;

import com.itheima.algorithms.剑指offer.树.TreeNode;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 */
public class Solution {
    //采用递归方法
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        if (pRootOfTree.right != null) {
            TreeNode right = Convert(pRootOfTree.right);
            pRootOfTree.right = right;
            right.left = pRootOfTree;
        }
        if (pRootOfTree.left != null) {
            TreeNode left = Convert(pRootOfTree.left);
            TreeNode cur = left;
            while (cur.right != null) {
                cur = cur.right;
            }
            cur.right = pRootOfTree;
            pRootOfTree.left = cur;
            return left;
        }
        return pRootOfTree;
    }
}
