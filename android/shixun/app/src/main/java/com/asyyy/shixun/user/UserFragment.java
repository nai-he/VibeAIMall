package com.asyyy.shixun.user;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;

import com.asyyy.shixun.AIAssistantActivity;
import com.asyyy.shixun.MActivity;
import com.asyyy.shixun.R;
import com.asyyy.shixun.RecommendationLogActivity;
import com.asyyy.shixun.base.BaseFragment;
import com.asyyy.shixun.cart.MyOrderActivity;
import com.asyyy.shixun.cart.Success2Activity;
import com.asyyy.shixun.play.Doctor;
import com.asyyy.shixun.play.Lb1;
import com.asyyy.shixun.play.LinkActivity;
import com.asyyy.shixun.play.PeachActivity;
import com.asyyy.shixun.play.Plane;
import com.asyyy.shixun.play.SettingActivity;

public class UserFragment extends BaseFragment {
    private GridView userTools;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        userTools = view.findViewById(R.id.user_tools);

        bindOrderStatus(view, R.id.order_pending_pay, "待付款");
        bindOrderStatus(view, R.id.order_pending_receive, "待收货");
        bindOrderStatus(view, R.id.order_pending_comment, "待评价");
        bindOrderStatus(view, R.id.order_after_sale, "退款/售后");
        bindOrderStatus(view, R.id.order_all, null);

        return view;
    }

    public void initData() {
        super.initData();
        int[] icons = {
                R.drawable.tools1, R.drawable.wodedingdan, R.drawable.main_cart_press, R.drawable.daipingjia,
                R.drawable.jdk4, R.drawable.fj, R.drawable.tools4, R.drawable.main_user_press,
                R.drawable.tools9, R.drawable.tools10, R.drawable.tools11, R.drawable.tools12
        };
        String[] names = {
                "AI助手", "我的订单", "购物车", "评价中心",
                "退出登录", "小游戏", "问医生", "用户绑定",
                "AI记录", "预约服务", "拼购", "小程序"
        };
        UserToolsAdapter adapter = new UserToolsAdapter(mContext, icons, names);
        userTools.setAdapter(adapter);
        userTools.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent;
            switch (position) {
                case 0:
                    intent = new Intent(mContext, AIAssistantActivity.class);
                    break;
                case 1:
                    intent = new Intent(mContext, MyOrderActivity.class);
                    break;
                case 2:
                    intent = new Intent(mContext, MActivity.class);
                    intent.putExtra("tab", 3);
                    break;
                case 3:
                    intent = new Intent(mContext, MyOrderActivity.class);
                    intent.putExtra(MyOrderActivity.EXTRA_STATUS_FILTER, "待评价");
                    break;
                case 4:
                    intent = new Intent(mContext, SettingActivity.class);
                    break;
                case 5:
                    intent = new Intent(mContext, Plane.class);
                    break;
                case 6:
                    intent = new Intent(mContext, Doctor.class);
                    break;
                case 7:
                    intent = new Intent(mContext, LinkActivity.class);
                    break;
                case 8:
                    intent = new Intent(mContext, RecommendationLogActivity.class);
                    break;
                case 9:
                    intent = new Intent(mContext, PeachActivity.class);
                    break;
                case 10:
                    intent = new Intent(mContext, Lb1.class);
                    break;
                default:
                    return;
            }
            startActivity(intent);
        });
    }

    private void bindOrderStatus(View root, int viewId, String status) {
        View item = root.findViewById(viewId);
        if (item == null) {
            return;
        }

        item.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MyOrderActivity.class);
            if (status != null) {
                intent.putExtra(MyOrderActivity.EXTRA_STATUS_FILTER, status);
            }
            startActivity(intent);
        });
    }
}
