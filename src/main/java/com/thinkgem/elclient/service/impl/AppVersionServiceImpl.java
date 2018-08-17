package com.thinkgem.elclient.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thinkgem.elclient.dao.AppVersionDao;
import com.thinkgem.elclient.entity.AppVersion;
import com.thinkgem.elclient.service.AppVersionService;


@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
	
	@Resource
	private AppVersionDao appVersionDao;

	@Override
	public AppVersion getData(String id) {
		AppVersion app= appVersionDao.findById(id);
		return app;
	}

	/*@Override
	public Result<AppVersion> getData(String id) {
		Result<AppVersion> result = new Result<AppVersion>();
		AppVersion appVersion = appVersionDao.findById(id);
		if(appVersion != null) {
			result.setStatus(1);
			result.setMsg("不需更新");
			result.setData(appVersion);
			return result;
		}
		result.setStatus(0);
		result.setMsg("需要更新");
		result.setData(appVersion);
		return result;
	}*/

}
