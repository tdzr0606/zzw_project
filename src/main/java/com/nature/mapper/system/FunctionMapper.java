package com.nature.mapper.system;

import com.nature.mybatis.config.MyMapper;
import com.nature.pojo.system.Function;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * zzs_project
 * Author: 竺志伟
 * Date:   2018-02-12 18:48
 */
public interface FunctionMapper extends MyMapper<Function>
{
    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Function> list(@Param(value = "key") String key);


    /**
     * 添加新数据
     *
     * @param object
     */
    public int add(Function object);

    /**
     * 更新数据
     *
     * @param object
     */
    public int modify(Function object);


    /**
     * 删除数据
     *
     * @param id
     */
    public int deleteById(@Param(value = "id") Integer id);


    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    public Function findById(@Param(value = "id") Integer id);

    /**
     * 批量删除
     * @param ids
     */
    public void deleteByIds(@Param(value = "ids") String[] ids );

    /**
     * 获取父级功能
     */
    public List<Function> listParent();


    /**
     * 获取子级功能
     * @return
     */
    public List<Function> listSub();

    /**
     * 根据父级功能Id 统计数量
     * @param parentId
     * @return
     */
    public int countByParentId(@Param(value = "parentId") Integer parentId);
}
