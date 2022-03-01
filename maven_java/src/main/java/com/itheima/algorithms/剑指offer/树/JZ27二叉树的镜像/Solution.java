package com.itheima.algorithms.剑指offer.树.JZ27二叉树的镜像;

import com.itheima.algorithms.剑指offer.树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 空间复杂度 O(n)。本题也有原地操作，即空间复杂度 O(1)的解法，时间复杂度 O(n)
 */
public class Solution {
    //采用递归方法
    public TreeNode Mirror (TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        TreeNode tmp = pRoot.left;
        pRoot.left = Mirror(pRoot.right);
        pRoot.right = Mirror(tmp);
        return pRoot;
    }

    //采用BFS解决
    public TreeNode Mirror2 (TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return pRoot;
    }

    //优化版本1，修改为尾递归
    public TreeNode Mirror3 (TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        TreeNode tmp = pRoot.left;
        pRoot.left = pRoot.right;
        pRoot.right = tmp;
        Mirror(pRoot.right);
        Mirror(pRoot.left);
        return pRoot;
    }
}
