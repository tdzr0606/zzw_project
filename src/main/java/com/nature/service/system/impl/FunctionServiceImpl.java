package com.nature.service.system.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.mapper.system.AdminMapper;
import com.nature.mapper.system.FunctionMapper;
import com.nature.pojo.system.Function;
import com.nature.service.system.FunctionService;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * zzs_project
 * FunctionServiceImpl
 *
 */
@Service
public class FunctionServiceImpl implements FunctionService
{
    @Autowired
    FunctionMapper functionMapper;

    @Override
    public Page<Function> listForPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>( PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                functionMapper.list(key);
            }
        }));
    }

    @Override
    public Function findById(Integer id)
    {
        return functionMapper.findById(id);
    }

    @Override
    public boolean add(Function obj)
    {
        return functionMapper.add(obj) == 1;
    }

    @Override
    public boolean modify(Function obj)
    {
        return functionMapper.modify(obj) == 1;
    }

    @Override
    public boolean deleteById(Integer id)
    {
        return functionMapper.deleteById(id) == 1;
    }

    @Override
    public void deleteByIds(String[] ids)
    {
        functionMapper.deleteByIds(ids);
    }

    @Override
    public List<Function> listParent()
    {
        return functionMapper.listParent();
    }

    @Override
    public List<Function> listSub()
    {
        return functionMapper.listSub();
    }

    @Override
    public Integer countByParentId(Integer parentId)
    {
        return functionMapper.countByParentId(parentId);
    }
}
