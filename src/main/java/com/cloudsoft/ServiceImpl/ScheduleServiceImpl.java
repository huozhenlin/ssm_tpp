package com.cloudsoft.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsoft.dao.ScheduleMapper;
import com.cloudsoft.entity.Schedule;
import com.cloudsoft.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	ScheduleMapper scheduleMapper;

	/**
	 * 根据id获取电影票信息
	 */
	public Schedule findById(Integer id) {
		return scheduleMapper.selectByPrimaryKey(id);
	}

	public List<Schedule> findAllSchedules() {
		return scheduleMapper.selectAllSchedules();
	}

	public int deleteByPrimaryKey(Integer id) {
		return scheduleMapper.deleteByPrimaryKey(id);
	}

	public int save(Schedule schedule) {
		return scheduleMapper.insert(schedule);
	}

	public List<Schedule> findByMovieId(Integer id) {
		return scheduleMapper.selectByMovieId(id);
	}

}
