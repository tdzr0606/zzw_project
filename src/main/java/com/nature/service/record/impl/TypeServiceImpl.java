package com.nature.service.record.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.mapper.record.TypeMapper;
import com.nature.pojo.record.Type;
import com.nature.service.record.TypeService;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * zzw_project
 * TypeServiceImpl
 *
 * @Author: 竺志伟
 * @Date: 2018-03-07 20:43
 */
@Service
public class TypeServiceImpl implements TypeService
{
    @Autowired
    TypeMapper typeMapper;

    @Override
    public Page<Type> listForPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                typeMapper.list(key);
            }
        }));
    }

    @Override
    public Type findById(Integer id)
    {
        return typeMapper.findById(id);
    }

    @Override
    public boolean add(Type obj)
    {
        return typeMapper.add(obj) == 1;
    }

    @Override
    public boolean modify(Type obj)
    {
        return typeMapper.modify(obj) == 1;
    }

    @Override
    public boolean deleteById(Integer id)
    {
        return typeMapper.deleteById(id) == 1;
    }

    @Override
    public void deleteByIds(String[] ids)
    {
        typeMapper.deleteByIds(ids);
    }

    @Override
    public boolean use(String[] ids)
    {
        return typeMapper.use(ids) > 0;
    }

    @Override
    public Page<Type> listPublicForPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                typeMapper.listForPublic(key);
            }
        }));
    }
}
