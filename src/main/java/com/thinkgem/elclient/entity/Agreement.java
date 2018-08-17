package com.thinkgem.elclient.entity;

import java.io.Serializable;

/**
 * 创建协议实体类
 * @author guyuqiao
 *
 */
public class Agreement implements Serializable{
	
	private String id;
	private String content;
	private int agreement_type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAgreement_type() {
		return agreement_type;
	}
	public void setAgreement_type(int agreement_type) {
		this.agreement_type = agreement_type;
	}
	
	@Override
	public String toString() {
		return "Agreement [id=" + id + ", content=" + content + ", agreement_type=" + agreement_type + "]";
	}
	
	
}
