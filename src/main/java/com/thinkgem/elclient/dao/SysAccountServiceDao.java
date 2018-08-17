package com.thinkgem.elclient.dao;

import com.thinkgem.elclient.entity.SysAccount;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhaoqingjie
 */
public interface SysAccountServiceDao {

    SysAccount findUserByPhone(@Param("phone") String phone);

    void changePassword(@Param("id")String id, @Param("newPassword")String newPassword);

}
