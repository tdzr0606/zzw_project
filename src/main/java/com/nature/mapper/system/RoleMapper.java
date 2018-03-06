package com.nature.mapper.system;

import com.nature.mybatis.config.MyMapper;
import com.nature.pojo.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * springboot
 * RoleMapper
 * Author: 竺志伟
 * Date:   2017-07-07 10:19
 */
public interface RoleMapper extends MyMapper<Role>
{
    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Role> list(@Param(value = "key") String key);


    /**
     * 添加新角色
     *
     * @param role
     */
    public int add(Role role);

    /**
     * 更新角色
     *
     * @param role
     */
    public int modify(Role role);


    /**
     * 删除角色
     *
     * @param id
     */
    public int deleteById(@Param(value = "id") Integer id);

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    public Role findById(@Param(value = "id") Integer id);

    /**
     *
     * @param ids
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);


    public List<Role> listAll();

}
