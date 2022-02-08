package com.itheima.algorithms.剑指offer.JZ23链表中环的入口结点;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)。
 */
public class  Solution {
    //空间复杂度实际上是O(n)，不满足要求，另外用map不合理，并不需要value
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode p = pHead;
        while (p != null) {
            if (map.containsKey(p)) return p;
            map.put(p, 1);
            p = p.next;
        }
        return null;
    }

    //优化版本1改成set
    public ListNode EntryNodeOfLoop2(ListNode pHead) {
        Set<ListNode> set = new HashSet<>();
        ListNode p = pHead;
        while (p != null) {
            if (set.contains(p)) return p;
            set.add(p);
            p = p.next;
        }
        return null;
    }

    //优化版本1改成set
    public ListNode EntryNodeOfLoop3(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = pHead;
        do {
            fast = fast != null && fast.next != null ? fast.next.next : null;
            slow = slow != null ? slow.next : null;
        } while (fast != slow);
        fast = fast != null ? pHead : null;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
