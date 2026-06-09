package com.asyyy.shixun.ai;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AiIntentParser {
    private static final Pattern PREFIX_BUDGET =
            Pattern.compile("(预算|价位|价格|控制在|不超过|少于|低于|￥|¥)\\s*(\\d{2,6})");
    private static final Pattern SUFFIX_BUDGET =
            Pattern.compile("(\\d{2,6})\\s*(元|块|以内|以下|左右)");
    private static final Pattern K_BUDGET =
            Pattern.compile("(\\d{1,2})\\s*[kK]");

    public AiIntent parse(String input) {
        String raw = input == null ? "" : input.trim();
        String text = raw.toLowerCase(Locale.ROOT);
        String category = parseCategory(text);
        String scene = parseScene(text);
        int budget = parseBudget(text);

        return new AiIntent(
                raw,
                scene,
                sceneName(scene),
                category,
                categoryName(category),
                budget
        );
    }

    private int parseBudget(String text) {
        Matcher prefixMatcher = PREFIX_BUDGET.matcher(text);
        if (prefixMatcher.find()) {
            return safeParse(prefixMatcher.group(2));
        }

        Matcher suffixMatcher = SUFFIX_BUDGET.matcher(text);
        if (suffixMatcher.find()) {
            return safeParse(suffixMatcher.group(1));
        }

        Matcher kMatcher = K_BUDGET.matcher(text);
        if (kMatcher.find()) {
            int value = safeParse(kMatcher.group(1));
            return value == AiIntent.NO_BUDGET ? value : value * 1000;
        }

        return AiIntent.NO_BUDGET;
    }

    private int safeParse(String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            return AiIntent.NO_BUDGET;
        }
    }

    private String parseCategory(String text) {
        if (containsAny(text, "平板", "ipad", "网课", "笔记")) {
            return "tablet";
        }
        if (containsAny(text, "手机", "拍照", "5g", "换机", "性能机")) {
            return "phone";
        }
        if (containsAny(text, "充电", "电源", "续航", "移动电源")) {
            return "power";
        }
        if (containsAny(text, "耳机", "降噪", "蓝牙", "听歌", "会议")) {
            return "headphones";
        }
        if (containsAny(text, "手表", "运动", "健康", "心率", "睡眠")) {
            return "wearable";
        }
        if (containsAny(text, "笔记本", "电脑", "编程", "论文", "剪辑", "办公软件")) {
            return "laptop";
        }
        if (containsAny(text, "空调", "洗衣", "扫地", "厨房", "空气炸锅", "家电", "智能家居")) {
            return "home_appliance";
        }
        if (containsAny(text, "礼物", "女生", "口红", "美妆", "生日", "礼盒")) {
            return "gift_beauty";
        }
        if (containsAny(text, "牙刷", "个护", "护肤", "清洁")) {
            return "personal_care";
        }
        if (containsAny(text, "鞋", "跑步", "训练", "健身", "通勤鞋")) {
            return "sport";
        }
        if (containsAny(text, "早餐", "酸奶", "饮品", "零食", "牛奶", "咖啡", "轻食", "猫粮", "宠物")) {
            return "food";
        }
        return "general";
    }

    private String parseScene(String text) {
        if (containsAny(text, "出差", "旅行", "差旅", "高铁", "飞机")) {
            return "travel";
        }
        if (containsAny(text, "学习", "学生", "网课", "笔记", "校园")) {
            return "study";
        }
        if (containsAny(text, "送", "礼物", "生日", "纪念日", "女生")) {
            return "gift";
        }
        if (containsAny(text, "通勤", "地铁", "公交", "上下班")) {
            return "commute";
        }
        if (containsAny(text, "办公", "会议", "编程", "论文", "剪辑")) {
            return "office";
        }
        if (containsAny(text, "卧室", "租房", "家里", "厨房", "家务")) {
            return "home";
        }
        if (containsAny(text, "运动", "健康", "跑步", "健身")) {
            return "health";
        }
        return "daily";
    }

    private String categoryName(String category) {
        switch (category) {
            case "tablet":
                return "平板电脑";
            case "phone":
                return "手机数码";
            case "power":
                return "续航配件";
            case "headphones":
                return "耳机音频";
            case "wearable":
                return "智能穿戴";
            case "laptop":
                return "电脑办公";
            case "home_appliance":
                return "家电家居";
            case "gift_beauty":
                return "礼物美妆";
            case "personal_care":
                return "个护清洁";
            case "sport":
                return "运动户外";
            case "food":
                return "食品日用";
            default:
                return "综合推荐";
        }
    }

    private String sceneName(String scene) {
        switch (scene) {
            case "travel":
                return "出差旅行";
            case "study":
                return "学习提升";
            case "gift":
                return "送礼表达";
            case "commute":
                return "通勤效率";
            case "office":
                return "办公生产力";
            case "home":
                return "家庭生活";
            case "health":
                return "健康运动";
            default:
                return "日常购物";
        }
    }

    private boolean containsAny(String text, String... keys) {
        for (String key : keys) {
            if (text.contains(key)) {
                return true;
            }
        }
        return false;
    }
}
