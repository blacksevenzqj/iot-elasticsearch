package com.thinkgem.elclient.dao;

import com.thinkgem.elclient.entity.Agreement;

public interface AgreementDao {
	public Agreement findByType(int agreement_type);
}
