package com.nature.util;


import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件上传下载工具类
 * springboot
 * FileUploadDownUtil
 * Author: 竺志伟
 * Date:   2017-04-01 15:41
 */
public class FileUploadDownUtil
{
    private static Logger logger = Logger.getLogger(FileUploadDownUtil.class);


    /**
     * 文件上传
     *
     * @param rootPath
     * @param filePath
     * @param file
     * @return
     */
    public static String uploadFile(String rootPath, String filePath, MultipartFile file)
    {
        if(null == file || file.isEmpty())
        {
            return null;
        }
        String fileName = file.getOriginalFilename();
        String exName = fileName.substring(fileName.lastIndexOf("."));
        String destFileName = Tools.getDateString(null, "yyyyMMddHHmmssSSS") + exName;
        File destFile = new File(rootPath + DirectoryTools.SYS_FILEPATH_SEPARATOR + filePath +
                DirectoryTools.SYS_FILEPATH_SEPARATOR + destFileName);
        if(!destFile.getParentFile().exists())
        {
            destFile.getParentFile().mkdirs();
        }

        try
        {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(destFile));
            stream.write(file.getBytes());
            stream.close();
            return destFileName;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("文件上传错误", e);
            return null;
        }
    }



}
