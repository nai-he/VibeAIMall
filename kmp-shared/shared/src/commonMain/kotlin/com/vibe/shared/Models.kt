package com.vibe.shared

const val NO_BUDGET: Int = -1

data class AiIntent(
    val rawText: String,
    val scene: String,
    val sceneName: String,
    val category: String,
    val categoryName: String,
    val budget: Int = NO_BUDGET
) {
    val hasBudget: Boolean get() = budget != NO_BUDGET
    val budgetText: String get() = if (hasBudget) "${budget}元以内" else "未限定预算"
    val summary: String get() = "场景：$sceneName，品类：$categoryName，预算：$budgetText"
}

data class RecommendationProduct(
    val id: Int,
    val name: String,
    val type: String,
    val price: Double,
    val sales: Int,
    val shop: String,
    val category: String,
    val tags: List<String>,
    val reason: String,
    val nextStep: String
)

data class RecommendationResult(
    val intent: AiIntent,
    val primaryProduct: RecommendationProduct,
    val alternatives: List<RecommendationProduct>,
    val decisionReason: String,
    val nextStep: String
)

data class AiChatCommand(
    val action: String,
    val message: String,
    val targetIndex: Int = 0
) {
    companion object {
        const val ACTION_RECOMMEND = "recommend"
        const val ACTION_ADD_TO_CART = "add_to_cart"
        const val ACTION_RECOMMEND_AND_ADD = "recommend_and_add"
        const val ACTION_VIEW_CART = "view_cart"
        const val ACTION_HELP = "help"
    }
}
