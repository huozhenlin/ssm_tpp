package com.cloudsoft.dto;

import java.util.Date;

public class OrderDetail {
	
	private Integer orderid;
	private String username;
	private String moviename;
	private Date starttime;
	private String hallname;
	private String seat;
	private Long price;
	
	
	
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getHallname() {
		return hallname;
	}
	public void setHallname(String hallname) {
		this.hallname = hallname;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	
}
