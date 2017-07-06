package com.cloudsoft.entity;

/**
 * Created by hzl on 2017/4/9.
 */
public class Users {
    private String name;
    private  String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
    //重写toString()方便测试
    @Override
    public String toString() {
        return "Users [name=" + name + ", password=" + password +  "]";
    }
}
