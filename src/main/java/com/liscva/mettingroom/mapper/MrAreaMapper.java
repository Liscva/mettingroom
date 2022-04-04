package com.liscva.mettingroom.mapper;

import com.liscva.mettingroom.entity.dto.SearchAreaDto;
import com.liscva.mettingroom.entity.po.MrArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liscva.mettingroom.entity.vo.AreaInfo;

import java.util.List;

/**
 * <p>
 * 区域表 Mapper 接口
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
public interface MrAreaMapper extends BaseMapper<MrArea> {

    List<AreaInfo> findAreaList(SearchAreaDto searchAreaDto);
}
