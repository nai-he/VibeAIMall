package com.asyyy.shixun.type;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.asyyy.shixun.R;
import com.asyyy.shixun.base.BaseFragment;
import com.asyyy.shixun.data.ProductRepository;
import com.asyyy.shixun.home.Goods;

public class ShoujiS extends BaseFragment {
    private GridView gridview_jd_r1;
    private TextView tv_jd_commr1;
    private String[] imagenames6 = {"5G手机","游戏手机","全面屏手机","长续航手机","拍照手机","手机服务"};
    private int[] ids6 = {R.drawable.a701,R.drawable.a702,R.drawable.a703,
            R.drawable.a704,R.drawable.a705,R.drawable.a706};
    private Goods[] products = ProductRepository.getGridProducts(ProductRepository.GRID_PHONE);

    @Override
    public View initView() {
        //初始化界面
        View view = View.inflate(mContext, R.layout.type_grid,null);
        gridview_jd_r1 = view.findViewById(R.id.gridview_jd_r1);
        tv_jd_commr1 = view.findViewById(R.id.tv_jd_commr1);
        gridview_jd_r1.setAdapter(new HomeAdapter6());
        gridview_jd_r1.setOnItemClickListener((parent, itemView, position, id) ->
                ProductNavigator.openDetail(mContext, products[position]));
        return view;
    }
    public void initData(){
        super.initData();
    }
    //显示gridview内容
    public class HomeAdapter6 extends BaseAdapter {
        @Override
        public int getCount() {
            return imagenames6.length;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            tv_jd_commr1.setText("爆款推荐");
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.type_grid_item1, null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.ic_icon);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(imagenames6[position]);
            iv_icon.setImageResource(ids6[position]);
            return view;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
