# 设计模式示例（GoF 23 种）

本模块用最通俗的生活案例演示 GoF 23 种设计模式，每种模式一个可直接运行的 `Demo.java` 和一份 `readme.md` 解释。

## 创建型（5）

- [单例 Singleton](src/main/java/com/hikvision/pattern/creational/singleton/readme.md) — 全国唯一公章
- [工厂方法 Factory Method](src/main/java/com/hikvision/pattern/creational/factorymethod/readme.md) — 不同业务走不同窗口
- [抽象工厂 Abstract Factory](src/main/java/com/hikvision/pattern/creational/abstractfactory/readme.md) — 中国/美国套餐
- [建造者 Builder](src/main/java/com/hikvision/pattern/creational/builder/readme.md) — 点外卖自由搭配
- [原型 Prototype](src/main/java/com/hikvision/pattern/creational/prototype/readme.md) — 文档模板克隆

## 结构型（7）

- [适配器 Adapter](src/main/java/com/hikvision/pattern/structural/adapter/readme.md) — 插头转接头
- [桥接 Bridge](src/main/java/com/hikvision/pattern/structural/bridge/readme.md) — 手机品牌 × 操作系统
- [组合 Composite](src/main/java/com/hikvision/pattern/structural/composite/readme.md) — 公司组织架构
- [装饰器 Decorator](src/main/java/com/hikvision/pattern/structural/decorator/readme.md) — 咖啡加糖/奶泡
- [外观 Facade](src/main/java/com/hikvision/pattern/structural/facade/readme.md) — 一键购物
- [享元 Flyweight](src/main/java/com/hikvision/pattern/structural/flyweight/readme.md) — 象棋棋子共享
- [代理 Proxy](src/main/java/com/hikvision/pattern/structural/proxy/readme.md) — 明星经纪人

## 行为型（11）

- [责任链 Chain of Responsibility](src/main/java/com/hikvision/pattern/behavioral/chain/readme.md) — 请假审批
- [命令 Command](src/main/java/com/hikvision/pattern/behavioral/command/readme.md) — 遥控器按钮
- [解释器 Interpreter](src/main/java/com/hikvision/pattern/behavioral/interpreter/readme.md) — 解析 "3+5"
- [迭代器 Iterator](src/main/java/com/hikvision/pattern/behavioral/iterator/readme.md) — 翻朋友圈
- [中介者 Mediator](src/main/java/com/hikvision/pattern/behavioral/mediator/readme.md) — 租房中介
- [备忘录 Memento](src/main/java/com/hikvision/pattern/behavioral/memento/readme.md) — 游戏存档
- [观察者 Observer](src/main/java/com/hikvision/pattern/behavioral/observer/readme.md) — 公众号推送
- [状态 State](src/main/java/com/hikvision/pattern/behavioral/state/readme.md) — 电梯状态机
- [策略 Strategy](src/main/java/com/hikvision/pattern/behavioral/strategy/readme.md) — 选交通方式
- [模板方法 Template Method](src/main/java/com/hikvision/pattern/behavioral/templatemethod/readme.md) — 泡咖啡/泡茶
- [访问者 Visitor](src/main/java/com/hikvision/pattern/behavioral/visitor/readme.md) — 老师评语访问学生

## 运行

每个 Demo 都有 `main` 方法，在 IDE 中直接运行，或：

```bash
mvn -o compile
java -cp design-pattern/target/classes com.hikvision.pattern.creational.singleton.SingletonDemo
```
