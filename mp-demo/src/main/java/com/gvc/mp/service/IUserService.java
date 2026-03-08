package com.gvc.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gvc.mp.domain.dto.PageDTO;
import com.gvc.mp.domain.po.User;
import com.gvc.mp.domain.vo.UserVO;
import com.gvc.mp.query.UserQuery;

import java.util.List;

public interface IUserService extends IService<User> {

    void deductBalance(Long id, Integer money);

    List<User> queryUsers(String name, Integer status, Integer minBalance, Integer maxBalance);

    UserVO queryUsersAndAddressById(Long id);

    List<UserVO> queryUserAndAddressByIds(List<Long> ids);

    PageDTO<UserVO> queryUsersPage(UserQuery query);
}
