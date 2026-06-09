package com.vibe.shared

class AiShoppingChatEngine {
    fun parse(input: String?, hasRecommendation: Boolean): AiChatCommand {
        val message = input?.trim().orEmpty()
        if (message.isEmpty()) {
            return AiChatCommand(AiChatCommand.ACTION_HELP, message)
        }

        if (message.containsAny("帮助", "怎么用", "指令", "你能做什么")) {
            return AiChatCommand(AiChatCommand.ACTION_HELP, message)
        }

        if (message.containsAny("购物车", "去结算", "结算", "查看加购")) {
            return AiChatCommand(AiChatCommand.ACTION_VIEW_CART, message)
        }

        if (isBuyMessage(message)) {
            val targetIndex = parseTargetIndex(message)
            val hasNewNeed = hasNewNeed(message)
            return when {
                hasRecommendation && !hasNewNeed ->
                    AiChatCommand(AiChatCommand.ACTION_ADD_TO_CART, message, targetIndex)
                hasNewNeed ->
                    AiChatCommand(AiChatCommand.ACTION_RECOMMEND_AND_ADD, message, targetIndex)
                else ->
                    AiChatCommand(AiChatCommand.ACTION_HELP, message)
            }
        }

        return AiChatCommand(AiChatCommand.ACTION_RECOMMEND, message)
    }

    private fun isBuyMessage(text: String): Boolean =
        text.containsAny("买", "购买", "加入购物车", "加购物车", "加购", "下单", "就这个", "要这个", "选这个", "拿下", "付款")

    private fun parseTargetIndex(text: String): Int = when {
        text.containsAny("备选3", "备选三", "第三个备选", "第三个") -> 3
        text.containsAny("备选2", "备选二", "第二个备选", "第二个") -> 2
        text.containsAny("备选1", "备选一", "第一个备选") -> 1
        else -> 0
    }

    private fun hasNewNeed(text: String): Boolean =
        text.containsAny(
            "预算", "以内", "左右", "平板", "手机", "耳机", "充电", "续航",
            "礼物", "女生", "通勤", "出差", "办公", "学习", "家电", "空调",
            "扫地", "厨房", "运动", "跑步", "口红", "美妆", "笔记本"
        )
}
