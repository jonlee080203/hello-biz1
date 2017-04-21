package com.lee.redis.pojo;

import java.util.Date;

public class Student {
	private int id;
	private String name;
	private Date birthday;
	private String description;
	private int avgscore;

	public Student(int id, String name, Date birthday, String description,
			int avgscore) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.description = description;
		this.avgscore = avgscore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAvgscore() {
		return avgscore;
	}

	public void setAvgscore(int avgscore) {
		this.avgscore = avgscore;
	}


	@Override
	public String toString() {
		return this.id + "&" + this.name + "&" + this.birthday + "&"
				+ this.description + "&" + this.avgscore;
	}
}
