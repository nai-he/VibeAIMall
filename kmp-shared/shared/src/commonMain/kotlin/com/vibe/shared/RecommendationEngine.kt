package com.vibe.shared

class RecommendationEngine(
    private val intentParser: AiIntentParser = AiIntentParser(),
    private val products: List<RecommendationProduct> = ProductCatalog.products()
) {
    fun recommend(input: String): RecommendationResult {
        val intent = intentParser.parse(input)
        val scored = products
            .map { product -> product to score(intent, product) }
            .sortedByDescending { it.second }

        val primary = scored.first().first
        val alternatives = scored.drop(1).take(3).map { it.first }

        return RecommendationResult(
            intent = intent,
            primaryProduct = primary,
            alternatives = alternatives,
            decisionReason = buildDecisionReason(intent, primary),
            nextStep = buildNextStep(primary)
        )
    }

    private fun score(intent: AiIntent, product: RecommendationProduct): Int {
        var score = product.sales / 300
        if (product.category == intent.category) score += 45
        if (intent.scene in product.tags) score += 25
        if (intent.category in product.tags) score += 10

        if (intent.hasBudget) {
            score += when {
                product.price <= intent.budget -> {
                    if (product.price >= intent.budget * 0.55) 33 else 25
                }
                product.price <= intent.budget * 1.15 -> 4
                else -> -18
            }
        }

        if (intent.category == "general" && "daily" in product.tags) {
            score += 12
        }

        return score
    }

    private fun buildDecisionReason(intent: AiIntent, product: RecommendationProduct): String {
        val budgetReason = if (intent.hasBudget) {
            if (product.price <= intent.budget) {
                " 价格也落在你的预算范围内，转化阻力比较低。"
            } else {
                " 这款略高于预算，建议把它作为性能或体验优先的参考方案。"
            }
        } else {
            ""
        }

        return "我先把你的描述拆成「${intent.sceneName} / ${intent.categoryName} / ${intent.budgetText}」。" +
            product.reason +
            budgetReason
    }

    private fun buildNextStep(product: RecommendationProduct): String =
        product.nextStep + " 如果这个方向符合预期，可以先加入购物车，再在订单页完成闭环演示。"
}
