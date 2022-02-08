package com.itheima.algorithms.剑指offer.JZ22链表中倒数最后k个结点;

/**
 *输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
 如果该链表长度小于k，请返回一个长度为 0 的链表。
 */
public class Solution {
    public ListNode FindKthToTail (ListNode pHead, int k) {
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        if (fast == null) {
            return null;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
