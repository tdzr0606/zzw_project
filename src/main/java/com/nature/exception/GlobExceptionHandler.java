package com.nature.exception;

import com.nature.util.CommonResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局错误处理
 * springboot
 * GlobExceptionHandler
 * Author: 竺志伟
 * Date:   2017-04-01 23:23
 */
@ControllerAdvice
public class GlobExceptionHandler
{

    private Logger logger = Logger.getLogger(GlobExceptionHandler.class);

    /**
     * 运行错误
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public CommonResult runtimeExceptionHandler(Exception e)
    {
        logger.error("Error:", e);
        CommonResult result = new CommonResult();
        result.setMessage(e.getMessage());
        if(e.getMessage().indexOf("Multipart Mime part uploadFile exceeds max filesize") > -1)
        {
            result.setMessage("上传文件过大,请重新上传!");
            result.setCode(CommonResult.SERVER_FILEUPLOAD_ERROR);
        }
        else
        {
            result.setCode(CommonResult.SERVER_RUNTIME_ERROR);
        }
        return result;
    }

    /**
     * 参数错误
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResult missingParamterHandler(MissingServletRequestParameterException exception)
    {
        CommonResult result = new CommonResult();
        result.setMessage(String.format("缺少请求参数:%s", exception.getParameterName()));
        result.setCode(CommonResult.FAILS);
        return result;
    }


    /**
     * 一般错误获取
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult defaultExceptionHandler(Exception e) throws IOException
    {
        logger.error("Error:", e);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.close();
        sw.close();
        CommonResult result = new CommonResult();
        result.setMessage(sw.getBuffer().toString());
        result.setCode(CommonResult.SERVER_ERROR);
        return result;
    }


    /**
     * 未登录处理
     * @param e
     * @return
     */
    @ExceptionHandler(NoLoginException.class)
    public ModelAndView noLoginExceptionHandler(Exception e)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error","登录超时或者未登录!");
        modelAndView.setViewName("/");
        return modelAndView;
    }
}
