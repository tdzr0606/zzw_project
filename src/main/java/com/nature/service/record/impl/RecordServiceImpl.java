package com.nature.service.record.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.mapper.record.RecordMapper;
import com.nature.pojo.record.Record;
import com.nature.service.record.RecordService;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * zzw_project
 * RecordServiceImpl
 *
 * @Author: 竺志伟
 * @Date: 2018-03-08 15:36
 */
@Service
public class RecordServiceImpl implements RecordService
{
    @Autowired
    RecordMapper recordMapper;

    @Override
    public Page<Record> listForPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                recordMapper.list(key);
            }
        }));
    }

    @Override
    public Record findById(Integer id)
    {
        return recordMapper.findById(id);
    }

    @Override
    public boolean add(Record obj)
    {
        return recordMapper.add(obj) == 1;
    }

    @Override
    public boolean modify(Record obj)
    {
        return recordMapper.modify(obj) == 1;
    }

    @Override
    public boolean deleteById(Integer id)
    {
        return recordMapper.deleteById(id) == 1;
    }

    @Override
    public void deleteByIds(String[] ids)
    {
        recordMapper.deleteByIds(ids);
    }
}
