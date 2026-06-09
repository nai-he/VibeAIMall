# Vibe智购AI Kotlin Multiplatform Shared

这个工程是真正的 Kotlin Multiplatform shared 层，不是空目录。

## 已迁入 shared 的能力

- `AiIntentParser`：自然语言需求识别。
- `RecommendationEngine`：商品推荐排序、主推和备选方案。
- `AiShoppingChatEngine`：识别“就买这个”“买备选1”“去购物车”等对话购买指令。
- `ProductCatalog`：跨端演示商品样本。
- `SharedEngineTest`：共享逻辑单元测试。

## 目录

```text
E:\VibeAIMallDual\kmp-shared
```

## Windows 上能做什么

- 编辑 shared 业务代码。
- 跑 JVM/common 逻辑测试。
- 后续把 Android App 逐步接入 shared。

已验证命令：

```powershell
$env:GRADLE_USER_HOME='E:\DevCache\gradle'
$env:KONAN_DATA_DIR='E:\DevCache\kotlin\konan'
.\gradlew.bat :shared:jvmTest
```

验证结果：`BUILD SUCCESSFUL`。

## iOS 怎么接

iOS 编译需要 macOS + Xcode。到 Mac 后：

1. 打开这个 `kmp-shared` 工程。
2. 执行 shared framework 构建。
3. 在 SwiftUI iOS 工程里导入生成的 framework。
4. SwiftUI 页面调用 `RecommendationEngine`、`AiIntentParser`、`AiShoppingChatEngine`。

Windows 上会自动禁用 `iosX64`、`iosArm64`、`iosSimulatorArm64` 的编译任务，这是 Kotlin/Native 对 Apple target 的系统限制，不代表 shared 工程没有配置 iOS。

## 面试讲法

当前 Android 原生 App 已可运行；为了双端复用，我把 AI 购物核心逻辑抽成 KMP shared。Android 和 iOS 后续不重复实现推荐算法，只分别负责原生 UI。iPhone 现场试用可以先用 PWA 体验版，真正 iOS App 编译需要 macOS/Xcode。
