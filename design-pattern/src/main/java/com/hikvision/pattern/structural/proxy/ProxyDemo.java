package com.hikvision.pattern.structural.proxy;

public class ProxyDemo {

    // 生活案例：明星接戏由经纪人代理，观众看到的是“明星办事”
    interface Star { void sign(String movie); }

    static class RealStar implements Star {
        public void sign(String movie) { System.out.println("明星出席签约：" + movie); }
    }

    static class Agent implements Star {
        private final Star real = new RealStar();
        public void sign(String movie) {
            System.out.println("经纪人先谈片酬...");
            real.sign(movie);
        }
    }

    public static void main(String[] args) {
        new Agent().sign("电影A");
    }
}
