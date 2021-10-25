package com.liscva.mettingroom.controller;


import com.liscva.framework.core.connect.DefaultPublicConnect;
import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.mettingroom.cache.Cache;
import com.liscva.mettingroom.cache.SessionCache;
import com.liscva.mettingroom.entity.dto.LoginDto;
import com.liscva.mettingroom.entity.dto.RegisterUser;
import com.liscva.mettingroom.entity.dto.ReserveDto;
import com.liscva.mettingroom.entity.vo.UserInfo;
import com.liscva.mettingroom.service.MrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
@RestController
@RequestMapping("/mrUser")
public class MrUserController {

    @Autowired
    MrUserService mrUserService;


    @PostMapping("/login.htm")
    public FinalConnect login(HttpServletRequest request,@Valid @RequestBody LoginDto loginDto ){
        UserInfo userInfo = mrUserService.login(loginDto);
        Cache cache = new SessionCache(request);
        cache.setCache("curruser",userInfo);
        return DefaultPublicConnect.of(userInfo);
    }


    @PostMapping("/registerUser.htm")
    public FinalConnect registerUser(@Valid @RequestBody RegisterUser registerUser ){
        mrUserService.registerUser(registerUser);
        return DefaultPublicConnect.of("添加成功");
    }


    @GetMapping("/findUserList.htm")
    public FinalConnect findUserList(){
        return DefaultPublicConnect.of(mrUserService.findUserList());
    }

}
