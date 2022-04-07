package com.liscva.mettingroom.controller;


import com.liscva.framework.core.connect.DefaultPublicConnect;
import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.framework.security.annotation.CheckLogin;
import com.liscva.framework.security.context.SecurityHolder;
import com.liscva.mettingroom.service.MrConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统配置相关接口
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
@RestController
@RequestMapping("/mrConfig")
@CheckLogin
public class MrConfigController {

    @Autowired
    MrConfigService mrConfigService;


    /**
     * 根据configCode获取系统配置
     * @author liscva
     * @date 2021/8/30 9:06
     * @param configCode 系统配置参数代码
     * @return com.liscva.mettingroom.connect.FinalConnect
     */
    @GetMapping("/queryMrConfigByConfigCode.htm")
    public FinalConnect queryMrConfigByConfigCode(@RequestParam("configCode") String configCode) {
        return DefaultPublicConnect.of(mrConfigService.queryMrConfigByConfigCode(configCode));
    }

}
