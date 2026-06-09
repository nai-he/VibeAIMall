package com.asyyy.shixun.type;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.asyyy.shixun.R;
import com.asyyy.shixun.base.BaseFragment;

import java.util.ArrayList;
//通过 RadioGroup 控制不同分类
public class TypeFragment extends BaseFragment {
//    TextView 类型的多个变量，用于表示不同的文本视图。
//    RadioGroup 类型的变量，用于表示单选按钮组。
//    ArrayList<BaseFragment> 类型的变量，用于存储多个 BaseFragment 实例。
//    int 类型的变量 position，用于记录当前选中的位置。
//    BaseFragment 类型的变量 tempFragment，用于临时存储当前的 BaseFragment 实例。

    private TextView rb_commf1,rb_commf2,rb_commf3,rb_commf4,rb_commf5,rb_commf6;
    private RadioGroup rg_type;

    private ArrayList<BaseFragment> fragments;//定义一个列表
    private int position = 0;
    private BaseFragment tempFragment;

//    重写 initView 方法：
//
//    使用 View.inflate 方法加载布局文件 type_home。
//    通过 findViewById 方法获取 RadioGroup 控件。
//    调用 initFragment 方法初始化片段。
//    调用 initListener 方法设置监听器。
//    默认选中第一个单选按钮。
//    返回加载的视图。！
    @Override
    public View initView() {
        //初始化界面
        View view = View.inflate(mContext, R.layout.type_home,null);
        rg_type = view.findViewById(R.id.rg_type);
        initFragment();
        initListener();
        rg_type.check(R.id.rb_commf1);

        return view;
    }
//    重写 initData 方法，调用父类的 initData 方法。
    public void initData(){
        super.initData();
    }

//    初始化片段的方法：
//    创建一个新的 ArrayList 实例。集合
//    向列表中添加多个 BaseFragment 子类的实例。！
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new Jdtuijian());
        fragments.add(new JdShoping());
        fragments.add(new Guoji());
        fragments.add(new Nanzhuang());
        fragments.add(new Nvzhuang());
        fragments.add(new ShoujiS());
    }


//    为 RadioGroup 设置一个 OnCheckedChangeListener。
//    根据选中的单选按钮 ID 更新 position 的值。
//    调用 getFragment 方法获取对应的 BaseFragment 实例。
//    调用 switchFragment 方法切换到新的片段。！
    //设置监听
    private void initListener(){
        rg_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_commf1:
                        position = 0;
                        break;
                    case R.id.rb_commf2:
                        position = 1;
                        break;
                    case R.id.rb_commf3:
                        position = 2;
                        break;
                    case R.id.rb_commf4:
                        position = 3;
                        break;
                    case R.id.rb_commf5:
                        position = 4;
                        break;
                    case R.id.rb_commf6:
                        position = 5;
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment,baseFragment);
            }
        });
    }
    //得到fragment

//    根据位置获取片段的方法：
//    如果 fragments 列表不为空且大小大于0，则返回对应位置的 BaseFragment 实例。
//    否则返回 null。！

    private BaseFragment getFragment(int position){
        if (fragments != null && fragments.size() > 0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }


//    切换片段的方法：
//    如果当前片段与目标片段不同，则进行切换。
//    更新 tempFragment 为新的目标片段。
//    如果目标片段不为空，则开始一个 FragmentTransaction。
//    如果目标片段未被添加，则隐藏当前片段并添加目标片段。
//    如果目标片段已被添加，则隐藏当前片段并显示目标片段。！
    //切换fragment
    private void switchFragment (Fragment fromfragment, BaseFragment nextfragment){

        if (tempFragment != nextfragment){
            tempFragment = nextfragment;
            if (nextfragment != null){
                //开启事务
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                //判断nextFragment是否有添加
                if (!nextfragment.isAdded()){
                    //隐藏当前的fragment
                    if (fromfragment != null){
                        transaction.hide(fromfragment);
                    }
                    transaction.add(R.id.frameLayout_type,nextfragment).commit();
                }else{
                    if (fromfragment != null){
                        transaction.hide(fromfragment);
                    }
                    transaction.show(nextfragment).commit();
                }
            }
        }
    }
}
