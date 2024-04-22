package com.ruoyi.saas.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 用户信息对象 app_user
 * 
 * @author ruoyi
 * @date 2024-02-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 手机号 */
    @Excel(name = "手机号")
    private Long phone;

    /** 登录密码 */
    @Excel(name = "登录密码")
    private String loginPassword;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Long isDel;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("realName", getRealName())
            .append("nickName", getNickName())
            .append("phone", getPhone())
            .append("loginPassword", getLoginPassword())
            .append("isDel", getIsDel())
            .toString();
    }
}
