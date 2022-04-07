package com.liscva.mettingroom.controller;


import com.liscva.framework.security.annotation.CheckLogin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
@RestController
@RequestMapping("/mrRole")
@CheckLogin
public class MrRoleController {

}
