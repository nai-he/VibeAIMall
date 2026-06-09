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

public class Nanzhuang extends BaseFragment {
    private GridView gridview_jd_r1;
    private TextView tv_jd_commr1;
    private String[] imagenames4 = {"男装馆","国际大牌","品质大牌","T恤","衬衫","套装"};
    private int[] ids4 = {R.drawable.a501,R.drawable.a502,R.drawable.a503,
            R.drawable.a504,R.drawable.a505,R.drawable.a506};
    private Goods[] products = ProductRepository.getGridProducts(ProductRepository.GRID_MEN);

    @Override
    public View initView() {
        //初始化界面
        View view = View.inflate(mContext, R.layout.type_grid,null);
        gridview_jd_r1 = view.findViewById(R.id.gridview_jd_r1);
        tv_jd_commr1 = view.findViewById(R.id.tv_jd_commr1);
        gridview_jd_r1.setAdapter(new HomeAdapter4());
        gridview_jd_r1.setOnItemClickListener((parent, itemView, position, id) ->
                ProductNavigator.openDetail(mContext, products[position]));
        return view;
    }
    public void initData(){
        super.initData();
    }
    //显示gridview内容
    public class HomeAdapter4 extends BaseAdapter {
        @Override
        public int getCount() {
            return imagenames4.length;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            tv_jd_commr1.setText("专场推荐");
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.type_grid_item1, null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.ic_icon);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(imagenames4[position]);
            iv_icon.setImageResource(ids4[position]);
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
