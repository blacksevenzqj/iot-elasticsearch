package com.thinkgem.elclient.controller;


import com.thinkgem.elclient.entity.Result;
import com.thinkgem.elclient.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.elclient.entity.About;

@Controller
@RequestMapping("/about")
public class AboutController {

	@Autowired
	private AboutService aboutService;

	/**
	 * 查询关于我们的内容
	 * i
	 * @return
	 */
	@RequestMapping(value = "/about.do", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Result SelectAbout() {
		About about = new About();
		about = aboutService.SelectAbout(about);
		if (about != null) {
			Result.success("返回数据成功");		
		}
		return Result.success("about", about);
			
	}
}