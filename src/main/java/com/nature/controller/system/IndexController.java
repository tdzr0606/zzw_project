package com.nature.controller.system;

import com.nature.controller.basic.BaseController;
import com.nature.pojo.system.Admin;
import com.nature.pojo.system.Function;
import com.nature.pojo.system.Role;
import com.nature.service.system.AdminService;
import com.nature.service.system.FunctionService;
import com.nature.service.system.RoleService;
import com.nature.util.CommonResult;
import com.nature.util.ConfigProperties;
import com.nature.util.DirectoryTools;
import com.nature.util.Tools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * springboot
 * IndexController
 * Author: 竺志伟
 * Date:   2017-04-02 22:22
 */
@Controller
public class IndexController extends BaseController
{

    private static Logger logger = Logger.getLogger(IndexController.class);
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private AdminService adminService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private RoleService roleService;


    /**
     * 进入网站首页
     * To index model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2018-03-02 10:20:57
     */
    @RequestMapping(value = "/")
    public ModelAndView toIndex()
    {
        logger.info("进入网站首页" + httpServletRequest.getRemoteAddr() + "--" + Tools.getDateTimeString());
        modelAndView = new ModelAndView();
        modelAndView.setView(new RedirectView("/webAdmin/"));
        return modelAndView;
    }



    /**
     * 后台登录页面
     *
     * @return
     */
    @RequestMapping(value = "/webAdmin/")
    public ModelAndView toWebAdminLogin()
    {
        logger.info("进入网站后台登录页面");
        modelAndView = new ModelAndView();
        modelAndView.addObject("vcode",DirectoryTools.VCODE_WEBADMIN_LOGIN);
        modelAndView.setViewName("webAdmin/login");
        return modelAndView;
    }


    /**
     * 后台登录
     *
     * @param loginName the login name
     * @param password  the password
     * @param loginCode the login code
     * @return common result
     * @author:竺志伟
     * @date :2018-03-12 12:08:23
     */
    @RequestMapping(value = "/webAdmin/login")
    @ResponseBody
    public CommonResult webAdmLogin(
            @RequestParam(value = "loginName", defaultValue = "", required = true) String loginName,
            @RequestParam(value = "password", defaultValue = "", required = true) String password,
            @RequestParam(value = "loginCode", defaultValue = "", required = true) String loginCode)
    {
        String sessionCode =
                httpServletRequest.getSession().getAttribute(DirectoryTools.VCODE_WEBADMIN_LOGIN).toString();
        if(!loginCode.equalsIgnoreCase(sessionCode))
        {
            return resultFailsWrapper("验证码输入错误", null);
        }


        Admin admin = adminService.login(loginName, DigestUtils.md5DigestAsHex(password.getBytes()));
        if(null != admin)
        {
            httpServletRequest.getSession().setAttribute(DirectoryTools.SESSION_LOGIN_USER, admin);

            Role role = roleService.findById(admin.getRoleId());
            if(null != role)
            {
                String[] functionIds = role.getFunctionId().split(",");
                List<Function> subList = new ArrayList<>();
                List<Function> parentList = new ArrayList<>();
                List<Integer> parentIdList = new ArrayList<>();

                Function tmp = null;
                for(String functionId : functionIds)
                {
                    tmp = functionService.findById(Integer.parseInt(functionId));
                    subList.add(tmp);

                    tmp = functionService.findById(tmp.getParentId());
                    if(!parentIdList.contains(tmp.getId()))
                    {
                        parentList.add(tmp);
                        parentIdList.add(tmp.getId());
                    }
                }

                httpServletRequest.getSession().setAttribute(DirectoryTools.SESSION_LOGIN_P_FUNCTION, parentList);
                httpServletRequest.getSession().setAttribute(DirectoryTools.SESSION_LOGIN_S_FUNCTION, subList);
            }
            else
            {
                httpServletRequest.getSession()
                        .setAttribute(DirectoryTools.SESSION_LOGIN_P_FUNCTION, new ArrayList<Function>());
                httpServletRequest.getSession()
                        .setAttribute(DirectoryTools.SESSION_LOGIN_S_FUNCTION, new ArrayList<Function>());
            }


            return resultSuccessWrapper("欢迎登录", admin);
        }
        else
        {
            httpServletRequest.getSession().removeAttribute(DirectoryTools.SESSION_LOGIN_USER);
            httpServletRequest.getSession().removeAttribute(DirectoryTools.SESSION_LOGIN_P_FUNCTION);
            httpServletRequest.getSession().removeAttribute(DirectoryTools.SESSION_LOGIN_S_FUNCTION);
            return resultFailsWrapper("用户名,密码错误", null);
        }
    }


    /**
     * 跳转 后台首页
     *
     * @return
     */
    @RequestMapping(value = "/webAdmin/toIndex")
    public ModelAndView toWebAdminIndex()
    {
        //设置权限
        modelAndView = new ModelAndView();
        modelAndView.addObject("nowDate", Tools.getDateString());
        modelAndView.setViewName("webAdmin/index");
        return modelAndView;
    }


    /**
     * 登录退出
     * Login out model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2018-02-13 09:01:19
     */
    @RequestMapping(value = "/webAdmin/loginOut")
    public ModelAndView webAdminoginOut()
    {
        httpServletRequest.getSession().removeAttribute(DirectoryTools.SESSION_LOGIN_USER);
        httpServletRequest.getSession().removeAttribute(DirectoryTools.SESSION_LOGIN_P_FUNCTION);
        httpServletRequest.getSession().removeAttribute(DirectoryTools.SESSION_LOGIN_S_FUNCTION);

        modelAndView = new ModelAndView();
        modelAndView.addObject("vcode",DirectoryTools.VCODE_WEBADMIN_LOGIN);
        modelAndView.setViewName("/webAdmin/login");
        return modelAndView;
    }
}
