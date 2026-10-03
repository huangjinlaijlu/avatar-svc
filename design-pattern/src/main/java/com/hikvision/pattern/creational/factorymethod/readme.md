# 工厂方法 Factory Method

**一句话**：对象由子类的"工厂方法"来创建，不在调用处 new 具体类。

**生活案例**：政务大厅不同业务走不同窗口，各窗口自己办理各自的业务。

**角色**：`ServiceHall`（抽象创建者）、`ServiceHall` 子类（具体工厂）、`Window`（产品接口）、`IdCardWindow/PassportWindow`（具体产品）。

**适用场景**：需要按类型选择具体实现时，避免大量 if-else new。
