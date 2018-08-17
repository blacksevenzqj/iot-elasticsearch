package com.thinkgem.elclient.controller;

import javax.annotation.Resource;

import com.thinkgem.elclient.entity.AppVersion;
import com.thinkgem.elclient.entity.Result;
import com.thinkgem.elclient.service.AppVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/appversion") //匹配请求路径
public class AppVersiveController {
	@Resource
	 private AppVersionService appVersionService;
	
	@RequestMapping(value = "/appversion.do", method = { RequestMethod.GET,RequestMethod.POST }) //匹配请求
	@ResponseBody //JSON输出
	public Result selectByid(String id) {
		AppVersion list = new AppVersion();
		list = appVersionService.getData(id);
		return Result.success("list", list);
		
	}
}
