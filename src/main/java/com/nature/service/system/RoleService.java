/*
 * Copyright (c) 2007~2017. 竺志伟 tdzr_0606@126.com
 */

package com.nature.service.system;

import com.nature.pojo.system.Role;
import com.nature.util.Page;

import java.util.List;

/**
 * springboot
 * RoleService
 * Author: 竺志伟
 * Date:   2017-07-07 10:24
 */
public interface RoleService
{
    public Page<Role> findListByPage(Integer nowPage, Integer pageSize, String key);

    public Role findById(Integer id);

    public boolean add(Role obj);

    public boolean modify(Role obj);

    public boolean deleteById(Integer id);

    public void deleteByIds(String[] ids);

    public List<Role> listAll();
}
