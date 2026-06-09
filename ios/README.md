# iOS App Plan

这个目录预留给 iOS 端工程。

建议路线：

1. 使用 SwiftUI 创建 iOS App。
2. 通过 Kotlin Multiplatform 生成的 shared framework 接入共享业务逻辑。
3. iOS UI 保持原生体验，复用 Android 端已经验证过的 AI 推荐、对话加购、订单闭环逻辑。

注意：iOS 编译、模拟器和上架需要 macOS + Xcode。Windows 当前主要负责 Android 端和 shared 逻辑整理。
