package com.course.authentication.service.impl;

import com.course.authentication.mapper.IndicationMapper;
import com.course.authentication.pojo.Indication;
import com.course.authentication.pojo.IndicationExample;
import com.course.authentication.service.IndicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2019-12-23  19:31
 */
@Service
public class IndicationServiceImpl implements IndicationService {

    @Autowired
    private IndicationMapper indicationMapper;

    @Override
    public Integer insertIndication(Indication indication) {
        return indicationMapper.insert(indication);
    }

    @Override
    public Integer updateIndication(Indication indication) {
        return indicationMapper.updateByPrimaryKeySelective(indication);
    }

    @Override
    public List<Indication> listIndication(String version) {
        IndicationExample example = new IndicationExample();
        IndicationExample.Criteria criteria = example.createCriteria();
        if(version!=null){
            criteria.andVersionEqualTo(version);
        }
        List<Indication> indicationList = indicationMapper.selectByExample(example);
        return indicationList;
    }

    @Override
    public Integer deleteIndication(Integer id) {
        return indicationMapper.deleteByPrimaryKey(id);
    }
}
