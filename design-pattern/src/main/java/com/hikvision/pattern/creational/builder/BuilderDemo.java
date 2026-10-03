package com.hikvision.pattern.creational.builder;

public class BuilderDemo {

    // 生活案例：点外卖，自由搭配主食/小菜/饮料
    static class Meal {
        private String staple;
        private String side;
        private String drink;

        public String toString() { return staple + " + " + side + " + " + drink; }

        static class Builder {
            private Meal meal = new Meal();
            Builder staple(String s) { meal.staple = s; return this; }
            Builder side(String s) { meal.side = s; return this; }
            Builder drink(String d) { meal.drink = d; return this; }
            Meal build() { return meal; }
        }
    }

    public static void main(String[] args) {
        Meal meal = new Meal.Builder().staple("大米饭").side("红烧肉").drink("酸梅汤").build();
        System.out.println("我的外卖：" + meal);
    }
}
