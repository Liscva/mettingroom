# mettingroom
会议预约系统后台,使用springboot + mybatis-plus + +mysql + smart-doc

需搭配前台程序使用[前台程序](https://github.com/Liscva/mettingroom-vue)

# 安装方式
## 1.导入项目
引入lib目录下面所有的jar包
或者
自行编译[核心包](https://github.com/Liscva/LiscvaFramework)

## 2.导入数据库
在mysql数据库中执行resources/metting.sql文件
修改application.yml文件中的数据库配置

## 3.导入日期数据
默认可预约日期为未来三天

执行test文件夹下面的测试用例 MrReserveDayTimeServiceTest
（会默认生成未来三天的日期数据,之后的数据会由定时任务执行EmptyTimeInputTask
参数配置都存在mr_config表）

## 4.启动程序
MettingRoomApplication启动类启动即可