# VibeAIMallDual E Drive Setup

这个目录用于把后续双端开发尽量放到 E 盘，减少 C 盘占用。

## 目录规划

- 桌面快捷方式 `Kotlin Multiplatform Mobile Vibe智购AI`
  - 一键打开 E 盘 Android Studio 和真正的 KMP shared 工程。
  - 启动脚本：`E:\DevTools\Start_KMM_VibeAIMall.bat`
  - KMP 工程：`E:\VibeAIMallDual\kmp-shared`
  - 说明文档：`E:\VibeAIMallDual\docs\KMM_DESKTOP_USAGE.md`

- 桌面快捷方式 `Vibe智购AI 双端试用 PWA`
  - 一键启动 Android/iPhone 都能打开的完整业务 PWA。
  - 覆盖登录注册、首页搜索、AI 导购、商品详情、购物车、订单状态、评价中心和 AI 记录。
  - 启动脚本：`E:\DevTools\Start_DualEnd_PWA_VibeAIMall.bat`
  - 页面目录：`E:\VibeAIMallDual\web-pwa`
  - 说明文档：`E:\VibeAIMallDual\docs\DUAL_END_TRYOUT.md`

- `E:\VibeAIMallDual\android\shixun`
  - 当前 Android 项目的 E 盘副本。
  - 后续建议在这里继续改，不再动 C 盘版本。

- `E:\VibeAIMallDual\shared`
  - 早期预留目录。
  - 真正的 KMP 工程已放在 `E:\VibeAIMallDual\kmp-shared`。

- `E:\VibeAIMallDual\ios`
  - 预留给 iOS/SwiftUI 工程。

- `E:\VibeAIMallDual\docs`
  - 放双端改造方案、面试讲稿、架构图。

- `E:\DevTools`
  - 建议安装 Android Studio、Flutter、Kotlin/Gradle 等工具到这里。

- `E:\DevCache\gradle`
  - 建议作为 Gradle 缓存目录。

- `E:\DevCache\android`
  - 建议作为 Android SDK 或 Android 构建缓存目录。

- `E:\DevCache\flutter`
  - 如果走 Flutter 双端路线，建议放 Flutter SDK 和缓存。

- `E:\DevCache\kotlin`
  - Kotlin Multiplatform / Kotlin Native 相关缓存。

## 建议环境变量

可以把这些变量配置到系统环境变量，减少 C 盘缓存：

```text
GRADLE_USER_HOME=E:\DevCache\gradle
ANDROID_SDK_ROOT=E:\DevCache\android\sdk
ANDROID_HOME=E:\DevCache\android\sdk
PUB_CACHE=E:\DevCache\flutter\pub-cache
KONAN_DATA_DIR=E:\DevCache\kotlin\konan
KOTLIN_USER_HOME=E:\DevCache\kotlin
```

## KMM 说明

Windows 上可以先做 Android 端和 shared 共享逻辑整理。iOS App 的编译、模拟器和真机调试需要 macOS + Xcode。

## API Key

真实 API Key 仍然不要写进源码。复制下面文件：

```text
E:\VibeAIMallDual\android\shixun\local.properties.example
```

然后在同目录创建 `local.properties`，写入：

```properties
ai.api.key=你的API_KEY
ai.api.baseUrl=https://api.deepseek.com
ai.api.model=deepseek-v4-flash
```
