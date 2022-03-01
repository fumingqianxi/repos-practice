package com.itheima.algorithms.剑指offer.树.JZ77按之字形顺序打印二叉树;

import com.itheima.algorithms.剑指offer.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)。
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (pRoot == null) {
            return lists;
        }
        int level = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while (curSize > 0) {
                TreeNode node = queue.poll();
                if (level % 2 != 0) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                curSize--;
            }
            lists.add(list);
            level++;
        }
        return lists;
    }
}
