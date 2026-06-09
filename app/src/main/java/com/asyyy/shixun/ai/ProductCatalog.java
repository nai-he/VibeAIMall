package com.asyyy.shixun.ai;

import com.asyyy.shixun.data.ProductRepository;
import com.asyyy.shixun.home.Goods;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    public static List<RecommendationProduct> getProducts() {
        List<RecommendationProduct> products = new ArrayList<>();

        products.add(build(1, "tablet", tags("study", "daily", "office", "tablet"),
                "覆盖学习、网课、笔记和轻办公场景，预算压力相对可控。",
                "如果需要键盘和手写笔，可以优先确认配件预算。"));
        products.add(build(3, "tablet", tags("study", "office", "creative", "tablet"),
                "更适合有绘画、剪辑、重度笔记需求的用户，性能余量更大。",
                "如果只是上网课，建议回到 iPad 2024 这类更高性价比选择。"));
        products.add(build(4, "phone", tags("phone", "performance", "daily", "office"),
                "兼顾性能、拍照和日常使用，适合换机用户作为均衡选择。",
                "如果重度拍照，可以对比华为 P40 5G 的影像取向。"));
        products.add(build(5, "phone", tags("phone", "photo", "5g", "daily"),
                "更偏影像和通信体验，适合明确提到拍照、5G、换机的需求。",
                "如果预算更低，可以考虑性能更均衡的小米 14。"));
        products.add(build(9, "power", tags("travel", "commute", "power", "daily"),
                "轻便、低价、高频，适合出差、通勤、校园外出时补足续航。",
                "如果多设备同时充电，可以考虑更大容量版本。"));
        products.add(build(10, "power", tags("travel", "power", "high_capacity"),
                "容量更大，适合长途差旅和多设备用户。",
                "重量会比 10000mAh 款更高，通勤场景要权衡便携性。"));
        products.add(build(11, "headphones", tags("commute", "travel", "office", "headphones"),
                "降噪耳机能明显改善地铁、办公室和差旅环境里的体验。",
                "如果预算有限，可以选择入门蓝牙耳机作为替代。"));
        products.add(build(13, "wearable", tags("health", "sport", "daily", "wearable"),
                "适合关注心率、睡眠、跑步和日常提醒的用户。",
                "购买前建议确认续航、运动模式和手机系统兼容性。"));
        products.add(build(14, "laptop", tags("office", "study", "laptop", "coding"),
                "适合论文、表格、编程和移动办公，16G 内存能覆盖更长使用周期。",
                "如果有剪辑需求，建议进一步关注显卡和散热。"));
        products.add(build(17, "home_appliance", tags("home", "appliance", "summer"),
                "适合卧室和小客厅，一级能效更适合长期使用。",
                "下单前建议确认房间面积、安装环境和安装服务。"));
        products.add(build(20, "home_appliance", tags("home", "smart_home", "clean"),
                "能减少日常家务时间，适合希望提升生活效率的用户。",
                "建议关注避障能力、拖地水箱和耗材成本。"));
        products.add(build(21, "home_appliance", tags("home", "kitchen", "daily"),
                "适合低门槛做薯条、鸡翅和烤物，家庭使用频率高。",
                "家庭使用建议选 4L 以上容量。"));
        products.add(build(31, "gift_beauty", tags("gift", "beauty", "birthday"),
                "送礼决策成本低、表达属性强，适合生日和节日场景。",
                "下单前可以再确认色号和收礼人的日常风格。"));
        products.add(build(34, "personal_care", tags("daily", "travel", "personal_care"),
                "个护产品使用频率高，适合作为自用升级或实用型礼物。",
                "建议关注刷头耗材价格和续航。"));
        products.add(build(36, "sport", tags("health", "sport", "commute"),
                "适合日常训练、健身和长时间走路，兼顾缓震和通勤。",
                "建议按脚型选择支撑或缓震取向。"));
        products.add(build(25, "food", tags("daily", "food", "home"),
                "日常消耗品复购频率高，适合早餐、轻食和家庭囤货。",
                "可以关注多件优惠和满减活动。"));

        return products;
    }

    private static RecommendationProduct build(int goodsId, String category, String[] tags,
                                               String reason, String nextStep) {
        Goods goods = ProductRepository.findById(goodsId);
        return new RecommendationProduct(
                goods.getG_id(),
                goods.getG_photo(),
                goods.getG_name(),
                goods.getG_type(),
                goods.getG_price(),
                goods.getG_sales(),
                goods.getG_shop(),
                category,
                tags,
                reason,
                nextStep
        );
    }

    private static String[] tags(String... values) {
        return values;
    }
}
