package com.cloudsoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cloudsoft.entity.Schedule;

public interface ScheduleMapper {
    @Delete({
        "delete from schedule",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into schedule (id,movieid, hallname, ",
        "moviename, starttime, ",
        "endtime)",
        "values (#{id,jdbcType=INTEGER},#{movieid,jdbcType=INTEGER}, #{hallname,jdbcType=VARCHAR}, ",
        "#{moviename,jdbcType=VARCHAR}, #{starttime,jdbcType=TIMESTAMP}, ",
        "#{endtime,jdbcType=TIMESTAMP})"
    })
    int insert(Schedule record);

    int insertSelective(Schedule record);

    @Select({
        "select",
        "id, movieid, hallname, moviename, starttime, endtime",
        "from schedule",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Schedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Schedule record);

    @Update({
        "update schedule",
        "set hallname = #{hallname,jdbcType=VARCHAR},",
          "movieid = #{movieid,jdbcType=INTEGER}",
          "moviename = #{moviename,jdbcType=VARCHAR},",
          "starttime = #{starttime,jdbcType=TIMESTAMP},",
          "endtime = #{endtime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Schedule record);
    
    
    @Select({"select * from schedule"})
    @ResultMap("BaseResultMap")
    List<Schedule> selectAllSchedules();
    
    @Select({
        "select",
        "id, movieid, hallname, moviename, starttime, endtime",
        "from schedule",
        "where movieid = #{movieid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    List<Schedule> selectByMovieId(Integer id);
    
}