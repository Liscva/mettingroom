package com.liscva.mettingroom.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 预约表
 * </p>
 *
 * @author 李诗诚
 * @since 2021-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MrReserve implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 预约ID
     */
    @TableId(value = "reserve_id", type = IdType.AUTO)
    private Integer reserveId;

    /**
     * 预约人
     */
    private Integer reserveUser;

    /**
     * 预约人名
     */
    private String reserveUserName;

    /**
     * 预约会议室要干的事情
     */
    private String reserveTodo;

    /**
     * 预约的会议室ID
     */
    private Integer reserveAreaId;

    /**
     * 预约的会议室的时间
     */
    private String reserveTime;


}
