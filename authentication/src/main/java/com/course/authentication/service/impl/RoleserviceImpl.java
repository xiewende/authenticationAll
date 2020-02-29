package com.course.authentication.service.impl;

import com.course.authentication.mapper.RoleMapper;
import com.course.authentication.pojo.Role;
import com.course.authentication.pojo.RoleExample;
import com.course.authentication.service.Roleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2019-12-19  20:35
 */
@Service
public class RoleserviceImpl implements Roleservice {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer inserRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public Integer updateRole(Role role) {

        //根据角色名字找id 前端没有传过来
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(role.getName());
        List<Role> list = roleMapper.selectByExample(example);
        Role selectRole = list.get(0);
        role.setId(selectRole.getId());
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Integer deleteRole(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Role> listRole() {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        return roleMapper.selectByExample(example);
    }
}
