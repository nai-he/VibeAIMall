package com.asyyy.shixun.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendationResult {
    private final AiIntent intent;
    private final RecommendationProduct primaryProduct;
    private final List<RecommendationProduct> alternatives;
    private final String decisionReason;
    private final String nextStep;

    public RecommendationResult(AiIntent intent, RecommendationProduct primaryProduct,
                                List<RecommendationProduct> alternatives,
                                String decisionReason, String nextStep) {
        this.intent = intent;
        this.primaryProduct = primaryProduct;
        this.alternatives = Collections.unmodifiableList(new ArrayList<>(alternatives));
        this.decisionReason = decisionReason;
        this.nextStep = nextStep;
    }

    public AiIntent getIntent() {
        return intent;
    }

    public RecommendationProduct getPrimaryProduct() {
        return primaryProduct;
    }

    public List<RecommendationProduct> getAlternatives() {
        return alternatives;
    }

    public String getDecisionReason() {
        return decisionReason;
    }

    public String getNextStep() {
        return nextStep;
    }
}
