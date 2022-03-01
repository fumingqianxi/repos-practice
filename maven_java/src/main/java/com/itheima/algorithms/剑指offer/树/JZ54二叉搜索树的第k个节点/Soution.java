package com.itheima.algorithms.剑指offer.树.JZ54二叉搜索树的第k个节点;

import com.itheima.algorithms.剑指offer.树.TreeNode;

import java.util.Stack;

/**
 * 给定一棵结点数为n 二叉搜索树，请找出其中的第 k 小的TreeNode结点值。
 * 1.返回第k小的节点值即可
 * 2.不能查找的情况，如二叉树为空，则返回-1，或者k大于n等等，也返回-1
 * 3.保证n个节点的值不一样
 * 进阶：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class Soution {
    //递归，借助辅助函数
    public int KthNode (TreeNode proot, int k) {
        if (proot == null || count(proot) < k) {
            return -1;
        }
        int left = count(proot.left);
        if (left + 1 == k) {
            return proot.val;
        } else if (left + 1 < k) {
            return KthNode(proot.right, k - left - 1);
        } else {
            return KthNode(proot.left, k);
        }
    }

    public int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return count(node.left) + count(node.right) + 1;
    }

    //优化版本1，利用二叉搜索树中序遍历有序的特点
    private int result = -1;
    private int count = 0;
    public int KthNode2 (TreeNode proot, int k) {
        inOrder(proot, k);
        return result;
    }

    public void inOrder(TreeNode root, int k) {
        if (root == null || count > k) {
            return;
        }
        inOrder(root.left, k);
        count++;
        if (count == k) {
            result = root.val;
        }
        inOrder(root.right, k);
    }

    //采用非递归中序遍历
    public int KthNode3 (TreeNode proot, int k) {
        if (proot == null) {
            return -1;
        }
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        stack.push(proot);
        TreeNode node = proot;
        while (!stack.isEmpty()) {
            while (node.left != null) {
                stack.push(node.left);
                node = node.left;
            }
            count++;
            if (count == k) {
                return stack.pop().val;
            }
            //此处不能写成node = stack.pop()，这是因为node表示的是需要一直往左走的初始结点
            TreeNode tmp = stack.pop();
            if (tmp.right != null) {
                stack.push(tmp.right);
                node = tmp.right;
            }
        }
        return -1;
    }
}
