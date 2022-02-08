package com.itheima.algorithms.剑指offer.JZ6从尾到头打印链表;

import java.util.ArrayList;

/**
 * 题目：输入一个链表的头节点，按链表从尾到头的顺序返回每个节点的值（用数组返回）。
 */
public class Solution {

    /**
     * 从这个链表的头节点开始进行遍历并添加到数组中，由于要求逆序输出，所以利用add(0,value)方法添加。
     * 时间复杂度为O(n^2)：循环n次，add时间复杂度为O(n)
     * 空间复杂度为O(n)
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(0, listNode.val);
            listNode = listNode.next;
        }
        return list;
    }

    /**
     * 从这个链表的头节点开始进行遍历并添加到数组中，add方法时间复杂度太高，所以通过一次遍历反转数组。
     * 时间复杂度为O(n)：while和for循环均为O(n)，总时间复杂度为O(n)
     * 空间复杂度为O(n)
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while(listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        for(int i = 0;i < list.size() / 2;i++) {
            int temp = list.get(i);
            list.set(i, list.get(list.size() - 1 - i));
            list.set((list.size() - 1 - i), temp);
        }
        return list;
    }

    /**
     * 递归遍历
     * 时间复杂度为O(n)
     * 空间复杂度为O(n)
     */
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead3(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }
}
