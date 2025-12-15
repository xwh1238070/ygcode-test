package com.ygsoft.jt.mapp.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PERSON_NEW")
public class Person {

	@Id
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "USER_NAME", length = 50)
	private String userName;

	@Column(name = "STATUS", length = 50)
	private String status;


	public String getCondition() {
		return status;
	}

	public void setCondition(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
