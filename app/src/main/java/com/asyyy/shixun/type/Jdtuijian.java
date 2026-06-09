package com.asyyy.shixun.type;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.asyyy.shixun.R;
import com.asyyy.shixun.base.BaseFragment;
import com.asyyy.shixun.data.ProductRepository;
import com.asyyy.shixun.home.Goods;
import com.asyyy.shixun.home.GoodsListAdapter;

import java.util.ArrayList;

public class Jdtuijian extends BaseFragment {
//    goodsListAdapter 到 goodsListAdapter6: 六个不同的商品列表适配器，用于显示不同分类的商品。
//    goodsList 到 goodsList6: 六个不同的商品列表，每个列表包含属于该分类的商品。
//    type_list: 用于显示商品列表的 ListView。
//    gridview_jd_r1: 用于显示商品分类的 GridView。
//    tv_jd_commr1: 用于显示分类标题的 TextView。
//    imagenames1 和 ids1: 分别存储商品分类的名称和对应的图片资源ID。!
    private GoodsListAdapter goodsListAdapter,goodsListAdapter2,goodsListAdapter3,
            goodsListAdapter4,goodsListAdapter5,goodsListAdapter6;
    private ArrayList<Goods> goodsList,goodsList2,goodsList3,goodsList4,
            goodsList5,goodsList6;
    private ListView type_list;
    private GridView gridview_jd_r1;
    private TextView tv_jd_commr1;
    private String[] imagenames1 = {"充电宝","空调","口红","平板电脑","眼影", "手机"};
    private int[] ids1 = {R.drawable.a105,R.drawable.a104,R.drawable.a103,
            R.drawable.a102,R.drawable.a101,R.drawable.a100};

//
//    initView(): 初始化视图的方法。
//            View.inflate(mContext, R.layout.type_grid, null): 加载布局文件 type_grid。
//    gridview_jd_r1, tv_jd_commr1, type_list: 通过 findViewById 获取布局文件中的控件实例。
//            gridview_jd_r1.setAdapter(new HomeAdapter1()): 为 GridView 设置适配器 HomeAdapter1。
//    GridListener(): 调用方法来设置 GridView 的点击事件监听器。!
    @Override
    public View initView() {
        //初始化界面
        View view = View.inflate(mContext, R.layout.type_grid,null);
        gridview_jd_r1 = view.findViewById(R.id.gridview_jd_r1);
        tv_jd_commr1 = view.findViewById(R.id.tv_jd_commr1);
        type_list = view.findViewById(R.id.type_list);
        gridview_jd_r1.setAdapter(new HomeAdapter1());

        GridListener();

        return view;
    }



//super.initData(): 调用父类的 initData 方法。
//    goodsList 到 goodsList6: 初始化六个不同的商品列表，每个列表包含属于该分类的商品。
//            goodsList.add(new Goods(...)): 向每个商品列表中添加商品对象。!
    public void initData(){
        super.initData();
        goodsList = ProductRepository.getRecommendCategoryProducts(0);
        goodsList2 = ProductRepository.getRecommendCategoryProducts(1);
        goodsList3 = ProductRepository.getRecommendCategoryProducts(2);
        goodsList4 = ProductRepository.getRecommendCategoryProducts(3);
        goodsList5 = ProductRepository.getRecommendCategoryProducts(4);
        goodsList6 = ProductRepository.getRecommendCategoryProducts(5);

        showGoodsList(goodsList);

    }
    //显示gridview内容


//    getCount(): 返回分类的数量。
//    getItem(): 返回指定位置的项目，这里返回 null。
//    getItemId(): 返回指定位置项目的ID，这里返回 0。
//    getView(): 创建或重用视图以显示分类项。设置分类标题、名称和图标。!
    public class HomeAdapter1 extends BaseAdapter {
        @Override
        public int getCount() {
            return imagenames1.length;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            tv_jd_commr1.setText("常用分类");
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.type_grid_item1, null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.ic_icon);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(imagenames1[position]);
            iv_icon.setImageResource(ids1[position]);
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
    //监听GridView点击事件


//    gridview_jd_r1.setOnItemClickListener: 为 GridView 设置点击事件监听器。
//            switch (index): 根据点击的位置选择相应的操作。
//            case 1: 如果点击第一个分类，实例化并设置适配器 goodsListAdapter，
//    并为 type_list 设置点击事件监听器。如果点击第一个商品，启动 ChatActivity。!
    private void GridListener(){
        gridview_jd_r1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position + 1;// id是从0开始的，所以需要+1
                switch (index){
                    case 1:
                        showGoodsList(goodsList);
                        break;
                    case 2:
                        showGoodsList(goodsList2);
                        break;
                    case 3:
                        showGoodsList(goodsList3);
                        break;
                    case 4:
                        showGoodsList(goodsList4);
                        break;
                    case 5:
                        showGoodsList(goodsList5);
                        break;
                    case 6:
                        showGoodsList(goodsList6);
                        break;
                        default:
                            break;
                }
                //Toast.makeText(getActivity(), "你按下了选项：" + index, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showGoodsList(ArrayList<Goods> goods) {
        GoodsListAdapter adapter = new GoodsListAdapter(mContext, goods);
        type_list.setAdapter(adapter);
    }

}
