package com.cloudsoft.service;

import java.util.List;

import com.cloudsoft.entity.Schedule;

public interface ScheduleService {
	
	public Schedule findById(Integer id);
	
	public List<Schedule> findAllSchedules();

	public int deleteByPrimaryKey(Integer id);
	
	public int save(Schedule schedule);
	
	public List<Schedule> findByMovieId(Integer id);

}
