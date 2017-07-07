package com.cloudsoft.dao;

import java.util.List;

import com.cloudsoft.entity.Movie;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MovieMapper {
    @Delete({
        "delete from movie",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into movie (id, name, ",
        "director, star, ",
        "summary, releasetime, ",
        "price,photourl)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{director,jdbcType=VARCHAR}, #{star,jdbcType=VARCHAR}, ",
        "#{summary,jdbcType=VARCHAR}, #{releasetime,jdbcType=TIMESTAMP}, ",
        "#{price,jdbcType=DECIMAL},#{photourl,jdbcType=VARCHAR})"
    })
    int insert(Movie record);

    int insertSelective(Movie record);

    @Select({
        "select",
        "id, name, director, star, summary, releasetime, price,photourl",
        "from movie",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Movie selectByPrimaryKey(Integer id);


    @Update({
        "update movie",
        "set name = #{name,jdbcType=VARCHAR},",
          "director = #{director,jdbcType=VARCHAR},",
          "star = #{star,jdbcType=VARCHAR},",
          "summary = #{summary,jdbcType=VARCHAR},",
          "releasetime = #{releasetime,jdbcType=TIMESTAMP},",
          "price = #{price,jdbcType=DECIMAL},",
          "photourl = #{photourl,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Movie record);

    @Select({"select * from movie"})
    @ResultMap("BaseResultMap")
    List<Movie> selectAllMovies();
    
    @Select({
        "select",
        "id, name, director, star, summary, releasetime, price,photourl",
        "from movie",
        "where name = #{name,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    Movie selectByName(String name);
}