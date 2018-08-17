package com.thinkgem.elclient.entity;

import java.util.Date;
/**
 * 创建app_upgrade_record实体类
 * @author guyuqiao
 *
 */
public class AppVersion {
	private String id;
	private String app_type;
	private String app_version;
	private String version_code;
	private String file_path;
	private String publish_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	public String getVersion_code() {
		return version_code;
	}
	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	@Override
	public String toString() {
		return "AppVersion [id=" + id + ", app_type=" + app_type + ", app_version=" + app_version + ", version_code="
				+ version_code + ", file_path=" + file_path + ", publish_time=" + publish_time + "]";
	}
	
	
	
	
}
