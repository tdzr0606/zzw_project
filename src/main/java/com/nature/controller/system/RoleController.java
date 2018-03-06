package com.nature.controller.system;

import com.nature.controller.basic.BaseController;
import com.nature.pojo.system.Role;
import com.nature.service.system.RoleService;
import com.nature.util.CommonResult;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * The type Role controller.
 *
 * @author:竺志伟
 * @date :2018-02-14 11:29:29
 */
@Controller
@RequestMapping(value = "/webAdmin/role")
public class RoleController extends BaseController
{
    @Autowired
    RoleService roleService;


    /**
     * 进入 账号管理页面
     *
     * @return
     */
    @RequestMapping(value = "/toPage")
    public ModelAndView toIndex()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("webAdmin/role");
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
    public Page<Role> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                               @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                               @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return roleService.findListByPage(page, rows, key);
    }


    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/new")
    @ResponseBody
    public CommonResult addNew(Role role)
    {
        role.setCreateTime(new Date());
        return resultBoolWrapper(roleService.add(role), "数据新建成功", "数据新建失败", null);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/modify")
    @ResponseBody
    public CommonResult modify(Role role)
    {
        role.setUpdateTime(new Date());
        return resultBoolWrapper(roleService.modify(role), "数据修改成功", "数据修改失败", null);
    }


    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "ids", defaultValue = "0", required = true) String ids)
    {
        roleService.deleteByIds(ids.split(","));
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
        return resultSuccessWrapper("数据装载成功",roleService.findById(id));
    }


    /**
     * List all common result.
     *
     * @return the common result
     * @author:竺志伟
     * @date :2018-02-14 12:51:39
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public CommonResult listAll()
    {
        return  resultSuccessWrapper("数据装载成功",roleService.listAll());
    }


    /**
     * 角色权限装载
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/loadRightTree")
    @ResponseBody
    public String loadRightTree(@RequestParam(value = "roleId", defaultValue = "0", required = true) Integer roleId)
    {
        StringBuffer sb = new StringBuffer();
//        // 根据角色查询现有 权限
//        List<Right> rightList = rightService.findListByRoleId(roleId);
//        // 查询第一层功能
//        List<Border> parentBorderList = borderService.findPublicBorderByParentId(0);
//        // 第二层功能
//        List<Border> subBorderList = null;
//        // 具体权限
//        List<Module> moduleList = null;
//
//        boolean hasChild1 = false;
//        boolean hasChild2 = false;
//
//        sb.append("[");
//        for(Border border : parentBorderList)
//        {
//            subBorderList = borderService.findPublicBorderByParentId(border.getId());
//            if(null != subBorderList && !subBorderList.isEmpty())
//            {
//                sb.append("{");
//                sb.append("\"id\":\"border_").append(border.getId()).append("\",\"text\":\"").append(border.getBorderName())
//                        .append("\"");
//
//                sb.append(",\"children\":[");
//                for(Border sborder : subBorderList)
//                {
//                    moduleList = moduleService.findPublicListByBorderId(sborder.getId());
//                    if(null != moduleList && !moduleList.isEmpty())
//                    {
//                        hasChild1 = true;
//                        sb.append("{");
//                        sb.append("\"id\":\"border_").append(sborder.getId()).append("\",\"text\":\"")
//                                .append(sborder.getBorderName()).append("\"");
//
//                        sb.append(",\"children\":[");
//                        for(Module module : moduleList)
//                        {
//                            hasChild2 = true;
//                            sb.append("{");
//                            sb.append("\"id\":\"module_").append(sborder.getId()).append("-")
//                                    .append(sborder.getBorderEnName()).append("_").append(module.getId())
//                                    .append("\",\"text\":\"").append(module.getModuleName()).append("\"");
//                            for(Right right : rightList)
//                            {
//                                if(right.getBorderId().intValue() == sborder.getId().intValue() &&
//                                        right.getBorderRight().indexOf(module.getModuleEnName()) > -1)
//                                {
//                                    sb.append(",\"checked\":").append(true);
//                                    break;
//                                }
//                            }
//                            sb.append("},");
//                        }
//                        if(hasChild2)
//                        {
//                            sb = sb.delete(sb.length() - 1, sb.length());
//                        }
//                        sb.append("]");
//                        sb.append("},");
//                    }
//                    moduleList = null;
//                }
//                if(hasChild1)
//                {
//                    sb = sb.delete(sb.length() - 1, sb.length());
//                }
//                sb.append("]");
//            }
//            subBorderList = null;
//            sb.append("},");
//        }
//        if(sb.length() > 1)
//        {
//            sb = sb.delete(sb.length() - 1, sb.length());
//        }
//        sb.append("]");
        return sb.toString();
    }


    /**
     * 角色权限设置保存
     *
     * @param roleSetValue
     * @return
     */
    @RequestMapping(value = "/rolesetSave")
    @ResponseBody
    public CommonResult rightSetSave(@RequestParam(value = "roleId", defaultValue = "0", required = true) Integer roleId,
                                     @RequestParam(value = "roleSetValue", defaultValue = "", required = true) String roleSetValue)
    {
//        List<Right> hasList = rightService.findListByRoleId(roleId);
//        if(null != hasList && !hasList.isEmpty())
//        {
//            rightService.deleteByRoleId(roleId);
//        }
//
//
//        // key: borderId,value: rightValue,
//        Map<String, String> rightMap = new HashMap<>();
//        String[] values = roleSetValue.split(",");
//
//        String mapKey;
//        String mapValue;
//        for(String value : values)
//        {
//            mapKey = value.split("_")[1];
//            mapValue = value.split("_")[2];
//            if(rightMap.containsKey(mapKey))
//            {
//                mapValue = rightMap.get(mapKey).toString() + "," + mapValue;
//
//            }
//            rightMap.put(mapKey, mapValue);
//        }
//
//        Right right = null;
//
//        // 获取所有的启用的权限
//        List<Module> moduleList = moduleService.findPublicAll();
//        Map.Entry<String, String> rightEntry;
//        Iterator rightMapIterator = rightMap.entrySet().iterator();
//
//        StringBuffer rightCodeSB;
//        String[] rightValueTemps;
//        while(rightMapIterator.hasNext())
//        {
//            rightEntry = (Map.Entry) rightMapIterator.next();
//
//            right = new Right();
//            right.setRoleId(roleId);
//            right.setBorderId(Integer.parseInt(rightEntry.getKey().split("-")[0]));
//            right.setBorderEnName(rightEntry.getKey().split("-")[1]);
//
//            rightCodeSB = new StringBuffer();
//            rightValueTemps = rightEntry.getValue().split(",");
//            for(String valueT : rightValueTemps)
//            {
//                for(Module temp : moduleList)
//                {
//                    if(temp.getId().intValue() == Integer.parseInt(valueT))
//                    {
//                        rightCodeSB.append(temp.getModuleEnName()).append(",");
//                        break;
//                    }
//                }
//            }
//            rightCodeSB = rightCodeSB.delete(rightCodeSB.length() - 1, rightCodeSB.length());
//            right.setBorderRight(rightCodeSB.toString());
//            rightService.add(right);
//        }

        return resultBoolWrapper(true, "角色权限设置成功", "角色权限设置失败", null);
    }


}
