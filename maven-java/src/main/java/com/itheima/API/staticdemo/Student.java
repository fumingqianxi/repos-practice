package com.itheima.API.staticdemo;

public class Student {
    private int id;
    private String name;
    private int age;
    private static int idCounter = 0;
    static String room;

    public Student() {
        this.id = ++idCounter;
    }

    public Student(String name, int age) {

        this.name = name;
        this.age = age;
        this.id = ++idCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static String getRoom() {
        return room;
    }

    public static void setRoom(String room) {
        Student.room = room;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
