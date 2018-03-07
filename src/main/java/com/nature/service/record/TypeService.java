package com.nature.service.record;

import com.nature.pojo.record.Type;
import com.nature.util.Page;

/**
 * zzw_project
 * TypeService
 *
 * @Author: 竺志伟
 * @Date: 2018-03-07 20:43
 */
public interface TypeService
{
    public Page<Type> listForPage(Integer nowPage, Integer pageSize, String key);

    public Type findById(Integer id);

    public boolean add(Type obj);

    public boolean modify(Type obj);

    public boolean deleteById(Integer id);

    public void deleteByIds(String[] ids);

    public boolean use(String[] ids);
}
