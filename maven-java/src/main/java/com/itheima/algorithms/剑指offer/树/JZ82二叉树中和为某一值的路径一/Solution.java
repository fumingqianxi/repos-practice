package com.itheima.algorithms.剑指offer.树.JZ82二叉树中和为某一值的路径一;

import com.itheima.algorithms.剑指offer.树.TreeNode;

/**
 * 给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 */
public class Solution {
    //采用递归方法
    public boolean hasPathSum (TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        //只需要保留root.val == sum即可，因为继续递归仍返回false
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return true;
            } else {
                return false;
            }
        }
        //该步骤多余，因为root.left == null，继续递归为false
        if (root.left == null) {
            return hasPathSum(root.right, sum - root.val);
        }
        //该步骤多余，因为root.right == null，继续递归为false
        if (root.right == null) {
            return hasPathSum(root.left, sum - root.val);
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    //优化版本1
    public boolean hasPathSum2 (TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
