package com.liscva.mettingroom.mapper;

import com.liscva.mettingroom.entity.dto.DeleteUserDto;
import com.liscva.mettingroom.entity.dto.EditUserDto;
import com.liscva.mettingroom.entity.dto.SearchUserDto;
import com.liscva.mettingroom.entity.po.MrUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liscva.mettingroom.entity.vo.UserInfo;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
public interface MrUserMapper extends BaseMapper<MrUser> {

    List<UserInfo> findUserList(SearchUserDto searchUserDto);

    void resetPassword(MrUser mrUser);

    void deleteUserUserInfo(DeleteUserDto deleteUserDto);

    void deleteUser(DeleteUserDto deleteUserDto);

    void updateUserInfoBy(EditUserDto editUserDto);
}
