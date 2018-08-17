package com.thinkgem.elclient.service;

import com.thinkgem.elclient.entity.Agreement;

public interface AgreementService {
	//检查得到内容
	//public Result<Agreement> getContent(int agreement_type);
	Agreement SelectAgreementPr(int agreement_type);
}
