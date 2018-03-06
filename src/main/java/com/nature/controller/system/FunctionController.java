package com.nature.controller.system;

import com.nature.controller.basic.BaseController;
import com.nature.pojo.system.Function;
import com.nature.service.system.FunctionService;
import com.nature.util.CommonResult;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * zzs_project
 * FunctionController
 *
 * @Author: 竺志伟
 * @Date: 2018-02-13 09:41
 */
@Controller
@RequestMapping(value = "/webAdmin/function")
public class FunctionController extends BaseController
{
    @Autowired
    FunctionService functionService;

    /**
     * 进入 账号管理页面
     *
     * @return
     */
    @RequestMapping(value = "/toPage")
    public ModelAndView toIndex()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("webAdmin/function");
        return modelAndView;
    }


    /**
     * List page list.
     *
     * @param key the key
     * @return the list
     * @author:竺志伟
     * @date :2018-02-14 09:13:21
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Page<Function> listPage(
            @RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        List<Function> resultList = new LinkedList();
        List<Function> parentList = functionService.listParent();
        List<Function> subList = functionService.listSub();

        for(Function pObj : parentList)
        {
            resultList.add(pObj);
            for(Function sObj : subList)
            {
                if(sObj.getParentId().intValue() == pObj.getId().intValue())
                {
                    resultList.add(sObj);
                }
            }
        }
        return new Page<>(1,999,1,999,resultList);
    }


    /**
     * Add common result.
     *
     * @param obj the obj
     * @return the common result
     * @author:竺志伟
     * @date :2018-02-14 09:53:33
     */
    @RequestMapping(value = "/new")
    @ResponseBody
    public CommonResult add(Function obj)
    {
        obj.setCreateTime(new Date());
        return resultBoolWrapper(functionService.add(obj), "信息创建成功", "信息创建失败", null);
    }


    /**
     * Modify common result.
     *
     * @param obj the obj
     * @return the common result
     * @author:竺志伟
     * @date :2018-02-14 09:54:08
     */
    @RequestMapping(value = "/modify")
    @ResponseBody
    public CommonResult modify(Function obj)
    {
        obj.setUpdateTime(new Date());
        return resultBoolWrapper(functionService.modify(obj), "信息修改成功", "信息修改失败", null);
    }


    /**
     * Delete common result.
     *
     * @param ids the ids
     * @return the common result
     * @author:竺志伟
     * @date :2018-02-14 09:54:43
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "ids", required = true, defaultValue = "0") String ids)
    {
        StringBuffer tmpSb = new StringBuffer();
        String[] idArray = ids.split(",");
        for(String id : idArray)
        {
            if(functionService.countByParentId(Integer.parseInt(id)) == 0)
            {
                tmpSb.append(id).append(",");
            }
        }

        functionService.deleteByIds(tmpSb.toString().split(","));
        return resultBoolWrapper(true, "信息删除成功,存在子级菜单的信息无法删除", "信息删除失败", null);
    }


    /**
     * Gets info by id.
     *
     * @param id the id
     * @return the info by id
     * @author:竺志伟
     * @date :2018-02-14 10:07:52
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    public CommonResult getInfoById(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(true, "信息装载成功", "信息装载失败", functionService.findById(id));
    }


    /**
     * List parent common result.
     *
     * @return the common result
     * @author:竺志伟
     * @date :2018-02-16 20:48:40
     */
    @RequestMapping(value = "/listParent")
    @ResponseBody
    public CommonResult listParent()
    {
        return resultSuccessWrapper("数据装载成功", functionService.listParent());
    }


    /**
     * List all common result.
     *
     * @return the common result
     * @author:竺志伟
     * @date :2018-02-17 16:09:57
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public CommonResult listAll()
    {
        List<Function> resultList = new LinkedList();
        List<Function> parentList = functionService.listParent();
        List<Function> subList = functionService.listSub();

        for(Function pObj : parentList)
        {
            resultList.add(pObj);
            for(Function sObj : subList)
            {
                if(sObj.getParentId().intValue() == pObj.getId().intValue())
                {
                    resultList.add(sObj);
                }
            }
        }
        return resultSuccessWrapper("",resultList) ;
    }
}
