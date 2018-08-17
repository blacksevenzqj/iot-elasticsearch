package com.thinkgem.elclient.dao;

import com.thinkgem.elclient.entity.AppVersion;
import org.apache.ibatis.annotations.Param;

/**
 * 版本更新
 * @author guyuqiao
 *
 */
public interface AppVersionDao {
	public AppVersion findById(@Param("id")String id);
}
