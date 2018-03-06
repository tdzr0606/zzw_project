package com.nature.mapper.record;

import com.nature.mybatis.config.MyMapper;
import com.nature.pojo.record.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * zzw_project
 * Author: 竺志伟
 * Date:   2018-03-05 18:55
 */
public interface LabelMapper extends MyMapper<Label>
{
    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Label> list(@Param(value = "key") String key);


    /**
     * 添加新数据
     *
     * @param obj
     */
    public int add(Label obj);

    /**
     * 更新数据
     *
     * @param obj
     */
    public int modify(Label obj);


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
    public Label findById(@Param(value = "id") Integer id);


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
}
