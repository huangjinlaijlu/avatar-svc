package com.hikvision.pattern.structural.facade;

public class FacadeDemo {

    // 生活案例：一键购物，内部封装了查库存、扣款、发货
    static class StockService { void check() { System.out.println("查库存"); } }
    static class PayService { void pay() { System.out.println("扣款"); } }
    static class ShipService { void ship() { System.out.println("发货"); } }

    static class ShopFacade {
        private final StockService stock = new StockService();
        private final PayService pay = new PayService();
        private final ShipService ship = new ShipService();
        void buy() {
            stock.check();
            pay.pay();
            ship.ship();
            System.out.println("下单完成");
        }
    }

    public static void main(String[] args) {
        new ShopFacade().buy();
    }
}
