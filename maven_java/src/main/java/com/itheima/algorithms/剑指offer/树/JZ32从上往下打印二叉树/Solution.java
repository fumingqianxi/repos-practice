package com.itheima.algorithms.剑指offer.树.JZ32从上往下打印二叉树;

import com.itheima.algorithms.剑指offer.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 不分行从上往下打印出二叉树的每个节点，同层节点从左至右打印。例如输入{8,6,10,#,#,2,1}，
 * 如以下图中的示例二叉树，则依次打印8,6,10,2,1(空节点不打印，跳过)，请你将打印的结果存放到一个数组里面，返回。
 */
public class Solution {
    //采用层次遍历打印
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        list.add(root.val);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                list.add(node.left.val);
            }
            if (node.right != null) {
                queue.offer(node.right);
                list.add(node.right.val);
            }
        }
        return list;
    }
}
