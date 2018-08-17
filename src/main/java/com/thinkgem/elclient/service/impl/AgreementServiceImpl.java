package com.thinkgem.elclient.service.impl;

import javax.annotation.Resource;

import com.thinkgem.elclient.entity.Agreement;
import org.springframework.stereotype.Service;

import com.thinkgem.elclient.dao.AgreementDao;
import com.thinkgem.elclient.service.AgreementService;

@Service("agreementService") //扫描到Spring容器
public class AgreementServiceImpl implements AgreementService {

	@Resource
	private AgreementDao agreementDao;

	@Override
	public Agreement SelectAgreementPr(int agreement_type) {
		Agreement record = new Agreement();
		record.setAgreement_type(agreement_type);
		return agreementDao.findByType(agreement_type);
	}

	/*@Override
	public Result<Agreement> getContent(int agreement_type) {
		//接受结果
		Result<Agreement> result = new Result<Agreement>(); 
		//按参数agreement-type查询数据库
		Agreement agreement = agreementDao.findByType(agreement_type);
		if(agreement == null) {
			result.setStatus(1);
			result.setMsg("查询失败");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(agreement);
		return result;
	}*/

}
