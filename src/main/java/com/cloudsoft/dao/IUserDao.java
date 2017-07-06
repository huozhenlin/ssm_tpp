package com.cloudsoft.dao;

import com.cloudsoft.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by hzl on 2017/3/29.
 */
public interface IUserDao {
    //用接口的形式定义了数据库操作方法
    //我们只需在Mybatis映射文件中对其进行映射就可以直接使用
    public User selectById(int id);
    public User selectByName(String username);


    public User selectByNameandPass(@Param("username") String username,
                                    @Param("password") String password);

//    找回密码
    public User findPassword(String email);

//    两个参数的添加用户
    public void addUser(@Param("username") String username,
                        @Param("password") String password);

//    三个参数的添加用户
    public void addUser(@Param("username") String username,
                        @Param("password") String password,
                        @Param("email") String email);

//    四个参数的添加用户
    public void addUsers(@Param("username") String username,
                         @Param("password") String password,
                         @Param("email") String email,
                         @Param("pic") String pic);

//    检查邮箱是否存在
    public User checkmail(String email);


    public void insert(String name);

    //修改用户密码
    public int alter(@Param("username") String username, @Param("password") String password);

}
