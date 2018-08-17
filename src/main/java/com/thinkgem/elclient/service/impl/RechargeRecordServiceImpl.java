package com.thinkgem.elclient.service.impl;


import com.thinkgem.elclient.dao.RechargeRecordDao;
import com.thinkgem.elclient.entity.recharge.RechargeRecordResult;
import com.thinkgem.elclient.entity.recharge.RechargeRecordVo;
import com.thinkgem.elclient.service.RechargeRecordService;
import com.thinkgem.elclient.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author guyuqiao
 * 刷卡记录
 */
@Service(value = "rechargeRecordService")
public class RechargeRecordServiceImpl implements RechargeRecordService {

    @Autowired
    RechargeRecordDao rechargeRecordDao;

    @Override
    public RechargeRecordResult monthRechargeRecord (String cardNo, String selectDay) {
        RechargeRecordResult result = new RechargeRecordResult();
        List<RechargeRecordVo> list = null;
        if(StringUtils.isNotBlank(cardNo)) {
        	list = rechargeRecordDao.monthSelectRechargeRecord(cardNo, selectDay);
        } else {
        	list = rechargeRecordDao.monthSelectAllRechargeRecord(selectDay);
        }
        
        if(list.size() > 0) {
        	String[] temp = new String[6];
        	try {
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				Calendar calendar = Calendar.getInstance();
				Date date = df.parse(selectDay);
				calendar.setTime(date);
				temp[0] = df.format(calendar.getTime()).substring(0, 6);
				for(int i=1; i<6; i++) {
					calendar.add(Calendar.MONTH,-1);
					temp[i] = df.format(calendar.getTime()).substring(0, 6);
				}
				RechargeRecordVo[] rrv = new RechargeRecordVo[6];
				BigDecimal totalRechargeAmount = new BigDecimal(0.00);
				BigDecimal totalBalance = new BigDecimal(0.00);
				Integer idNum = 0;
				for(int i=0; i<6; i++) {
					for(RechargeRecordVo rv : list) {
						if(temp[i].equals(rv.getYearMonths())) {
							rrv[i] = rv;
							totalRechargeAmount = totalRechargeAmount.add(rv.getRechargeAmount());
							totalBalance = totalBalance.add(rv.getBalance());
							idNum += rv.getIdNum();
						}
					}
				}
				result.setTotalRechargeAmount(totalRechargeAmount);
				result.setTotalBalance(totalBalance);
				result.setTotalIdNum(idNum);
				result.setMonthRechargeRecord(Arrays.asList(rrv));
			} catch (Exception e) {
				 return result;
			}
        }
        return result;
    }

  

	@Override
	public RechargeRecordResult listRechargeRecord(Integer pageNo, Integer pageSize, String cardNo, String selectDay) {
		selectDay = selectDay.substring(0, 6);
		RechargeRecordResult result = new RechargeRecordResult();
		RechargeRecordVo rCrv;
        if(StringUtils.isNotBlank(cardNo)){
           rCrv = rechargeRecordDao.monthRechargeRecord(cardNo, selectDay);
        }else{
            rCrv = rechargeRecordDao.monthAllRechargeRecord(selectDay);
        }
        if(rCrv != null) {
            result.setTotalRechargeAmount(rCrv.getRechargeAmount());
            result.setTotalBalance(rCrv.getBalance());
            result.setTotalIdNum(rCrv.getIdNum());

            List<RechargeRecordVo> list;
            pageNo = pageNo == null ? 1 : pageNo;
            pageSize = pageSize == null ? 10 : pageSize;
            PageHelper.startPage(pageNo, pageSize);
            if(StringUtils.isNotBlank(cardNo)){
                list = rechargeRecordDao.listRechargeRecord(cardNo, selectDay);
            }else{
                list = rechargeRecordDao.listAllRechargeRecord(selectDay);
            }
            if (list.size() > 0) {
                PageInfo<RechargeRecordVo> pageInfo = new PageInfo<RechargeRecordVo>(list);
                PageUtils<RechargeRecordVo> pageUtils = new PageUtils<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
                result.setPageUtils(pageUtils);
            }
        }
        return result;
    }





}
