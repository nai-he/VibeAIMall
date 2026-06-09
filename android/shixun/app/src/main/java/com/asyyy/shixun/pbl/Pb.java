//package com.asyyy.shixun.pbl;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.StaggeredGridLayoutManager;
//
//import com.asyyy.shixun.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class Pb extends Fragment {
//    private View rootView;
//    private Banner banner;
//    private List<BannerDataInfo> mBannerDataInfos =new ArrayList<>();
//    private RecyclerView recyclerView;
//    private WaterfallAdapter adapter;
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        rootView= inflater.inflate(R.layout.p1, container, false);
//
//
//        //初始化控件
//        banner =rootView.findViewById(R.id.banner);
//
//        //模拟数据
//        mBannerDataInfos.add(new BannerDataInfo(R.mipmap.lunboone,"标题一"));
//        mBannerDataInfos.add(new BannerDataInfo(R.mipmap.lunbotwo,"标题二"));
//        mBannerDataInfos.add(new BannerDataInfo(R.mipmap.lunbothree,"标题三"));
//        mBannerDataInfos.add(new BannerDataInfo(R.mipmap.lunbofour,"标题四"));
//        mBannerDataInfos.add(new BannerDataInfo(R.mipmap.lunbofive,"标题四"));
//
//        //设置Adapter
//        banner.setAdapter(new BannerImageAdapter<BannerDataInfo>(mBannerDataInfos) {
//
//
//            @Override
//            public void onBindView(BannerImageHolder bannerImageHolder, BannerDataInfo bannerDataInfo, int i, int i1) {
//                //设置数据
//                bannerImageHolder.imageView.setImageResource(bannerDataInfo.getImg());
//
//            }
//        })
//                .addBannerLifecycleObserver(this)//添加生命周期观察者
//                // 传入正确的上下文，这里可以使用 getContext() 获取 Fragment 关联的上下文
//                .setIndicator(new CircleIndicator(getContext()));
//
//       //画廊效果
//        banner.setBannerGalleryEffect(10,10);
//
//
//        recyclerView = rootView.findViewById(R.id.recyclerView);
//
//        // 创建数据列表
//        List<ImageInfo> imageItemList = new ArrayList<>();
//        imageItemList.add(new ImageInfo(R.mipmap.one, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.two, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.three, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.fifteen, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.four, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.five, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.six, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.seven, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.eight, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.night, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.ten, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.eleven, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.twelve, " "));
//        imageItemList.add(new ImageInfo(R.mipmap.thirteen, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.fourteen, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.sixteen, " "));
//        imageItemList.add(new ImageInfo(R.mipmap.seventeen, ""));
//        imageItemList.add(new ImageInfo(R.mipmap.eighteen, "“));}
//        // 可继续添加更多图片数据
//
//
//        // 创建适配器并传入上下文和数据列表
//        adapter = new WaterfallAdapter(getContext(), imageItemList);
//
//        // 设置布局管理器为瀑布流布局
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // 设置适配器
//        recyclerView.setAdapter(adapter);
//
//        return  rootView;
//    }
//}