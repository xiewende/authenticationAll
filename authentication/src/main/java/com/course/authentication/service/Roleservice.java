package com.course.authentication.service;

import com.course.authentication.pojo.Role;

import java.util.List;

/**
 * @create 2019-12-19  20:35
 */
public interface Roleservice {

    //添加角色
    public Integer inserRole(Role role);

    //修改角色
    public Integer updateRole(Role role);

    //删除角色
    public Integer deleteRole(Integer id);

    //查询所有角色
    public List<Role> listRole();
}
