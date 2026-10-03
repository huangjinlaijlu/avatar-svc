# Avatar 告警 SDK

本工程包含一套可独立引入的告警能力模块，供其他服务按需依赖。

## 模块总览

| 模块 | 作用 |
|------|------|
| alert-core | 统一告警模型与工具（Alert / Severity / Status / AlertIds） |
| alert-receiver | 告警接收、去重、路由分发（含 Prometheus 适配） |
| alert-notify | 多渠道通知投递 |

## alert-core

告警信息的公共数据结构，是各模块的统一语言。

- `Alert`：指纹、标题、内容、级别、来源、状态、标签、时间
- `Severity`：P0 / P1 / P2 / P3
- `Status`：FIRING / ACKED / RESOLVED
- `AlertIds.fingerprint()`：基于来源 + 标题 + 标签生成 MD5，用于去重

## alert-receiver

对外统一的告警接入口，负责：

- `POST /alerts`：接收统一格式的告警 JSON
- `POST /alerts/prometheus`：接收 Prometheus AlertManager webhook，自动转换成 `Alert`
- 去重：相同 `fingerprint` 在 `dedup-seconds` 窗口内只处理一次
- 路由：按 `alert.receiver.rules` 配置（级别 → 渠道/手机号/邮箱）分发到 `alert-notify`

配置示例：

```yaml
alert:
  receiver:
    dedup-seconds: 60
    rules:
      - severity: P0
        channels: [SMS, VOICE, DINGTALK]
        phones: ["13800000000"]
      - severity: P2
        channels: [DINGTALK, WECOM]
```

## alert-notify

按渠道发送通知，支持多种渠道与可插拔 Provider。

- 渠道：钉钉 / 企微 / 飞书 webhook、邮件、短信、语音电话
- `NotificationService`：统一入口，按 `NotifyChannel` 分发
- Webhook 类渠道支持签名（`alert.notify.<channel>.secret`）
- 邮件 / 短信 / 电话由 `EmailProvider` / `SmsProvider` / `VoiceCallProvider` 实现，默认为日志输出，配置 `alert.notify.httpEmail/httpSms/httpVoice` 即可走通用 HTTP Provider，也可自行实现接口替换（如对接阿里云、云片等 SDK）

配置示例：

```yaml
alert:
  notify:
    dingtalk:
      url: https://oapi.dingtalk.com/robot/send?access_token=xxx
      secret: SECxxxx
    wecom:
      url: https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=xxx
    feishu:
      url: https://open.feishu.cn/open-apis/bot/v2/hook/xxx
      secret: xxx
    httpEmail:
      url: https://your-mail-gateway/send
    httpSms:
      url: https://your-sms-gateway/send
      headers:
        Authorization: Bearer xxx
    httpVoice:
      url: https://your-voice-gateway/send
```

## 使用方式

```xml
<dependency>
    <groupId>com.hikvision</groupId>
    <artifactId>alert-receiver</artifactId>  <!-- 已传递依赖 alert-core / alert-notify -->
    <version>1.0-SNAPSHOT</version>
</dependency>
```

启动类放在 `com.hikvision` 包下即可自动加载 `NotifyAutoConfiguration` / `AlertReceiverAutoConfiguration`；其他包名需通过 `@Import` 引入两个配置类。

## 数据流

```
Prometheus / SkyWalking / ES / 自研系统
        │  (webhook)
        ▼
alert-receiver  → 统一模型 → 去重 → 规则路由
        │
        ▼
alert-notify → 钉钉 / 企微 / 飞书 / 邮件 / 短信 / 电话
```
