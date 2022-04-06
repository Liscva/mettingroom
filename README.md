# mettingroom


会议预约系统后台,使用springboot + mybatis-plus + flyway +mysql + smart-doc

需搭配前台程序使用[前台程序](https://github.com/Liscva/mettingroom-vue)

有问题欢迎联系QQ :3214444445




部分后台功能正在开发中
# 安装方式
## 1.导入项目
自行编译[核心包](https://github.com/Liscva/LiscvaFramework)

## 2.导入数据库
修改application.yml文件中的数据库配置
直接启动即可，flyway会自动创建所需要的数据，默认用户 root 123456

## 3.导入日期数据
默认可预约日期为未来三天

执行test文件夹下面的测试用例 MrReserveDayTimeServiceTest
（会默认生成未来三天的日期数据,之后的数据会由定时任务执行EmptyTimeInputTask
参数配置都存在mr_config表）

## 4.启动程序
MettingRoomApplication启动类启动即可