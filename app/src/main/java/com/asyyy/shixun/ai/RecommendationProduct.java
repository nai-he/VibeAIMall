package com.asyyy.shixun.ai;

public class RecommendationProduct {
    private final int id;
    private final int photo;
    private final String name;
    private final String type;
    private final double price;
    private final int sales;
    private final String shop;
    private final String category;
    private final String[] tags;
    private final String reason;
    private final String nextStep;

    public RecommendationProduct(int id, int photo, String name, String type, double price,
                                 int sales, String shop, String category, String[] tags,
                                 String reason, String nextStep) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.type = type;
        this.price = price;
        this.sales = sales;
        this.shop = shop;
        this.category = category;
        this.tags = tags;
        this.reason = reason;
        this.nextStep = nextStep;
    }

    public int getId() {
        return id;
    }

    public int getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getSales() {
        return sales;
    }

    public String getShop() {
        return shop;
    }

    public String getCategory() {
        return category;
    }

    public String[] getTags() {
        return tags;
    }

    public String getReason() {
        return reason;
    }

    public String getNextStep() {
        return nextStep;
    }
}
