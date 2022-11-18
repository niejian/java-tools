# 关于
一套java相关的工具集；随拿随用
# 说明
## shell-tools
此目录收集平时可能会用到的一些shell脚本，脚本功能说明如下：
### maven_clean.sh
此脚本的作用是对于那些存储在本地的maven项目，按照项目名来依次执行`mvn clean`指令，来清除本地缓存的`target`目录信息，从而减轻本地磁盘压力；

需要注意的点是：
* 所有的项目都是java maven项目；执行方式：`./Desktop/maven_clean.sh /Users/a/myproject/java`；后面参数是路径
