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

public class Guoji extends BaseFragment {
    private GridView gridview_jd_r1;
    private TextView tv_jd_commr1;
    private String[] imagenames3 = {"韩国馆","美国馆","日本馆"};
    private int[] ids3 = {R.drawable.a401,R.drawable.a402,R.drawable.a403,};
    private Goods[] products = ProductRepository.getGridProducts(ProductRepository.GRID_GLOBAL);

    @Override
    public View initView() {
        //初始化界面
        View view = View.inflate(mContext, R.layout.type_grid,null);
        gridview_jd_r1 = view.findViewById(R.id.gridview_jd_r1);
        tv_jd_commr1 = view.findViewById(R.id.tv_jd_commr1);
        gridview_jd_r1.setAdapter(new HomeAdapter3());
        gridview_jd_r1.setOnItemClickListener((parent, itemView, position, id) ->
                ProductNavigator.openDetail(mContext, products[position]));
        return view;
    }
    public void initData(){
        super.initData();
    }
    //显示gridview内容
    public class HomeAdapter3 extends BaseAdapter {
        @Override
        public int getCount() {
            return imagenames3.length;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            tv_jd_commr1.setText("特色馆区");
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.type_grid_item1, null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.ic_icon);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(imagenames3[position]);
            iv_icon.setImageResource(ids3[position]);
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
