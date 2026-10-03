package com.hikvision.pattern.behavioral.memento;

public class MementoDemo {

    // 生活案例：游戏存档，随时可读档
    static class Game {
        private int level;
        void play(int level) { this.level = level; }
        int level() { return level; }

        static class Snapshot {
            private final int level;
            Snapshot(int level) { this.level = level; }
            int level() { return level; }
        }

        Snapshot save() { return new Snapshot(level); }
        void load(Snapshot s) { level = s.level(); }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play(3);
        Game.Snapshot save = game.save();
        System.out.println("存档在第 " + save.level() + " 关");
        game.play(10);
        System.out.println("现在玩到第 " + game.level() + " 关");
        game.load(save);
        System.out.println("读档后回到第 " + game.level() + " 关");
    }
}
