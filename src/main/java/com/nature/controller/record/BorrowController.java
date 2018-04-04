package com.nature.controller.record;

import com.nature.controller.basic.BaseController;
import com.nature.pojo.record.Borrow;
import com.nature.pojo.system.Admin;
import com.nature.service.record.BorrowService;
import com.nature.service.record.RecordService;
import com.nature.util.CommonResult;
import com.nature.util.Page;
import com.sun.org.apache.xml.internal.resolver.helpers.BootstrapResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.UnknownServiceException;
import java.util.Calendar;
import java.util.Date;

/**
 * zzw_project
 * BorrowController
 *
 * @Author: 竺志伟
 * @Date: 2018-03-30 16:54
 */
@Controller
@RequestMapping("/webAdmin/borrow")
public class BorrowController extends BaseController
{
    @Autowired
    BorrowService borrowService;
    @Autowired
    RecordService recordService;

    /**
     * 进入 账号管理页面
     *
     * @return
     */
    @RequestMapping(value = "/toPage")
    public ModelAndView toIndex()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("webAdmin/borrow");
        modelAndView.addObject("roleId", super.getLoginInfo().getRoleId());
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
    @RequestMapping(value = "/list")
    @ResponseBody
    public Page<Borrow> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                 @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                 @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return borrowService.listForPage(page, rows, key, super.getLoginInfo());
    }


    /**
     * 创建数据
     *
     * @param obj
     * @return
     */
    @RequestMapping(value = "/new")
    @ResponseBody
    public CommonResult addNew(Borrow obj)
    {
        Admin user = super.getLoginInfo();
        obj.setBorrowDate(new Date());
        obj.setBorrowId(user.getId());
        obj.setBorrowName(user.getUserName());
        return resultBoolWrapper(borrowService.add(obj) && recordService.modifyStatus(obj.getRecordId(), 2),
                "档案借阅请求成功,请联系档案管理员处理!", "档案借阅请求失败", null);
    }


    /**
     * 借阅处理
     * Modify 1 common result.
     *
     * @param id the id
     * @return the common result
     * @author:竺志伟
     * @date :2018-04-04 17:46:49
     */
    @RequestMapping(value = "/modify1")
    @ResponseBody
    public CommonResult modify1(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        Admin user = super.getLoginInfo();
        Borrow borrow = borrowService.findById(id);
        borrow.setDealId(user.getId());
        borrow.setDealName(user.getUserName());
        borrow.setIsBack(false);
        return resultBoolWrapper(borrowService.modify(borrow), "借阅处理成功", "借阅处理失败", null);
    }


    /**
     * 归还处理
     * Modify 2 common result.
     *
     * @param id the id
     * @return the common result
     * @author:竺志伟
     * @date :2018-04-04 17:52:04
     */
    @RequestMapping(value = "/modify2")
    @ResponseBody
    public CommonResult modify2(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        Borrow borrow = borrowService.findById(id);
        borrow.setIsBack(true);
        borrow.setBackDate(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrow.getBorrowDate());
        calendar.add(Calendar.DATE, borrow.getBorrowDayNum());
        borrow.setIsBackLater(borrow.getBackDate().after(calendar.getTime()));
        return resultBoolWrapper(
                borrowService.modify(borrow) && recordService.modifyStatus(borrow.getRecordId(), 1), "归还处理成功",
                "归还处理失败", null);
    }


}
