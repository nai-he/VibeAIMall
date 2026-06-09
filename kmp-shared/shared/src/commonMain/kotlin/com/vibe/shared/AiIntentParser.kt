package com.vibe.shared

class AiIntentParser {
    fun parse(input: String?): AiIntent {
        val raw = input?.trim().orEmpty()
        val category = detectCategory(raw)
        val scene = detectScene(raw)
        return AiIntent(
            rawText = raw,
            scene = scene,
            sceneName = sceneName(scene),
            category = category,
            categoryName = categoryName(category),
            budget = detectBudget(raw)
        )
    }

    private fun detectBudget(text: String): Int {
        val match = Regex("(\\d{2,6})").find(text) ?: return NO_BUDGET
        return match.value.toIntOrNull() ?: NO_BUDGET
    }

    private fun detectCategory(text: String): String = when {
        text.containsAny("平板", "ipad", "iPad", "网课", "笔记") -> "tablet"
        text.containsAny("手机", "换机", "拍照", "5G", "5g") -> "phone"
        text.containsAny("充电", "续航", "电源", "充电宝") -> "power"
        text.containsAny("耳机", "降噪", "蓝牙") -> "headphones"
        text.containsAny("笔记本", "电脑", "编程", "论文") -> "laptop"
        text.containsAny("空调", "家电", "扫地", "厨房") -> "home_appliance"
        text.containsAny("礼物", "女生", "生日", "口红", "美妆") -> "gift_beauty"
        else -> "general"
    }

    private fun detectScene(text: String): String = when {
        text.containsAny("学习", "网课", "论文", "校园") -> "study"
        text.containsAny("出差", "旅行", "旅游", "续航") -> "travel"
        text.containsAny("通勤", "地铁", "办公室", "办公") -> "commute"
        text.containsAny("礼物", "生日", "送") -> "gift"
        text.containsAny("家", "厨房", "空调", "扫地") -> "home"
        else -> "daily"
    }

    private fun categoryName(category: String): String = when (category) {
        "tablet" -> "平板"
        "phone" -> "手机"
        "power" -> "移动电源"
        "headphones" -> "耳机"
        "laptop" -> "笔记本"
        "home_appliance" -> "家电"
        "gift_beauty" -> "礼物美妆"
        else -> "综合商品"
    }

    private fun sceneName(scene: String): String = when (scene) {
        "study" -> "学习场景"
        "travel" -> "差旅场景"
        "commute" -> "通勤办公"
        "gift" -> "送礼场景"
        "home" -> "居家场景"
        else -> "日常场景"
    }
}

internal fun String.containsAny(vararg keys: String): Boolean =
    keys.any { contains(it) }
