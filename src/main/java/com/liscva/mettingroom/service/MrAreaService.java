package com.liscva.mettingroom.service;

import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.mettingroom.entity.dto.IncreaseAreaDto;
import com.liscva.mettingroom.entity.dto.SearchAreaDto;
import com.liscva.mettingroom.entity.po.MrArea;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liscva.mettingroom.entity.vo.AreaInfo;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 区域表 服务类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
public interface MrAreaService extends IService<MrArea> {

    /**
     * 查询会议室区域
     * @author liscva
     * @date 2021/10/12 15:40
     * @return java.util.List<com.liscva.mettingroom.entity.vo.AreaInfo>
     */
    List<AreaInfo> findAreaList(SearchAreaDto searchAreaDto);


    /**
     * 新增会议室
     * @author liscva
     * @date 2021/11/2 10:36
     * @param increaseAreaDto
     */
    void increaseArea(IncreaseAreaDto increaseAreaDto);
}
