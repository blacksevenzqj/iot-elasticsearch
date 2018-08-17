package com.thinkgem.elclient.service.impl;


/**
 * 关于我们实现接口
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.elclient.dao.AboutDao;
import com.thinkgem.elclient.entity.About;
import com.thinkgem.elclient.service.AboutService;
@Service("aboutService")
public class AboutServiceImpl implements AboutService{
	
	@Autowired
	private AboutDao aboutDao;

	@Override
	public About SelectAbout(About about) {
		About about1 = new About();
		about1 = aboutDao.selectAbout();
		return about1;
	}


}
