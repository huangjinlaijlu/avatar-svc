package com.hikvision.pattern.structural.bridge;

public class BridgeDemo {

    // 生活案例：手机品牌（维度1）和操作系统（维度2）可以自由组合
    interface OS { String name(); }
    static class Android implements OS { public String name() { return "Android"; } }
    static class IOS implements OS { public String name() { return "iOS"; } }

    static abstract class Phone {
        protected OS os;
        Phone(OS os) { this.os = os; }
        abstract String brand();
        void info() { System.out.println(brand() + " + " + os.name()); }
    }

    static class Xiaomi extends Phone {
        Xiaomi(OS os) { super(os); }
        String brand() { return "小米"; }
    }

    static class Apple extends Phone {
        Apple(OS os) { super(os); }
        String brand() { return "苹果"; }
    }

    public static void main(String[] args) {
        new Xiaomi(new Android()).info();
        new Apple(new IOS()).info();
    }
}
