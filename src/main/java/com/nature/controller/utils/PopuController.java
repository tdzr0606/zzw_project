package com.nature.controller.utils;

import com.nature.controller.basic.BaseController;
import com.nature.pojo.record.Label;
import com.nature.pojo.record.Record;
import com.nature.pojo.record.Type;
import com.nature.service.record.LabelService;
import com.nature.service.record.RecordService;
import com.nature.service.record.TypeService;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * zzw_project
 * PopuController
 *
 * @Author: 竺志伟
 * @Date: 2018-03-08 20:21
 */
@Controller
@RequestMapping(value = "/popu/")
public class PopuController extends BaseController
{

    @Autowired
    TypeService typeService;
    @Autowired
    LabelService labelService;
    @Autowired
    RecordService recordService;


    /**
     * 进入 档案类型 选择页面
     * To type select page string.
     *
     * @return the string
     * @author:竺志伟
     * @date :2018-03-08 20:34:27
     */
    @RequestMapping(value = "/type/toSelect")
    public String toTypeSelectPage()
    {
        return "webAdmin/popu/typeSelect";
    }


    /**
     * 档案类型 选择页面 分页数据
     * List type for page page.
     *
     * @param rows the rows
     * @param page the page
     * @param key  the key
     * @return the page
     * @author:竺志伟
     * @date :2018-03-08 20:35:42
     */
    @RequestMapping(value = "/type/list")
    @ResponseBody
    public Page<Type> listTypeForPage(
            @RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return typeService.listPublicForPage(page,rows,key);
    }


    /**
     * 进入 档案标签 选择页面
     * To type select page string.
     *
     * @return the string
     * @author:竺志伟
     * @date :2018-03-08 21:05:16
     */
    @RequestMapping(value = "/label/toSelect")
    public String toLabelSelectPage()
    {
        return "webAdmin/popu/labelSelect";
    }


    /**
     * 档案类型 档案标签 分页数据
     * List type for page page.
     *
     * @param rows the rows
     * @param page the page
     * @param key  the key
     * @return the page
     * @author:竺志伟
     * @date :2018-03-08 21:05:27
     */
    @RequestMapping(value = "/label/list")
    @ResponseBody
    public Page<Label> listLabelForPage(
            @RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return labelService.listForPublicPage(page,rows,key);
    }

    /**
     * 进入 档案选择页面
     * To borrow select page string.
     *
     * @return the string
     * @author:竺志伟
     * @date :2018-03-31 09:31:52
     */
    @RequestMapping(value = "/record/toSelect")
    public String toRecordSelectPage()
    {
        return "/webAdmin/popu/recordSelect";
    }


    /**
     * 查询 未外借的 档案信息
     * List record for page page.
     *
     * @param rows the rows
     * @param page the page
     * @param key  the key
     * @return the page
     * @author:竺志伟
     * @date :2018-03-31 09:50:39
     */
    @RequestMapping(value = "/record/list")
    @ResponseBody
    public Page<Record> listRecordForPage(
            @RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return recordService.listForPageForNormal(page,rows,key);
    }
}
