package com.ruoyi.saas.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.saas.mapper.AppUserMapper;
import com.ruoyi.saas.domain.AppUser;
import com.ruoyi.saas.service.IAppUserService;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

/**
 * 用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-23
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper,AppUser> implements IAppUserService
{
    @Autowired
    private IAppUserService appUserService;

    /**
     * 查询用户信息
     * 
     * @param id 用户信息主键
     * @return 用户信息
     */
    @Override
    public AppUser selectAppUserById(String id)
    {
        return appUserService.selectAppUserById(id);
    }

    /**
     * 查询用户信息列表
     * 
     * @param appUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<AppUser> selectAppUserList(AppUser appUser)
    {
        return appUserService.selectAppUserList(appUser);
    }

    /**
     * 新增用户信息
     * 
     * @param appUser 用户信息
     * @return 结果
     */
    @Override
    public int insertAppUser(AppUser appUser)
    {
        return appUserService.insertAppUser(appUser);
    }

    /**
     * 修改用户信息
     * 
     * @param appUser 用户信息
     * @return 结果
     */
    @Override
    public int updateAppUser(AppUser appUser)
    {
        return appUserService.updateAppUser(appUser);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteAppUserByIds(String[] ids)
    {
        return appUserService.deleteAppUserByIds(ids);
    }

    /**
     * 删除用户信息信息
     * 
     * @param id 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteAppUserById(String id)
    {
        return appUserService.deleteAppUserById(id);
    }

    /**
     * 设置用户密码
     *
     * @param pwd 用户密码
     * @param id 用户id
     * @return 结果
     */
    @Override
    public int setPassWord(String pwd,String id) {
        Base64Utils.encodeToString((DigestUtils.md5DigestAsHex((pwd).getBytes()).getBytes()));
        return appUserService.setPassWord(pwd, id);
    }
}
