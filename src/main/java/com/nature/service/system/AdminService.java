package com.nature.service.system;

import com.nature.pojo.system.Admin;
import com.nature.util.Page;

import java.util.List;

/**
 * springboot
 * AdminService
 * Author: 竺志伟
 * Date:   2017-04-02 21:24
 */
public interface AdminService
{

    public Page<Admin> findListByPage(Integer nowPage, Integer pageSize, String key);

    public Admin findById(Integer id);

    public boolean add(Admin obj);

    public boolean modify(Admin obj);

    public boolean deleteById(Integer id);

    public Admin login(String loginName, String loginPass);

    public boolean loginNameCheck(String loginName);

    public boolean modifyPass(Integer id,String loginPassN);

    public void deleteByIds(String[] ids);

}
