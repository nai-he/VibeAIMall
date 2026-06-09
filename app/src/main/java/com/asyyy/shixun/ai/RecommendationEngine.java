package com.asyyy.shixun.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecommendationEngine {
    private final AiIntentParser intentParser = new AiIntentParser();

    public RecommendationResult recommend(String input) {
        AiIntent intent = intentParser.parse(input);
        List<ScoredProduct> scoredProducts = new ArrayList<>();

        for (RecommendationProduct product : ProductCatalog.getProducts()) {
            scoredProducts.add(new ScoredProduct(product, score(intent, product)));
        }

        Collections.sort(scoredProducts, new Comparator<ScoredProduct>() {
            @Override
            public int compare(ScoredProduct left, ScoredProduct right) {
                return right.score - left.score;
            }
        });

        RecommendationProduct primary = scoredProducts.get(0).product;
        List<RecommendationProduct> alternatives = new ArrayList<>();
        for (int i = 1; i < scoredProducts.size() && alternatives.size() < 3; i++) {
            alternatives.add(scoredProducts.get(i).product);
        }

        return new RecommendationResult(
                intent,
                primary,
                alternatives,
                buildDecisionReason(intent, primary),
                buildNextStep(intent, primary)
        );
    }

    private int score(AiIntent intent, RecommendationProduct product) {
        int score = product.getSales() / 300;

        if (product.getCategory().equals(intent.getCategory())) {
            score += 45;
        }

        if (hasTag(product, intent.getScene())) {
            score += 25;
        }

        if (hasTag(product, intent.getCategory())) {
            score += 10;
        }

        if (intent.hasBudget()) {
            if (product.getPrice() <= intent.getBudget()) {
                score += 25;
                if (product.getPrice() >= intent.getBudget() * 0.55) {
                    score += 8;
                }
            } else if (product.getPrice() <= intent.getBudget() * 1.15) {
                score += 4;
            } else {
                score -= 18;
            }
        }

        if ("general".equals(intent.getCategory()) && hasTag(product, "daily")) {
            score += 12;
        }

        return score;
    }

    private String buildDecisionReason(AiIntent intent, RecommendationProduct product) {
        StringBuilder builder = new StringBuilder();
        builder.append("我先把你的描述拆成「")
                .append(intent.getSceneName())
                .append(" / ")
                .append(intent.getCategoryName())
                .append(" / ")
                .append(intent.getBudgetText())
                .append("」。");
        builder.append(product.getReason());
        if (intent.hasBudget()) {
            if (product.getPrice() <= intent.getBudget()) {
                builder.append(" 价格也落在你的预算范围内，转化阻力比较低。");
            } else {
                builder.append(" 这款略高于预算，建议把它作为性能或体验优先的参考方案。");
            }
        }
        return builder.toString();
    }

    private String buildNextStep(AiIntent intent, RecommendationProduct product) {
        StringBuilder builder = new StringBuilder();
        builder.append(product.getNextStep());
        builder.append(" 如果这个方向符合预期，可以先加入购物车，再在订单页完成闭环演示。");
        return builder.toString();
    }

    private boolean hasTag(RecommendationProduct product, String target) {
        for (String tag : product.getTags()) {
            if (tag.equals(target)) {
                return true;
            }
        }
        return false;
    }

    private static class ScoredProduct {
        private final RecommendationProduct product;
        private final int score;

        private ScoredProduct(RecommendationProduct product, int score) {
            this.product = product;
            this.score = score;
        }
    }
}
