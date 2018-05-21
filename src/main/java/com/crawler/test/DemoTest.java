package com.crawler.test;


import java.util.function.IntUnaryOperator;

class DemoClass{
    public static int startMethod(int i){
        return i*3;
    }

    public int towMethod(int i){
        System.out.println("实例方法可以访问this"+this);
        return i*3;
    }
}

public class DemoTest {

    public static void main(String[] args){
        // 静态方法的方法引用
        IntUnaryOperator methodRefrence1 = DemoClass::startMethod;
        System.out.println(methodRefrence1.applyAsInt(111));

        DemoClass demo = new DemoClass();

        // 实例方法的方法引用
        IntUnaryOperator methodRefrence2 = demo::towMethod;
        System.out.println(methodRefrence2.applyAsInt(111));

        DemoClass demoClass=new DemoClass();

    }

}
