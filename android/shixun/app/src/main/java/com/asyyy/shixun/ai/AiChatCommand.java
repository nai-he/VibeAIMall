package com.asyyy.shixun.ai;

public class AiChatCommand {
    public static final String ACTION_RECOMMEND = "recommend";
    public static final String ACTION_ADD_TO_CART = "add_to_cart";
    public static final String ACTION_RECOMMEND_AND_ADD = "recommend_and_add";
    public static final String ACTION_VIEW_CART = "view_cart";
    public static final String ACTION_HELP = "help";

    private final String action;
    private final String message;
    private final int targetIndex;

    public AiChatCommand(String action, String message, int targetIndex) {
        this.action = action;
        this.message = message;
        this.targetIndex = targetIndex;
    }

    public String getAction() {
        return action;
    }

    public String getMessage() {
        return message;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public boolean is(String expectedAction) {
        return action.equals(expectedAction);
    }
}
