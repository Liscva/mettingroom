package com.liscva.mettingroom.service;

import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.mettingroom.entity.dto.DayTimeDto;
import com.liscva.mettingroom.entity.dto.DeleteUserDto;
import com.liscva.mettingroom.entity.dto.EditUserDto;
import com.liscva.mettingroom.entity.dto.LoginDto;
import com.liscva.mettingroom.entity.dto.RegisterUser;
import com.liscva.mettingroom.entity.dto.ResetPwdUserDto;
import com.liscva.mettingroom.entity.dto.SearchUserDto;
import com.liscva.mettingroom.entity.po.MrReserveDayTime;
import com.liscva.mettingroom.entity.po.MrUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liscva.mettingroom.entity.vo.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
public interface MrUserService extends IService<MrUser> {


    /**
     * 登录方法，如果登陆成功则返回用户信息，否则抛出异常
     * @author liscva
     * @date 2021/10/11 15:17
     * @param loginDto
     * @return com.liscva.mettingroom.entity.vo.UserInfo
     */
    UserInfo login(LoginDto loginDto);


    /**
     * 查询用户集合
     * @author liscva
     * @date 2021/10/12 15:02
     * @return java.util.List<com.liscva.mettingroom.entity.vo.UserInfo>
     */
    List<UserInfo> findUserList(SearchUserDto searchUserDto);

    /**
     * 新增用户
     * @author liscva
     * @date 2021/10/19 10:37
     * @param registerUser
     */
    void registerUser(RegisterUser registerUser);

    /**
     * 重置密码
     * @author liscva
     * @date 2021/11/1 11:49
     * @param resetPwdUserDto
     */
    void resetPassword(ResetPwdUserDto resetPwdUserDto);

    /**
     * 删除一个用户
     * @author liscva
     * @date 2021/11/1 16:11
     * @param deleteUserDto
     */
    void deleteUser(DeleteUserDto deleteUserDto);

    /**
     * 修改一个用户
     * @author liscva
     * @date 2021/11/1 16:26
     * @param editUserDto
     */
    void editUser(EditUserDto editUserDto);

}
