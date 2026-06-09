#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
截取PWA在iPhone模拟环境下的截图
"""
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
import time
import os

# iPhone 13 Pro 的屏幕尺寸
IPHONE_WIDTH = 390
IPHONE_HEIGHT = 844

def setup_driver():
    """配置Chrome驱动，模拟iPhone"""
    chrome_options = Options()

    # 设置移动设备模拟
    mobile_emulation = {
        "deviceMetrics": {
            "width": IPHONE_WIDTH,
            "height": IPHONE_HEIGHT,
            "pixelRatio": 3.0
        },
        "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.0 Mobile/15E148 Safari/604.1"
    }
    chrome_options.add_experimental_option("mobileEmulation", mobile_emulation)

    # 无头模式
    chrome_options.add_argument('--headless=new')
    chrome_options.add_argument('--disable-gpu')
    chrome_options.add_argument('--no-sandbox')
    chrome_options.add_argument('--disable-dev-shm-usage')

    driver = webdriver.Chrome(options=chrome_options)
    driver.set_window_size(IPHONE_WIDTH, IPHONE_HEIGHT)

    return driver

def take_screenshot(url, output_path, wait_seconds=3):
    """截取指定URL的截图"""
    driver = setup_driver()
    try:
        print(f"正在访问: {url}")
        driver.get(url)
        time.sleep(wait_seconds)  # 等待页面加载

        # 确保输出目录存在
        os.makedirs(os.path.dirname(output_path), exist_ok=True)

        driver.save_screenshot(output_path)
        print(f"截图已保存: {output_path}")

    finally:
        driver.quit()

if __name__ == "__main__":
    base_url = "http://localhost:8080"
    output_dir = "../docs/screenshots"

    screenshots = [
        ("index.html", "pwa_iphone_login.png", 3, "登录页"),
        ("index.html#home", "pwa_iphone_home.png", 4, "首页"),
        ("index.html#products", "pwa_iphone_products.png", 4, "商品列表"),
        ("index.html#cart", "pwa_iphone_cart.png", 3, "购物车"),
    ]

    for url_path, output_name, wait_time, desc in screenshots:
        print(f"\n正在截取: {desc}")
        take_screenshot(
            f"{base_url}/{url_path}",
            f"{output_dir}/{output_name}",
            wait_seconds=wait_time
        )

    print("\n✅ 所有截图完成！")
