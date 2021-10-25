package com.liscva.mettingroom.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 会议系统配置
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MrConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "config_id", type = IdType.AUTO)
    private Integer configId;

    /**
     * 配置代码
     */
    private String configCode;

    /**
     * 配置值
     */
    private String configValue;

    /**
     * 配置说明
     */
    private String configRemark;


}
