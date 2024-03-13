package com.ruoyi.saas.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.saas.domain.AppUser;
import com.ruoyi.saas.service.IAppUserService;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
    @Resource
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
    @RequiresPermissions("saas:user:setPwd")
    @Log(title = "设置用户密码",businessType = BusinessType.UPDATE)
    @PostMapping("/setPassword")
    public AjaxResult setPassWord(String pwd,String id){
        return toAjax(appUserService.setPassWord(pwd,id));
    }

    /**
     *  后台充值密码为点钱用户手机号后六位
     */
    @RequiresPermissions("saas:user:resetPwd")
    @Log(title = "重置用户密码",businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    public AjaxResult resetPwd(String id){
        AppUser appUser = appUserService.selectAppUserById(id);
        appUser.setLoginPassword(Base64Utils.encodeToString((DigestUtils.md5DigestAsHex((appUser.getPhone().toString().substring(4,10)).getBytes()).getBytes())));
        return toAjax(appUserService.updateAppUser(appUser));
    }
    /**
     *  用户更改资料
     */
    @RequiresPermissions("saas:user:updateUser")
    @Log(title = "重置用户资料",businessType = BusinessType.UPDATE)
    @PostMapping("/updateUser")
    public AjaxResult resetUser(AppUser appUser){
        return toAjax(appUserService.updateAppUser(appUser));
    }
    /**
     * 用户注册
     */
    @RequiresPermissions("saas:user:delete")
    @Log(title = "伪删除用户信息",businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult deleteUser(String id){
        return toAjax(appUserService.lambdaUpdate().set(AppUser::getIsDel,0).eq(AppUser::getId,id).update());
    }
    /**
     * 添加根据手机号模糊查询用户
     */
    @RequiresPermissions("saas:user:listByPhone")
    @GetMapping("/listByPhone")
    public TableDataInfo listByPhone(String phone)
{
    startPage();
    LambdaQueryChainWrapper<AppUser> eq = appUserService.lambdaQuery().like(AppUser::getPhone, phone).eq(AppUser::getIsDel, 0);
    return getDataTable((List<?>) eq);
}
}
