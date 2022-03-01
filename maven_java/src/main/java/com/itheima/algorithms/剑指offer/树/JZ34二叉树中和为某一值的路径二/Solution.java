package com.itheima.algorithms.剑指offer.树.JZ34二叉树中和为某一值的路径二;

import com.itheima.algorithms.剑指offer.树.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一颗二叉树的根节点root和一个整数expectNumber，找出二叉树中结点值的和为expectNumber的所有路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 */
public class Solution {
    private ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
    private ArrayList<Integer> path = new ArrayList<>();
    //采用递归求解
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int expectNumber) {
        if (root == null) {
            return paths;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == expectNumber) {
            paths.add(new ArrayList<>(path));
            //可直接优化掉
            path.remove(path.size() - 1);
            //可直接优化掉
            return paths;
        }
        FindPath(root.left, expectNumber - root.val);
        FindPath(root.right, expectNumber - root.val);
        path.remove(path.size() - 1);
        return paths;
    }
    //采用栈的思想，由于栈会丢失访问过的结点，所以无法实现
    public ArrayList<ArrayList<Integer>> FindPath2(TreeNode root, int expectNumber) {
        if (root == null) {
            return paths;
        }
        ArrayList<TreeNode> stack = new ArrayList<>();
        stack.add(root);
        path.add(root.val);
        TreeNode node = root;
        while (!stack.isEmpty()) {
            while (node.left != null) {
                stack.add(node.left);
                path.add(node.left.val);
                node = node.left;
            }
            //此处不能写成node = stack.pop()，这是因为node表示的是需要一直往左走的初始结点
            TreeNode tmp = stack.remove(stack.size() - 1);
            if (tmp.right == null) {
                int sum = 0;
                for (int a : path) {
                    sum += a;
                }
                if (sum == expectNumber) {
                    paths.add(new ArrayList<>(path));
                }
                path.remove(path.size() - 1);
            } else {
                stack.add(tmp.right);
                path.add(tmp.right.val);
                node = tmp.right;
            }
        }
        return paths;
    }
}
