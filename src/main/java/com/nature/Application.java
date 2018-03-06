package com.nature;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 名称:Application
 * 描述:项目启动入口
 * 类型:JAVA
 *
 * @author 竺志伟
 * @since 2017-03-26
 */
@SpringBootApplication
// Mybatis 扫描
@MapperScan(value = "com.nature.mapper")
// 过滤器 扫描
@ServletComponentScan(basePackages={"com.nature.filter"})
public class Application
{
    public static void main(String args[])
    {
        SpringApplication.run(Application.class);
    }
}
