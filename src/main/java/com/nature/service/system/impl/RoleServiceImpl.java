package com.nature.service.system.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.mapper.system.RoleMapper;
import com.nature.pojo.system.Role;
import com.nature.service.system.RoleService;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * springboot
 * RoleServiceImpl
 * Author: 竺志伟
 * Date:   2017-07-07 10:25
 */
@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleMapper roleMapper;


    @Override
    public Page<Role> findListByPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                roleMapper.list(key);
            }
        }));
    }

    @Override
    public Role findById(Integer id)
    {
        return roleMapper.findById(id);
    }

    @Override
    public boolean add(Role role)
    {
        return roleMapper.add(role) == 1;
    }

    @Override
    public boolean modify(Role role)
    {
        return roleMapper.modify(role) == 1;
    }

    @Override
    public boolean deleteById(Integer id)
    {
        return roleMapper.deleteById(id) == 1;
    }

    @Override
    public void deleteByIds(String[] ids)
    {
        roleMapper.deleteByIds(ids);
    }

    @Override
    public List<Role> listAll()
    {
        return roleMapper.listAll();
    }
}
