package com.cloudsoft.ServiceImpl;

import java.util.List;

import com.cloudsoft.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsoft.dao.MovieMapper;
import com.cloudsoft.entity.Movie;
import com.cloudsoft.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieMapper movieMapper;
	
	/**
	 * 查出所有电影信息
	 */
	public List<Movie> findAllMovies() {
		
		return movieMapper.selectAllMovies();
	}

	/**
	 * 根据id删除电影信息
	 */
	public int deleteByPrimaryKey(Integer id) {
		
		return movieMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 添加电影信息
	 */
	public int save(Movie movie) {
		
		return movieMapper.insert(movie);
	}

	/**
	 * 根据id获取电影信息
	 */
	public Movie findById(Integer id) {

		return movieMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据电影名获取电影信息
	 */
	public Movie findByName(String name) {
		
		return movieMapper.selectByName(name);
	}

	/**
	 * 根据id修改电影信息
	 * 需要传入movie实体
	 */
	public int updateMovieById(Movie movie) {
		return movieMapper.updateByPrimaryKey(movie);
	}

	@Override
	public int update(Movie movie) {
		return movieMapper.updateByPrimaryKey(movie);
	}


}
