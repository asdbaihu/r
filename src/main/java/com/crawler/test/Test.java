package com.crawler.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crawler.utils.httpclient.HttpClientUtil;

import java.io.*;
import java.util.*;

/**
 * Created by hcj on 2018/4/27.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        //初始化四个不同的学生
        Student stu1 = new Student("路人甲", 20);
        Student stu2 = new Student("路人已", 18);
        Student stu3 = new Student("路人丙", 16);
        Student stu4 = new Student("路人丁", 19);
        //新建List把学生加进List
        List<Student> stuList = new ArrayList<>();
        stuList.add(stu1);
        stuList.add(stu2);
        stuList.add(stu3);
        stuList.add(stu4);
        System.out.println("排序前:=====");
        for(Student stu :stuList){
            System.out.println("姓名："+stu.getName() +" 年龄"+stu.getAge());
        }
        //排序
        Collections.sort(stuList, stu1);  //第一个参数为List 第二个参数为对象的一个实例
        System.out.println("排序后:=====");
        for(Student stu :stuList){
            System.out.println("姓名："+stu.getName() +" 年龄"+stu.getAge());
        }
    }

    static class Student implements Comparator<Student>{
        private String name; //姓名
        private int age;  //年龄

        //重写 比较方法  本次例子定义为按年龄比较
        @Override
        public int compare(Student o1, Student o2) {
            System.out.println("-----");
            if(o1.getAge() > o2.getAge()){
                return -1;
            }else{
                return 1;
            }
        }


        public Student(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }


        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }


}
