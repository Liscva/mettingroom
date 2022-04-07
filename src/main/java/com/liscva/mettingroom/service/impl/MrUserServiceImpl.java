package com.liscva.mettingroom.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liscva.mettingroom.entity.dto.DeleteUserDto;
import com.liscva.mettingroom.entity.dto.EditUserDto;
import com.liscva.mettingroom.entity.dto.LoginDto;
import com.liscva.mettingroom.entity.dto.RegisterUser;
import com.liscva.mettingroom.entity.dto.ResetPwdUserDto;
import com.liscva.mettingroom.entity.dto.SearchUserDto;
import com.liscva.mettingroom.entity.po.MrUser;
import com.liscva.mettingroom.entity.po.MrUserInfo;
import com.liscva.mettingroom.entity.vo.UserInfo;
import com.liscva.mettingroom.mapper.MrUserMapper;
import com.liscva.mettingroom.service.MrUserInfoService;
import com.liscva.mettingroom.service.MrUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liscva.mettingroom.utils.LspAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
@Service
public class MrUserServiceImpl extends ServiceImpl<MrUserMapper, MrUser> implements MrUserService {

    public final static Integer USER_STATUS_ENABLE = 0;
    public final static Integer USER_STATUS_DISABLE = 1;
    public final static Integer USER_STATUS_DELETE = 2;
    public final static String USER_DEFAULE_PWD = "123456";

    @Resource
    MrUserMapper mrUserMapper;

    @Autowired
    MrUserInfoService userInfoService;

    @Override
    public UserInfo login(LoginDto loginDto) {
        UserInfo userInfo = new UserInfo();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_account", loginDto.getUserAccount());
        queryWrapper.eq("user_password", SecureUtil.md5(loginDto.getUserPassword()));
        queryWrapper.eq("user_status", USER_STATUS_ENABLE);
        MrUser mrUser = mrUserMapper.selectOne(queryWrapper);
        LspAssert.notNull(mrUser,"查询不到用户信息，请检查用户名或密码！");
        MrUserInfo mrUserInfo = userInfoService.findUserInfoByCode(mrUser.getUserCode());
        BeanUtils.copyProperties(mrUserInfo,userInfo);
        return userInfo;
    }

    @Override
    public List<UserInfo> findUserList(SearchUserDto searchUserDto) {
        return mrUserMapper.findUserList(searchUserDto);
    }

    @Override
    @Transactional
    public void registerUser(RegisterUser registerUser) {
        MrUser mrUser = registerUser.getMrUser();
        MrUserInfo mrUserInfo = registerUser.getMrUserInfo();
        mrUser.setUserPassword(SecureUtil.md5(mrUser.getUserPassword()));
        mrUserMapper.insert(mrUser);
        mrUserInfo.setUserCode(mrUser.getUserCode());
        userInfoService.save(mrUserInfo);
    }

    @Override
    public void resetPassword(ResetPwdUserDto resetPwdUserDto) {
        MrUser mrUser = new MrUser();
        mrUser.setUserAccount(resetPwdUserDto.getUserAccount());
        mrUser.setUserPassword(SecureUtil.md5(USER_DEFAULE_PWD));
        mrUserMapper.resetPassword(mrUser);
    }

    @Override
    @Transactional
    public void deleteUser(DeleteUserDto deleteUserDto) {
        if(deleteUserDto.getSoftDelete()){
            //1.如果为软删除，则改变数据库状态
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("user_account", deleteUserDto.getUserAccount());
            updateWrapper.set("user_status", "2");
            mrUserMapper.update(null, updateWrapper);
        }else{
            mrUserMapper.deleteUserUserInfo(deleteUserDto);
            mrUserMapper.deleteUser(deleteUserDto);
        }
    }

    @Override
    public void editUser(EditUserDto editUserDto) {
        mrUserMapper.updateUserInfoBy(editUserDto);
    }
}
