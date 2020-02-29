package com.course.authentication.service;

import com.course.authentication.pojo.Indication;

import java.util.List;

/**
 * @create 2019-12-23  19:28
 */
public interface IndicationService {

    //添加指标
    public Integer insertIndication(Indication indication);

    //修改指标
    public Integer updateIndication(Indication indication);

    //查询所有指标
    public List<Indication> listIndication(String version);

    //删除某个指标
    public Integer deleteIndication(Integer id);
}
