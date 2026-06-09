package com.vibe.shared

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SharedEngineTest {
    @Test
    fun parserReadsStudyTabletBudget() {
        val intent = AiIntentParser().parse("预算3000学习平板")

        assertEquals("study", intent.scene)
        assertEquals("tablet", intent.category)
        assertEquals(3000, intent.budget)
    }

    @Test
    fun recommendationReturnsBudgetFriendlyTablet() {
        val result = RecommendationEngine().recommend("预算3000学习平板")

        assertEquals("tablet", result.intent.category)
        assertTrue(result.primaryProduct.price <= 3000)
        assertTrue(result.alternatives.isNotEmpty())
    }

    @Test
    fun chatEngineBuysCurrentRecommendation() {
        val command = AiShoppingChatEngine().parse("就买这个", hasRecommendation = true)

        assertEquals(AiChatCommand.ACTION_ADD_TO_CART, command.action)
        assertEquals(0, command.targetIndex)
    }

    @Test
    fun chatEngineRequiresRecommendationBeforeBuyCurrent() {
        val command = AiShoppingChatEngine().parse("就买这个", hasRecommendation = false)

        assertEquals(AiChatCommand.ACTION_HELP, command.action)
    }

    @Test
    fun chatEngineRecommendsAndBuysNewNeed() {
        val command = AiShoppingChatEngine().parse("买一个出差续航充电宝", hasRecommendation = false)

        assertEquals(AiChatCommand.ACTION_RECOMMEND_AND_ADD, command.action)
    }
}
