# 开发用的工具类集合
## Step 1. Add the JitPack repository to your build file
### Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
## Step 2. Add the dependency
```
	dependencies {
		compile 'com.github.User:Repo:Tag'
	}
```
## 基类
- BrioalBaseActivity
- BrioalBaseFragment
- BrioalBaseDialog

## 监听器
- BrioalViewOperatorListener
- BrioalDataLoadListener

## 工具类
- StringUtil
- BLog
- NetWorkUtil
- ScreenUtil
- VersionUtil
- SoftInputUtil
- SizeUtil
- SerializeUtil
- MD5Utils
- ListUtil
- GlideUtil
- DateFormatUtil
- ArithUtil
- BitmapUtil
- BtnClickUtils
- DataCleanUtil
- ScreenListener
- IoUtil
- NumberUtil
- CacheUtil
- RegexUtils

## 资源
- 颜色
- 主题


