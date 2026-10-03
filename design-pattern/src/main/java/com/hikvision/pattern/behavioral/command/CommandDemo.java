package com.hikvision.pattern.behavioral.command;

public class CommandDemo {

    // 生活案例：遥控器上的按钮，一个按钮对应一个开/关命令
    interface Command { void execute(); }

    static class LightOn implements Command {
        public void execute() { System.out.println("开灯"); }
    }

    static class LightOff implements Command {
        public void execute() { System.out.println("关灯"); }
    }

    static class Remote {
        void press(Command c) { c.execute(); }
    }

    public static void main(String[] args) {
        Remote remote = new Remote();
        remote.press(new LightOn());
        remote.press(new LightOff());
    }
}
