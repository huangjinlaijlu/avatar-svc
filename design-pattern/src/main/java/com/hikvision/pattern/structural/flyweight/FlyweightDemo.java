package com.hikvision.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightDemo {

    // 生活案例：象棋棋子“帅”只有一个模子，谁用谁取
    static class Chess {
        private final String name; // 内在状态，可共享
        Chess(String name) { this.name = name; }
        void display(String pos) { System.out.println(name + " 放在 " + pos); }
    }

    static class ChessFactory {
        private static final Map<String, Chess> POOL = new HashMap<>();
        static Chess get(String name) {
            return POOL.computeIfAbsent(name, Chess::new);
        }
    }

    public static void main(String[] args) {
        Chess a = ChessFactory.get("帅");
        Chess b = ChessFactory.get("帅");
        a.display("九宫中");
        System.out.println("两个'帅'是同一个对象：" + (a == b));
    }
}
