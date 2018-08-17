package com.thinkgem.elclient.entity;

import java.io.Serializable;
import java.util.Date;

public class ExceptionFeedback implements Serializable {
	private String id;
	private String account_id;
	private Date feedback_time;
	private String feedback_content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public Date getFeedback_time() {
		return feedback_time;
	}
	public void setFeedback_time(Date feedback_time) {
		this.feedback_time = feedback_time;
	}
	public String getFeedback_content() {
		return feedback_content;
	}
	public void setFeedback_content(String feedback_content) {
		this.feedback_content = feedback_content;
	}
	@Override
	public String toString() {
		return "EcxeptionFeedback [id=" + id + ", account_id=" + account_id + ", feedback_time=" + feedback_time
				+ ", feedback_content=" + feedback_content + "]";
	}
	
	
}
