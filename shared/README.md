# Shared Kotlin Multiplatform Module Plan

这个目录预留给 Kotlin Multiplatform Mobile 的共享业务层。

优先迁移这些模块：

- AI 意图解析：`AiIntentParser`
- 商品推荐引擎：`RecommendationEngine`
- 对话购买解析：`AiShoppingChatEngine`
- 商品/推荐数据模型：`AiIntent`、`RecommendationProduct`、`RecommendationResult`
- API 抽象：真实大模型导购回复接口

Android 端当前可继续使用：

```text
E:\VibeAIMallDual\android\shixun
```

iOS 端后续用 SwiftUI 接入 shared 逻辑。
