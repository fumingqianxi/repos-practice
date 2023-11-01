package com.itheima.algorithms.剑指offer.树.JZ26树的子结构;

import com.itheima.algorithms.剑指offer.树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。
 */
public class Solution {
    //借助辅助函数，递归求解
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return testSubtree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    public boolean testSubtree(TreeNode n1, TreeNode n2) {
//        if (root1 == null && root2 == null) {
//            return true;
//        }
        //由于该条件已经包含n1 == null && root2 == null的情况，优化if语句18-20
        if (n2 == null) {
            return true;
        }
        if (n1 == null) {
            return false;
        }
        return n1.val == n2.val && testSubtree(n1.left, n2.left) && testSubtree(n1.right, n2.right);
    }

    //优化版本1，修改名称
    public boolean HasSubtree2(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return isSubtree(root1, root2) || HasSubtree2(root1.left, root2) || HasSubtree2(root1.right, root2);
    }

    public boolean isSubtree(TreeNode root1, TreeNode root2) {
        //root2为null说明已经匹配到底并且由HasSubtree的第一步判断知，子树的根节点不为null，root2表示子节点
        if (root2 == null) {
            return true;
        }
        //表示root2不为null，所以匹配失败
        if (root1 == null) {
            return false;
        }
        return root1.val == root2.val && isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
    }

    //优化版本2，采用非递归方法
    public boolean HasSubtree3(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (isSubtree(cur, root2)) {
                return true;
            } else {
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return false;
    }
}
