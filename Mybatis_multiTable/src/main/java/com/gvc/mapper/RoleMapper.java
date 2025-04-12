package com.gvc.mapper;
import com.gvc.domain.Role;

import java.util.List;

public interface RoleMapper {
    //根据用户id查询对应角色
    public List<Role> findByUid(Integer uid);
}
