package com.course.authentication.dao;

import com.course.authentication.mapper.FinallyMapper;
import com.course.authentication.pojo.GraduationDachengdu;
import com.course.authentication.pojo.PersonalDachengdu;
import com.course.authentication.pojo.Student;
import com.course.authentication.pojo.Totaldachengdu;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @create 2019-12-28  14:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
  /*  @Autowired
    private RedisTemplate<String,Object> redisTemplate;
*/
    @Autowired
    private StringRedisTemplate stringRedisTemplateredisTemplate;

    @Autowired
    private FinallyMapper finallyMapper;

    //取一个值
   /* @Test
    public void testSelect(){
        String valeu = (String) this.redisTemplate.opsForValue().get("key");
        System.out.println(valeu);
    }*/

    //取一个值
    @Test
    public void testSelectone(){
        String valeu = (String) stringRedisTemplateredisTemplate.opsForValue().get("key");
        System.out.println(valeu);
    }

    @Test
    public void Tsere(){
        Student student = new Student();
        student.setId(1);
        student.setClassName("fnkisf");
        student.setGrade("29101");
        student.setName("zhhdh");

        JSONObject jsonObject = JSONObject.fromObject(student);
        System.out.println(jsonObject);

        Student o = (Student)JSONObject.toBean(jsonObject, Student.class);
        System.out.println(o.getClassName());

    }

    @Test
    public void test3(){
        List<Totaldachengdu> totaldachengduList = finallyMapper.checkTotaldachengdu("程序设计基础", "2017");
        System.out.println("hahah");
    }

    @Test
    public void test4(){
        List<PersonalDachengdu> list = finallyMapper.checkPersonalDachengdu("计算机网络", "2017");
        System.out.println("hahah");
    }

    @Test
    public void test5(){
        List<GraduationDachengdu> graduationDachengdus = finallyMapper.checkGraduationDachengdu("2017");
        System.out.println("hahah");
    }
}
