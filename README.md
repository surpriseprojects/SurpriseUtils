---
SurpriseUtils
---

#### 前言

目前只有两个 module：

BaseLibrary 用来收集一些开发常用的工具类；

DevelopTools 即开发者工具；

#### BaseLibrary

| 工具类                                                       | 说明                                    |
| ------------------------------------------------------------ | --------------------------------------- |
| [DeviceOSUtil](https://github.com/surpriseprojects/SurpriseUtils/blob/master/base_library/src/main/java/top/omooo/base_library/utils/DeviceOSUtil.java) | 判断手机系统（比如华为、小米、Vivo 等） |
| [StatusBarUtil](https://github.com/surpriseprojects/SurpriseUtils/blob/master/base_library/src/main/java/top/omooo/base_library/utils/StatusBarUtil.java) | 适配沉浸式状态栏                        |
|                                                              |                                         |



#### DevelopTools

| 工具                                                         | 使用                                                         | 说明      |
| ------------------------------------------------------------ | ------------------------------------------------------------ | --------- |
| [PreferenceManagerActivity](https://github.com/surpriseprojects/SurpriseUtils/blob/master/develop_tools/src/main/java/top/omooo/develop_tools/sp/PreferenceManagerActivity.kt) | 参考：[PreferenceActivity](https://github.com/surpriseprojects/SurpriseUtils/blob/master/app/src/main/java/top/omooo/surpriseutils/ui/PreferenceActivity.java) | SP 管理类 |
|                                                              |                                                              |           |
|                                                              |                                                              |           |

#### 学习总结

旨在记录写这个库的过程中学到的一些知识点。

##### SharedPreferences

最初我想看看 SP 的文件在哪个目录下，这个时候就需要用 adb 了。

我们知道，直接 adb shell 就可以进入命令行来，但是如果有多个设备的话，就不能这样了，需要以下操作：

```
adb devices		//查看所有连接的设备

//这时候可能打印的有：
//device1
//device2

//然后连接指定设备就需要
adb -s [deviceName] shell
```

进入指定 App 的 data/data/ 目录下：

```
adb-run [package_name]		//指定应用包名
```

然后可以列出目录下的所有文件：

```
gemini:/data/data/top.omooo.surpriseutils $ ls
cache  code_cache  shared_prefs 
```

然后进入 shared_prefs 目录即可：

```mariadb
gemini:/data/data/top.omooo.surpriseutils $ cd shared_prefs
gemini:/data/data/top.omooo.surpriseutils/shared_prefs $ ls
sp_file_1.xml  sp_file_2.xml  
gemini:/data/data/top.omooo.surpriseutils/shared_prefs $ cat sp_file_1.xml
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <int name="intKey" value="2333" />
    <boolean name="booleanKey" value="true" />
    <string name="stringKey">Omooo</string>
    <long name="longKey" value="2333" />
    <float name="floatKey" value="1.0" />
</map>
gemini:/data/data/top.omooo.surpriseutils/shared_prefs $ 
```

这没什么可说的，但是如果我们想要查看每个文件大小可以：

```
gemini:/data/data/top.omooo.surpriseutils/shared_prefs $ ls -l
total 8
-rw-rw---- 1 u0_a827 u0_a827 283 2019-06-12 12:18 sp_file_1.xml
-rw-rw---- 1 u0_a827 u0_a827 286 2019-06-12 12:18 sp_file_2.xml
```

那每个参数的含义是什么呢？

![](https://upload-images.jianshu.io/upload_images/9003674-5f5fed4d37e00f68.png?imageMogr2/auto-orient/)

图片引自：[https://www.jianshu.com/p/b71f3a5af337](https://www.jianshu.com/p/b71f3a5af337)

比较难理解的就是那个链接数了，这里就涉及到一些 Linux 知识了：

我们知道文件都有文件名和数据，这在 Linux 上被分为两个部分：用户数据（user data）与元数据（metadata）。用户数据，即文件数据块，数据块是记录文件真实内容的地方；而元数据则是文件的附加属性，如文件大小、创建时间、所有者等信息。在 Linux 中，元数据的 inode 号（inode 是文件元数据的一部分但其并不包含文件名，inode 号即索引节点号）才是文件的唯一标识而非文件名。文件名仅是为了方便人们的记忆和使用，系统或程序通过 inode 号寻找正确的文件数据块。下图展示了程序通过文件名获取文件内容的过程：

![](https://www.ibm.com/developerworks/cn/linux/l-cn-hardandsymb-links/image001.jpg)

为解决文件的共享使用，Linux 系统引入了两种链接：硬链接与软链接。链接为 Linux 系统解决了文件的共享使用，还带来了隐藏文件路径、增加权限安全及节省存储等好处。若一个 inode 号对应多个文件名，则称这些文件为硬链接。换言之，硬链接就是同一个文件使用了多个别名。每添加一个硬链接，文件链接数就加一。现 Linux 文件系统中的目录均隐藏了两个特殊的目录：当前目录（.）与父目录（..）。查看这两个特殊目录的 inode 号可知其实这两个目录就是两个硬链接。

软链接与硬链接不同，若文件用户数据块中存放的内容是另一个文件的路径名的指向，则该文件就是软链接。软链接就是一个普通文件，只是数据块内容有点特殊。

想了解更多请参考：[理解 Linux 的硬链接与软链接](https://www.ibm.com/developerworks/cn/linux/l-cn-hardandsymb-links/index.html)