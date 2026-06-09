package com.asyyy.shixun.data;

import com.asyyy.shixun.R;
import com.asyyy.shixun.home.Goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    public static final String GRID_SUPERMARKET = "supermarket";
    public static final String GRID_GLOBAL = "global";
    public static final String GRID_MEN = "men";
    public static final String GRID_WOMEN = "women";
    public static final String GRID_PHONE = "phone";

    private static final Map<Integer, Goods> PRODUCT_INDEX = new HashMap<>();

    public static ArrayList<Goods> getHomeProducts() {
        ArrayList<Goods> products = new ArrayList<>();
        add(products, new Goods(1, R.drawable.ipad, "iPad 2024 128G", "学习娱乐 / 轻办公", 2999.00, 2100, "Apple 产品旗舰店"));
        add(products, new Goods(2, R.drawable.ipad2, "iPad Air 11 英寸", "轻薄平板 / 网课笔记", 4599.00, 1680, "Apple 产品旗舰店"));
        add(products, new Goods(3, R.drawable.ipad3, "iPad Pro 11", "专业创作 / 影音办公", 6599.00, 1200, "Apple 产品旗舰店"));
        add(products, new Goods(4, R.drawable.xiaomi10, "小米 14", "性能手机 / 8+256G", 3799.00, 1900, "小米官方旗舰店"));
        add(products, new Goods(5, R.drawable.huaweip40, "华为 P40 5G", "拍照手机 / 12+512G", 4488.00, 1800, "华为官方旗舰店"));
        add(products, new Goods(6, R.drawable.a701, "荣耀 Magic 手机", "长续航 / 影像旗舰", 3999.00, 1430, "荣耀官方旗舰店"));
        add(products, new Goods(7, R.drawable.a702, "Redmi 游戏手机", "高刷屏 / 性能模式", 2299.00, 2600, "小米官方旗舰店"));
        add(products, new Goods(8, R.drawable.a703, "vivo 轻薄手机", "人像拍照 / 大内存", 2999.00, 1320, "vivo 官方旗舰店"));
        add(products, new Goods(9, R.drawable.c101, "小米移动电源 10000mAh", "轻便续航 / 双向快充", 79.00, 5000, "小米官方旗舰店"));
        add(products, new Goods(10, R.drawable.c102, "罗马仕 20000mAh 充电宝", "大容量 / 出差备用", 129.00, 4200, "罗马仕自营旗舰店"));
        add(products, new Goods(11, R.drawable.a601, "Sony 降噪耳机", "通勤降噪 / 长续航", 1299.00, 2100, "Sony 官方旗舰店"));
        add(products, new Goods(12, R.drawable.a602, "Redmi Buds 蓝牙耳机", "入耳降噪 / 高性价比", 199.00, 6800, "小米官方旗舰店"));
        add(products, new Goods(13, R.drawable.a603, "智能运动手表", "健康监测 / 运动记录", 899.00, 3200, "智能穿戴旗舰店"));
        add(products, new Goods(14, R.drawable.a301, "联想轻薄笔记本", "学习办公 / 16G内存", 4999.00, 880, "联想官方旗舰店"));
        add(products, new Goods(15, R.drawable.a302, "机械键盘 K87", "办公游戏 / 热插拔", 299.00, 2400, "数码外设旗舰店"));
        add(products, new Goods(16, R.drawable.a303, "便携蓝牙音箱", "露营聚会 / 低音增强", 259.00, 1700, "数码潮电旗舰店"));
        add(products, new Goods(17, R.drawable.c201, "格力 1.5匹空调", "一级能效 / 卧室静音", 2899.00, 14000, "格力官方旗舰店"));
        add(products, new Goods(18, R.drawable.c202, "美的变频空调", "快速冷暖 / 节能省电", 2399.00, 18000, "美的官方旗舰店"));
        add(products, new Goods(19, R.drawable.c203, "海尔智能洗衣机", "除菌洗 / 家庭大容量", 1899.00, 9000, "海尔官方旗舰店"));
        add(products, new Goods(20, R.drawable.a401, "米家扫地机器人", "自动清洁 / 智能规划", 1999.00, 2300, "小米官方旗舰店"));
        add(products, new Goods(21, R.drawable.a402, "空气炸锅 5L", "少油烹饪 / 家用小厨电", 399.00, 3100, "厨房电器旗舰店"));
        add(products, new Goods(22, R.drawable.a403, "高速吹风机", "速干护发 / 低噪音", 499.00, 2800, "个护电器旗舰店"));
        add(products, new Goods(23, R.drawable.tools8, "智能门锁 Pro", "指纹解锁 / 远程提醒", 1399.00, 1100, "智能家居旗舰店"));
        add(products, new Goods(24, R.drawable.tools9, "WiFi 7 路由器", "全屋覆盖 / 低延迟", 699.00, 1900, "网络设备旗舰店"));
        add(products, new Goods(25, R.drawable.anmuxi, "安慕希高端畅饮装", "健康饮品 / 家庭囤货", 59.90, 2400, "智购超市"));
        add(products, new Goods(26, R.drawable.a305, "每日坚果礼盒", "早餐加餐 / 送礼装", 129.00, 3600, "食品自营旗舰店"));
        add(products, new Goods(27, R.drawable.a306, "进口牛奶整箱", "早餐饮品 / 高钙蛋白", 89.00, 5200, "智购超市"));
        add(products, new Goods(28, R.drawable.img, "精品咖啡豆", "办公提神 / 中深烘焙", 78.00, 1600, "咖啡生活馆"));
        add(products, new Goods(29, R.drawable.img_1, "低脂鸡胸肉", "健身餐 / 即食轻食", 69.00, 2100, "健康食品旗舰店"));
        add(products, new Goods(30, R.drawable.img_2, "宠物猫粮 5kg", "成猫主粮 / 高蛋白", 159.00, 1700, "宠物生活馆"));
        add(products, new Goods(31, R.drawable.kouhong, "MAC 经典口红", "礼物 / 美妆", 188.00, 3000, "美妆官方店"));
        add(products, new Goods(32, R.drawable.c301, "Dior 哑光口红", "高级彩妆 / 经典色号", 330.00, 3900, "Dior 美妆旗舰店"));
        add(products, new Goods(33, R.drawable.c501, "完美日记眼影盘", "日常妆容 / 粉质细腻", 119.00, 5300, "完美日记官方店"));
        add(products, new Goods(34, R.drawable.tools10, "电动牙刷 Pro", "声波清洁 / 旅行便携", 249.00, 4100, "个护清洁旗舰店"));
        add(products, new Goods(35, R.drawable.tools11, "男士护肤套装", "洁面水乳 / 清爽控油", 199.00, 2300, "个护美妆旗舰店"));
        add(products, new Goods(36, R.drawable.tools12, "运动跑鞋", "缓震支撑 / 日常训练", 399.00, 2600, "运动户外旗舰店"));
        add(products, new Goods(37, R.drawable.bamai8, "BMW i8 跑车模型", "收藏玩具 / 潮流礼物", 99.00, 900, "潮玩旗舰店"));
        add(products, new Goods(38, R.drawable.peach_pic, "创意生日礼盒", "节日礼物 / 精致包装", 168.00, 1350, "礼品旗舰店"));
        return products;
    }

    public static ArrayList<Goods> getRecommendCategoryProducts(int index) {
        ArrayList<Goods> products = new ArrayList<>();
        switch (index) {
            case 0:
                add(products, new Goods(9, R.drawable.c101, "小米移动电源3", "10000mAh 快充版", 79.00, 2800, "小米官方旗舰店"));
                add(products, new Goods(10, R.drawable.c102, "小米移动电源3", "20000mAh USB-C双向快充版", 129.00, 21000, "小米官方旗舰店"));
                add(products, new Goods(11, R.drawable.c103, "罗马仕LT20", "20000mAh 大容量", 79.00, 3000, "罗马仕官方旗舰店"));
                break;
            case 1:
                add(products, new Goods(17, R.drawable.c201, "格力 1.5匹空调", "一级能效 / 卧室静音", 2899.00, 14000, "格力官方旗舰店"));
                add(products, new Goods(18, R.drawable.c202, "美的变频空调", "1.5匹 变频智弧", 2099.00, 18000, "美的官方旗舰店"));
                add(products, new Goods(19, R.drawable.c203, "海尔智能空调", "1.5匹 变频自清洁", 1899.00, 9000, "海尔官方旗舰店"));
                break;
            case 2:
                add(products, new Goods(32, R.drawable.c301, "Dior 哑光口红", "高级彩妆 / 经典色号", 330.00, 390000, "Dior 美妆旗舰店"));
                add(products, new Goods(39, R.drawable.c302, "纪梵希小羊皮306", "礼物美妆 / 经典口红", 338.00, 18000, "纪梵希美妆旗舰店"));
                add(products, new Goods(40, R.drawable.c303, "圣罗兰复古哑光", "高级彩妆 / 气质色号", 335.00, 10000, "圣罗兰美妆旗舰店"));
                break;
            case 3:
                add(products, new Goods(1, R.drawable.ipad, "iPad 2024 128G", "学习娱乐 / 轻办公", 2999.00, 2100, "Apple 产品旗舰店"));
                add(products, new Goods(2, R.drawable.ipad2, "iPad Air 11 英寸", "轻薄平板 / 网课笔记", 4599.00, 1680, "Apple 产品旗舰店"));
                add(products, new Goods(3, R.drawable.ipad3, "iPad Pro 11", "专业创作 / 影音办公", 6599.00, 1200, "Apple 产品旗舰店"));
                break;
            case 4:
                add(products, new Goods(33, R.drawable.c501, "完美日记眼影盘", "十二色 / 粉质细腻", 119.00, 5300, "完美日记官方旗舰店"));
                break;
            case 5:
                add(products, new Goods(4, R.drawable.xiaomi10, "小米 14", "蓝色 8+256G", 3799.00, 1900, "小米官方旗舰店"));
                add(products, new Goods(5, R.drawable.huaweip40, "华为 P40 5G", "白色 12+512G", 4488.00, 1800, "华为官方旗舰店"));
                add(products, new Goods(6, R.drawable.a701, "荣耀 Magic 手机", "长续航 / 影像旗舰", 3999.00, 1430, "荣耀官方旗舰店"));
                break;
            default:
                return getHomeProducts();
        }
        return products;
    }

    public static Goods[] getGridProducts(String section) {
        if (GRID_SUPERMARKET.equals(section)) {
            return new Goods[]{
                    product(14), new Goods(102, R.drawable.a302, "通勤休闲外套", "舒适百搭 / 四季可穿", 299.00, 2400, "服饰自营旗舰店"),
                    new Goods(103, R.drawable.a303, "精选生鲜礼盒", "家庭囤货 / 新鲜直达", 168.00, 3200, "智购生鲜"),
                    new Goods(104, R.drawable.a304, "效率提升图书套装", "学习成长 / 职场进阶", 89.00, 1800, "智购图书"),
                    product(26), product(27)
            };
        }
        if (GRID_GLOBAL.equals(section)) {
            return new Goods[]{
                    product(20), product(21), product(22)
            };
        }
        if (GRID_MEN.equals(section)) {
            return new Goods[]{
                    new Goods(301, R.drawable.a501, "男装馆精选夹克", "通勤休闲 / 防风百搭", 399.00, 2100, "男装馆"),
                    new Goods(302, R.drawable.a502, "国际大牌商务外套", "商务通勤 / 质感面料", 899.00, 860, "国际男装旗舰店"),
                    new Goods(303, R.drawable.a503, "品质大牌休闲裤", "修身版型 / 舒适耐穿", 299.00, 1700, "品质男装馆"),
                    new Goods(304, R.drawable.a504, "纯棉基础 T 恤", "透气亲肤 / 日常百搭", 99.00, 5200, "男装自营旗舰店"),
                    new Goods(305, R.drawable.a505, "免烫商务衬衫", "上班通勤 / 易打理", 159.00, 3300, "商务男装旗舰店"),
                    new Goods(306, R.drawable.a506, "男士套装两件套", "省心搭配 / 场景完整", 499.00, 1200, "男装套装馆")
            };
        }
        if (GRID_WOMEN.equals(section)) {
            return new Goods[]{
                    new Goods(401, R.drawable.a601, "娃娃领衬衫", "甜美通勤 / 轻薄舒适", 169.00, 2300, "女装精选馆"),
                    new Goods(402, R.drawable.a602, "高腰休闲短裤", "夏季清爽 / 显高版型", 129.00, 1800, "女装自营旗舰店"),
                    new Goods(403, R.drawable.a603, "衬衫连衣裙", "约会通勤 / 一件成套", 259.00, 1600, "女装趋势馆")
            };
        }
        if (GRID_PHONE.equals(section)) {
            return new Goods[]{
                    product(6), product(7), product(8),
                    new Goods(504, R.drawable.a704, "长续航商务手机", "大电池 / 快充", 2699.00, 2100, "手机数码旗舰店"),
                    new Goods(505, R.drawable.a705, "拍照旗舰手机", "夜景人像 / 影像算法", 4488.00, 1800, "影像手机旗舰店"),
                    new Goods(506, R.drawable.a706, "手机延保服务", "碎屏保障 / 换新无忧", 199.00, 9000, "手机服务中心")
            };
        }
        return new Goods[0];
    }

    public static Goods findById(int id) {
        ensureIndex();
        return PRODUCT_INDEX.get(id);
    }

    private static Goods product(int id) {
        Goods goods = findById(id);
        return goods == null ? new Goods() : goods;
    }

    private static void add(ArrayList<Goods> products, Goods goods) {
        products.add(goods);
        PRODUCT_INDEX.put(goods.getG_id(), goods);
    }

    private static void ensureIndex() {
        if (PRODUCT_INDEX.isEmpty()) {
            getHomeProducts();
        }
    }
}
