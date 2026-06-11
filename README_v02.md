# VibeAIMall v02 - 植物大战僵尸版

基于原版VibeAIMall的增强版，新增完整植物大战僵尸游戏模块。

## 新增功能

### 🎮 植物大战僵尸游戏
- **完整游戏系统**：阳光收集、植物种植、僵尸战斗
- **多种植物**：豌豆射手、向日葵、坚果墙等
- **多种僵尸**：普通僵尸、路障僵尸等
- **关卡系统**：完整的关卡流程
- **自定义开场**：孤寒艺术字动画

### 游戏入口
打开应用 → 我的页面 → 点击"植物大战僵尸"

## 截图

![植物大战僵尸游戏](screenshots/pvz_game.png)

## 技术栈

- **游戏引擎**：Cocos2D-Android
- **语言**：Java
- **最低SDK**：API 16 (Android 4.1)

## 版本说明

- **v01 (master分支)**：原始版本，包含商城、AI助手等功能
- **v02 (v02分支)**：新增植物大战僵尸游戏模块

## 构建说明

```bash
# 克隆项目
git clone -b v02 https://github.com/nai-he/VibeAIMall.git

# 构建
cd VibeAIMall
./gradlew assembleDebug

# 安装
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## 项目结构

```
shixun/
├── app/           # 主应用模块
├── pvz/           # 植物大战僵尸游戏模块
└── pokemon/       # 口袋妖怪模块
```

## 开源协议

本项目基于原作者的开源项目进行二次开发。

---

**Powered by 孤寒**
