package com.zzxy.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
public class Cat {

    //@Bean
    //当name=dog存在时才指定该方法
    //@ConditionalOnBean(name="dog")
    //当name=dog不存在时才执行该方法
    //@ConditionalOnMissingBean(name = "dog")
    /*public Pig test(Dog dog){
        dog.eat();
        return new Pig();
    }*/

    @Bean
    //当com.zzxy.Tigger类存在时才执行该方法
    //@ConditionalOnClass(name = "com.zzxy.bean.Dog")
    //当com.zzxy.bean.Dog类不存在时才执行该方法
    /*@ConditionalOnMissingClass(value = "com.zzxy.bean.Dog")
    public Pig test(){
        return new Pig();
    }*/

    //@Conditional()
    public Pig test(){
        return new Pig();
    }
}
