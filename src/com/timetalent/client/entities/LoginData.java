package com.timetalent.client.entities;

import android.util.Log;


/******************************************
 * 类描述： 登录信息
 * 类名称：LoginData  
 * @version: 1.0
 * @time: 2014-2-20 下午3:30:21 
 ******************************************/
public class LoginData {   
	private String	_session_id; //会话ID，用来验证是否登陆
	private String	id; //用户编号
	private String	username; //用户名
	private String	phone; //手机号
	private String	email; //邮箱
	private String	sex; //性别 （1男2女）
	private String	nickname; //昵称
	private String	realname; //真实姓名
	private String	birthday; //生日
	private String	avatar; //头像路径 null为没有头像
	private String	constella; //星座
	private String	certificate; //身份证
	private String	province; //省
	private String	city; //市
	private String	money; //金额 格式0.00
	private String	score; //积分
	private String	type; //身份类型 星探scout明星star粉丝fans
	private String	major; //专业 导演director经纪人agent歌手singer模特model演员actor其他other
	private String	add_time; //注册时间
	private String	last_time; //最后登陆时间
	private String	update_time; //最后更新时间
	private String	sort; //排序
	private String	status; //状态 正常1
	private String	age; //年龄
	private String	loyal_pass; //是否认证
	private String	userinfo_percent; //信息完整度
	/**
	 * @return session_id : return the property session_id.
	 */
	public String getSession_id() {
		return _session_id;
	}
	/**
	 * @param session_id : set the property session_id.
	 */
	public void setSession_id(String session_id) {
		this._session_id = session_id;
	}
	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return username : return the property username.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username : set the property username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return phone : return the property phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone : set the property phone.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return email : return the property email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email : set the property email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return sex : return the property sex.
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex : set the property sex.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return nickname : return the property nickname.
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname : set the property nickname.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return realname : return the property realname.
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * @param realname : set the property realname.
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * @return birthday : return the property birthday.
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday : set the property birthday.
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return avatar : return the property avatar.
	 */
	public String getAvatar() {
		Log.i("touxiangdizhi", avatar);
		return avatar;
	}
	/**
	 * @param avatar : set the property avatar.
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * @return constella : return the property constella.
	 */
	public String getConstella() {
		return constella;
	}
	/**
	 * @param constella : set the property constella.
	 */
	public void setConstella(String constella) {
		this.constella = constella;
	}
	/**
	 * @return certificate : return the property certificate.
	 */
	public String getCertificate() {
		return certificate;
	}
	/**
	 * @param certificate : set the property certificate.
	 */
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	/**
	 * @return province : return the property province.
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province : set the property province.
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return city : return the property city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city : set the property city.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return money : return the property money.
	 */
	public String getMoney() {
		return money;
	}
	/**
	 * @param money : set the property money.
	 */
	public void setMoney(String money) {
		this.money = money;
	}
	/**
	 * @return score : return the property score.
	 */
	public String getScore() {
		return score;
	}
	/**
	 * @param score : set the property score.
	 */
	public void setScore(String score) {
		this.score = score;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type : set the property type.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return major : return the property major.
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major : set the property major.
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @return add_time : return the property add_time.
	 */
	public String getAdd_time() {
		return add_time;
	}
	/**
	 * @param add_time : set the property add_time.
	 */
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	/**
	 * @return last_time : return the property last_time.
	 */
	public String getLast_time() {
		return last_time;
	}
	/**
	 * @param last_time : set the property last_time.
	 */
	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}
	/**
	 * @return update_time : return the property update_time.
	 */
	public String getUpdate_time() {
		return update_time;
	}
	/**
	 * @param update_time : set the property update_time.
	 */
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	/**
	 * @return sort : return the property sort.
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * @param sort : set the property sort.
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * @return status : return the property status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return age : return the property age.
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age : set the property age.
	 */
	public void setAge(String age) {
		this.age = age;
	}
	public String getLoyal_pass() {
		return loyal_pass;
	}
	public void setLoyal_pass(String loyal_pass) {
		this.loyal_pass = loyal_pass;
	}
	public String getUserinfo_percent() {
		return userinfo_percent;
	}
	public void setUserinfo_percent(String userinfo_percent) {
		this.userinfo_percent = userinfo_percent;
	}
	
}
