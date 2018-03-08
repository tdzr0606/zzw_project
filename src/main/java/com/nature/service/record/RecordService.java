package com.nature.service.record;

import com.nature.pojo.record.Record;
import com.nature.util.Page;

/**
 * zzw_project
 * Author: 竺志伟
 * Date:   2018-03-08 15:36
 */
public interface RecordService
{
    public Page<Record> listForPage(Integer nowPage, Integer pageSize, String key);

    public Record findById(Integer id);

    public boolean add(Record obj);

    public boolean modify(Record obj);

    public boolean deleteById(Integer id);

    public void deleteByIds(String[] ids);
}