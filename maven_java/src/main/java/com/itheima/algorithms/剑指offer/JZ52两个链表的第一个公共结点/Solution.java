package com.itheima.algorithms.剑指offer.JZ52两个链表的第一个公共结点;

/**
 * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)。
 */
public class Solution {
    //该方法实际上遍历了两遍链表
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        int k1 = 0;
        int k2 = 0;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != null ) {
            p1 = p1.next;
            k1++;
        }
        while (p2 != null) {
            p2 = p2.next;
            k2++;
        }
        int k = Math.abs(k1 - k2);
        while (k > 0) {
            if (k1 >= k2) {
                pHead1 = pHead1.next;
            } else {
                pHead2 = pHead2.next;
            }
            k--;
        }
        while (pHead1 != null) {
            if (pHead1 == pHead2) {
                break;
            }
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return pHead1;
    }

    //优化通过一次遍历完成操作
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            if (p1 == null) {
                p1 = pHead2;
            } else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = pHead1;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    //对版本2进行小优化
    //只要分别证明当两个都为null和一个为null，另外一个不为null时均正确即可；另外再优化if和else语句
    public ListNode FindFirstCommonNode3(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            p1 = p1 == null ? pHead2 : p1.next;
            p2 = p2 == null ? pHead1 : p2.next;
        }
        return p1;
    }
}
