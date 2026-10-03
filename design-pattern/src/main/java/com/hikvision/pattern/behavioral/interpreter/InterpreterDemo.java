package com.hikvision.pattern.behavioral.interpreter;

public class InterpreterDemo {

    // 生活案例：把 "3 + 5" 这样的文本翻译成数字结果
    interface Expression { int interpret(); }

    static class Number implements Expression {
        private final int value;
        Number(int value) { this.value = value; }
        public int interpret() { return value; }
    }

    static class Add implements Expression {
        private final Expression a, b;
        Add(Expression a, Expression b) { this.a = a; this.b = b; }
        public int interpret() { return a.interpret() + b.interpret(); }
    }

    public static void main(String[] args) {
        Expression expr = new Add(new Number(3), new Number(5));
        System.out.println("3 + 5 = " + expr.interpret());
    }
}
