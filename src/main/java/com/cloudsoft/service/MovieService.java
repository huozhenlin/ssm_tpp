package com.cloudsoft.service;

import java.util.List;

import com.cloudsoft.entity.Movie;

public interface MovieService {

	public List<Movie> findAllMovies();

	public int deleteByPrimaryKey(Integer id);
	
	public int save(Movie movie);
	
	public Movie findById(Integer id);
	
	public Movie findByName(String name);
	
}
