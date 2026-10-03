package com.hikvision.pattern.behavioral.chain;

public class ChainDemo {

    // 生活案例：请假审批，组长->经理->总监逐级通过
    static abstract class Approver {
        protected Approver next;
        Approver setNext(Approver n) { this.next = n; return n; }
        abstract void approve(int days);
    }

    static class GroupLeader extends Approver {
        void approve(int days) {
            if (days <= 3) System.out.println("组长批准" + days + "天");
            else next.approve(days);
        }
    }

    static class Manager extends Approver {
        void approve(int days) {
            if (days <= 7) System.out.println("经理批准" + days + "天");
            else next.approve(days);
        }
    }

    static class Director extends Approver {
        void approve(int days) { System.out.println("总监批准" + days + "天"); }
    }

    public static void main(String[] args) {
        Approver chain = new GroupLeader();
        chain.setNext(new Manager()).setNext(new Director());
        chain.approve(2);
        chain.approve(5);
        chain.approve(10);
    }
}
