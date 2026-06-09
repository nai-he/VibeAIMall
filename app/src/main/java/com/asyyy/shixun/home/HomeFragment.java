package com.asyyy.shixun.home;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.asyyy.shixun.AIAssistantActivity;
import com.asyyy.shixun.R;
import com.asyyy.shixun.base.BaseFragment;
import com.asyyy.shixun.data.ProductRepository;

public class HomeFragment extends BaseFragment {
    private ListView lv_home;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        lv_home = view.findViewById(R.id.lv_home);
        ib_top = view.findViewById(R.id.ib_top);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);
        initListener();
        return view;
    }

    public void initData() {
        super.initData();
        GoodsListAdapter adapter = new GoodsListAdapter(mContext, ProductRepository.getHomeProducts());
        lv_home.setAdapter(adapter);
    }

    private void initListener() {
        ib_top.setOnClickListener(v -> lv_home.smoothScrollToPosition(0));
        tv_search_home.setOnClickListener(v -> showSearchInputDialog());
        tv_message_home.setOnClickListener(v ->
                startActivity(new Intent(mContext, AIAssistantActivity.class)));
    }

    private void showSearchInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("AI 需求输入");

        final EditText input = new EditText(mContext);
        input.setHint("例如：预算3000学习平板 / 出差续航");
        builder.setView(input);

        builder.setPositiveButton("让AI推荐", (dialog, which) -> {
            String searchText = input.getText().toString().trim();
            if (!searchText.isEmpty()) {
                openAiAssistant(searchText);
            }
        });

        builder.setNegativeButton("取消", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void openAiAssistant(String need) {
        Intent intent = new Intent(mContext, AIAssistantActivity.class);
        intent.putExtra("need", need);
        startActivity(intent);
    }
}
