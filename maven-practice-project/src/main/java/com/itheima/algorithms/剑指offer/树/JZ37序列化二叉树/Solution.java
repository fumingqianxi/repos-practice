package com.itheima.algorithms.剑指offer.树.JZ37序列化二叉树;

import com.itheima.algorithms.剑指offer.树.TreeNode;
import org.apache.ibatis.annotations.Delete;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树，不对序列化之后的字符串进行约束，
 * 但要求能够根据序列化之后的字符串重新构造出一棵与原二叉树相同的树。
 */
public class Solution {
    //全局变量，始终保证该变量表示待递归的结点
    private String deserializeStr;

    //采用先序遍历的思想
    String Serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
    }

    TreeNode Deserialize(String str) {
        deserializeStr = str;
        return Deserialize();
    }

    TreeNode Deserialize() {
        if (deserializeStr.length() == 0) {
            return null;
        }
        int index = deserializeStr.indexOf(" ");
        String node = index == -1 ? deserializeStr : deserializeStr.substring(0, index);
        deserializeStr = index == -1 ? "" : deserializeStr.substring(index + 1);
        if (node.equals("#")) {
            return null;
        }
        TreeNode t = new TreeNode(Integer.valueOf(node));
        t.left = Deserialize();
        t.right = Deserialize();
        return t;
    }
}
