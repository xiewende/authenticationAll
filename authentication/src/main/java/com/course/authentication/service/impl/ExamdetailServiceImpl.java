package com.course.authentication.service.impl;

import com.course.authentication.mapper.ExamdetailMapper;
import com.course.authentication.pojo.Examdetail;
import com.course.authentication.pojo.ExamdetailExample;
import com.course.authentication.service.ExamdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2019-12-28  16:34
 */
@Service
public class ExamdetailServiceImpl implements ExamdetailService {

    @Autowired
    private ExamdetailMapper examdetailMapper;
    @Override
    public Integer insertExamdetail(Examdetail examdetail,List<String> nameList,List<Double> vualeList) {
        //插入的对象 examdetail

        //判断数据库中是否有
        ExamdetailExample examdetailExample = new ExamdetailExample();
        ExamdetailExample.Criteria criteria = examdetailExample.createCriteria();
        criteria.andVersionEqualTo(examdetail.getVersion());
        criteria.andExamtypeIdEqualTo(examdetail.getExamtypeId());
        criteria.andTargetIdEqualTo(examdetail.getTargetId());
        List<Examdetail> examdetailList = examdetailMapper.selectByExample(examdetailExample);
        if(examdetailList.size()>0){
            return 0;
        }

        //成功判断标志
        Integer tag = 0;

        String name ;
        Double value;
        for(int i=0 ;i<nameList.size() ;i++){
            name = nameList.get(i);
            value = vualeList.get(i);
            if(value<=1){
                examdetail.setWeight(value);
            }else {
                examdetail.setScore(value);
            }
            examdetail.setName(name);
            //执行cherubic
            examdetailMapper.insert(examdetail);
            tag++;
        }

        return tag;
    }

    //查询所有的考核细则  根据考核方式  目标 二选一  版本必填
    @Override
    public List<Examdetail> listAllExamDetailByExamTypeOrTarget(Integer targetId, Integer examtypeId, String version) {
        List<Examdetail> examdetailList = new ArrayList<>();
        if(targetId!=null && examtypeId!=null){
            examdetailList = examdetailMapper.listAllExamDetailByExamTypeAndTarget(targetId, examtypeId, version);
        }
        if(targetId==null && examtypeId!=null){
            examdetailList = examdetailMapper.listAllExamDetailByExamType(examtypeId,version);
        }
        if(targetId!=null && examtypeId==null){
            examdetailList = examdetailMapper.listAllExamDetailByTarget(targetId,version);
        }
        return examdetailList;
    }
}
