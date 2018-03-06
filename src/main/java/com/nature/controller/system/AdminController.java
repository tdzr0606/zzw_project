package com.nature.controller.system;

import com.nature.controller.basic.BaseController;
import com.nature.pojo.system.Admin;
import com.nature.service.system.AdminService;
import com.nature.util.CommonResult;
import com.nature.util.DirectoryTools;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping(value = "/webAdmin/admin")
public class AdminController extends BaseController
{
    @Autowired
    private AdminService adminService;


    /**
     * 进入 账号管理页面
     *
     * @return
     */
    @RequestMapping(value = "/toPage")
    public ModelAndView toIndex()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("webAdmin/admin");
        return modelAndView;
    }


    /**
     * 分页数据查询
     *
     * @param rows pageSize
     * @param page curPageNum
     * @param key
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Page<Admin> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return adminService.findListByPage(page, rows, key);
    }


    /**
     * 创建用户
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/new")
    @ResponseBody
    public CommonResult addNew(Admin admin)
    {
        if(adminService.loginNameCheck(admin.getUserName()))
        {
            admin.setCreateTime(new Date());
            admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
            return resultBoolWrapper(adminService.add(admin), "数据新建成功", "数据新建失败", null);
        }
        return resultFailsWrapper("当前登录名已经存在", null);
    }

    /**
     * 修改用户
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/modify")
    @ResponseBody
    public CommonResult modify(Admin admin)
    {
        Admin oldAmin = adminService.findById(admin.getId());
        if( !oldAmin.getPassword().equals(admin.getPassword()))
        {
            admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        }
        admin.setUpdateTime(new Date());
        return resultBoolWrapper(adminService.modify(admin), "数据修改成功", "数据修改失败", null);
    }


    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "ids", defaultValue = "0", required = true) String ids)
    {
        adminService.deleteByIds(ids.split(","));
        return resultBoolWrapper(true, "数据删除成功", "数据删除失败", null);
    }


    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    public CommonResult findById(@RequestParam(value = "id", defaultValue = "0", required = true) Integer id)
    {
        return resultSuccessWrapper("数据装载成功",adminService.findById(id));
    }


    /**
     * Modify password common result.
     *
     * @param loginPass  the login pass
     * @param loginPassN the login pass n
     * @return the common result
     * @author:竺志伟
     * @date :2018-02-13 09:10:13
     */
    @RequestMapping(value = "/passwordModify")
    @ResponseBody
    public CommonResult modifyPassword(
            @RequestParam(value = "loginPass", defaultValue = "", required = true) String loginPass,
            @RequestParam(value = "loginPassN", defaultValue = "", required = true) String loginPassN)
    {
        Admin admin = (Admin) httpServletRequest.getSession().getAttribute(DirectoryTools.SESSION_LOGIN_USER);
        String oldPass = DigestUtils.md5DigestAsHex(loginPass.getBytes());
        String newPass = DigestUtils.md5DigestAsHex(loginPassN.getBytes());

        if(!oldPass.equals(admin.getPassword()))
        {
            return resultFailsWrapper("原始密码匹配错误", null);
        }
        return resultBoolWrapper(adminService.modifyPass(admin.getId(), newPass), "密码修改成功", "密码修改失败", null);
    }

}
