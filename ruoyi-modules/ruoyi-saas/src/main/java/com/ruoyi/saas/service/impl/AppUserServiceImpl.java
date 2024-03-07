package com.ruoyi.saas.service.impl;

import java.util.List;
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
public class AppUserServiceImpl implements IAppUserService 
{
    @Autowired
    private AppUserMapper appUserMapper;

    /**
     * 查询用户信息
     * 
     * @param id 用户信息主键
     * @return 用户信息
     */
    @Override
    public AppUser selectAppUserById(String id)
    {
        return appUserMapper.selectAppUserById(id);
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
        return appUserMapper.selectAppUserList(appUser);
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
        return appUserMapper.insertAppUser(appUser);
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
        return appUserMapper.updateAppUser(appUser);
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
        return appUserMapper.deleteAppUserByIds(ids);
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
        return appUserMapper.deleteAppUserById(id);
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
        return appUserMapper.setAppUserPassWord(pwd,id);
    }
}
