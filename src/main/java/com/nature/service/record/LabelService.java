package com.nature.service.record;

import com.nature.pojo.record.Label;
import com.nature.util.Page;

/**
 * zzw_project
 * Author: 竺志伟
 * Date:   2018-03-05 19:09
 */
public interface LabelService
{
    public Page<Label> listForPage(Integer nowPage, Integer pageSize, String key);

    public Label findById(Integer id);

    public boolean add(Label obj);

    public boolean modify(Label obj);

    public boolean deleteById(Integer id);

    public void deleteByIds(String[] ids);

    public boolean use(String[] ids);
}
