package com.hikvision.pattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class IteratorDemo {

    // 生活案例：朋友圈按时间从前到后翻
    static class Moments implements Iterable<String> {
        private final List<String> items = new ArrayList<>();
        void add(String s) { items.add(s); }
        public java.util.Iterator<String> iterator() { return items.iterator(); }
    }

    public static void main(String[] args) {
        Moments moments = new Moments();
        moments.add("今天吃了火锅");
        moments.add("晒了个太阳");
        for (String m : moments) {
            System.out.println("朋友圈：" + m);
        }
    }
}
