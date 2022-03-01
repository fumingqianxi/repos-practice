package com.itheima.algorithms.剑指offer.链表.JZ35复杂链表的复制;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），
 * 请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）。
 */
public class Solution {
    //空间复杂度 O(n)，时间复杂度 O(n)
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return pHead;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = pHead;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);
            pre.next = clone;
            map.put(cur, clone);
            pre = pre.next;
            cur = cur.next;
        }
        for (Map.Entry<RandomListNode, RandomListNode> entry : map.entrySet()) {
            entry.getValue().random = entry.getKey().random == null ? null : map.get(entry.getKey().random);
        }
        return map.get(pHead);
    }
}
