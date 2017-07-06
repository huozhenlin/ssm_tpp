package com.cloudsoft.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Movie {
    private Integer id;

    private String name;

    private String director;

    private String star;

    private String summary;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releasetime;

    private Long price;
    
    private String photourl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director == null ? null : director.trim();
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star == null ? null : star.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}


}