package com.cloudsoft.dao;

import java.util.List;

import com.cloudsoft.entity.UserOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserOrderMapper {
    @Delete({
        "delete from userorder",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into userorder (id, userid, ",
        "movieid, scheduleid, ",
        "seat, price)",
        "values (#{userorder.id,jdbcType=INTEGER}, #{userorder.userid,jdbcType=INTEGER}, ",
        "#{userorder.movieid,jdbcType=INTEGER}, #{userorder.scheduleid,jdbcType=INTEGER}, ",
        "#{userorder.seat,jdbcType=VARCHAR}, #{userorder.price,jdbcType=DECIMAL})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "userorder.id")
    int insert(@Param("userorder") UserOrder record);

    int insertSelective(UserOrder record);

    @Select({
        "select",
        "id, userid, movieid, scheduleid, seat, price",
        "from userorder",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOrder record);

    @Update({
        "update userorder",
        "set userid = #{userid,jdbcType=INTEGER},",
          "movieid = #{movieid,jdbcType=INTEGER},",
          "scheduleid = #{scheduleid,jdbcType=INTEGER},",
          "seat = #{seat,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserOrder record);



    @Select({"select * from userorder"})
    @ResultMap("BaseResultMap")
    List<UserOrder> selectAllOrders();
    
    
    @Select({
        "select",
        "id, userid, movieid, scheduleid, seat, price",
        "from userorder",
        "where userid = #{userid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    List<UserOrder> selectByUserId(Integer id);
    
}