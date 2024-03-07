package com.ruoyi.saas.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.saas.domain.AppUser;
import com.ruoyi.saas.service.IAppUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 用户信息Controller
 * 
 * @author ruoyi
 * @date 2024-02-23
 */
@RestController
@RequestMapping("/user")
public class AppUserController extends BaseController
{
    @Autowired
    private IAppUserService appUserService;

    /**
     * 查询用户信息列表
     */
    @RequiresPermissions("saas:user:list")
    @GetMapping("/list")
    public TableDataInfo list(AppUser appUser)
    {
        startPage();
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @RequiresPermissions("saas:user:export")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppUser appUser)
    {
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        ExcelUtil<AppUser> util = new ExcelUtil<>(AppUser.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @RequiresPermissions("saas:user:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(appUserService.selectAppUserById(id));
    }

    /**
     * 新增用户信息
     */
    @RequiresPermissions("saas:user:add")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppUser appUser)
    {
        return toAjax(appUserService.insertAppUser(appUser));
    }

    /**
     * 修改用户信息
     */
    @RequiresPermissions("saas:user:edit")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppUser appUser)
    {
        return toAjax(appUserService.updateAppUser(appUser));
    }

    /**
     * 删除用户信息
     */
    @RequiresPermissions("saas:user:remove")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(appUserService.deleteAppUserByIds(ids));
    }

    /**
     * 修改用户信息
     */
    @RequiresPermissions("saas:user:update")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult update(AppUser appUser){
        return toAjax(appUserService.updateAppUser(appUser));
    }

    /**
     * 添加用户信息
     */
    @RequiresPermissions("saas:user:adduser")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/adduser")
    public AjaxResult adduser(AppUser appUser){
        appUser.setId(UUID.randomUUID().toString());
        return toAjax(appUserService.insertAppUser(appUser));
    }

    /**
    * 添加用户信息接口数据
    */
    @RequiresPermissions("saas:user:addAll")
    @Log(title = "添加用户所有信息",businessType = BusinessType.GENCODE)
    @PostMapping("/getCodeAddAll")
    public AjaxResult getCodeAddAll(AppUser appUser){
        appUser.setId(UUID.randomUUID().toString());
        appUser.setCreateTime(LocalDateTime.now());
        appUser.setLoginPassword(Arrays.toString(appUser.getPhone().toString().split(String.valueOf(6),10)));
        return toAjax(appUserService.insertAppUser(appUser));
    }

    @RequiresPermissions("saas:user:getAll")
    @Log(title = "获取用户所有信息", businessType = BusinessType.FORCE)
    @GetMapping("/getAll")
    public AjaxResult getAll(AppUser appUser){
        return success(appUserService.selectAppUserList(appUser));
    }

    @RequiresPermissions("saas:user:getAdmin")
    @Log(title = "根据管理员获取信息",businessType = BusinessType.GENCODE)
    @GetMapping("/admin")
    public AjaxResult getAdmin(AppUser appUser){
        return success(appUserService.selectAppUserById(appUser.getId()));
    }

    /**
     * 添加用户备注
     */
    @RequiresPermissions("saas:user:addAll")
    @Log(title = "添加用户所有信息",businessType = BusinessType.GENCODE)
    @PostMapping("/getCodeSetAll")
    public AjaxResult set(AppUser appUser){
        appUser.setId(UUID.randomUUID().toString());
        appUser.setCreateTime(LocalDateTime.now());
        appUser.setLoginPassword(Arrays.toString(appUser.getPhone().toString().split(String.valueOf(6),10)));
        appUser.setRemark("添加参数配置");
        return toAjax(appUserService.insertAppUser(appUser));
    }

    /**
     * 对接受到的密码信息进行MD5和base64进行加密处理
     */
    public AjaxResult setPassWord(String pwd,String id){
        return toAjax(appUserService.setPassWord(pwd,id));
    }
}
