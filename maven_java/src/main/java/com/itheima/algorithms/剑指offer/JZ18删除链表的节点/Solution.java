package com.itheima.algorithms.剑指offer.JZ18删除链表的节点;

public class Solution {
    public ListNode deleteNode (ListNode head, int val) {
        ListNode vhead = new ListNode(-1);
        vhead.next = head;
        ListNode cur = head;
        ListNode pre = vhead;
        while (cur != null) {
            if (cur.val == val) {
                cur = cur.next;
                pre.next = cur;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
