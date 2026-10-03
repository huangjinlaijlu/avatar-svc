package com.hikvision.pattern.creational.singleton;

public class SingletonDemo {

    // 生活化案例：全国唯一的公章，只有一枚
    static class Seal {
        private static final Seal INSTANCE = new Seal();
        private Seal() {}
        public static Seal getInstance() { return INSTANCE; }
        public void stamp(String who) { System.out.println(who + " 盖章成功"); }
    }

    public static void main(String[] args) {
        Seal s1 = Seal.getInstance();
        Seal s2 = Seal.getInstance();
        System.out.println("两枚公章是不是同一个：" + (s1 == s2));
        s1.stamp("张三");
        s2.stamp("李四");
    }
}
