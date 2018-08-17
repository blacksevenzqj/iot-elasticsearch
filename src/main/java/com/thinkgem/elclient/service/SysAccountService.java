package com.thinkgem.elclient.service;

import com.thinkgem.elclient.entity.SysAccount;
import com.thinkgem.elclient.utils.BaseResponse;

/**
 * @author zhaoqingjie
 */
public interface SysAccountService {

    BaseResponse login(String phone, String password);

    BaseResponse loginOut(String authHeader);

    SysAccount findUserByPhone(String phone);

    BaseResponse changePassword(String phone, String oldPassword, String newPassword);

}
