package com.hikvision.pattern.behavioral.strategy;

public class StrategyDemo {

    // 生活案例：出门可以选择不同交通方式，策略可随时换
    interface Transport { void go(); }

    static class Walk implements Transport {
        public void go() { System.out.println("步行去公司"); }
    }

    static class Bus implements Transport {
        public void go() { System.out.println("坐公交去公司"); }
    }

    static class Car implements Transport {
        public void go() { System.out.println("开车去公司"); }
    }

    static class Person {
        private Transport transport;
        Person(Transport transport) { this.transport = transport; }
        void setTransport(Transport transport) { this.transport = transport; }
        void commute() { transport.go(); }
    }

    public static void main(String[] args) {
        Person p = new Person(new Walk());
        p.commute();
        p.setTransport(new Car());
        p.commute();
    }
}
