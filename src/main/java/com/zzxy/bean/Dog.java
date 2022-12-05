package com.zzxy.bean;

import org.springframework.stereotype.Component;

@Component
public class Dog{
    public Dog(){
        System.out.println("我家门前");
    }

    public void eat() {
        System.out.println("狗拿耗子");
    }
}
