package com.hikvision.pattern.structural.decorator;

public class DecoratorDemo {

    // 生活案例：一杯咖啡，可加糖、加奶泡，功能动态叠加
    interface Coffee {
        String desc();
        double cost();
    }

    static class SimpleCoffee implements Coffee {
        public String desc() { return "咖啡"; }
        public double cost() { return 10; }
    }

    static class Sugar implements Coffee {
        private final Coffee c;
        Sugar(Coffee c) { this.c = c; }
        public String desc() { return c.desc() + "+糖"; }
        public double cost() { return c.cost() + 2; }
    }

    static class Foam implements Coffee {
        private final Coffee c;
        Foam(Coffee c) { this.c = c; }
        public String desc() { return c.desc() + "+奶泡"; }
        public double cost() { return c.cost() + 3; }
    }

    public static void main(String[] args) {
        Coffee c = new Foam(new Sugar(new SimpleCoffee()));
        System.out.println(c.desc() + "，价格：" + c.cost());
    }
}
