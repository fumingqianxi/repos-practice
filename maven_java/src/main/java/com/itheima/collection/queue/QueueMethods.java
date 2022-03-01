package com.itheima.collection.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 队列（Queue）是一种经常使用的集合。Queue实际上是实现了一个先进先出（FIFO：First In First Out）的有序表。
 * 它和List的区别在于，List可以在任意位置添加和删除元素，而Queue只有两个操作：
 * 把元素添加到队列末尾；
 * 从队列头部取出元素。
 */
public class QueueMethods {
    public static void main(String[] args) {
        show01();
        System.out.println("===========");
    }

    /**
     * int size()：获取队列长度；
     * boolean add(E)/boolean offer(E)：添加元素到队尾；
     * E remove()/E poll()：获取队首元素并从队列中删除；
     * E element()/E peek()：获取队首元素但并不从队列中删除。
     */
    public static void show01() {
        Queue<String> q = new LinkedList<>();
        // 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        // 从队列取出元素:
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // null,因为队列是空的
        System.out.println(q.isEmpty());
    }

    /**
     * LinkedList即实现了List接口，又实现了Queue接口，
     * 但是，在使用的时候，如果我们把它当作List，就获取List的引用，如果我们把它当作Queue，就获取Queue的引用
     */
    public static void show02() {
        // 这是一个List:
        List<String> list = new LinkedList<>();
        // 这是一个Queue:
        Queue<String> queue = new LinkedList<>();
    }
}
