const products = [
  product(1, "iPad 2024 128G", "tablet", ["study", "daily", "office", "tablet"], 2999, 2100, "Apple 产品旗舰店", "./assets/ipad.jpg", "学习娱乐 / 轻办公", "覆盖学习、网课、笔记和轻办公场景，预算压力相对可控。"),
  product(2, "iPad Air 11 英寸", "tablet", ["study", "office", "tablet"], 4599, 1680, "Apple 产品旗舰店", "./assets/ipad2.jpg", "轻薄平板 / 网课笔记", "更轻薄，适合上课、记笔记和移动办公。"),
  product(3, "iPad Pro 11", "tablet", ["study", "office", "creative", "tablet"], 6599, 1200, "Apple 产品旗舰店", "./assets/ipad3.jpg", "专业创作 / 影音办公", "更适合绘画、剪辑和重度笔记需求，性能余量更大。"),
  product(4, "小米 14", "phone", ["phone", "performance", "daily", "office"], 3799, 1900, "小米官方旗舰店", "./assets/xiaomi10.jpg", "性能手机 / 8+256G", "兼顾性能、拍照和日常使用，适合换机用户作为均衡选择。"),
  product(5, "华为 P40 5G", "phone", ["phone", "photo", "5g", "daily"], 4488, 1800, "华为官方旗舰店", "./assets/huaweip40.jpg", "拍照手机 / 12+512G", "更偏影像和通信体验，适合明确提到拍照、5G、换机的需求。"),
  product(6, "荣耀 Magic 手机", "phone", ["phone", "photo", "power"], 3999, 1430, "荣耀官方旗舰店", "./assets/a701.png", "长续航 / 影像旗舰", "长续航和影像能力均衡，适合重度日常使用。"),
  product(7, "Redmi 游戏手机", "phone", ["phone", "game", "performance"], 2299, 2600, "小米官方旗舰店", "./assets/a702.png", "高刷屏 / 性能模式", "价格更友好，适合游戏和高刷需求。"),
  product(8, "vivo 轻薄手机", "phone", ["phone", "photo", "daily"], 2999, 1320, "vivo 官方旗舰店", "./assets/a703.png", "人像拍照 / 大内存", "更适合自拍、人像和轻薄手感偏好。"),
  product(9, "小米移动电源 10000mAh", "power", ["travel", "commute", "power", "daily"], 79, 5000, "小米官方旗舰店", "./assets/c101.png", "轻便续航 / 双向快充", "适合出差、通勤、校园外出时补足续航，购买决策成本低。"),
  product(10, "罗马仕 20000mAh 充电宝", "power", ["travel", "power", "high_capacity"], 129, 4200, "罗马仕自营旗舰店", "./assets/c102.png", "大容量 / 出差备用", "容量更大，适合长途差旅和多设备用户。"),
  product(11, "Sony 降噪耳机", "headphones", ["commute", "travel", "office", "headphones"], 1299, 2100, "Sony 官方旗舰店", "./assets/a601.png", "通勤降噪 / 长续航", "能明显改善地铁、办公室和差旅环境里的体验。"),
  product(12, "Redmi Buds 蓝牙耳机", "headphones", ["commute", "daily", "headphones"], 199, 6800, "小米官方旗舰店", "./assets/a602.png", "入耳降噪 / 高性价比", "预算友好，适合日常通勤和备用耳机。"),
  product(13, "智能运动手表", "wearable", ["sport", "health", "daily"], 899, 3200, "智能穿戴旗舰店", "./assets/a603.png", "健康监测 / 运动记录", "适合运动记录、健康提醒和轻量通知。"),
  product(14, "联想轻薄笔记本", "laptop", ["office", "study", "laptop", "coding"], 4999, 880, "联想官方旗舰店", "./assets/a301.png", "学习办公 / 16G内存", "适合论文、表格、编程和移动办公，16G 内存能覆盖更长使用周期。"),
  product(15, "机械键盘 K87", "digital", ["office", "game", "coding"], 299, 2400, "数码外设旗舰店", "./assets/a302.png", "办公游戏 / 热插拔", "适合写代码、办公和桌面改造。"),
  product(16, "便携蓝牙音箱", "digital", ["travel", "daily", "audio"], 259, 1700, "数码潮电旗舰店", "./assets/a303.png", "露营聚会 / 低音增强", "适合露营、聚会和宿舍场景。"),
  product(17, "格力 1.5匹空调", "home_appliance", ["home", "appliance", "summer"], 2899, 14000, "格力官方旗舰店", "./assets/c201.png", "一级能效 / 卧室静音", "适合卧室和小客厅，一级能效更适合长期使用。"),
  product(18, "美的变频空调", "home_appliance", ["home", "appliance", "summer"], 2399, 18000, "美的官方旗舰店", "./assets/c202.png", "快速冷暖 / 节能省电", "销量高，适合追求性价比和安装效率的用户。"),
  product(19, "海尔智能洗衣机", "home_appliance", ["home", "clean", "appliance"], 1899, 9000, "海尔官方旗舰店", "./assets/c203.png", "除菌洗 / 家庭大容量", "适合家庭大容量洗护和除菌需求。"),
  product(20, "米家扫地机器人", "home_appliance", ["home", "smart_home", "clean"], 1999, 2300, "小米官方旗舰店", "./assets/a401.png", "自动清洁 / 智能规划", "能减少日常家务时间，适合希望提升生活效率的用户。"),
  product(21, "空气炸锅 5L", "home_appliance", ["home", "kitchen", "daily"], 399, 3100, "厨房电器旗舰店", "./assets/a402.png", "少油烹饪 / 家用小厨电", "适合低门槛做薯条、鸡翅和烤物，家庭使用频率高。"),
  product(22, "高速吹风机", "home_appliance", ["home", "beauty", "daily"], 499, 2800, "个护电器旗舰店", "./assets/a403.png", "速干护发 / 低噪音", "适合宿舍、家庭和通勤前快速打理。"),
  product(23, "智能门锁 Pro", "smart_home", ["home", "smart_home"], 1399, 1100, "智能家居旗舰店", "./assets/tools8.png", "指纹解锁 / 远程提醒", "适合租房升级和家庭安全场景。"),
  product(24, "WiFi 7 路由器", "digital", ["home", "office", "network"], 699, 1900, "网络设备旗舰店", "./assets/tools9.png", "全屋覆盖 / 低延迟", "适合家庭网络升级、游戏和远程办公。"),
  product(25, "安慕希高端畅饮装", "supermarket", ["food", "home", "daily"], 59.9, 2400, "智购超市", "./assets/anmuxi.jpg", "健康饮品 / 家庭囤货", "适合家庭囤货和早餐搭配。"),
  product(26, "每日坚果礼盒", "supermarket", ["food", "gift", "daily"], 129, 3600, "食品自营旗舰店", "./assets/a305.png", "早餐加餐 / 送礼装", "低风险送礼选择，也适合日常加餐。"),
  product(27, "进口牛奶整箱", "supermarket", ["food", "daily", "home"], 89, 5200, "智购超市", "./assets/a306.png", "早餐饮品 / 高钙蛋白", "适合家庭早餐和学生补充营养。"),
  product(28, "精品咖啡豆", "supermarket", ["food", "office", "daily"], 78, 1600, "咖啡生活馆", "./assets/img.png", "办公提神 / 中深烘焙", "适合办公室和居家咖啡需求。"),
  product(29, "低脂鸡胸肉", "supermarket", ["food", "sport", "daily"], 69, 2100, "健康食品旗舰店", "./assets/img_1.png", "健身餐 / 即食轻食", "适合健身、控脂和工作日晚餐。"),
  product(30, "宠物猫粮 5kg", "supermarket", ["pet", "daily", "home"], 159, 1700, "宠物生活馆", "./assets/img_2.png", "成猫主粮 / 高蛋白", "适合家有宠物的周期性囤货。"),
  product(31, "MAC 经典口红", "gift_beauty", ["gift", "beauty", "birthday"], 188, 3000, "美妆官方店", "./assets/kouhong.jpg", "礼物 / 美妆", "送礼决策成本低、表达属性强，适合生日和节日场景。"),
  product(32, "Dior 哑光口红", "gift_beauty", ["gift", "beauty", "global"], 330, 3900, "Dior 美妆旗舰店", "./assets/c301.png", "高级彩妆 / 经典色号", "品牌感更强，适合预算稍高的送礼场景。"),
  product(33, "完美日记眼影盘", "gift_beauty", ["beauty", "daily", "gift"], 119, 5300, "完美日记官方店", "./assets/c501.png", "日常妆容 / 粉质细腻", "价格友好，适合入门彩妆和礼物备选。"),
  product(34, "电动牙刷 Pro", "personal_care", ["daily", "travel", "home"], 249, 4100, "个护清洁旗舰店", "./assets/tools10.png", "声波清洁 / 旅行便携", "适合自用和生活方式类送礼。"),
  product(35, "男士护肤套装", "gift_beauty", ["beauty", "men", "daily"], 199, 2300, "个护美妆旗舰店", "./assets/tools11.png", "洁面水乳 / 清爽控油", "适合男士日常护理和礼物。"),
  product(36, "运动跑鞋", "sports", ["sport", "daily", "gift"], 399, 2600, "运动户外旗舰店", "./assets/tools12.png", "缓震支撑 / 日常训练", "适合跑步、通勤和日常训练。"),
  product(37, "BMW i8 跑车模型", "gift", ["gift", "toy", "birthday"], 99, 900, "潮玩旗舰店", "./assets/bamai8.jpg", "收藏玩具 / 潮流礼物", "适合模型收藏和低预算惊喜礼物。"),
  product(38, "创意生日礼盒", "gift", ["gift", "birthday", "daily"], 168, 1350, "礼品旗舰店", "./assets/peach_pic.png", "节日礼物 / 精致包装", "包装完整，适合快速完成送礼决策。"),
  product(39, "纪梵希小羊皮306", "gift_beauty", ["gift", "beauty", "global"], 338, 18000, "纪梵希美妆旗舰店", "./assets/c302.png", "礼物美妆 / 经典口红", "品牌认知强，适合重要节日礼物。"),
  product(40, "圣罗兰复古哑光", "gift_beauty", ["gift", "beauty", "global"], 335, 10000, "圣罗兰美妆旗舰店", "./assets/c303.png", "高级彩妆 / 气质色号", "适合更强调质感和仪式感的送礼场景。"),
  product(102, "通勤休闲外套", "men", ["men", "commute", "daily"], 299, 2400, "服饰自营旗舰店", "./assets/a302.png", "舒适百搭 / 四季可穿", "通勤和日常都能覆盖，试错成本低。"),
  product(103, "精选生鲜礼盒", "supermarket", ["food", "gift", "home"], 168, 3200, "智购生鲜", "./assets/a303.png", "家庭囤货 / 新鲜直达", "适合家庭聚餐和节日走访。"),
  product(104, "效率提升图书套装", "book", ["study", "office", "gift"], 89, 1800, "智购图书", "./assets/a304.png", "学习成长 / 职场进阶", "适合学生和职场成长需求。"),
  product(301, "男装馆精选夹克", "men", ["men", "commute", "daily"], 399, 2100, "男装馆", "./assets/a501.png", "通勤休闲 / 防风百搭", "适合通勤和春秋季日常。"),
  product(302, "国际大牌商务外套", "men", ["men", "global", "office"], 899, 860, "国际男装旗舰店", "./assets/a502.png", "商务通勤 / 质感面料", "适合商务场景和正式通勤。"),
  product(303, "品质大牌休闲裤", "men", ["men", "daily", "global"], 299, 1700, "品质男装馆", "./assets/a503.png", "修身版型 / 舒适耐穿", "百搭属性强，适合日常搭配。"),
  product(304, "纯棉基础 T 恤", "men", ["men", "daily"], 99, 5200, "男装自营旗舰店", "./assets/a504.png", "透气亲肤 / 日常百搭", "低价高频基础款。"),
  product(305, "免烫商务衬衫", "men", ["men", "office", "commute"], 159, 3300, "商务男装旗舰店", "./assets/a505.png", "上班通勤 / 易打理", "适合上班族和面试场景。"),
  product(306, "男士套装两件套", "men", ["men", "office", "daily"], 499, 1200, "男装套装馆", "./assets/a506.png", "省心搭配 / 场景完整", "减少搭配成本，适合快速成套购买。"),
  product(401, "娃娃领衬衫", "women", ["women", "commute", "daily"], 169, 2300, "女装精选馆", "./assets/a601.png", "甜美通勤 / 轻薄舒适", "适合通勤和日常约会。"),
  product(402, "高腰休闲短裤", "women", ["women", "summer", "daily"], 129, 1800, "女装自营旗舰店", "./assets/a602.png", "夏季清爽 / 显高版型", "适合夏季通勤和周末出行。"),
  product(403, "衬衫连衣裙", "women", ["women", "commute", "daily"], 259, 1600, "女装趋势馆", "./assets/a603.png", "约会通勤 / 一件成套", "一件成套，适合减少搭配时间。"),
  product(504, "长续航商务手机", "phone", ["phone", "power", "office"], 2699, 2100, "手机数码旗舰店", "./assets/a704.png", "大电池 / 快充", "适合外勤、出差和长续航偏好。"),
  product(505, "拍照旗舰手机", "phone", ["phone", "photo", "global"], 4488, 1800, "影像手机旗舰店", "./assets/a705.png", "夜景人像 / 影像算法", "适合拍照、人像和短视频创作。"),
  product(506, "手机延保服务", "phone_service", ["phone", "service"], 199, 9000, "手机服务中心", "./assets/a706.png", "碎屏保障 / 换新无忧", "适合手机购买后的保障加购。")
];

const categories = [
  ["all", "全部"],
  ["tablet", "平板"],
  ["phone", "手机"],
  ["power", "充电宝"],
  ["headphones", "耳机"],
  ["laptop", "电脑"],
  ["home_appliance", "家电"],
  ["gift_beauty", "美妆礼物"]
];

const typeSections = [
  { key: "recommend", label: "推荐分类", title: "常用分类", productIds: [9, 10, 11, 17, 18, 19, 32, 39, 40, 1, 2, 3, 33, 4, 5, 6] },
  { key: "supermarket", label: "智购超市", title: "智购超市", productIds: [14, 102, 103, 104, 26, 27, 25, 28, 29, 30] },
  { key: "global", label: "国际品牌", title: "国际品牌", productIds: [20, 21, 22, 32, 39, 40, 302, 303] },
  { key: "men", label: "男装", title: "男装馆", productIds: [301, 302, 303, 304, 305, 306, 35] },
  { key: "women", label: "女装", title: "女装馆", productIds: [401, 402, 403, 31, 32, 33] },
  { key: "phone", label: "手机数码", title: "手机数码", productIds: [6, 7, 8, 504, 505, 506, 4, 5, 11, 12, 13, 15, 16, 24] }
];

const shortcutCategories = [
  { label: "充电宝", category: "power", icon: "./assets/a105.png" },
  { label: "空调", category: "home_appliance", icon: "./assets/a104.png" },
  { label: "口红", category: "gift_beauty", icon: "./assets/a103.png" },
  { label: "平板电脑", category: "tablet", icon: "./assets/a102.png" },
  { label: "眼影", category: "gift_beauty", icon: "./assets/a101.png" },
  { label: "手机", category: "phone", icon: "./assets/a100.png" }
];

const orderStatuses = ["全部", "待付款", "待收货", "待评价", "已完成", "退款/售后"];
const quickNeeds = ["预算3000学习平板", "买一个出差续航充电宝", "通勤降噪耳机", "送女生生日礼物"];
const mainViews = new Set(["home", "type", "community", "cart", "user"]);
const STORAGE = {
  users: "vibe_ai_users",
  session: "vibe_ai_session",
  dataPrefix: "vibe_ai_user_data_"
};

const state = {
  currentUser: null,
  data: null,
  currentResult: null,
  productFilter: "all",
  typeSection: "recommend",
  orderFilter: "全部",
  selectedProduct: null,
  reviewOrderId: null,
  reviewRating: 5,
  lastMainView: "home",
  game: null
};

const els = {
  authTip: qs("#authTip"),
  loginForm: qs("#loginForm"),
  registerForm: qs("#registerForm"),
  loginAccount: qs("#loginAccount"),
  loginPassword: qs("#loginPassword"),
  registerName: qs("#registerName"),
  registerAccount: qs("#registerAccount"),
  registerPassword: qs("#registerPassword"),
  demoLoginBtn: qs("#demoLoginBtn"),
  logoutBtn: qs("#logoutBtn"),
  pageEyebrow: qs("#pageEyebrow"),
  pageTitle: qs("#pageTitle"),
  quickRow: qs("#quickRow"),
  homeSearchForm: qs("#homeSearchForm"),
  homeSearchInput: qs("#homeSearchInput"),
  homeProductList: qs("#homeProductList"),
  typeRail: qs("#typeRail"),
  typeHeading: qs("#typeHeading"),
  typeShortcutGrid: qs("#typeShortcutGrid"),
  typeProductList: qs("#typeProductList"),
  metricProductCount: qs("#metricProductCount"),
  chatForm: qs("#chatForm"),
  chatInput: qs("#chatInput"),
  chatLog: qs("#chatLog"),
  intentText: qs("#intentText"),
  recommendList: qs("#recommendList"),
  buyPrimaryBtn: qs("#buyPrimaryBtn"),
  cartBadge: qs("#cartBadge"),
  cartSummary: qs("#cartSummary"),
  cartList: qs("#cartList"),
  checkoutBtn: qs("#checkoutBtn"),
  clearCartBtn: qs("#clearCartBtn"),
  selectAllBtn: qs("#selectAllBtn"),
  receiverName: qs("#receiverName"),
  receiverAddress: qs("#receiverAddress"),
  orderFlow: qs("#orderFlow"),
  orderFilters: qs("#orderFilters"),
  orderSummary: qs("#orderSummary"),
  orderList: qs("#orderList"),
  profileName: qs("#profileName"),
  profileAccount: qs("#profileAccount"),
  profileIntro: qs("#profileIntro"),
  statAi: qs("#statAi"),
  statReviews: qs("#statReviews"),
  statHistory: qs("#statHistory"),
  orderCountPay: qs("#orderCountPay"),
  orderCountReceive: qs("#orderCountReceive"),
  orderCountReview: qs("#orderCountReview"),
  orderCountAfterSale: qs("#orderCountAfterSale"),
  orderCountAll: qs("#orderCountAll"),
  toolGrid: qs("#toolGrid"),
  aiLogList: qs("#aiLogList"),
  clearLogsBtn: qs("#clearLogsBtn"),
  reviewList: qs("#reviewList"),
  doctorForm: qs("#doctorForm"),
  doctorInput: qs("#doctorInput"),
  doctorReply: qs("#doctorReply"),
  bindForm: qs("#bindForm"),
  bindPhone: qs("#bindPhone"),
  bindCode: qs("#bindCode"),
  bindTip: qs("#bindTip"),
  installBtn: qs("#installBtn"),
  gameCanvas: qs("#gameCanvas"),
  gameScore: qs("#gameScore"),
  startGameBtn: qs("#startGameBtn"),
  resetGameBtn: qs("#resetGameBtn"),
  productDialog: qs("#productDialog"),
  closeDialogBtn: qs("#closeDialogBtn"),
  dialogImage: qs("#dialogImage"),
  dialogName: qs("#dialogName"),
  dialogMeta: qs("#dialogMeta"),
  dialogTags: qs("#dialogTags"),
  dialogReason: qs("#dialogReason"),
  dialogService: qs("#dialogService"),
  dialogPrice: qs("#dialogPrice"),
  dialogBuyBtn: qs("#dialogBuyBtn"),
  dialogBuyNowBtn: qs("#dialogBuyNowBtn"),
  reviewDialog: qs("#reviewDialog"),
  closeReviewBtn: qs("#closeReviewBtn"),
  reviewOrderText: qs("#reviewOrderText"),
  ratingButtons: qs("#ratingButtons"),
  reviewText: qs("#reviewText"),
  submitReviewBtn: qs("#submitReviewBtn"),
  installDialog: qs("#installDialog"),
  closeInstallBtn: qs("#closeInstallBtn"),
  installPlatform: qs("#installPlatform"),
  installTitle: qs("#installTitle"),
  installSummary: qs("#installSummary"),
  installSteps: qs("#installSteps"),
  apkDownloadLink: qs("#apkDownloadLink"),
  copyUrlBtn: qs("#copyUrlBtn"),
  navCartCount: qs("#navCartCount"),
  toastHost: qs("#toastHost")
};

let deferredInstallPrompt = null;

const viewTitles = {
  home: ["首页商品流", "Vibe智购AI"],
  type: ["Android 同款主 Tab", "商品分类"],
  community: ["AI 产品实验室", "发现"],
  cart: ["购买闭环", "购物车"],
  user: ["账号与服务", "我的"],
  assistant: ["自然语言购物", "AI 对话"],
  orders: ["订单状态流转", "我的订单"],
  reviews: ["评价闭环", "评价中心"],
  logs: ["推荐行为记录", "AI 记录"],
  game: ["触控小游戏", "小游戏"],
  doctor: ["服务入口", "问医生"],
  bind: ["账号服务", "用户绑定"],
  settings: ["账号操作", "设置"]
};

const toolItems = [
  { label: "AI助手", icon: "./assets/tools1.png", view: "assistant" },
  { label: "我的订单", icon: "./assets/wodedingdan.png", view: "orders", filter: "全部" },
  { label: "购物车", icon: "./assets/main_cart_press.png", view: "cart" },
  { label: "评价中心", icon: "./assets/daipingjia.png", view: "reviews" },
  { label: "退出登录", icon: "./assets/jdk4.png", action: "logout" },
  { label: "小游戏", icon: "./assets/fj.png", view: "game" },
  { label: "问医生", icon: "./assets/tools4.png", view: "doctor" },
  { label: "用户绑定", icon: "./assets/main_user_press.png", view: "bind" },
  { label: "AI记录", icon: "./assets/tools9.png", view: "logs" },
  { label: "预约服务", icon: "./assets/tools10.png", action: "reserve" },
  { label: "拼购", icon: "./assets/tools11.png", action: "groupBuy" },
  { label: "小程序", icon: "./assets/tools12.png", action: "miniProgram" }
];

function product(id, name, category, tags, price, sales, shop, image, type, reason) {
  return { id, name, category, tags, price, sales, shop, image, type, reason };
}

function qs(selector) {
  return document.querySelector(selector);
}

function qsa(selector) {
  return [...document.querySelectorAll(selector)];
}

function byId(id) {
  return products.find((item) => item.id === id);
}

function money(value) {
  return `¥${Number(value).toFixed(Number(value) % 1 === 0 ? 0 : 1)}`;
}

function nowText() {
  return new Date().toLocaleString("zh-CN", { hour12: false });
}

function toast(message, type = "info") {
  if (!els.toastHost) return;
  const node = document.createElement("div");
  node.className = `toast ${type}`;
  node.textContent = message;
  els.toastHost.appendChild(node);
  window.setTimeout(() => node.classList.add("show"), 20);
  window.setTimeout(() => {
    node.classList.remove("show");
    window.setTimeout(() => node.remove(), 240);
  }, 2200);
}

function getInstallGuide() {
  const ua = navigator.userAgent || "";
  const isAndroid = /Android/i.test(ua);
  const isIOS = /iPhone|iPad|iPod/i.test(ua);
  const isStandalone = window.matchMedia("(display-mode: standalone)").matches || navigator.standalone;
  const isSecure = window.isSecureContext || location.hostname === "localhost" || location.hostname === "127.0.0.1";

  if (isStandalone) {
    return {
      platform: "已安装",
      title: "已经是桌面应用模式",
      summary: "当前页面已经像 App 一样运行，可以直接继续演示登录、AI 对话购物、购物车和订单闭环。",
      steps: ["从桌面图标再次打开即可进入同一体验。", "安卓真 App 仍可下载 APK 安装包进行原生端演示。"],
      showApk: isAndroid
    };
  }

  if (isAndroid) {
    return {
      platform: "Android 安装",
      title: "安卓可以这样安装",
      summary: isSecure
        ? "如果浏览器允许，点击安装会调起原生 PWA 安装；若没有弹出，请按下面步骤。"
        : "你现在用的是局域网 HTTP 地址，安卓浏览器可能不会把原生安装弹窗交给网页按钮。",
      steps: [
        "网页体验版：点浏览器右上角三个点。",
        "选择“安装应用”或“添加到主屏幕”。",
        "原生 App：点下面“下载安卓 APK”，下载后按系统提示安装。"
      ],
      showApk: true
    };
  }

  if (isIOS) {
    return {
      platform: "iPhone 安装",
      title: "iPhone 用 Safari 添加到主屏幕",
      summary: "iPhone 不支持网页按钮直接安装 PWA，需要在 Safari 里通过分享菜单添加。",
      steps: [
        "用 Safari 打开这个网址。",
        "点击底部分享按钮。",
        "选择“添加到主屏幕”，确认后桌面会出现 Vibe智购AI 图标。"
      ],
      showApk: false
    };
  }

  return {
    platform: "桌面浏览器安装",
    title: "桌面端安装方式",
    summary: "如果地址栏出现安装图标，可以直接安装；没有出现时可通过浏览器菜单添加应用。",
    steps: [
      "Chrome/Edge 地址栏右侧查看是否有安装图标。",
      "也可以打开浏览器菜单，选择“安装 Vibe智购AI”。",
      "安卓手机测试建议打开同一局域网地址，或直接下载 APK。"
    ],
    showApk: true
  };
}

function openInstallGuide() {
  const guide = getInstallGuide();
  els.installPlatform.textContent = guide.platform;
  els.installTitle.textContent = guide.title;
  els.installSummary.textContent = guide.summary;
  els.installSteps.innerHTML = guide.steps.map((step, index) => `<p><strong>${index + 1}</strong><span>${step}</span></p>`).join("");
  els.apkDownloadLink.hidden = !guide.showApk;
  els.apkDownloadLink.parentElement.classList.toggle("only-copy", !guide.showApk);
  els.installDialog.showModal();
}

async function copyCurrentUrl() {
  const url = location.href;
  try {
    await navigator.clipboard.writeText(url);
    toast("当前网址已复制", "success");
  } catch (error) {
    toast(url, "info");
  }
}

function serviceText(item) {
  const prefix = item.price >= 1000 ? "支持分期、7天无理由、正品保障" : "7天无理由、极速发货、正品保障";
  return `${prefix}｜已纳入 AI 推荐日志和订单闭环演示`;
}

function orderStatusCounts() {
  const orders = state.data?.orders || [];
  return {
    all: orders.length,
    pay: orders.filter((order) => order.status === "待付款").length,
    receive: orders.filter((order) => order.status === "待收货").length,
    review: orders.filter((order) => order.status === "待评价").length,
    afterSale: orders.filter((order) => order.status === "退款/售后").length
  };
}

function userDataKey(account) {
  return STORAGE.dataPrefix + account;
}

function loadUsers() {
  try {
    return JSON.parse(localStorage.getItem(STORAGE.users) || "[]");
  } catch {
    return [];
  }
}

function saveUsers(users) {
  localStorage.setItem(STORAGE.users, JSON.stringify(users));
}

function defaultUserData() {
  return {
    cart: [],
    orders: [],
    logs: [],
    reviews: [],
    history: [],
    boundPhone: ""
  };
}

function normalizeUserData(data) {
  return { ...defaultUserData(), ...(data || {}) };
}

function loadUserData(account) {
  try {
    return normalizeUserData(JSON.parse(localStorage.getItem(userDataKey(account)) || "null"));
  } catch {
    return defaultUserData();
  }
}

function saveUserData() {
  if (!state.currentUser) return;
  localStorage.setItem(userDataKey(state.currentUser.account), JSON.stringify(state.data));
}

function setAuthTip(message, type = "") {
  els.authTip.textContent = message;
  els.authTip.className = `auth-tip ${type}`.trim();
}

function switchAuthTab(tab) {
  qsa("[data-auth-tab]").forEach((button) => button.classList.toggle("active", button.dataset.authTab === tab));
  qsa("[data-auth-form]").forEach((form) => form.classList.toggle("active", form.dataset.authForm === tab));
  setAuthTip(tab === "login" ? "已有账号可直接登录；没有账号请先注册。" : "注册信息会保存在当前浏览器，适合现场演示。");
}

function showAuth(tab = "login") {
  document.body.classList.add("locked");
  document.body.classList.remove("secondary-active");
  switchAuthTab(tab);
}

function showApp(user) {
  state.currentUser = user;
  state.data = loadUserData(user.account);
  document.body.classList.remove("locked");
  els.receiverName.value = user.name;
  els.profileName.textContent = user.name;
  els.profileAccount.textContent = `账号：${user.account}`;
  els.profileIntro.textContent = state.data.boundPhone
    ? `智能推荐会员 | 已绑定 ${state.data.boundPhone}`
    : "智能推荐会员 | 专属购物服务";
  renderEverything();
  switchView("home");
}

function registerUser(event) {
  event.preventDefault();
  const name = els.registerName.value.trim();
  const account = els.registerAccount.value.trim();
  const password = els.registerPassword.value;
  if (!name || !account || !password) return setAuthTip("昵称、账号和密码都要填写。", "error");
  if (password.length < 4) return setAuthTip("密码至少 4 位。", "error");
  const users = loadUsers();
  if (users.some((user) => user.account === account)) return setAuthTip("这个账号已注册，请直接登录。", "error");
  const user = { name, account, password, createdAt: Date.now() };
  users.push(user);
  saveUsers(users);
  localStorage.setItem(STORAGE.session, account);
  showApp(user);
}

function loginUser(event) {
  event.preventDefault();
  const account = els.loginAccount.value.trim();
  const password = els.loginPassword.value;
  const user = loadUsers().find((item) => item.account === account && item.password === password);
  if (!user) return setAuthTip("账号或密码不正确。没有账号可以切到注册。", "error");
  localStorage.setItem(STORAGE.session, account);
  showApp(user);
}

function demoLogin() {
  const users = loadUsers();
  let user = users.find((item) => item.account === "demo");
  if (!user) {
    user = { name: "求职演示用户", account: "demo", password: "1234", createdAt: Date.now() };
    users.push(user);
    saveUsers(users);
  }
  localStorage.setItem(STORAGE.session, user.account);
  showApp(user);
}

function logoutUser() {
  localStorage.removeItem(STORAGE.session);
  state.currentUser = null;
  state.data = null;
  state.currentResult = null;
  showAuth("login");
}

function switchView(viewName, options = {}) {
  const normalized = normalizeViewName(viewName);
  if (options.orderFilter) state.orderFilter = options.orderFilter;
  qsa(".view").forEach((view) => view.classList.toggle("active", view.dataset.view === normalized));

  const isMain = mainViews.has(normalized);
  if (isMain) state.lastMainView = normalized;
  document.body.classList.toggle("secondary-active", !isMain);
  qsa("[data-view-link]").forEach((button) => button.classList.toggle("active", isMain && button.dataset.viewLink === normalized));

  const title = viewTitles[normalized] || viewTitles.home;
  els.pageEyebrow.textContent = title[0];
  els.pageTitle.textContent = title[1];

  if (normalized === "orders") renderOrders();
  if (normalized === "type") renderTypePage();
  if (normalized === "game") renderGame();
  window.scrollTo({ top: 0, behavior: "smooth" });
}

function normalizeViewName(viewName) {
  const aliases = {
    ai: "assistant",
    products: "type",
    me: "user"
  };
  return aliases[viewName] || viewName || "home";
}

function parseIntent(input) {
  const text = input.trim();
  const budgetMatch = text.match(/(\d{2,6})/);
  const budget = budgetMatch ? Number(budgetMatch[1]) : -1;
  const category = detectCategory(text);
  const scene = detectScene(text);
  return {
    rawText: text,
    budget,
    category,
    scene,
    categoryName: categoryName(category),
    sceneName: sceneName(scene),
    budgetText: budget > 0 ? `${budget}元以内` : "未限定预算"
  };
}

function detectCategory(text) {
  if (/平板|ipad|网课|笔记/i.test(text)) return "tablet";
  if (/手机|换机|拍照|5g/i.test(text)) return "phone";
  if (/充电|续航|电源|充电宝/.test(text)) return "power";
  if (/耳机|降噪|蓝牙/.test(text)) return "headphones";
  if (/笔记本|电脑|编程|论文/.test(text)) return "laptop";
  if (/空调|家电|扫地|厨房|洗衣机|门锁/.test(text)) return "home_appliance";
  if (/礼物|女生|生日|口红|美妆|眼影/.test(text)) return "gift_beauty";
  if (/男装|衬衫|夹克|裤/.test(text)) return "men";
  if (/女装|连衣裙|短裤/.test(text)) return "women";
  if (/牛奶|坚果|咖啡|鸡胸|猫粮|超市/.test(text)) return "supermarket";
  return "general";
}

function detectScene(text) {
  if (/学习|网课|论文|校园|职场进阶/.test(text)) return "study";
  if (/出差|旅行|旅游|续航|露营/.test(text)) return "travel";
  if (/通勤|地铁|办公室|办公|商务/.test(text)) return "commute";
  if (/礼物|生日|送|节日/.test(text)) return "gift";
  if (/家|厨房|空调|扫地|洗衣|门锁|路由/.test(text)) return "home";
  if (/运动|健身|跑步/.test(text)) return "sport";
  return "daily";
}

function categoryName(category) {
  return {
    tablet: "平板",
    phone: "手机",
    power: "移动电源",
    headphones: "耳机",
    laptop: "笔记本",
    home_appliance: "家电",
    gift_beauty: "礼物美妆",
    men: "男装",
    women: "女装",
    supermarket: "超市商品",
    general: "综合商品"
  }[category] || "综合商品";
}

function sceneName(scene) {
  return {
    study: "学习场景",
    travel: "差旅场景",
    commute: "通勤办公",
    gift: "送礼场景",
    home: "居家场景",
    sport: "运动场景",
    daily: "日常场景"
  }[scene] || "日常场景";
}

function recommend(input) {
  const intent = parseIntent(input);
  const pool = recommendationPool(intent);
  const scored = pool
    .map((item) => ({ product: item, score: scoreProduct(intent, item) }))
    .sort((a, b) => b.score - a.score);
  const primary = scored[0].product;
  const alternatives = scored.slice(1, 4).map((item) => item.product);
  return {
    intent,
    primary,
    alternatives,
    reason: `我把你的需求拆成「${intent.sceneName} / ${intent.categoryName} / ${intent.budgetText}」。${primary.reason}`,
    nextStep: "如果这个方向符合预期，可以说“就买这个”或“买备选1”，也可以直接去购物车。"
  };
}

function recommendationPool(intent) {
  if (intent.category === "general") {
    return products.filter((item) => !["phone_service"].includes(item.category));
  }
  const categoryMatches = products.filter((item) => item.category === intent.category || item.tags.includes(intent.category));
  if (categoryMatches.length >= 3) return categoryMatches;
  const sceneMatches = products.filter((item) => item.tags.includes(intent.scene));
  return [...new Map([...categoryMatches, ...sceneMatches, ...products].map((item) => [item.id, item])).values()];
}

function scoreProduct(intent, item) {
  let score = Math.floor(item.sales / 260);
  if (item.category === intent.category) score += 55;
  if (item.tags.includes(intent.scene)) score += 28;
  if (item.tags.includes(intent.category)) score += 15;
  if (intent.budget > 0) {
    if (item.price <= intent.budget) score += item.price >= intent.budget * 0.55 ? 35 : 22;
    else if (item.price <= intent.budget * 1.15) score += 6;
    else score -= 20;
  }
  if (intent.category === "general" && item.tags.includes("daily")) score += 10;
  return score;
}

function parseChatCommand(message) {
  const text = message.trim();
  if (/购物车|结算|查看加购/.test(text)) return { action: "viewCart" };
  if (/订单|我的订单/.test(text)) return { action: "viewOrders" };
  if (/帮助|怎么用|指令/.test(text)) return { action: "help" };
  if (/买|购买|加购|下单|就这个|要这个|选这个|拿下|付款/.test(text)) {
    const targetIndex = parseTargetIndex(text);
    const hasNeed = /预算|以内|左右|平板|手机|耳机|充电|续航|礼物|女生|通勤|出差|办公|学习|家电|空调|笔记本|男装|女装|超市/.test(text);
    if (state.currentResult && !hasNeed) return { action: "add", targetIndex };
    if (hasNeed) return { action: "recommendAndAdd", targetIndex };
    return { action: "help" };
  }
  return { action: "recommend" };
}

function parseTargetIndex(text) {
  if (/备选3|备选三|第三个/.test(text)) return 3;
  if (/备选2|备选二|第二个/.test(text)) return 2;
  if (/备选1|备选一|第一个/.test(text)) return 1;
  return 0;
}

function addMessage(type, text) {
  const node = document.createElement("div");
  node.className = `message ${type}`;
  node.textContent = text;
  els.chatLog.appendChild(node);
  els.chatLog.scrollTop = els.chatLog.scrollHeight;
}

function addLog(action, text, productId = 0) {
  if (!state.data) return;
  state.data.logs.unshift({ action, text, productId, time: nowText() });
  state.data.logs = state.data.logs.slice(0, 60);
  saveUserData();
  renderLogs();
  renderStats();
}

function addHistory(productId) {
  if (!state.data) return;
  state.data.history = [productId, ...state.data.history.filter((id) => id !== productId)].slice(0, 80);
  saveUserData();
  renderStats();
}

function renderRecommendation(result) {
  els.intentText.textContent = `识别：${result.intent.sceneName} / ${result.intent.categoryName} / ${result.intent.budgetText}`;
  const cards = [result.primary, ...result.alternatives];
  els.recommendList.innerHTML = cards.map((item, index) => productCard(item, index === 0 ? "主推" : `备选${index}`, "recommend")).join("");
  els.buyPrimaryBtn.disabled = false;
  addMessage("ai", `主推：${result.primary.name}\n${result.reason}\n${result.nextStep}`);
  addLog("recommended", result.intent.rawText, result.primary.id);
  switchView("assistant");
}

function productCard(item, label = "", source = "catalog") {
  return `
    <article class="product-card ${label === "主推" ? "primary" : ""}">
      <img src="${item.image}" alt="${item.name}" loading="lazy">
      <div class="product-body">
        <div class="product-name">${label ? `${label}｜` : ""}${item.name}</div>
        <div class="product-meta">${item.type}</div>
        <div class="product-meta">${item.shop}｜销量 ${item.sales}</div>
        <div class="price-row">
          <span class="price">${money(item.price)}</span>
          <button class="buy-small muted" type="button" data-detail-id="${item.id}">详情</button>
          <button class="buy-small" type="button" data-buy-id="${item.id}" data-source="${source}">加购</button>
        </div>
      </div>
    </article>
  `;
}

function productListItem(item, source = "catalog") {
  return `
    <article class="goods-item" data-card-id="${item.id}">
      <img src="${item.image}" alt="${item.name}" loading="lazy">
      <div class="goods-info">
        <h3>${item.name}</h3>
        <strong>${money(item.price)}</strong>
        <span>${item.sales} 条评价 99%好评</span>
        <span>${item.shop}</span>
      </div>
      <button type="button" data-buy-id="${item.id}" data-source="${source}" aria-label="加入购物车">
        <img src="./assets/buyit.png" alt="">
      </button>
    </article>
  `;
}

function renderHome() {
  els.homeProductList.innerHTML = products.slice(0, 38).map((item) => productListItem(item, "home")).join("");
  els.metricProductCount.textContent = products.length;
}

function renderTypePage() {
  els.typeRail.innerHTML = typeSections.map((section) => `
    <button class="${state.typeSection === section.key ? "active" : ""}" type="button" data-type-section="${section.key}">${section.label}</button>
  `).join("");

  const current = typeSections.find((section) => section.key === state.typeSection) || typeSections[0];
  els.typeHeading.textContent = current.title;
  els.typeShortcutGrid.innerHTML = shortcutCategories.map((item) => `
    <button type="button" data-shortcut-category="${item.category}">
      <img src="${item.icon}" alt="">
      <span>${item.label}</span>
    </button>
  `).join("");

  const list = current.productIds.map(byId).filter(Boolean);
  els.typeProductList.innerHTML = list.map((item) => productListItem(item, "type")).join("");
}

function renderFilteredType(category) {
  const list = products.filter((item) => item.category === category);
  const label = categoryName(category);
  els.typeHeading.textContent = label;
  els.typeProductList.innerHTML = list.length ? list.map((item) => productListItem(item, "type")).join("") : `<div class="empty-state">当前分类暂无商品。</div>`;
}

function addToCart(productId, source = "catalog") {
  const item = byId(productId);
  if (!item || !state.data) return;
  const cartItem = state.data.cart.find((entry) => entry.id === productId);
  if (cartItem) cartItem.count += 1;
  else state.data.cart.push({ id: item.id, count: 1, selected: true });
  saveUserData();
  renderCart();
  renderStats();
  addMessage("ai", `已加入购物车：${item.name}`);
  addLog(source === "recommend" ? "chat_added_to_cart" : "added_to_cart", item.name, item.id);
  toast(`已加入购物车：${item.name}`, "success");
}

function addProductByIndex(targetIndex) {
  if (!state.currentResult) {
    addMessage("ai", "我还没有推荐商品。你可以先说：预算3000学习平板。");
    return;
  }
  const item = targetIndex <= 0 ? state.currentResult.primary : state.currentResult.alternatives[targetIndex - 1] || state.currentResult.primary;
  addToCart(item.id, "recommend");
  renderOrderFlow(2);
}

function cartEntries() {
  if (!state.data) return [];
  return state.data.cart
    .map((entry) => ({ ...byId(entry.id), count: entry.count, selected: entry.selected !== false }))
    .filter((item) => item.id);
}

function renderCart() {
  const items = cartEntries();
  const selected = items.filter((item) => item.selected);
  const totalCount = selected.reduce((sum, item) => sum + item.count, 0);
  const totalPrice = selected.reduce((sum, item) => sum + item.count * item.price, 0);
  const allCount = items.reduce((sum, item) => sum + item.count, 0);
  els.cartBadge.textContent = `${allCount} 件`;
  els.navCartCount.textContent = String(allCount);
  els.navCartCount.hidden = allCount === 0;
  els.cartSummary.textContent = `合计：${money(totalPrice)}`;
  els.checkoutBtn.disabled = totalCount === 0;
  els.selectAllBtn.textContent = items.length && selected.length === items.length ? "取消" : "全选";
  els.cartList.innerHTML = items.length ? items.map((item) => `
    <div class="cart-item ${item.selected ? "" : "muted"}">
      <button class="check-button ${item.selected ? "checked" : ""}" type="button" data-toggle-cart="${item.id}">${item.selected ? "✓" : ""}</button>
      <img src="${item.image}" alt="${item.name}">
      <div>
        <strong>${item.name}</strong>
        <span>${item.type}</span>
        <span>${item.shop}</span>
      </div>
      <div class="cart-actions">
        <strong>${money(item.price * item.count)}</strong>
        <button type="button" data-dec="${item.id}">-</button>
        <span>${item.count}</span>
        <button type="button" data-inc="${item.id}">+</button>
        <button type="button" data-remove="${item.id}">删</button>
      </div>
    </div>
  `).join("") : `<div class="empty-state">购物车为空，可以从首页、分类页或 AI 助手加购。</div>`;
}

function updateCart(productId, delta) {
  const item = state.data.cart.find((entry) => entry.id === productId);
  if (!item) return;
  item.count += delta;
  if (item.count <= 0) state.data.cart = state.data.cart.filter((entry) => entry.id !== productId);
  saveUserData();
  renderCart();
}

function toggleCartSelection(productId) {
  const item = state.data.cart.find((entry) => entry.id === productId);
  if (!item) return;
  item.selected = item.selected === false;
  saveUserData();
  renderCart();
}

function toggleSelectAll() {
  if (!state.data.cart.length) return;
  const shouldSelect = state.data.cart.some((entry) => entry.selected === false);
  state.data.cart.forEach((entry) => {
    entry.selected = shouldSelect;
  });
  saveUserData();
  renderCart();
}

function clearCart() {
  if (!state.data.cart.length) return;
  state.data.cart = [];
  saveUserData();
  renderCart();
  toast("购物车已清空");
}

function buyNow(productId) {
  const item = byId(productId);
  if (!item || !state.data) return;
  state.data.cart = state.data.cart.filter((entry) => entry.id !== productId);
  state.data.cart.unshift({ id: productId, count: 1, selected: true });
  saveUserData();
  renderCart();
  addLog("added_to_cart", `立即购买：${item.name}`, item.id);
  if (els.productDialog.open) els.productDialog.close();
  switchView("cart");
  toast("已为你选中商品，可直接去结算", "success");
}

function checkout() {
  const selected = cartEntries().filter((item) => item.selected);
  if (selected.length === 0) return;
  const receiver = els.receiverName.value.trim() || state.currentUser.name;
  const address = els.receiverAddress.value.trim() || "厦门市思明区软件园";
  const totalPrice = selected.reduce((sum, item) => sum + item.price * item.count, 0);
  state.data.orders.unshift({
    id: Date.now(),
    items: selected,
    totalPrice,
    receiver,
    address,
    status: "待付款",
    createdAt: nowText()
  });
  const selectedIds = new Set(selected.map((item) => item.id));
  state.data.cart = state.data.cart.filter((entry) => !selectedIds.has(entry.id));
  saveUserData();
  renderEverything();
  addLog("order_created", `生成订单 ${money(totalPrice)}`, 0);
  switchView("orders", { orderFilter: "全部" });
  toast("订单已生成，当前状态：待付款", "success");
}

function renderOrderFlow(activeIndex = 0) {
  const steps = [["需求识别", "预算/场景/品类"], ["AI 推荐", "主推与备选"], ["加入购物车", "对话或按钮加购"], ["订单完成", "付款/收货/评价"]];
  els.orderFlow.innerHTML = steps.map((step, index) => `<div class="order-step ${index <= activeIndex ? "active" : ""}"><strong>${step[0]}</strong><span>${step[1]}</span></div>`).join("");
}

function renderOrderFilters() {
  els.orderFilters.innerHTML = orderStatuses.map((status) => `
    <button class="${state.orderFilter === status ? "active" : ""}" type="button" data-order-filter="${status}">${status}</button>
  `).join("");
}

function renderOrders() {
  if (!state.data) return;
  const orders = state.data.orders.filter((order) => state.orderFilter === "全部" || order.status === state.orderFilter);
  els.orderSummary.textContent = state.data.orders.length ? `共 ${state.data.orders.length} 个订单，当前筛选：${state.orderFilter}` : "生成订单后会显示在这里";
  els.orderList.innerHTML = orders.length ? orders.map(orderCard).join("") : `<div class="empty-state">当前筛选下没有订单。</div>`;
  renderOrderFilters();
  renderReviews();
}

function orderCard(order) {
  const actions = {
    "待付款": `<button type="button" data-order-action="pay" data-order-id="${order.id}">去付款</button>`,
    "待收货": `<button type="button" data-order-action="receive" data-order-id="${order.id}">确认收货</button>`,
    "待评价": `<button type="button" data-order-action="review" data-order-id="${order.id}">去评价</button>`,
    "已完成": `<button type="button" data-order-action="afterSale" data-order-id="${order.id}">申请售后</button>`,
    "退款/售后": `<button type="button" data-order-action="done" data-order-id="${order.id}">查看进度</button>`
  }[order.status] || "";
  return `
    <div class="order-card">
      <div class="order-head">
        <strong>订单 #${String(order.id).slice(-6)}｜${order.status}</strong>
        <span>${order.createdAt}</span>
      </div>
      ${order.items.map((item) => `
        <div class="order-line">
          <img src="${item.image}" alt="${item.name}">
          <div><strong>${item.name}</strong><span>${item.type} x${item.count}</span></div>
          <b>${money(item.price * item.count)}</b>
        </div>
      `).join("")}
      <div class="order-footer">
        <span>${order.receiver}｜${order.address}</span>
        <strong>${money(order.totalPrice)}</strong>
      </div>
      <div class="order-actions">${actions}</div>
    </div>
  `;
}

function handleOrderAction(action, orderId) {
  const order = state.data.orders.find((item) => item.id === orderId);
  if (!order) return;
  if (action === "pay") {
    order.status = "待收货";
    toast("付款成功，订单进入待收货", "success");
  }
  if (action === "receive") {
    order.status = "待评价";
    toast("已确认收货，可以去评价", "success");
  }
  if (action === "review") {
    openReviewDialog(order);
    return;
  }
  if (action === "afterSale") {
    order.status = "退款/售后";
    toast("售后申请已提交", "success");
  }
  if (action === "done") {
    toast("售后进度：平台已受理，预计 1 个工作日内反馈。");
  }
  saveUserData();
  renderEverything();
}

function openReviewDialog(order) {
  state.reviewOrderId = order.id;
  state.reviewRating = 5;
  els.reviewOrderText.textContent = `订单 #${String(order.id).slice(-6)}｜${order.items.map((item) => item.name).join("、")}`;
  els.reviewText.value = "推荐准确，购物流程完整，体验顺畅。";
  qsa("[data-rating]").forEach((button) => button.classList.toggle("active", button.dataset.rating === "5"));
  els.reviewDialog.showModal();
}

function submitReview() {
  const order = state.data.orders.find((item) => item.id === state.reviewOrderId);
  if (!order) return;
  const text = els.reviewText.value.trim() || "推荐准确，购物流程完整，体验顺畅。";
  order.status = "已完成";
  state.data.reviews.unshift({
    orderId: order.id,
    text: `${state.reviewRating}分｜${text}`,
    rating: state.reviewRating,
    time: nowText(),
    items: order.items.map((item) => item.name)
  });
  addLog("reviewed", text, 0);
  saveUserData();
  renderEverything();
  els.reviewDialog.close();
  switchView("reviews");
  toast("评价已提交，订单已完成", "success");
}

function renderReviews() {
  if (!state.data) return;
  els.reviewList.innerHTML = state.data.reviews.length ? state.data.reviews.map((review) => `
    <div class="cart-item review-item">
      <div><strong>${review.items.join("、")}</strong><span>${review.text}</span><span>${review.time}</span></div>
    </div>
  `).join("") : `<div class="empty-state">暂无评价。待评价订单提交后会出现在这里。</div>`;
}

function renderLogs() {
  if (!state.data) return;
  els.aiLogList.innerHTML = state.data.logs.length ? state.data.logs.map((log) => `
    <div class="cart-item log-item">
      <div><strong>${actionText(log.action)}</strong><span>${log.text}</span><span>${log.time}</span></div>
    </div>
  `).join("") : `<div class="empty-state">暂无 AI 记录。</div>`;
}

function actionText(action) {
  return {
    recommended: "生成推荐",
    added_to_cart: "加入购物车",
    chat_added_to_cart: "对话加购",
    order_created: "生成订单",
    reviewed: "提交评价"
  }[action] || action;
}

function renderStats() {
  if (!state.data) return;
  const counts = orderStatusCounts();
  els.statAi.textContent = state.data.logs.filter((log) => log.action === "recommended").length;
  els.statReviews.textContent = state.data.reviews.length || 60;
  els.statHistory.textContent = state.data.history.length || 233;
  els.orderCountPay.textContent = String(counts.pay);
  els.orderCountReceive.textContent = String(counts.receive);
  els.orderCountReview.textContent = String(counts.review);
  els.orderCountAfterSale.textContent = String(counts.afterSale);
  els.orderCountAll.textContent = String(counts.all);
}

function renderTools() {
  els.toolGrid.innerHTML = toolItems.map((item, index) => `
    <button type="button" data-tool-index="${index}">
      <img src="${item.icon}" alt="">
      <span>${item.label}</span>
    </button>
  `).join("");
}

function handleMessage(message) {
  addMessage("user", message);
  const command = parseChatCommand(message);
  if (command.action === "help") return addMessage("ai", "你可以说：预算3000学习平板、买一个通勤降噪耳机、就买这个、买备选1、去购物车。");
  if (command.action === "viewCart") {
    switchView("cart");
    return addMessage("ai", "已带你到购物车，可以继续提交订单。");
  }
  if (command.action === "viewOrders") {
    switchView("orders", { orderFilter: "全部" });
    return addMessage("ai", "已打开我的订单，可以查看状态流转。");
  }
  if (command.action === "add") return addProductByIndex(command.targetIndex);
  if (command.action === "recommendAndAdd") {
    state.currentResult = recommend(message);
    renderRecommendation(state.currentResult);
    addProductByIndex(command.targetIndex);
    return;
  }
  state.currentResult = recommend(message);
  renderRecommendation(state.currentResult);
  renderOrderFlow(1);
}

function openProductDetail(productId) {
  const item = byId(productId);
  if (!item) return;
  state.selectedProduct = item;
  addHistory(productId);
  els.dialogImage.src = item.image;
  els.dialogImage.alt = item.name;
  els.dialogName.textContent = item.name;
  els.dialogMeta.textContent = `${item.type}｜${item.shop}｜销量 ${item.sales}`;
  els.dialogTags.innerHTML = item.tags.slice(0, 4).map((tag) => `<span>${tagText(tag)}</span>`).join("");
  els.dialogReason.textContent = item.reason;
  els.dialogService.textContent = serviceText(item);
  els.dialogPrice.textContent = money(item.price);
  els.productDialog.showModal();
}

function tagText(tag) {
  return {
    study: "学习",
    daily: "日常",
    office: "办公",
    tablet: "平板",
    phone: "手机",
    performance: "性能",
    photo: "影像",
    power: "续航",
    travel: "差旅",
    commute: "通勤",
    headphones: "降噪",
    home: "居家",
    gift: "送礼",
    beauty: "美妆",
    global: "品牌",
    men: "男装",
    women: "女装",
    sport: "运动"
  }[tag] || tag;
}

function handleTool(index) {
  const item = toolItems[index];
  if (!item) return;
  if (item.action === "logout") return logoutUser();
  if (item.action === "reserve") return toast("预约服务：演示入口已创建，可后续对接真实服务。");
  if (item.action === "groupBuy") return toast("拼购：演示入口已接入，可扩展为多人拼团流程。");
  if (item.action === "miniProgram") return toast("小程序：PWA 端展示同等入口，便于说明跨端业务一致性。");
  if (item.view === "orders") return switchView("orders", { orderFilter: item.filter || "全部" });
  switchView(item.view);
}

function handleNeed(need) {
  switchView("assistant");
  handleMessage(need);
}

function handleDoctor(event) {
  event.preventDefault();
  const text = els.doctorInput.value.trim();
  if (!text) return;
  els.doctorReply.textContent = `演示回复：你提到“${text}”。建议先记录持续时间、诱因和严重程度；如果症状明显或持续加重，请及时线下就医。`;
}

function handleBind(event) {
  event.preventDefault();
  const phone = els.bindPhone.value.trim();
  const code = els.bindCode.value.trim();
  if (!/^1\d{10}$/.test(phone)) {
    els.bindTip.textContent = "请输入 11 位手机号。";
    els.bindTip.className = "auth-tip error";
    return;
  }
  if (code !== "1234") {
    els.bindTip.textContent = "验证码错误。演示验证码是 1234。";
    els.bindTip.className = "auth-tip error";
    return;
  }
  state.data.boundPhone = phone;
  saveUserData();
  els.bindTip.textContent = "绑定成功。";
  els.bindTip.className = "auth-tip success";
  els.profileIntro.textContent = `智能推荐会员 | 已绑定 ${phone}`;
}

function renderGame() {
  if (state.game) {
    drawGame();
    return;
  }
  const canvas = els.gameCanvas;
  const ctx = canvas.getContext("2d");
  const game = {
    ctx,
    running: false,
    score: 0,
    plane: { x: canvas.width / 2 - 22, y: canvas.height - 74, w: 44, h: 48 },
    drops: [],
    raf: 0,
    lastSpawn: 0
  };
  state.game = game;

  const movePlane = (clientX) => {
    const rect = canvas.getBoundingClientRect();
    const scale = canvas.width / rect.width;
    game.plane.x = Math.max(8, Math.min(canvas.width - game.plane.w - 8, (clientX - rect.left) * scale - game.plane.w / 2));
    drawGame();
  };

  canvas.addEventListener("pointerdown", (event) => {
    canvas.setPointerCapture(event.pointerId);
    movePlane(event.clientX);
  });
  canvas.addEventListener("pointermove", (event) => movePlane(event.clientX));
  drawGame();
}

function startGame() {
  renderGame();
  const game = state.game;
  if (game.running) return;
  game.running = true;
  game.lastSpawn = performance.now();
  game.raf = requestAnimationFrame(gameLoop);
}

function resetGame() {
  renderGame();
  const game = state.game;
  cancelAnimationFrame(game.raf);
  game.running = false;
  game.score = 0;
  game.drops = [];
  els.gameScore.textContent = "0";
  drawGame();
}

function gameLoop(time) {
  const game = state.game;
  if (!game || !game.running) return;
  if (time - game.lastSpawn > 700) {
    game.drops.push({ x: 16 + Math.random() * 328, y: -16, r: 9 + Math.random() * 8, speed: 2 + Math.random() * 2.5 });
    game.lastSpawn = time;
  }

  game.drops.forEach((drop) => {
    drop.y += drop.speed;
  });

  game.drops = game.drops.filter((drop) => {
    const hit = drop.x > game.plane.x && drop.x < game.plane.x + game.plane.w && drop.y + drop.r > game.plane.y;
    if (hit) {
      game.score += 1;
      els.gameScore.textContent = String(game.score);
      return false;
    }
    return drop.y < 540;
  });

  drawGame();
  game.raf = requestAnimationFrame(gameLoop);
}

function drawGame() {
  const game = state.game;
  if (!game) return;
  const { ctx } = game;
  ctx.clearRect(0, 0, 360, 520);
  ctx.fillStyle = "#e8f7f3";
  ctx.fillRect(0, 0, 360, 520);
  ctx.fillStyle = "#0f766e";
  ctx.fillRect(0, 468, 360, 52);
  ctx.fillStyle = "#10201d";
  ctx.font = "16px sans-serif";
  ctx.fillText(game.running ? "拖动飞机接住掉落物" : "点击开始后拖动飞机", 18, 30);
  ctx.fillStyle = "#ef4444";
  game.drops.forEach((drop) => {
    ctx.beginPath();
    ctx.arc(drop.x, drop.y, drop.r, 0, Math.PI * 2);
    ctx.fill();
  });
  drawPlane(ctx, game.plane);
}

function drawPlane(ctx, plane) {
  ctx.save();
  ctx.translate(plane.x + plane.w / 2, plane.y + plane.h / 2);
  ctx.fillStyle = "#ffffff";
  ctx.beginPath();
  ctx.moveTo(0, -28);
  ctx.lineTo(18, 18);
  ctx.lineTo(0, 8);
  ctx.lineTo(-18, 18);
  ctx.closePath();
  ctx.fill();
  ctx.fillStyle = "#0f766e";
  ctx.fillRect(-6, -4, 12, 16);
  ctx.restore();
}

function renderEverything() {
  renderHome();
  renderTypePage();
  renderCart();
  renderOrderFilters();
  renderOrders();
  renderLogs();
  renderReviews();
  renderStats();
  renderTools();
  renderOrderFlow(0);
}

function bindEvents() {
  qsa("[data-auth-tab]").forEach((button) => button.addEventListener("click", () => switchAuthTab(button.dataset.authTab)));
  els.loginForm.addEventListener("submit", loginUser);
  els.registerForm.addEventListener("submit", registerUser);
  els.demoLoginBtn.addEventListener("click", demoLogin);
  els.logoutBtn.addEventListener("click", logoutUser);

  els.homeSearchForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const value = els.homeSearchInput.value.trim();
    if (value) handleNeed(value);
  });

  quickNeeds.forEach((need) => {
    const button = document.createElement("button");
    button.type = "button";
    button.textContent = need;
    button.addEventListener("click", () => handleNeed(need));
    els.quickRow.appendChild(button);
  });

  els.chatForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const message = els.chatInput.value.trim();
    if (!message) return;
    els.chatInput.value = "";
    handleMessage(message);
  });

  els.buyPrimaryBtn.addEventListener("click", () => addProductByIndex(0));
  els.checkoutBtn.addEventListener("click", checkout);
  els.clearCartBtn.addEventListener("click", clearCart);
  els.selectAllBtn.addEventListener("click", toggleSelectAll);
  els.clearLogsBtn.addEventListener("click", () => {
    state.data.logs = [];
    saveUserData();
    renderLogs();
    renderStats();
  });
  els.closeDialogBtn.addEventListener("click", () => els.productDialog.close());
  els.dialogBuyBtn.addEventListener("click", () => {
    if (state.selectedProduct) addToCart(state.selectedProduct.id, "detail");
    els.productDialog.close();
  });
  els.dialogBuyNowBtn.addEventListener("click", () => {
    if (state.selectedProduct) buyNow(state.selectedProduct.id);
  });
  els.closeReviewBtn.addEventListener("click", () => els.reviewDialog.close());
  els.submitReviewBtn.addEventListener("click", submitReview);
  els.closeInstallBtn.addEventListener("click", () => els.installDialog.close());
  els.copyUrlBtn.addEventListener("click", copyCurrentUrl);
  els.ratingButtons.addEventListener("click", (event) => {
    const rating = event.target.closest("[data-rating]");
    if (!rating) return;
    state.reviewRating = Number(rating.dataset.rating);
    qsa("[data-rating]").forEach((button) => button.classList.toggle("active", button === rating));
  });
  els.startGameBtn.addEventListener("click", startGame);
  els.resetGameBtn.addEventListener("click", resetGame);
  els.doctorForm.addEventListener("submit", handleDoctor);
  els.bindForm.addEventListener("submit", handleBind);

  document.body.addEventListener("click", (event) => {
    const viewButton = event.target.closest("[data-view-link]");
    if (viewButton) return switchView(viewButton.dataset.viewLink);

    const openView = event.target.closest("[data-open-view]");
    if (openView) return switchView(openView.dataset.openView);

    const scrollTop = event.target.closest("[data-scroll-top]");
    if (scrollTop) return window.scrollTo({ top: 0, behavior: "smooth" });

    const need = event.target.closest("[data-need]");
    if (need) return handleNeed(need.dataset.need);

    const typeSection = event.target.closest("[data-type-section]");
    if (typeSection) {
      state.typeSection = typeSection.dataset.typeSection;
      return renderTypePage();
    }

    const shortcut = event.target.closest("[data-shortcut-category]");
    if (shortcut) return renderFilteredType(shortcut.dataset.shortcutCategory);

    const card = event.target.closest("[data-card-id]");
    if (card && !event.target.closest("[data-buy-id]")) return openProductDetail(Number(card.dataset.cardId));

    const detail = event.target.closest("[data-detail-id]");
    if (detail) return openProductDetail(Number(detail.dataset.detailId));

    const buy = event.target.closest("[data-buy-id]");
    if (buy) return addToCart(Number(buy.dataset.buyId), buy.dataset.source || "catalog");

    const inc = event.target.closest("[data-inc]");
    if (inc) return updateCart(Number(inc.dataset.inc), 1);

    const dec = event.target.closest("[data-dec]");
    if (dec) return updateCart(Number(dec.dataset.dec), -1);

    const remove = event.target.closest("[data-remove]");
    if (remove) return updateCart(Number(remove.dataset.remove), -999);

    const toggle = event.target.closest("[data-toggle-cart]");
    if (toggle) return toggleCartSelection(Number(toggle.dataset.toggleCart));

    const orderAction = event.target.closest("[data-order-action]");
    if (orderAction) return handleOrderAction(orderAction.dataset.orderAction, Number(orderAction.dataset.orderId));

    const orderFilter = event.target.closest("[data-order-filter]");
    if (orderFilter) {
      state.orderFilter = orderFilter.dataset.orderFilter;
      return renderOrders();
    }

    const orderShortcut = event.target.closest("[data-order-shortcut]");
    if (orderShortcut) return switchView("orders", { orderFilter: orderShortcut.dataset.orderShortcut });

    const tool = event.target.closest("[data-tool-index]");
    if (tool) return handleTool(Number(tool.dataset.toolIndex));
  });

  els.installBtn.addEventListener("click", async () => {
    if (deferredInstallPrompt) {
      deferredInstallPrompt.prompt();
      const choice = await deferredInstallPrompt.userChoice;
      deferredInstallPrompt = null;
      if (choice.outcome === "accepted") toast("安装请求已提交", "success");
      else openInstallGuide();
    } else {
      openInstallGuide();
    }
  });

  window.addEventListener("beforeinstallprompt", (event) => {
    event.preventDefault();
    deferredInstallPrompt = event;
  });
}

function init() {
  bindEvents();
  if ("serviceWorker" in navigator) navigator.serviceWorker.register("./sw.js").catch(() => {});
  const account = localStorage.getItem(STORAGE.session);
  const user = account ? loadUsers().find((item) => item.account === account) : null;
  if (user) showApp(user);
  else showAuth("login");
  addMessage("ai", "双端体验版已就绪。登录后可以完整体验 AI 推荐、商品分类、购物车、订单、评价和 AI 记录。");
}

init();
