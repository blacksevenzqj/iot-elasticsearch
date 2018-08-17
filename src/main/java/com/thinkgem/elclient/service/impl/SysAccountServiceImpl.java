package com.thinkgem.elclient.service.impl;

import com.thinkgem.elclient.config.Constant;
import com.thinkgem.elclient.config.enums.ResultEnum;
import com.thinkgem.elclient.config.jwt.JwtConfigBean;
import com.thinkgem.elclient.config.jwt.LoginResponse;
import com.thinkgem.elclient.config.redis.RedisUtil;
import com.thinkgem.elclient.dao.SysAccountServiceDao;
import com.thinkgem.elclient.entity.SysAccount;
import com.thinkgem.elclient.service.SysAccountService;
import com.thinkgem.elclient.utils.BaseResponse;
import com.thinkgem.elclient.utils.BaseResponseUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoqingjie
 * 登录
 */
@Service(value = "sysAccountService")
public class SysAccountServiceImpl implements SysAccountService {

    private static Logger log = LoggerFactory.getLogger(SysAccountServiceImpl.class);

    @Autowired
    private JwtConfigBean jwtConfigBean;

    @Autowired
    SysAccountServiceDao sysAccountServiceDao;

    @Autowired
    RedisUtil redisUtil;


    public BaseResponse login(String phone, String password){
        SysAccount sysAccount = findUserByPhone(phone);
        if(sysAccount != null){
            if(sysAccount.getAccountType() == 3) {
                LoginResponse loginResponse = new LoginResponse();
                ////根据规则加密密码
                String encodePwd = DigestUtils.sha1Hex(password);
                if (sysAccount.getLoginPwd().equals(encodePwd)) {
                    Date now = new Date();
                    String token = Jwts.builder().setSubject(sysAccount.getLoginName())
                            .signWith(SignatureAlgorithm.HS256, Constant.JWT_SECRETE_KEY)
                            .setIssuedAt(now)
                            .claim("id", sysAccount.getId())
                            .claim("name", sysAccount.getLoginName())
                            .setExpiration(Date.from(Instant.now().plus(jwtConfigBean.getValidity(), Constant.jwtValidityMap.get(jwtConfigBean.getValidityType()))))
                            .compact();
                    loginResponse.setToken(token);

                    Map<String,Object> map = new HashMap<>();
                    map.put("id", sysAccount.getId());
                    map.put("name", sysAccount.getLoginName());
                    map.put("dayTime", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    redisUtil.hmset(token, map, jwtConfigBean.getValidity());

                    return BaseResponseUtil.success(loginResponse);
                } else {
                    log.info("农户[{}]密码校验失败", sysAccount.getLoginName());
                    return BaseResponseUtil.error(ResultEnum.LOGIN_PASSWORD_ERROR.getCode(), ResultEnum.LOGIN_PASSWORD_ERROR.getMessage());
                }
            }else{
                return BaseResponseUtil.error(ResultEnum.NO_FARMER_LOGIN.getCode(), ResultEnum.NO_FARMER_LOGIN.getMessage());
            }
        }else{
            return BaseResponseUtil.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMessage());
        }
    }

    @Override
    public SysAccount findUserByPhone(String phone) {
        return sysAccountServiceDao.findUserByPhone(phone);
    }

    @Override
    public BaseResponse changePassword(String phone, String oldPassword, String newPassword) {
        SysAccount sysAccount = findUserByPhone(phone);
        if(sysAccount != null){
            if(sysAccount.getAccountType() == 3) {
                LoginResponse loginResponse = new LoginResponse();
                ////根据规则加密密码
                String encodePwd = DigestUtils.sha1Hex(oldPassword);
                if (sysAccount.getLoginPwd().equals(encodePwd)) {
                    sysAccountServiceDao.changePassword(sysAccount.getId(), DigestUtils.sha1Hex(newPassword));
                    return BaseResponseUtil.success(ResultEnum.CHANGE_PASSWORD_SUCCESS.getCode(), ResultEnum.CHANGE_PASSWORD_SUCCESS.getMessage(), null);
                }else {
                    return BaseResponseUtil.error(ResultEnum.OLD_PASSWORD_ERROR.getCode(), ResultEnum.OLD_PASSWORD_ERROR.getMessage());
                }
            }else{
                return BaseResponseUtil.error(ResultEnum.NO_FARMER_CHANGEPASSWORD.getCode(), ResultEnum.NO_FARMER_CHANGEPASSWORD.getMessage());
            }
        }else{
            return BaseResponseUtil.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMessage());
        }
    }

    @Override
    public BaseResponse loginOut(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("BillClient ")) {
            return BaseResponseUtil.error(ResultEnum.LOGIN_TOKEN_ERROR.getCode(), ResultEnum.LOGIN_TOKEN_ERROR.getMessage());
        }
        String token = authHeader.substring(11);
        redisUtil.del(token);
        if(redisUtil.hasKey(token)){
            return BaseResponseUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
        }
        return BaseResponseUtil.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

}
