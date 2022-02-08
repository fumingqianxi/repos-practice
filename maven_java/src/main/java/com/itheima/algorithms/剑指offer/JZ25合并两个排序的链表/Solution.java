package com.itheima.algorithms.剑指offer.JZ25合并两个排序的链表;

/**
 * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)。
 */
public class Solution {

    //该程序代码不够简洁，其实没必要两层while循环，逻辑混乱，需要优化
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
        } else {
            head = list2;
        }
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                ListNode pre = list1;
                ListNode next = list1.next;
                while (next != null && next.val <= list2.val) {
                    pre = next;
                    next = next.next;
                }
                pre.next = list2;
                list1 = next;
            } else {
                ListNode pre = list2;
                ListNode next = list2.next;
                while (next != null && next.val <= list1.val) {
                    pre = next;
                    next = next.next;
                }
                pre.next = list1;
                list2 = next;
            }
        }
        return head;
    }

    //优化版本1，只用一层循环
    public ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
        } else {
            head = list2;
        }
        ListNode r = head;
        while (list1 != null && list2 != null) {
            if (head == list1 || head == list2) {
                if (head == list1) {
                    list1 = list1.next;
                } else {
                    list2 = list2.next;
                }
                continue;
            }
            if (list1.val <= list2.val) {
                r.next = list1;
                list1 = list1.next;
                //r = r.next;
            } else {
                r.next = list2;
                list2 = list2.next;
                //r = r.next;
            }
            //优化两行代码为一行
            r = r.next;
        }
//        if (list1 == null){
//            r.next = list2;
//        } else {
//            r.next = list1;
//        }
        //优化if else语句
        r.next = list1 == null ? list2 : list1;
        return head;
    }

    //添加虚拟头结点，优化版本2
    public ListNode Merge3(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list1 : list2;
        return head.next;
    }

    //使用递归进行求解
    public ListNode Merge4(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = Merge4(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge4(list1, list2.next);
            return list2;
        }
    }
}
