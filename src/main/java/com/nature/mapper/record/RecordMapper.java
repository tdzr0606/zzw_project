package com.nature.mapper.record;

import com.nature.pojo.record.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * zzw_project
 * Author: 竺志伟
 * Date:   2018-03-07 21:24
 */
public interface RecordMapper
{
    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Record> list(@Param(value = "key") String key);


    /**
     * 添加新数据
     *
     * @param obj
     */
    public int add(Record obj);

    /**
     * 更新数据
     *
     * @param obj
     */
    public int modify(Record obj);


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
    public Record findById(@Param(value = "id") Integer id);


    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);
}
