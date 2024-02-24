package com.ruoyi.saas.mapper;

import java.util.List;
import com.ruoyi.saas.domain.AppUser;

/**
 * 用户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-23
 */
public interface AppUserMapper 
{
    /**
     * 查询用户信息
     * 
     * @param id 用户信息主键
     * @return 用户信息
     */
    public AppUser selectAppUserById(String id);

    /**
     * 查询用户信息列表
     * 
     * @param appUser 用户信息
     * @return 用户信息集合
     */
    public List<AppUser> selectAppUserList(AppUser appUser);

    /**
     * 新增用户信息
     * 
     * @param appUser 用户信息
     * @return 结果
     */
    public int insertAppUser(AppUser appUser);

    /**
     * 修改用户信息
     * 
     * @param appUser 用户信息
     * @return 结果
     */
    public int updateAppUser(AppUser appUser);

    /**
     * 删除用户信息
     * 
     * @param id 用户信息主键
     * @return 结果
     */
    public int deleteAppUserById(String id);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppUserByIds(String[] ids);
}
