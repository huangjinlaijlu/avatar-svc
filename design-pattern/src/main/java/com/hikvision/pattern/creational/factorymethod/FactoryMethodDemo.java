package com.hikvision.pattern.creational.factorymethod;

public class FactoryMethodDemo {

    // 生活案例：政务大厅，不同业务去不同窗口办理
    interface Window {
        void handle(String name);
    }

    static class IdCardWindow implements Window {
        public void handle(String name) { System.out.println(name + " 在【身份证窗口】办理"); }
    }

    static class PassportWindow implements Window {
        public void handle(String name) { System.out.println(name + " 在【护照窗口】办理"); }
    }

    static abstract class ServiceHall {
        abstract Window openWindow();
        void serve(String name) { openWindow().handle(name); }
    }

    static class IdCardHall extends ServiceHall {
        Window openWindow() { return new IdCardWindow(); }
    }

    static class PassportHall extends ServiceHall {
        Window openWindow() { return new PassportWindow(); }
    }

    public static void main(String[] args) {
        new IdCardHall().serve("张三");
        new PassportHall().serve("李四");
    }
}
