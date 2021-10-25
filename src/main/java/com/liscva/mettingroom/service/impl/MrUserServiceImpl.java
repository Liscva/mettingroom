package com.liscva.mettingroom.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liscva.framework.core.lang.Assert;
import com.liscva.mettingroom.entity.dto.LoginDto;
import com.liscva.mettingroom.entity.dto.RegisterUser;
import com.liscva.mettingroom.entity.po.MrUser;
import com.liscva.mettingroom.entity.po.MrUserInfo;
import com.liscva.mettingroom.entity.vo.UserInfo;
import com.liscva.mettingroom.mapper.MrUserMapper;
import com.liscva.mettingroom.service.MrUserInfoService;
import com.liscva.mettingroom.service.MrUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        Assert.notNull(mrUser,"用户名或密码错误！");
        MrUserInfo mrUserInfo = userInfoService.findUserInfoByCode(mrUser.getUserCode());
        BeanUtils.copyProperties(mrUserInfo,userInfo);
        return userInfo;
    }

    @Override
    public List<UserInfo> findUserList() {
        return mrUserMapper.findUserList();
    }

    @Override
    @Transactional
    public void registerUser(RegisterUser registerUser) {
        MrUser mrUser = registerUser.getMrUser();
        MrUserInfo mrUserInfo = registerUser.getMrUserInfo();
        mrUser.setUserPassword(SecureUtil.md5(mrUser.getUserPassword()));
        mrUserMapper.insert(mrUser);
        mrUserInfo.setUserCode(mrUser.getUserCode());
        mrUserInfo.setCreateTime(LocalDate.now().toString());
        userInfoService.save(mrUserInfo);
    }
}
