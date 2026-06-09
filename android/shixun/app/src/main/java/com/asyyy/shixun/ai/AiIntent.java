package com.asyyy.shixun.ai;

public class AiIntent {
    public static final int NO_BUDGET = -1;

    private final String rawText;
    private final String scene;
    private final String sceneName;
    private final String category;
    private final String categoryName;
    private final int budget;

    public AiIntent(String rawText, String scene, String sceneName,
                    String category, String categoryName, int budget) {
        this.rawText = rawText;
        this.scene = scene;
        this.sceneName = sceneName;
        this.category = category;
        this.categoryName = categoryName;
        this.budget = budget;
    }

    public String getRawText() {
        return rawText;
    }

    public String getScene() {
        return scene;
    }

    public String getSceneName() {
        return sceneName;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getBudget() {
        return budget;
    }

    public boolean hasBudget() {
        return budget != NO_BUDGET;
    }

    public String getBudgetText() {
        return hasBudget() ? budget + "元以内" : "未限定预算";
    }

    public String getSummary() {
        return "场景：" + sceneName + "，品类：" + categoryName + "，预算：" + getBudgetText();
    }
}
