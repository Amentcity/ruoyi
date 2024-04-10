package com.ruoyi.saas.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
}
