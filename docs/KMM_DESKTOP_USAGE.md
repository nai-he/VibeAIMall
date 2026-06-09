# Kotlin Multiplatform Mobile Desktop Entry

## 一键入口

桌面快捷方式：

```text
Kotlin Multiplatform Mobile Vibe智购AI.lnk
```

它会启动：

```text
E:\DevTools\Start_KMM_VibeAIMall.bat
```

并用 E 盘 Android Studio 打开当前 Android 项目：

```text
E:\VibeAIMallDual\android\shixun
```

## E 盘缓存

启动脚本会把开发缓存指到 E 盘：

```text
GRADLE_USER_HOME=E:\DevCache\gradle
ANDROID_SDK_ROOT=E:\DevCache\android\sdk
ANDROID_HOME=E:\DevCache\android\sdk
ANDROID_USER_HOME=E:\DevCache\android\.android
KONAN_DATA_DIR=E:\DevCache\kotlin\konan
KOTLIN_USER_HOME=E:\DevCache\kotlin
```

## 当前状态

- Android 端项目已经可构建、可测试。
- `shared` 目录预留给 KMM 共享业务层。
- `ios` 目录预留给 SwiftUI/iOS 端工程。
- Windows 可以整理 shared 逻辑和 Android 端；iOS 真机/模拟器编译最终需要 macOS + Xcode。

## 建议迁移顺序

1. 先把 AI 意图解析、推荐引擎、对话购买解析迁到 `shared`。
2. 再把真实 API 调用抽象成 shared 接口。
3. Android 端继续复用当前页面。
4. iOS 端用 SwiftUI 接入 shared 逻辑。
