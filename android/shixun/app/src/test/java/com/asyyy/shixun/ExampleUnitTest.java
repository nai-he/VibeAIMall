package com.asyyy.shixun;

import com.asyyy.shixun.ai.AiIntent;
import com.asyyy.shixun.ai.AiIntentParser;
import com.asyyy.shixun.ai.AiChatCommand;
import com.asyyy.shixun.ai.AiShoppingChatEngine;
import com.asyyy.shixun.ai.RecommendationEngine;
import com.asyyy.shixun.ai.RecommendationResult;
import com.asyyy.shixun.data.ProductRepository;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExampleUnitTest {
    private final AiIntentParser parser = new AiIntentParser();

    @Test
    public void parserReadsStudyTabletBudget() {
        AiIntent intent = parser.parse("预算3000学习平板");

        assertEquals("study", intent.getScene());
        assertEquals("tablet", intent.getCategory());
        assertEquals(3000, intent.getBudget());
    }

    @Test
    public void parserReadsTravelPowerNeed() {
        AiIntent intent = parser.parse("出差需要续航");

        assertEquals("travel", intent.getScene());
        assertEquals("power", intent.getCategory());
        assertTrue(!intent.hasBudget());
    }

    @Test
    public void parserReadsGiftBeautyNeed() {
        AiIntent intent = parser.parse("送女生生日礼物");

        assertEquals("gift", intent.getScene());
        assertEquals("gift_beauty", intent.getCategory());
    }

    @Test
    public void recommendationReturnsBudgetFriendlyTablet() {
        RecommendationResult result = new RecommendationEngine().recommend("预算3000学习平板");

        assertEquals("tablet", result.getIntent().getCategory());
        assertTrue(result.getPrimaryProduct().getPrice() <= 3000);
        assertTrue(result.getAlternatives().size() > 0);
    }

    @Test
    public void productRepositoryFindsHomeProduct() {
        assertNotNull(ProductRepository.findById(9));
        assertEquals("小米移动电源 10000mAh", ProductRepository.findById(9).getG_name());
    }

    @Test
    public void chatEngineBuysCurrentRecommendation() {
        AiChatCommand command = new AiShoppingChatEngine().parse("就买这个", true);

        assertEquals(AiChatCommand.ACTION_ADD_TO_CART, command.getAction());
        assertEquals(0, command.getTargetIndex());
    }

    @Test
    public void chatEngineRequiresRecommendationBeforeBuyCurrent() {
        AiChatCommand command = new AiShoppingChatEngine().parse("就买这个", false);

        assertEquals(AiChatCommand.ACTION_HELP, command.getAction());
    }

    @Test
    public void chatEngineBuysSelectedAlternative() {
        AiChatCommand command = new AiShoppingChatEngine().parse("买备选2", true);

        assertEquals(AiChatCommand.ACTION_ADD_TO_CART, command.getAction());
        assertEquals(2, command.getTargetIndex());
    }

    @Test
    public void chatEngineRecommendsAndBuysNewNeed() {
        AiChatCommand command = new AiShoppingChatEngine().parse("买一个出差续航充电宝", false);

        assertEquals(AiChatCommand.ACTION_RECOMMEND_AND_ADD, command.getAction());
    }

    @Test
    public void chatEngineOpensCart() {
        AiChatCommand command = new AiShoppingChatEngine().parse("去购物车结算", true);

        assertEquals(AiChatCommand.ACTION_VIEW_CART, command.getAction());
    }
}
