#!/bin/bash
if [ -z "$1" ]; then
  #statements
  echo "请输入需要执行mvn clean的目录"
  exit 0
fi
clean_dir=$1
# 获取目录下所有文件
cd $clean_dir
java_dir=$(ls -l ./|awk '/^d/{print $NF}')
for i in $java_dir
do
  echo "进入文件夹 $i"
  cd $i
  echo "执行指令mvn clean"
  mvn clean
  cd ..

done

# 获取目录下所有文件

