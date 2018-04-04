package com.nature.service.record;

import com.nature.pojo.record.Borrow;
import com.nature.pojo.system.Admin;
import com.nature.util.Page;

/**
 * zzw_project
 * Author: 竺志伟
 * Date:   2018-03-30 16:11
 */
public interface BorrowService
{
    public Page<Borrow> listForPage(Integer nowPage, Integer pageSize, String key,Admin user);

    public Borrow findById(Integer id);

    public boolean add(Borrow obj);

    public boolean modify(Borrow obj);

    public boolean deleteById(Integer id);

    public void deleteByIds(String[] ids);
}
