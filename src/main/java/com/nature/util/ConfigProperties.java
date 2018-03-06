package com.nature.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义配置文件
 * springboot
 * ConfigProperties
 * Author: 竺志伟
 * Date:   2017-04-01 15:45
 */
@Component
@PropertySource(value = {"classpath:config.properties"},encoding = "UTF-8")
public class ConfigProperties
{
    @Value("${companyName}")
    private String companyName;
    @Value("${linkPhone}")
    private String linkPhone;
    @Value("${softVersion}")
    private String softVersion;

    public String getCompanyName()
    {
        return companyName;
    }

    public String getLinkPhone()
    {
        return linkPhone;
    }

    public String getSoftVersion()
    {
        return softVersion;
    }


}
