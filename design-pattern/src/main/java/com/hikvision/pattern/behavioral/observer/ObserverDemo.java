package com.hikvision.pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverDemo {

    // 生活案例：公众号发布新文章，所有订阅者都收到推送
    interface Observer { void update(String article); }

    static class WechatAccount {
        private final List<Observer> followers = new ArrayList<>();
        void subscribe(Observer o) { followers.add(o); }
        void publish(String article) {
            for (Observer o : followers) o.update(article);
        }
    }

    static class User implements Observer {
        private final String name;
        User(String name) { this.name = name; }
        public void update(String article) {
            System.out.println(name + " 收到推送：" + article);
        }
    }

    public static void main(String[] args) {
        WechatAccount account = new WechatAccount();
        account.subscribe(new User("张三"));
        account.subscribe(new User("李四"));
        account.publish("设计模式入门");
    }
}
