package com.nature.mapper.record;

import com.nature.mybatis.config.MyMapper;
import com.nature.pojo.record.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * zzw_project
 * BorrowMapper
 *
 * @Author: 竺志伟
 * @Date: 2018-03-30 16:09
 */
public interface BorrowMapper extends MyMapper<Borrow>
{
    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Borrow> list(@Param(value = "key") String key);

    public List<Borrow> listByBorrowId(Map<String,String> paramMap);


    /**
     * 添加新数据
     *
     * @param obj
     */
    public int add(Borrow obj);

    /**
     * 更新数据
     *
     * @param obj
     */
    public int modify(Borrow obj);


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
    public Borrow findById(@Param(value = "id") Integer id);


    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);
}
