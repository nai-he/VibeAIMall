package com.asyyy.shixun.ai;

import java.util.Locale;

public class AiShoppingChatEngine {
    public AiChatCommand parse(String input, boolean hasRecommendation) {
        String message = input == null ? "" : input.trim();
        String text = message.toLowerCase(Locale.ROOT);

        if (message.length() == 0) {
            return new AiChatCommand(AiChatCommand.ACTION_HELP, message, 0);
        }

        if (containsAny(text, "帮助", "怎么用", "指令", "你能做什么")) {
            return new AiChatCommand(AiChatCommand.ACTION_HELP, message, 0);
        }

        if (containsAny(text, "购物车", "去结算", "结算", "查看加购")) {
            return new AiChatCommand(AiChatCommand.ACTION_VIEW_CART, message, 0);
        }

        if (isBuyMessage(text)) {
            int targetIndex = parseTargetIndex(text);
            boolean hasNewNeed = hasNewNeed(text);
            if (hasRecommendation && !hasNewNeed) {
                return new AiChatCommand(AiChatCommand.ACTION_ADD_TO_CART, message, targetIndex);
            }
            if (hasNewNeed) {
                return new AiChatCommand(AiChatCommand.ACTION_RECOMMEND_AND_ADD, message, targetIndex);
            }
            return new AiChatCommand(AiChatCommand.ACTION_HELP, message, 0);
        }

        return new AiChatCommand(AiChatCommand.ACTION_RECOMMEND, message, 0);
    }

    private boolean isBuyMessage(String text) {
        return containsAny(text,
                "买", "购买", "加入购物车", "加购物车", "加购", "下单",
                "就这个", "要这个", "选这个", "拿下", "付款");
    }

    private int parseTargetIndex(String text) {
        if (containsAny(text, "备选3", "备选三", "第三个备选", "第三个")) {
            return 3;
        }
        if (containsAny(text, "备选2", "备选二", "第二个备选", "第二个")) {
            return 2;
        }
        if (containsAny(text, "备选1", "备选一", "第一个备选")) {
            return 1;
        }
        return 0;
    }

    private boolean hasNewNeed(String text) {
        return containsAny(text,
                "预算", "以内", "左右", "平板", "手机", "耳机", "充电", "续航",
                "礼物", "女生", "通勤", "出差", "办公", "学习", "家电", "空调",
                "扫地", "厨房", "运动", "跑步", "口红", "美妆", "笔记本");
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
