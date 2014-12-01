package com.timetalent.client.entities;

import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：Searchpackage  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-11-3 上午10:58:20 
 ******************************************/
public class Userinfomorepackage {
	String user_id = "";
	String bust = "";
	String waist = "";
	String hip = "";
	String height = "";
	String weight = "";
	List<zuopinpackage> works = null;
	List<awardpackage> award = null;
	List<advocatuspackage> advocatus = null;
	List<MyPhotopackage> photos = null;
	
	public List<zuopinpackage> getWorks() {
		return works;
	}
	public void setWorks(List<zuopinpackage> works) {
		this.works = works;
	}
	public List<awardpackage> getAward() {
		return award;
	}
	public void setAward(List<awardpackage> award) {
		this.award = award;
	}
	public List<advocatuspackage> getAdvocatus() {
		return advocatus;
	}
	public void setAdvocatus(List<advocatuspackage> advocatus) {
		this.advocatus = advocatus;
	}
	public List<MyPhotopackage> getPhotos() {
		return photos;
	}
	public void setPhotos(List<MyPhotopackage> photos) {
		this.photos = photos;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBust() {
		return bust;
	}
	public void setBust(String bust) {
		this.bust = bust;
	}
	public String getWaist() {
		return waist;
	}
	public void setWaist(String waist) {
		this.waist = waist;
	}
	public String getHip() {
		return hip;
	}
	public void setHip(String hip) {
		this.hip = hip;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
}
