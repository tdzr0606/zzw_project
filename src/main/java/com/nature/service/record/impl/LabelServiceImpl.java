package com.nature.service.record.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.mapper.record.LabelMapper;
import com.nature.pojo.record.Label;
import com.nature.service.record.LabelService;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * zzw_project
 * LabelServiceImpl
 *
 * @Author: 竺志伟
 * @Date: 2018-03-05 19:09
 */
@Service
public class LabelServiceImpl implements LabelService
{
    @Autowired
    LabelMapper labelMapper;

    @Override
    public Page<Label> listForPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                labelMapper.list(key);
            }
        }));
    }

    @Override
    public Label findById(Integer id)
    {
        return labelMapper.findById(id);
    }

    @Override
    public boolean add(Label obj)
    {
        return labelMapper.add(obj) == 1;
    }

    @Override
    public boolean modify(Label obj)
    {
        return labelMapper.modify(obj) == 1;
    }

    @Override
    public boolean deleteById(Integer id)
    {
        return labelMapper.deleteById(id) == 1;
    }

    @Override
    public void deleteByIds(String[] ids)
    {
        labelMapper.deleteByIds(ids);
    }

    @Override
    public boolean use(String[] ids)
    {
        return labelMapper.use(ids) > 0;
    }

    @Override
    public Page<Label> listForPublicPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                labelMapper.listForPublic(key);
            }
        }));
    }
}
