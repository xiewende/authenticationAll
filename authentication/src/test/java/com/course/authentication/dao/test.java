package com.course.authentication.dao;
import com.course.authentication.mapper.*;
import com.course.authentication.pojo.*;
import com.course.authentication.util.DownloadUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @create 2019-12-19  20:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private RoleMapper mapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SelcourseMapper selcourseMapper;
    @Autowired
    private ExamdetailMapper examdetailMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    @Test
    public void test1(){
        Role role = new Role();
        role.setName("系统管理员");
        role.setDescription("很有权力得管理员");
        mapper.insert(role);
    }

    @Test
    public void test2(){
        Role role = mapper.selectByPrimaryKey(1);
        System.out.println(role.getDescription());
    }

    @Test
    public void test3(){
        List<Student> studentList = courseMapper.listStudentByCourse("程序设计基础", "2017");
        for(Student student : studentList){
            System.out.println(student.getName());
        }

        List<String> stringList = courseMapper.listExamdetailByCourse("程序设计基础", "2017");
        for(String str : stringList){
            System.out.println("str="+str);
        }
    }

    @Test
    public void  test4() throws Exception{
        DownloadUtils downloadUtils = new DownloadUtils();
        downloadUtils.DowmExcelByCourseName(courseMapper,"程序设计基础","2017");
    }

    @Test
    public void test5(){

        String courseName = "程序设计基础";
        String version = "2017";
        File file = new File("D:\\upfile\\程序设计基础-2017.xlsx");

        //根据课程名本拿course_id
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria courseExampleCriteria= courseExample.createCriteria();
        courseExampleCriteria.andNameEqualTo(courseName);
        courseExampleCriteria.andVersionEqualTo(version);
        Integer course_id = courseMapper.selectByExample(courseExample).get(0).getId();

        //拿学生id 拿考核id
        List<String> examDeatilList = new ArrayList<>();
        List<String> studentNumList = new ArrayList<>();
        List<Double> onestudentScore = null;
        List<List<Double>> allstudentScore = new ArrayList<>();
        try {
            //获得该文件的输入流
            FileInputStream stream = new FileInputStream(file);
            // 多态  抛异常
            Workbook sheets = new XSSFWorkbook(stream);
            //获取一个工作表(sheet页)，下标从0开始
            Sheet sheet = sheets.getSheetAt(0);

            //行数列数
            Integer rowNum =  sheet.getLastRowNum();
            Integer colNum = sheet.getRow(0).getPhysicalNumberOfCells();

            for (int i = 0; i<=rowNum ; i++) {
                onestudentScore = new ArrayList<>();
                for(int j = 0 ;j<colNum ; j++){
                    if(i==0){
                        // 获取行数
                        Row row = sheet.getRow(i);
                        // 获取单元格 取值
                        String examDetailName = row.getCell(j).getStringCellValue();
                        if(j>1){
                            examDeatilList.add(examDetailName);
                        }
                    }else {  //第二行开始
                        // 获取行数
                        Row row = sheet.getRow(i);
                        if(j<1){
                            String studentNum = row.getCell(0).getStringCellValue();
                            System.out.println("num="+studentNum);
                            studentNumList.add(studentNum);
                        }
                        if(j>1){
                            Double value = row.getCell(j).getNumericCellValue();
                            onestudentScore.add(value);
                        }
                    }

                }
                if(i!=0){
                    allstudentScore.add(onestudentScore);
                }
            }
            //关流
            sheets.close();
            stream.close();

            for(int i =0 ;i<studentNumList.size(); i++){
                System.out.print(studentNumList.get(i)+" ");
                for(int j=0 ; j<examDeatilList.size() ; j++){
                    System.out.print(examDeatilList.get(j)+" ");
                    System.out.print(allstudentScore.get(i).get(j));
                }
                System.out.println();


            }

            //
            StudentExample studentExample = new StudentExample();
            StudentExample.Criteria studentExampleCriteria = studentExample.createCriteria();
            studentExampleCriteria.andStudentNumIn(studentNumList);
            List<Student> students = studentMapper.selectByExample(studentExample);
            List<Integer> studentIdList = new ArrayList<>();
            for(Student student : students){
                studentIdList.add(student.getId());
            }

            //根据学生学号 课程号 版本 插selcourseid
            SelcourseExample selcourseExample = new SelcourseExample();
            SelcourseExample.Criteria selcourseExampleCriteria = selcourseExample.createCriteria();
            selcourseExampleCriteria.andCourseIdEqualTo(course_id);
            selcourseExampleCriteria.andVersionEqualTo(version);
            selcourseExampleCriteria.andStudentIdIn(studentIdList);
            List<Selcourse> selcourses = selcourseMapper.selectByExample(selcourseExample);
            List<Integer> selcourseIdList = new ArrayList<>();
            for(Selcourse selcourse : selcourses){
                selcourseIdList.add(selcourse.getId());
            }

            //拿考核细则id
            ExamdetailExample examdetailExample = new ExamdetailExample();
            ExamdetailExample.Criteria examdetailExampleCriteria = examdetailExample.createCriteria();
            examdetailExampleCriteria.andVersionEqualTo(version);
            examdetailExampleCriteria.andNameIn(examDeatilList);
            List<Examdetail> examdetailList = examdetailMapper.selectByExample(examdetailExample);
            List<Integer> examdetailIdList = new ArrayList<>();
            for(Examdetail examdetail : examdetailList){
                examdetailIdList.add(examdetail.getId());
            }

            System.out.println("studentidList="+studentIdList.toString());
            System.out.println("selcorseidList="+selcourseIdList.toString());
            System.out.println("examdetailId="+examdetailIdList.toString());

            Score score = new Score();
            score.setTerm("1");
            int tag = 0;


            //插入数据库
            for(int i =0 ;i<selcourseIdList.size(); i++){
                score.setSelcourseId(selcourseIdList.get(i));
                System.out.print(selcourseIdList.get(i)+" ");
                for(int j=0 ; j<examdetailIdList.size() ; j++){
                    score.setExamdetailId(examdetailIdList.get(j));
                    score.setScores(allstudentScore.get(i).get(j));
                    //执行插入
                    scoreMapper.insert(score);
                    tag++;
                    System.out.print(examdetailIdList.get(j)+" ");
                    System.out.print(allstudentScore.get(i).get(j));
                }
                System.out.println();

            }

            System.out.println("tag="+tag);




        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
