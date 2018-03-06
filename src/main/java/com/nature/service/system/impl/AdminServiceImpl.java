package com.nature.service.system.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.mapper.system.AdminMapper;
import com.nature.pojo.system.Admin;
import com.nature.service.system.AdminService;
import com.nature.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * springboot
 * AdminServiceImpl
 * Author: 竺志伟
 * Date:   2017-04-02 21:24
 */
@Service
public class AdminServiceImpl implements AdminService
{
    @Autowired
    AdminMapper adminMapper;


    @Override
    public Page<Admin> findListByPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>( PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                adminMapper.list(key);
            }
        }));
    }

    @Override
    public Admin findById(Integer id)
    {
        return adminMapper.findById(id);
    }

    @Override
    public boolean add(Admin admin)
    {
       return adminMapper.add(admin) == 1;
    }

    @Override
    public boolean modify(Admin admin)
    {
        return adminMapper.modify(admin)== 1;
    }

    @Override
    public boolean deleteById(Integer id)
    {
        return adminMapper.deleteById(id)== 1;
    }


    @Override
    public Admin login(String loginName,String loginPass)
    {
        Map<String,String> map = new HashMap<>();
        map.put("loginName",loginName);
        map.put("password", loginPass);
        return adminMapper.login(map);
    }

    @Override
    public boolean loginNameCheck(String loginName)
    {
        return adminMapper.loginNameCheck(loginName) < 1;
    }

    @Override
    public boolean modifyPass(Integer id, String loginPassN)
    {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setPassword(loginPassN);
        return adminMapper.modifyPassword(admin) == 1;
    }

    @Override
    public void deleteByIds(String[] ids)
    {
        adminMapper.deleteByIds(ids);
    }

}
