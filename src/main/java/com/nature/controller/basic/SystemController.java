/*
 * Copyright (c) 2017~2030. 竺志伟 tdzr_0606@126.com
 */

package com.nature.controller.basic;

import com.nature.util.CommonResult;
import com.nature.util.FileUploadDownUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * springboot
 * FileUploadController
 * Author: 竺志伟
 * Date:   2017-08-31 12:01
 */
@Controller
@RequestMapping(value = "/system/")
public class SystemController extends BaseController
{

    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 文件上传
     *
     * @return
     */
    @RequestMapping("/fileupload")
    @ResponseBody
    public CommonResult fileUpload(@RequestParam(name = "uploadFile", required = true) MultipartFile uploadFile,
                                   @RequestParam(name = "uploadFilePath", required = true) String uploadFilePath)
    {
        String fileName = FileUploadDownUtil.uploadFile(uploadPath, uploadFilePath, uploadFile);
        return resultSuccessWrapper("文件上传成功", uploadFilePath + "/" + fileName);
    }


}
