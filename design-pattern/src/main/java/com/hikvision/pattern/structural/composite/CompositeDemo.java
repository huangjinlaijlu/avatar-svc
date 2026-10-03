package com.hikvision.pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeDemo {

    // 生活案例：公司 = 部门 = 员工，统一展示组织架构
    static abstract class Node {
        String name;
        Node(String name) { this.name = name; }
        abstract void show(String prefix);
    }

    static class Employee extends Node {
        Employee(String name) { super(name); }
        void show(String prefix) { System.out.println(prefix + name); }
    }

    static class Department extends Node {
        List<Node> children = new ArrayList<>();
        Department(String name) { super(name); }
        void add(Node n) { children.add(n); }
        void show(String prefix) {
            System.out.println(prefix + name);
            for (Node n : children) n.show(prefix + "  ");
        }
    }

    public static void main(String[] args) {
        Department company = new Department("公司");
        Department tech = new Department("技术部");
        tech.add(new Employee("张三"));
        tech.add(new Employee("李四"));
        Department hr = new Department("人事部");
        hr.add(new Employee("王五"));
        company.add(tech);
        company.add(hr);
        company.show("");
    }
}
