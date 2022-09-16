package com.purple.model;

import java.util.Date;

/**
 * Actor
 */
public class Actor {
	private long id;
	private String name;
	private Date birthDay;
	private Date deathDay;
	private String biography;
	private String profilePath;
	private String homePage;

	public Actor() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Date getDeathDay() {
		return deathDay;
	}

	public void setDeathDay(Date deathDay) {
		this.deathDay = deathDay;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
}