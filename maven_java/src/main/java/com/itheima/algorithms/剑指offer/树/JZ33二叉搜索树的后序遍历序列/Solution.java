package com.itheima.algorithms.剑指offer.树.JZ33二叉搜索树的后序遍历序列;

import java.util.Arrays;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则返回 true ,否则返回 false 。假设输入的数组的任意两个数字都互不相同。
 */
public class Solution {
    //采用辅助函数的递归方法
    public boolean VerifySquenceOfBST(int[] sequence) {
        //sequence的情况只能是第一次传入[]
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        if (sequence.length == 1) {
            return true;
        }
        int positon = isSatisfy(sequence);
        if (positon == -1) {
            return false;
        }
        if (positon == 0) {
            return VerifySquenceOfBST(Arrays.copyOfRange(sequence, positon, sequence.length - 1));
        } else if (positon == sequence.length - 1) {
            return VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, positon));
        } else {
            return VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, positon)) &&
                    VerifySquenceOfBST(Arrays.copyOfRange(sequence, positon, sequence.length - 1));
        }
    }

    //postion为-1代表不满足条件
    public int isSatisfy(int[] sequence) {
        int position = -1;
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] >= sequence[sequence.length - 1]) {
                position = i;
                break;
            }
        }
        if (position >= 0 && position <= sequence.length - 2) {
            for (int i = position; i < sequence.length - 1; i++) {
                if (sequence[i] < sequence[sequence.length - 1]) {
                    position = -1;
                    break;
                }
            }
        }
        return position;
    }

    //版本1是在原函数递归，实际上应该辅助函数递归
    public boolean VerifySquenceOfBST2(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return isSatisfy2(sequence, 0, sequence.length - 1);
    }

    public boolean isSatisfy2(int[] sequence, int start, int end) {
        if (end - start <= 1) {
            return true;
        }
        int i;
        for (i = start; i < end; i++) {
            if (sequence[i] > sequence[end]) {
                break;
            }
        }
        for (int j = i; j < end; j++) {
            if (sequence[j] < sequence[end]) {
                return false;
            }
        }
        return isSatisfy2(sequence, start, i - 1) && isSatisfy2(sequence, i, end - 1);
    }
}
