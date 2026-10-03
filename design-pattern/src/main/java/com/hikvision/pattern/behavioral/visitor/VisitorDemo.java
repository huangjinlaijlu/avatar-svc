package com.hikvision.pattern.behavioral.visitor;

public class VisitorDemo {

    // 生活案例：学生接受不同老师的“评语”，不用修改学生类
    interface Visitor {
        void visit(Student s);
    }

    interface Element {
        void accept(Visitor v);
    }

    static class Student implements Element {
        String name;
        int score;
        Student(String name, int score) { this.name = name; this.score = score; }
        public void accept(Visitor v) { v.visit(this); }
    }

    static class Teacher implements Visitor {
        public void visit(Student s) {
            System.out.println("老师评语：" + s.name + " 成绩 " + s.score + (s.score >= 90 ? "，优秀！" : "，加油！"));
        }
    }

    public static void main(String[] args) {
        new Student("张三", 95).accept(new Teacher());
        new Student("李四", 70).accept(new Teacher());
    }
}
