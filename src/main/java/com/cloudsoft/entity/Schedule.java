package com.cloudsoft.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Schedule {
    private Integer id;
    
    private Integer movieid;

    private String hallname;

    private String moviename;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date starttime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovieid() {
		return movieid;
	}

	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}

	public String getHallname() {
        return hallname;
    }

    public void setHallname(String hallname) {
        this.hallname = hallname == null ? null : hallname.trim();
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename == null ? null : moviename.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}