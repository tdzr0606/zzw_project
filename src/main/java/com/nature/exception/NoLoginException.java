package com.nature.exception;

/**
 * 未登录错误
 * springboot
 * NoLogException
 * Author: 竺志伟
 * Date:   2017-04-03 11:48
 */
public class NoLoginException extends Exception
{
    public NoLoginException(String message)
    {
        super(message);
    }
}
