package com.thinkgem.elclient.controller;

import javax.annotation.Resource;

import com.thinkgem.elclient.entity.Agreement;
import com.thinkgem.elclient.entity.Result;
import com.thinkgem.elclient.service.AgreementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/agreement") //匹配请求路径
public class AgreementController {

	@Resource
	 private AgreementService agreementService;
	
	@RequestMapping(value = "/agreement.do", method = { RequestMethod.GET,RequestMethod.POST }) //匹配请求
	@ResponseBody //JSON输出
	public Result SelectAgreement(int agreement_type) {
		Agreement res = new Agreement();
		res = agreementService.SelectAgreementPr(agreement_type);
		if (res != null) {
			Result.success("查询协议成功");
		}
		return Result.success("agreement", res);
	}

}




