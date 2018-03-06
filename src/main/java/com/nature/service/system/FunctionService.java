package com.nature.service.system;

import com.nature.pojo.system.Function;
import com.nature.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能service接口
 */
public interface FunctionService
{
    public Page<Function> listForPage(Integer nowPage, Integer pageSize, String key);

    public Function findById(Integer id);

    public boolean add(Function obj);

    public boolean modify(Function obj);

    public boolean deleteById(Integer id);

    public void deleteByIds(String[] ids );

    public List<Function> listParent();

    public List<Function> listSub();

    public Integer countByParentId(Integer parentId);

}
