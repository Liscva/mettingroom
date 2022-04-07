package com.liscva.mettingroom.controller;


import com.liscva.framework.core.connect.DefaultPublicConnect;
import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.framework.security.annotation.CheckLogin;
import com.liscva.framework.security.lsp.LspUtil;
import com.liscva.framework.security.lsp.SecurityInfo;
import com.liscva.mettingroom.cache.Cache;
import com.liscva.mettingroom.cache.SessionCache;
import com.liscva.mettingroom.entity.dto.DeleteUserDto;
import com.liscva.mettingroom.entity.dto.EditUserDto;
import com.liscva.mettingroom.entity.dto.LoginDto;
import com.liscva.mettingroom.entity.dto.RegisterUser;
import com.liscva.mettingroom.entity.dto.ResetPwdUserDto;
import com.liscva.mettingroom.entity.dto.SearchUserDto;
import com.liscva.mettingroom.entity.vo.UserInfo;
import com.liscva.mettingroom.service.MrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        LspUtil.login(userInfo.getUserCode());
        SecurityInfo tokenInfo = LspUtil.getTokenInfo();
        userInfo.setSecurityInfo(tokenInfo);
        return DefaultPublicConnect.of(userInfo);
    }


    @PostMapping("/registerUser.htm")
    public FinalConnect registerUser(@Valid @RequestBody RegisterUser registerUser ){
        mrUserService.registerUser(registerUser);
        return DefaultPublicConnect.of("添加成功");
    }


    @GetMapping("/findUserList.htm")
    @CheckLogin
    public FinalConnect findUserList(SearchUserDto searchUserDto){
        return DefaultPublicConnect.of(mrUserService.findUserList(searchUserDto));
    }

    @PostMapping("/resetPassword.htm")
    @CheckLogin
    public FinalConnect resetPassword(@Valid @RequestBody ResetPwdUserDto resetPwdUserDto ){
        mrUserService.resetPassword(resetPwdUserDto);
        return DefaultPublicConnect.of("重置成功,默认密码123456");
    }



    @PostMapping("/deleteUser.htm")
    @CheckLogin
    public FinalConnect deleteUser(@Valid @RequestBody DeleteUserDto deleteUserDto ){
        mrUserService.deleteUser(deleteUserDto);
        return DefaultPublicConnect.of("删除成功！");
    }

    @PostMapping("/editUser.htm")
    @CheckLogin
    public FinalConnect editUser(@Valid @RequestBody EditUserDto editUserDto ){
        mrUserService.editUser(editUserDto);
        return DefaultPublicConnect.of("修改成功！");
    }
}
