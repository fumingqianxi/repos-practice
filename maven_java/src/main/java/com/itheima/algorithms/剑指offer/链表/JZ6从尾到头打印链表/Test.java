package com.itheima.algorithms.剑指offer.链表.JZ6从尾到头打印链表;

import com.itheima.algorithms.剑指offer.链表.ListNode;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        if (str.equals("{}")) {
            ArrayList<Integer> list = new Solution().printListFromTailToHead3(null);
            System.out.println(list);
        } else {
            String[] strs = str.substring(1, str.length() - 1).split(",");
            ListNode head = null;
            ListNode pre = null;
            for (int i = 0; i < strs.length; i++) {
                ListNode node = new ListNode(Integer.parseInt(strs[i]));
                if (i == 0) {
                    head = node;
                    pre = node;
                } else {
                    pre.next = node;
                    pre = node;
                }
            }
            ArrayList<Integer> list = new Solution().printListFromTailToHead3(head);
            System.out.println(list);
        }
    }
}
