package com.ruoyi.saas.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.saas.domain.AppUser;

/**
 * 用户信息Service接口
 * 
 * @author ruoyi
 * @date 2024-02-23
 */
public interface IAppUserService extends IService<AppUser>
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
     * 批量删除用户信息
     * 
     * @param ids 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteAppUserByIds(String[] ids);

    /**
     * 删除用户信息信息
     * 
     * @param id 用户信息主键
     * @return 结果
     */
    public int deleteAppUserById(String id);

    /**
     * 设置用户密码
     *
     * @param pwd 用户密码
     * @param id 用户id
     * @return 结果
     */
    int setPassWord(String pwd,String id);
}
