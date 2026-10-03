# 抽象工厂 Abstract Factory

**一句话**：一个工厂可以生产一整族"配套"产品。

**生活案例**：跨国快餐：中国套餐=中式汉堡+豆浆；美国套餐=美式汉堡+可乐，整套一起换。

**角色**：`MealFactory`（抽象工厂）、`ChinaFactory/AmericanFactory`（具体工厂）、`Burger/Drink`（产品接口）。

**适用场景**：产品成套出现、需要整体切换风格时（如不同主题的 UI 组件族）。
