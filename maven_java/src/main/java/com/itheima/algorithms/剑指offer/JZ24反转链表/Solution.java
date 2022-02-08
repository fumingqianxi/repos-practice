package com.itheima.algorithms.剑指offer.JZ24反转链表;

import java.util.Stack;

/**
 * 题目：给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
 * 数据范围： n≤1000
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)。
 */
public class Solution {
    //该写法不够简洁，含有多个变量，需要优化
    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        //p和q代表两个相邻结点
        ListNode p = head;
        ListNode q = head.next;
        p.next = null;
        //r存储下一个结点，防止断链
        ListNode r;
        while (q != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        return p;
    }

    //优化版本1，减少多余变量
    //新建一个结点newList，采用头插法，最后返回newList.next
    public ListNode ReverseList2(ListNode head) {
        ListNode newList = new ListNode(-1);
        while (head != null) {
            ListNode r = head.next;
            head.next = newList.next;
            newList.next = head;
            head = r;
        }
        return newList.next;
    }

    //使用栈解决
    public ListNode ReverseList3(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) return null;
        ListNode node = stack.pop();
        ListNode dummy = node;
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        node.next = null;
        return dummy;
    }

    //递归版本
    public ListNode ReverseList4(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode reverse = ReverseList4(next);
        next.next = head;
        head.next = null;
        return reverse;
    }

    //因为递归调用之后head.next节点就会成为reverse节点的尾结点，我们可以直接让head.next.next = head;
    public ListNode ReverseList5(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverse = ReverseList5(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }

    //之前的递归往下传递的时候基本上没有逻辑处理，当往回反弹的时候才开始处理，也就是从链表的尾端往前开始处理的。
    //可以继续优化，在链表递归的时候从前往后处理，处理完之后直接返回递归的结果，这就是所谓的尾递归，这种运行效率要比上一种好很多
    public ListNode ReverseList6(ListNode head) {
        return ReverseListTail(head, null);
    }

    private ListNode ReverseListTail(ListNode head, ListNode next) {
        if (head == null) {
            return next;
        }
        ListNode r = head.next;
        head.next = next;
        return ReverseListTail(r, head);
    }
}
