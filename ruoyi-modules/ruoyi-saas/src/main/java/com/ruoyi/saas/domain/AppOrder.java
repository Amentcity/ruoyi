package com.ruoyi.saas.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppOrder extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3848559168010196712L;

    private String id;

    private Integer orderType;

    private Integer orderStatus;

    private String appUserId;

    private BigDecimal totalPrice;

    private LocalDateTime createTime;
}
