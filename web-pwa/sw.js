const CACHE_NAME = "vibe-ai-mall-pwa-v11";
const ASSETS = [
  "./",
  "./index.html",
  "./styles.css?v=11",
  "./app.js?v=11",
  "./manifest.webmanifest",
  "./assets/app-icon.svg",
  "./assets/user.png",
  "./assets/message.png",
  "./assets/setting.png",
  "./assets/jdk9.png",
  "./assets/buyit.png",
  "./assets/main_home.png",
  "./assets/main_type.png",
  "./assets/main_community.png",
  "./assets/main_cart.png",
  "./assets/main_user.png",
  "./assets/main_cart_press.png",
  "./assets/main_user_press.png",
  "./assets/daifukuan.png",
  "./assets/daishouhuo.png",
  "./assets/daipingjia.png",
  "./assets/shouhou.png",
  "./assets/wodedingdan.png",
  "./assets/tools1.png",
  "./assets/tools4.png",
  "./assets/tools9.png",
  "./assets/tools10.png",
  "./assets/tools11.png",
  "./assets/tools12.png",
  "./assets/fj.png",
  "./assets/jdk4.png",
  "./assets/a100.png",
  "./assets/a101.png",
  "./assets/a102.png",
  "./assets/a103.png",
  "./assets/a104.png",
  "./assets/a105.png",
  "./assets/ipad.jpg",
  "./assets/ipad2.jpg",
  "./assets/ipad3.jpg",
  "./assets/xiaomi10.jpg",
  "./assets/huaweip40.jpg",
  "./assets/a301.png",
  "./assets/a302.png",
  "./assets/a303.png",
  "./assets/a304.png",
  "./assets/a305.png",
  "./assets/a306.png",
  "./assets/a401.png",
  "./assets/a402.png",
  "./assets/a403.png",
  "./assets/a501.png",
  "./assets/a502.png",
  "./assets/a503.png",
  "./assets/a504.png",
  "./assets/a505.png",
  "./assets/a506.png",
  "./assets/a601.png",
  "./assets/a602.png",
  "./assets/a603.png",
  "./assets/a701.png",
  "./assets/a702.png",
  "./assets/a703.png",
  "./assets/a704.png",
  "./assets/a705.png",
  "./assets/a706.png",
  "./assets/c101.png",
  "./assets/c102.png",
  "./assets/c201.png",
  "./assets/c202.png",
  "./assets/c203.png",
  "./assets/c301.png",
  "./assets/c302.png",
  "./assets/c303.png",
  "./assets/c501.png",
  "./assets/anmuxi.jpg",
  "./assets/img.png",
  "./assets/img_1.png",
  "./assets/img_2.png",
  "./assets/kouhong.jpg",
  "./assets/bamai8.jpg",
  "./assets/peach_pic.png"
];

self.addEventListener("install", (event) => {
  event.waitUntil(caches.open(CACHE_NAME).then((cache) => cache.addAll(ASSETS)));
  self.skipWaiting();
});

self.addEventListener("activate", (event) => {
  event.waitUntil(
    caches.keys().then((keys) =>
      Promise.all(keys.filter((key) => key !== CACHE_NAME).map((key) => caches.delete(key)))
    )
  );
  self.clients.claim();
});

self.addEventListener("fetch", (event) => {
  if (event.request.method !== "GET") return;
  const url = new URL(event.request.url);
  const shouldNetworkFirst =
    event.request.mode === "navigate" ||
    url.pathname.endsWith(".html") ||
    url.pathname.endsWith(".js") ||
    url.pathname.endsWith(".css");
  if (shouldNetworkFirst) {
    event.respondWith(
      fetch(event.request)
        .then((response) => {
          const copy = response.clone();
          caches.open(CACHE_NAME).then((cache) => cache.put(event.request, copy));
          return response;
        })
        .catch(() => caches.match(event.request))
    );
    return;
  }
  event.respondWith(
    caches.match(event.request).then((cached) => cached || fetch(event.request))
  );
});
