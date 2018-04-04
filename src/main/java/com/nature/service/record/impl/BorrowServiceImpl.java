package com.nature.service.record.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.mapper.record.BorrowMapper;
import com.nature.pojo.record.Borrow;
import com.nature.pojo.system.Admin;
import com.nature.service.record.BorrowService;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * zzw_project
 * BorrowServiceImpl
 *
 * @Author: 竺志伟
 * @Date: 2018-03-30 16:11
 */
@Service
public class BorrowServiceImpl implements BorrowService
{
    @Autowired
    BorrowMapper borrowMapper;

    @Override
    public Page<Borrow> listForPage(Integer nowPage, Integer pageSize, String key, Admin user)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                // 普通用户
                if(user.getRoleId().intValue() == 2)
                {
                    Map<String,String> paramMap = new HashMap<>();
                    paramMap.put("key",key);
                    paramMap.put("borrowId",user.getId().toString());
                    borrowMapper.listByBorrowId(paramMap);
                }
                else
                {
                    borrowMapper.list(key);
                }
            }
        }));
    }

    @Override
    public Borrow findById(Integer id)
    {
        return borrowMapper.findById(id);
    }

    @Override
    public boolean add(Borrow obj)
    {
        return borrowMapper.add(obj) == 1;
    }

    @Override
    public boolean modify(Borrow obj)
    {
        return borrowMapper.modify(obj) == 1;
    }

    @Override
    public boolean deleteById(Integer id)
    {
        return borrowMapper.deleteById(id) == 1;
    }

    @Override
    public void deleteByIds(String[] ids)
    {
        borrowMapper.deleteByIds(ids);
    }
}
