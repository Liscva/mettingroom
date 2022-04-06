package com.liscva.mettingroom.controller;


import com.liscva.framework.core.connect.DefaultPublicConnect;
import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.framework.security.annotation.CheckLogin;
import com.liscva.mettingroom.entity.dto.IncreaseAreaDto;
import com.liscva.mettingroom.entity.dto.SearchAreaDto;
import com.liscva.mettingroom.service.MrAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 区域表 前端控制器
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
@RestController
@RequestMapping("/mrArea")
@CheckLogin
public class MrAreaController {

    @Autowired
    MrAreaService mrAreaService;

    @GetMapping("/findAreaList.htm")
    public FinalConnect findAreaList(SearchAreaDto searchAreaDto){
        return DefaultPublicConnect.of(mrAreaService.findAreaList(searchAreaDto));
    }


    @PostMapping("/increaseArea.htm")
    public FinalConnect increaseArea(@Valid @RequestBody IncreaseAreaDto increaseAreaDto){
        mrAreaService.increaseArea(increaseAreaDto);
        return DefaultPublicConnect.ok("新增完毕");
    }


}
