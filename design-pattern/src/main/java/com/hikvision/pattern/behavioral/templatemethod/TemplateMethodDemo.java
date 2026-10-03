package com.hikvision.pattern.behavioral.templatemethod;

public class TemplateMethodDemo {

    // 生活案例：泡热饮流程固定（烧水->冲泡->加料），咖啡和茶步骤不同
    static abstract class Drink {
        final void make() {
            boilWater();
            brew();
            addMaterial();
            System.out.println("完成\n");
        }

        void boilWater() { System.out.println("烧开水"); }
        abstract void brew();
        abstract void addMaterial();
    }

    static class Coffee extends Drink {
        void brew() { System.out.println("冲咖啡"); }
        void addMaterial() { System.out.println("加糖"); }
    }

    static class Tea extends Drink {
        void brew() { System.out.println("泡茶叶"); }
        void addMaterial() { System.out.println("加柠檬"); }
    }

    public static void main(String[] args) {
        new Coffee().make();
        new Tea().make();
    }
}
