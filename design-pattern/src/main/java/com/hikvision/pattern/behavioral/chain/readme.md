# 责任链模式 Chain of Responsibility

**一句话**：请求沿着处理者链传递，直到有人能处理。

**生活案例**：请假审批，组长 → 经理 → 总监，逐级往上报。

**角色**：`Approver`（抽象处理者）、`GroupLeader/Manager/Director`（具体处理者）。

**适用场景**：多个处理者决策、可动态调整顺序的审批流。
