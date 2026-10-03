package com.hikvision.pattern.behavioral.state;

public class StateDemo {

    // 生活案例：电梯按“停/运行/开门”不同状态响应按键
    interface ElevatorState {
        void pressCall(Elevator e);
    }

    static class Stopped implements ElevatorState {
        public void pressCall(Elevator e) {
            System.out.println("电梯：开门并准备运行");
            e.setState(new Running());
        }
    }

    static class Running implements ElevatorState {
        public void pressCall(Elevator e) {
            System.out.println("电梯：已在运行，无法开门");
        }
    }

    static class Elevator {
        private ElevatorState state = new Stopped();
        void setState(ElevatorState state) { this.state = state; }
        void pressCall() { state.pressCall(this); }
    }

    public static void main(String[] args) {
        Elevator e = new Elevator();
        e.pressCall();
        e.pressCall();
    }
}
