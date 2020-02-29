package com.course.authentication.controller;

import com.course.authentication.mapper.CourseMapper;
import com.course.authentication.service.ScoreService;
import com.course.authentication.util.DownLoadUtilsByzhongwen;
import com.course.authentication.util.DownloadUtils;
import com.course.authentication.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @create 2019-12-28  20:15
 */
@CrossOrigin
@RestController
public class FileController {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/upLoad")
    public Result upLoad(@RequestParam("file") MultipartFile file,
                         HttpServletRequest request){
        if(!file.isEmpty())
        {
            String filename=file.getOriginalFilename();
            String path = "D:\\authentication\\upload"+File.separator+filename;
            try {
                file.transferTo(new File(path));

                //程序设计基础-2017
                String[] split1 = filename.split("-");

                String[] split2 = split1[1].split("\\.");
                Integer integer = scoreService.insertScore(split1[0], split2[0], path);

                if(integer>0){
                    return Result.build(200,"上传成功！！");
                }else {
                    return Result.build(400,"系统出现异常，稍后重试！！");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            return Result.build(400,"文件为空，请重新上传！！");
        }
        return Result.build(400,"系统出现异常，稍后重试！！");
    }

    @RequestMapping("/xiazai")
    public void xiazai(HttpServletRequest request, HttpServletResponse response,String courseName,String version){
        System.out.println("courserName="+courseName);
        System.out.println("version="+version);
        try {
            String filename = courseName+"-"+version+".xlsx";
            String filePath = "D:\\authentication\\download\\";
            File file = new File(filePath+filename);
            if(!file.exists()){  //本地没有 去创建
                DownloadUtils.DowmExcelByCourseName(courseMapper,courseName,version);
            }
            //2.3用字节流关联进内存
            FileInputStream fis = new FileInputStream(filePath+filename);
            ServletContext context = request.getSession().getServletContext();
            //3.设置响应头成附件格式
            //3.1设置响应头类型：content-type
            String mimeType = context.getMimeType(filename);
            response.setHeader("content-type", mimeType);
            //3.2解决中文文件名问题
            String agent = request.getHeader("user-agent");
            filename = DownLoadUtilsByzhongwen.getFileName(agent, filename);
            //3.3设置响应头打开方式：content-disposition
            response.setHeader("content-disposition", "attachment;filename="+filename);
            //4.将输入流的数据写出到输出流中
            ServletOutputStream sos = response.getOutputStream();
            //缓冲区
            byte[] buff = new byte[1024 * 8];
            int len = 0;
            while((len = fis.read(buff)) != -1){
                sos.write(buff, 0, len);
            }
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
