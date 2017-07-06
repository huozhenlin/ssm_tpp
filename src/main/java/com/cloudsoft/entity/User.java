package com.cloudsoft.entity;

//public class User {
//    private Integer id;
//
//    private String username;
//
//    private String password;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username == null ? null : username.trim();
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password == null ? null : password.trim();
//    }
//}

/**
 * Created by hzl on 2017/3/29.
 */
public class User {
    private int id;
    private String username;
    private String code;
    private String email;
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //不需要持久到DB的属性就使用该注解
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
        super();
    }
}