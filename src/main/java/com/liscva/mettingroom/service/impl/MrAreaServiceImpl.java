package com.liscva.mettingroom.service.impl;

import com.liscva.mettingroom.entity.dto.IncreaseAreaDto;
import com.liscva.mettingroom.entity.dto.SearchAreaDto;
import com.liscva.mettingroom.entity.po.MrArea;
import com.liscva.mettingroom.entity.vo.AreaInfo;
import com.liscva.mettingroom.mapper.MrAreaMapper;
import com.liscva.mettingroom.service.MrAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
@Service
public class MrAreaServiceImpl extends ServiceImpl<MrAreaMapper, MrArea> implements MrAreaService {

    @Resource
    MrAreaMapper mrAreaMapper;

    @Override
    public List<AreaInfo> findAreaList(SearchAreaDto searchAreaDto) {
        return mrAreaMapper.findAreaList(searchAreaDto);
    }

    @Override
    public void increaseArea(IncreaseAreaDto increaseAreaDto) {
        MrArea mrArea = new MrArea();
        BeanUtils.copyProperties(increaseAreaDto,mrArea);
        mrAreaMapper.insert(mrArea);
    }
}
