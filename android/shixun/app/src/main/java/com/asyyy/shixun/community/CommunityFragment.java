package com.asyyy.shixun.community;

import android.content.Intent;
import android.view.View;

import com.asyyy.shixun.AIAssistantActivity;
import com.asyyy.shixun.R;
import com.asyyy.shixun.base.BaseFragment;
import com.asyyy.shixun.cart.MyOrderActivity;

public class CommunityFragment extends BaseFragment {

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_community, null);
        bindAiCase(view, R.id.discover_ai_assistant, "");
        bindAiCase(view, R.id.discover_case_study, "预算3000学习平板");
        bindAiCase(view, R.id.discover_case_travel, "出差需要续航");
        bindAiCase(view, R.id.discover_case_gift, "送女生生日礼物");
        bindOrderFlow(view);
        return view;
    }

    public void initData() {
        super.initData();
    }

    private void bindAiCase(View root, int viewId, String need) {
        View item = root.findViewById(viewId);
        if (item == null) {
            return;
        }

        item.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AIAssistantActivity.class);
            if (need != null && need.length() > 0) {
                intent.putExtra("need", need);
            }
            startActivity(intent);
        });
    }

    private void bindOrderFlow(View root) {
        View item = root.findViewById(R.id.discover_order_flow);
        if (item == null) {
            return;
        }

        item.setOnClickListener(v -> startActivity(new Intent(mContext, MyOrderActivity.class)));
    }
}
