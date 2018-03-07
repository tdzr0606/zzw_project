package com.nature.controller.record;

import com.nature.controller.basic.BaseController;
import com.nature.pojo.record.Type;
import com.nature.service.record.TypeService;
import com.nature.util.CommonResult;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * zzw_project
 * TypeController
 *
 * @Author: 竺志伟
 * @Date: 2018-03-07 20:45
 */
@Controller
@RequestMapping(value = "/webAdmin/type")
public class TypeController extends BaseController
{
    @Autowired
    TypeService typeService;

    /**
     * 进入 账号管理页面
     *
     * @return
     */
    @RequestMapping(value = "/toPage")
    public ModelAndView toIndex()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("webAdmin/type");
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
    public Page<Type> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return typeService.listForPage(page, rows, key);
    }


    /**
     * 创建数据
     *
     * @param obj
     * @return
     */
    @RequestMapping(value = "/new")
    @ResponseBody
    public CommonResult addNew(Type obj)
    {
        obj.setIsPublic(true);
        return resultBoolWrapper(typeService.add(obj), "数据新建成功", "数据新建失败", null);
    }

    /**
     * 修改数据
     *
     * @param obj
     * @return
     */
    @RequestMapping(value = "/modify")
    @ResponseBody
    public CommonResult modify(Type obj)
    {
        return resultBoolWrapper(typeService.modify(obj), "数据修改成功", "数据修改失败", null);
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
        typeService.deleteByIds(ids.split(","));
        return resultBoolWrapper(true, "数据删除成功,已经启用的数据无法删除", "数据删除失败", null);
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
        return resultSuccessWrapper("数据装载成功", typeService.findById(id));
    }


    /**
     * 数据启用/禁用
     * Use common result.
     *
     * @param ids the ids
     * @return the common result
     * @author:竺志伟
     * @date :2018-03-05 20:02:12
     */
    @RequestMapping(value = "/use")
    @ResponseBody
    public CommonResult use(@RequestParam(value = "ids", defaultValue = "", required = true) String ids)
    {
        return resultBoolWrapper(typeService.use(ids.split(",")), "数据启用/禁用成功", "数据启用/禁用失败", null);
    }

}
