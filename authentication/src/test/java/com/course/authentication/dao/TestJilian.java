package com.course.authentication.dao;

import com.course.authentication.mapper.ScMapper;
import com.course.authentication.mapper.StuMapper;
import com.course.authentication.pojo.Stu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @create 2019-12-25  16:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJilian {

    @Autowired
    private ScMapper scMapper;

    @Autowired
    private StuMapper stuMapper;

    @Test
    public void test(){
        /*Stu stu = new Stu();
        stu.setName("wenge");
        stuMapper.insert(stu);*/

        stuMapper.deleteByPrimaryKey(1);

    }
}
