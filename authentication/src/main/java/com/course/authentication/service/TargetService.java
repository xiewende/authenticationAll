package com.course.authentication.service;

import com.course.authentication.pojo.Target;

import java.util.List;

/**
 * @create 2019-12-27  12:43
 */
public interface TargetService {

    //添加修改目标
    Integer insertTarget(Target target);

    //修改目标
    Integer updateTarget(Target target);

    //删除目标
    Integer delleteTarget(Integer id);

    //查询目标
    List<Target> listAllTarget(String courseName,String version);

    //查询所有目标 ，没有任何一个条件
    List<Target> listAllTargetByNo();
}
