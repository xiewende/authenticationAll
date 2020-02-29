package com.course.authentication.util;

import com.course.authentication.mapper.CourseMapper;
import com.course.authentication.pojo.Student;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @create 2019-12-29  12:39
 */
public class DownloadUtils {

    //根据课程名拿到填写分数的excel表
    public static Boolean DowmExcelByCourseName(CourseMapper courseMapper,String courseName,String version)
            throws Exception{

        //获取学生信息和考核细则集合
        List<Student> studentList = courseMapper.listStudentByCourse(courseName, version);
        List<String> stringList = courseMapper.listExamdetailByCourse(courseName, version);

        //创建工作簿 类似于创建Excel文件
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建 sheetname页名
        XSSFSheet sheet = workbook.createSheet(courseName);
        sheet.setColumnWidth(0,20*256);//给第3列设置为20个字的宽度
       // sheet.setColumnWidth(1,20*256);//给第4列设置为20个字的宽度
        //创建一行,下标从0开始
        XSSFRow row = sheet.createRow(0);
        //创建这行中的列,下标从0开始 （表头）
       // XSSFCell cell = row.createCell(0);
        // 给cell 0下表赋值
       // cell.setCellValue("姓名");
        //创建这行中的列,并给该列直接赋值
        row.createCell(0).setCellValue("学号");
        row.createCell(1).setCellValue("姓名");
        for(int i=0 ;i<stringList.size() ;i++){
            System.out.println(stringList.get(i));
            row.createCell(i+2).setCellValue(stringList.get(i));
        }
        // 设置表里内容
        for(int i=0 ; i<studentList.size() ;i++){
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(studentList.get(i).getStudentNum());
            row.createCell(1).setCellValue(studentList.get(i).getName());
        }

        //设定 路径
        File file = new File("D:\\authentication\\download\\"+courseName+"-"+version+".xlsx");
        FileOutputStream stream = new FileOutputStream(file);
        // 需要抛异常
        workbook.write(stream);
        //关流
        stream.close();
        return true;
    }
}
