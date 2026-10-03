package com.hikvision.pattern.structural.adapter;

public class AdapterDemo {

    // 生活案例：两脚插头换成三孔插头，转接头就是适配器
    interface ThreePin { void charge(); }

    static class TwoPinPlug {
        public void plugIn() { System.out.println("两脚插头接上了"); }
    }

    static class TwoToThree implements ThreePin {
        private final TwoPinPlug plug = new TwoPinPlug();
        public void charge() { plug.plugIn(); System.out.println("转接后开始充电"); }
    }

    public static void main(String[] args) {
        ThreePin pin = new TwoToThree();
        pin.charge();
    }
}
