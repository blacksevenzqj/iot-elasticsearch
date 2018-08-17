package com.thinkgem.elclient.controller;

import com.thinkgem.elclient.service.SysAccountService;
import com.thinkgem.elclient.utils.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoqingjie
 * 登录
 */
@RestController
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SysAccountService sysAccountService;

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public BaseResponse login(@RequestParam("phone") String  phone, @RequestParam("password") String  password){
        return sysAccountService.login(phone, password);
    }

    @RequestMapping(value="/changePassword",method=RequestMethod.POST)
    public BaseResponse changePassword(@RequestParam("phone") String  phone, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword){
        return sysAccountService.changePassword(phone, oldPassword, newPassword);
    }

    @RequestMapping(value="/loginOut",method=RequestMethod.POST)
    public BaseResponse loginOut(HttpServletRequest request){
        return sysAccountService.loginOut(request.getHeader("Authorization"));
    }




}
