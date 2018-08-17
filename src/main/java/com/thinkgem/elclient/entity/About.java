package com.thinkgem.elclient.entity;

import java.io.Serializable;

/**
 * 关于我们实体类
 * @author Administrator
 *
 */
public class About implements Serializable {
	private String id;
	private String company_name;
	private String company_addr;
	private String official_website;
	private String contact;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_addr() {
		return company_addr;
	}
	public void setCompany_addr(String company_addr) {
		this.company_addr = company_addr;
	}
	public String getOfficial_website() {
		return official_website;
	}
	public void setOfficial_website(String official_website) {
		this.official_website = official_website;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "About [id=" + id + ", company_name=" + company_name + ", company_addr=" + company_addr
				+ ", official_website=" + official_website + ", contact=" + contact + ", content=" + content + "]";
	}
	
	
}
