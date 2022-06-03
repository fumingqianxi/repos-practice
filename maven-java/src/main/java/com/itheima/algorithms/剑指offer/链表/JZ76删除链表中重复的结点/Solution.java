package com.itheima.algorithms.剑指offer.链表.JZ76删除链表中重复的结点;

import com.itheima.algorithms.剑指offer.链表.ListNode;

import java.util.HashSet;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表 1->2->3->3->4->4->5  处理后为 1->2->5
 * 空间复杂度 O(n)，时间复杂度 O(n)
 */
public class Solution {
    //采用set集合方法
    // 空间复杂度 O(n)，时间复杂度 O(n)
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode vhead = new ListNode(0);
        vhead.next = pHead;
        ListNode pre = vhead;
        ListNode cur = pHead;
        HashSet<Integer> set = new HashSet<>();
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                set.add(cur.val);
            }
            cur = cur.next;
        }
        cur = pHead;
        while (cur != null ) {
            if (set.contains(cur.val)) {
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return vhead.next;
    }

    //空间复杂度 O(1)，时间复杂度 O(n)
    public ListNode deleteDuplication2(ListNode pHead) {
        ListNode vhead = new ListNode(0);
        vhead.next = pHead;
        ListNode pre = vhead;
        ListNode cur = pHead;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return vhead.next;
    }

    //采用递归方法
    public ListNode deleteDuplication3(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        if (pHead.val == pHead.next.val) {
            ListNode node = pHead.next.next;
            while (node != null && node.val == pHead.val) {
                node = node.next;
            }
            return deleteDuplication(node);
        } else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }
}
