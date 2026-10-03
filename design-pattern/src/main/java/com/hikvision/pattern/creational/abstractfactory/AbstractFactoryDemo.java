package com.hikvision.pattern.creational.abstractfactory;

public class AbstractFactoryDemo {

    // 生活案例：跨国快餐店，中国套餐和美国套餐各自搭配
    interface Burger { String name(); }
    interface Drink { String name(); }

    interface MealFactory {
        Burger burger();
        Drink drink();
    }

    static class ChinaFactory implements MealFactory {
        public Burger burger() { return () -> "中式汉堡"; }
        public Drink drink() { return () -> "豆浆"; }
    }

    static class AmericanFactory implements MealFactory {
        public Burger burger() { return () -> "美式汉堡"; }
        public Drink drink() { return () -> "可乐"; }
    }

    public static void main(String[] args) {
        MealFactory factory = new ChinaFactory();
        System.out.println("中国套餐：" + factory.burger().name() + " + " + factory.drink().name());
        factory = new AmericanFactory();
        System.out.println("美国套餐：" + factory.burger().name() + " + " + factory.drink().name());
    }
}
