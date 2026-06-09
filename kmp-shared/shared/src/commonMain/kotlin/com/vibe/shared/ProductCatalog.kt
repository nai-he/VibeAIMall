package com.vibe.shared

object ProductCatalog {
    fun products(): List<RecommendationProduct> = listOf(
        build(
            id = 1,
            name = "iPad 2024 学习平板",
            type = "网课、笔记、轻办公",
            price = 2999.0,
            sales = 9800,
            shop = "Vibe数码旗舰店",
            category = "tablet",
            tags = listOf("study", "daily", "office", "tablet"),
            reason = "覆盖学习、网课、笔记和轻办公场景，预算压力相对可控。",
            nextStep = "如果需要键盘和手写笔，可以优先确认配件预算。"
        ),
        build(
            id = 3,
            name = "iPad Pro 创作平板",
            type = "绘画、剪辑、重度笔记",
            price = 4999.0,
            sales = 5200,
            shop = "Vibe数码旗舰店",
            category = "tablet",
            tags = listOf("study", "office", "creative", "tablet"),
            reason = "更适合绘画、剪辑和重度笔记需求，性能余量更大。",
            nextStep = "如果只是上网课，建议回到更高性价比选择。"
        ),
        build(
            id = 4,
            name = "小米 14 性能手机",
            type = "性能、拍照、日常使用",
            price = 3999.0,
            sales = 8600,
            shop = "智能手机专营店",
            category = "phone",
            tags = listOf("phone", "performance", "daily", "office"),
            reason = "兼顾性能、拍照和日常使用，适合换机用户作为均衡选择。",
            nextStep = "如果重度拍照，可以再对比影像取向机型。"
        ),
        build(
            id = 9,
            name = "小米移动电源 10000mAh",
            type = "轻便、低价、通勤补电",
            price = 99.0,
            sales = 18800,
            shop = "小米智能生活店",
            category = "power",
            tags = listOf("travel", "commute", "power", "daily"),
            reason = "适合出差、通勤、校园外出时补足续航，购买决策成本低。",
            nextStep = "如果多设备同时充电，可以考虑更大容量版本。"
        ),
        build(
            id = 10,
            name = "大容量快充移动电源",
            type = "20000mAh、多设备快充",
            price = 169.0,
            sales = 9200,
            shop = "出行数码优选",
            category = "power",
            tags = listOf("travel", "power", "high_capacity"),
            reason = "容量更大，适合长途差旅和多设备用户。",
            nextStep = "通勤场景要权衡重量和便携性。"
        ),
        build(
            id = 11,
            name = "通勤降噪蓝牙耳机",
            type = "降噪、通勤、办公专注",
            price = 399.0,
            sales = 12800,
            shop = "音频体验馆",
            category = "headphones",
            tags = listOf("commute", "travel", "office", "headphones"),
            reason = "能明显改善地铁、办公室和差旅环境里的体验。",
            nextStep = "如果预算有限，可以选择入门蓝牙耳机作为替代。"
        ),
        build(
            id = 14,
            name = "轻薄办公笔记本 16G",
            type = "论文、表格、编程、移动办公",
            price = 4599.0,
            sales = 7600,
            shop = "电脑办公旗舰店",
            category = "laptop",
            tags = listOf("office", "study", "laptop", "coding"),
            reason = "适合论文、表格、编程和移动办公，16G 内存能覆盖更长使用周期。",
            nextStep = "如果有剪辑需求，建议进一步关注显卡和散热。"
        ),
        build(
            id = 31,
            name = "生日礼物美妆套装",
            type = "送礼、美妆、生日",
            price = 299.0,
            sales = 11300,
            shop = "礼物灵感馆",
            category = "gift_beauty",
            tags = listOf("gift", "beauty", "birthday"),
            reason = "送礼决策成本低、表达属性强，适合生日和节日场景。",
            nextStep = "下单前可以再确认色号和收礼人的日常风格。"
        )
    )

    private fun build(
        id: Int,
        name: String,
        type: String,
        price: Double,
        sales: Int,
        shop: String,
        category: String,
        tags: List<String>,
        reason: String,
        nextStep: String
    ) = RecommendationProduct(id, name, type, price, sales, shop, category, tags, reason, nextStep)
}
