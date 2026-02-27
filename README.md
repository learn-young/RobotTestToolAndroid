# AndroidTestTool

面向轮臂机器人的 Android 测试工具项目骨架（Windows 开发友好）。

## 已搭建内容
- Kotlin + Jetpack Compose + MVVM 基础架构
- 4 个页面：连接 / 控制 / 状态 / 日志
- TCP 通信客户端骨架（连接、发送、接收、断开）
- Repository + ViewModel 状态流
- 基础导航与主题

## 目录结构
- `app/src/main/java/com/example/androidtesttool/data`：通信与仓储层
- `app/src/main/java/com/example/androidtesttool/domain`：领域模型
- `app/src/main/java/com/example/androidtesttool/ui`：导航、页面、ViewModel、主题

## 下一步建议
1. 按机器人协议完善命令 JSON 序列化/反序列化。
2. 增加心跳、超时重试、请求 ID 匹配。
3. 接入 Room 持久化日志导出（CSV/JSON）。
4. 增加自动化测试流程编排与急停优先级队列。
