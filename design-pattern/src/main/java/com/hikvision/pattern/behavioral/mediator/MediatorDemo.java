package com.hikvision.pattern.behavioral.mediator;

public class MediatorDemo {

    // 生活案例：租房中介协调租客和房东
    interface Mediator { void contact(String from, String msg); }

    static class Agency implements Mediator {
        void register(Person p) { p.mediator = this; }
        public void contact(String from, String msg) {
            System.out.println("中介转达 [" + from + "] 说: " + msg);
        }
    }

    static class Person {
        String name;
        Mediator mediator;
        Person(String name) { this.name = name; }
        void say(String msg) { mediator.contact(name, msg); }
    }

    public static void main(String[] args) {
        Agency agency = new Agency();
        Person zhang = new Person("张三");
        Person landlord = new Person("房东");
        agency.register(zhang);
        agency.register(landlord);
        zhang.say("这房子能便宜点吗？");
        landlord.say("可以再谈谈");
    }
}
