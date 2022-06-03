package com.itheima.algorithms.剑指offer.树.JZ7重建二叉树;

import com.itheima.algorithms.剑指offer.树.TreeNode;

import java.util.Arrays;

/**
 * 给定节点数为 n 的二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建出如下图所示。
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class Solution {
    //递归
    public TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        int position = -1;
        for (int i = 0; i < vin.length; i++) {
            if (pre[0] == vin[i]) {
                position = i;
            }
        }
        int[] lpre = copyArray(pre, 1, position);
        int[] rpre = copyArray(pre, position + 1, pre.length - 1);
        int[] lvin = copyArray(vin, 0, position - 1);
        int[] rvin = copyArray(vin, position + 1, vin.length - 1);
        root.left = reConstructBinaryTree(lpre, lvin);
        root.right = reConstructBinaryTree(rpre, rvin);
        return root;
    }

    public int[] copyArray(int[] fromArray, int start, int end) {
        int[] result = new int[end - start + 1];
        for (int i = 0; i < fromArray.length; i++) {
            if (i >= start && i <= end) {
                result[i - start] = fromArray[i];
            }
        }
        return result;
    }

    //同样递归，优化版本1
    public TreeNode reConstructBinaryTree2(int[] pre, int[] vin) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        int position = -1;
        for (int i = 0; i < vin.length; i++) {
            if (pre[0] == vin[i]) {
                position = i;
            }
        }
        root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, position + 1), Arrays.copyOfRange(vin, 0, position));
        root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, position + 1, pre.length), Arrays.copyOfRange(vin, position + 1, vin.length));
        return root;
    }
}
