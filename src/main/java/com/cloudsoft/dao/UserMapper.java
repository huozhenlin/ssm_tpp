package com.cloudsoft.dao;

import java.util.List;

import com.cloudsoft.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Update("update user set password = #{password} where id = #{id}")
    int updateUserById(User user);

    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (id, username, ",
        "password)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @Select({
        "select",
        "*",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    User selectByPrimaryKey(Integer id);


    @Select({
        "select",
        "id, username, password",
        "from user",
        "where username = #{username,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    User selectByUsernameAndPassword(User record);
    
    
    
    @Select({
        "select",
        "id, username, password",
        "from user",
        "where  username = #{name,jdbcType=VARCHAR} and password = #{pwd,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    User selectByNameAndPwd(@Param("name")String username,@Param("pwd")String password);



    @Select({"select * from user"})
    @ResultMap("BaseResultMap")
    List<User> selectAllUsers();
}