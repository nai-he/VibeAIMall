# Vibe智购AI - Android AI Shopping Assistant

面向 AI 工程师（Vibe Coding）岗位定制的 Android 求职项目。项目以购物 App 为载体，完成从自然语言需求、端侧推荐、加购、下单、订单状态流转到评价/售后的可演示闭环。

## 岗位匹配点

- Android 客户端落地：注册登录、首页商品、AI 推荐、购物车、订单、评论等流程可本地运行。
- AI 原生交互：用户输入一句话需求，系统识别预算、场景、品类，并输出主推商品、备选方案和购买建议。
- 对话式购买：用户可以继续输入“就买这个”“买备选1”“去购物车”，AI 助手会直接加购或跳转购物车。
- 真实 API + 端侧兜底：已接入兼容 `/chat/completions` 的大模型 API；未配置 Key 或网络失败时自动使用端侧推荐引擎。
- 端侧推荐引擎：`AiIntentParser`、`RecommendationEngine`、`ProductCatalog` 从页面层拆出，便于测试和扩展。
- 业务闭环：推荐日志记录用户输入、识别意图、推荐商品和加购动作，并提供 App 内“AI 推荐记录”页面。
- 工程化拆分：`ProductRepository` 统一首页、分类、详情和 AI 推荐商品数据，`CartService`、`OrderService` 复用购物车和订单状态逻辑。
- 产品化页面：发现页改造成 AI 产品实验室，个人中心订单状态入口可点击，订单支持状态流转。

## 核心演示路径

1. 注册/登录进入 App。
2. 首页搜索框输入“预算3000学习平板”或在发现页点击场景卡片。
3. AI 助手展示需求识别、主推商品、备选方案和购买建议。
4. 继续输入“就买这个”或“买备选1”，AI 直接加入购物车。
5. 输入“去购物车”，进入购物车结算。
6. 下单后进入我的订单，演示待付款、待收货、待评价、退款/售后筛选与状态动作。
7. 待评价订单进入评价页，提交后写入商品评论并流转为已完成。
8. 个人中心点击“AI记录”查看推荐日志，验证推荐到加购的闭环记录。
9. 个人中心点击“小游戏”“AI助手”“我的订单”等入口验证功能完整性。

## 技术栈

- Java
- AndroidX
- SQLite
- RecyclerView / ListView
- 本地端侧推荐规则引擎
- 兼容 OpenAI/DeepSeek 风格的 Chat Completions API
- JUnit 单元测试

## 真实 AI API 配置

为了避免把 Key 写死在源码里，项目从 `local.properties` 或环境变量读取配置。`local.properties` 已被 `.gitignore` 忽略，适合放本机 Key。

```properties
ai.api.key=你的API_KEY
ai.api.baseUrl=https://api.deepseek.com
ai.api.model=deepseek-v4-flash
```

也可以用环境变量：`AI_API_KEY`、`AI_API_BASE_URL`、`AI_API_MODEL`。未配置 Key 时 App 会自动走端侧推荐，核心演示链路不会崩。

## 主要模块

- `app/src/main/java/com/asyyy/shixun/ai`：AI 意图解析、商品匹配、推荐结果。
- `AiShoppingChatEngine`：对话购买指令解析，支持推荐、加购、备选购买和跳转购物车。
- `AiRemoteAdvisor`：真实大模型 API 调用，负责生成自然语言导购回复。
- `app/src/main/java/com/asyyy/shixun/data/ProductRepository.java`：统一商品数据仓库。
- `AIAssistantActivity`：AI 推荐交互页。
- `CartService`：统一加购逻辑。
- `OrderService`：订单查询、演示订单和状态更新。
- `CartDBOpenHelper`：购物车、订单、评论、推荐日志数据表。
- `MyOrderActivity`：订单筛选、状态流转和演示订单。
- `OrderReviewActivity`：待评价订单提交评价并完成订单。
- `RecommendationLogActivity`：AI 推荐日志展示与清空。
- `CommunityFragment`：AI 产品实验室/发现页。
- `UserFragment`：个人中心工具与订单状态入口。

## 可扩展方向

- 将真实大模型 API 进一步用于多轮偏好记忆、推荐解释优化和客服问答。
- 增加推荐日志统计页，展示需求到加购的转化率。
- 增加更多商品属性，按预算、评分、销量、场景进行多因子排序。
- 引入 UI 自动化测试覆盖核心演示链路。
