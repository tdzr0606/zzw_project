package com.nature.controller.record;

import com.nature.controller.basic.BaseController;
import com.nature.pojo.record.Record;
import com.nature.pojo.system.Admin;
import com.nature.service.record.RecordService;
import com.nature.util.CommonResult;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * zzw_project
 * RecordController
 *
 * @Author: 竺志伟
 * @Date: 2018-03-08 15:38
 */
@Controller
@RequestMapping(value = "/webAdmin/record")
public class RecordController extends BaseController
{
    @Autowired
    RecordService recordService;

    /**
     * 进入页面
     *
     * @return
     */
    @RequestMapping(value = "/toPage")
    public ModelAndView toIndex()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("webAdmin/record");
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
    public Page<Record> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                               @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                               @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return recordService.listForPage(page, rows, key);
    }


    /**
     * 创建数据
     *
     * @param obj
     * @return
     */
    @RequestMapping(value = "/new")
    @ResponseBody
    public CommonResult addNew(Record obj)
    {
        Admin admin = super.getLoginInfo();
        obj.setCreateId(admin.getId());
        obj.setCreateName(admin.getUserName());
        obj.setCreateDate(new Date());
        obj.setStatus(1);
        return resultBoolWrapper(recordService.add(obj), "数据新建成功", "数据新建失败", null);
    }

    /**
     * 修改数据
     *
     * @param obj
     * @return
     */
    @RequestMapping(value = "/modify")
    @ResponseBody
    public CommonResult modify(Record obj)
    {
        return resultBoolWrapper(recordService.modify(obj), "数据修改成功", "数据修改失败", null);
    }


    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "ids", defaultValue = "0", required = true) String ids)
    {
        recordService.deleteByIds(ids.split(","));
        return resultBoolWrapper(true, "数据删除成功,已经外借的数据无法删除", "数据删除失败", null);
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
        return resultSuccessWrapper("数据装载成功", recordService.findById(id));
    }
}
