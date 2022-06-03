package com.itheima.collection.stack;

import java.util.Stack;

public class StackMethods {
    public static void main(String[] args) {
        show01();
    }

    /**
     * 把元素压栈：push(E)；
     * 把栈顶的元素“弹出”：pop()；
     * 取栈顶元素但不弹出：peek()。
     */
    public static void show01() {
        Stack<String> stack = new Stack<>();
        stack.push("apple");
        stack.push("pear");
        stack.push("banana");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }
}
