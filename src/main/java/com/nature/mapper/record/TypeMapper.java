package com.nature.mapper.record;

import com.nature.mybatis.config.MyMapper;
import com.nature.pojo.record.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * zzw_project
 * TypeMapper
 *
 * @Author: 竺志伟
 * @Date: 2018-03-07 20:40
 */
public interface TypeMapper extends MyMapper<Type>
{
    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Type> list(@Param(value = "key") String key);


    /**
     * 添加新数据
     *
     * @param obj
     */
    public int add(Type obj);

    /**
     * 更新数据
     *
     * @param obj
     */
    public int modify(Type obj);


    /**
     * 删除数据
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
    public Type findById(@Param(value = "id") Integer id);


    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);

    /**
     * 启用/禁用
     *
     * @param ids
     * @return
     */
    public int use(@Param(value = "ids") String[] ids);


    /**
     *  获取已启用列表信息
     * @param key
     * @return
     */
    public List<Type> listForPublic(@Param(value = "key") String key);
}
