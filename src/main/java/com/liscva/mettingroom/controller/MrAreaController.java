package com.liscva.mettingroom.controller;


import com.liscva.framework.core.connect.DefaultPublicConnect;
import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.mettingroom.service.MrAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 区域表 前端控制器
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
@RestController
@RequestMapping("/mrArea")
public class MrAreaController {

    @Autowired
    MrAreaService mrAreaService;

    @GetMapping("/findAreaList.htm")
    public FinalConnect findAreaList(){
        return DefaultPublicConnect.of(mrAreaService.findAreaList());
    }
}
