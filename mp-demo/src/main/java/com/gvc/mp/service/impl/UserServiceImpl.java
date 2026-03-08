package com.gvc.mp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.gvc.mp.domain.dto.PageDTO;
import com.gvc.mp.domain.po.Address;
import com.gvc.mp.domain.po.User;
import com.gvc.mp.domain.vo.AddressVO;
import com.gvc.mp.domain.vo.UserVO;
import com.gvc.mp.enums.UserStatus;
import com.gvc.mp.mapper.UserMapper;
import com.gvc.mp.query.UserQuery;
import com.gvc.mp.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Override
    @Transactional
    public void deductBalance(Long id, Integer money) {
        //1.查询用户
        User user = this.getById(id);
        //2.校验用户状态
        if(user==null || user.getStatus()==UserStatus.FREEZE){
            throw new RuntimeException("用户状态异常！");
        }
        //3.校验余额是否充足
        if(user.getBalance() < money){
            throw new RuntimeException("用户余额不足！");
        }
        //4.扣减余额 update tb_user set balance = balance - ?
        int remainBalance = user.getBalance() - money;
        lambdaUpdate()
                .set(User::getBalance,remainBalance)
                .set(remainBalance == 0,User::getStatus,2)
                .eq(User::getId,id)
                .eq(User::getBalance,user.getBalance()) //乐观锁
                .update();
    }

    @Override
    public List<User> queryUsers(String name, Integer status, Integer minBalance, Integer maxBalance) {
        return lambdaQuery()
                .like(name != null, User::getUsername, name)
                .eq(status != null, User::getStatus, status)
                .ge(minBalance != null, User::getBalance, minBalance)
                .le(maxBalance != null, User::getBalance, maxBalance)
                .list();
    }

    @Override
    public UserVO queryUsersAndAddressById(Long id) {
        //1.查用户
        User user = getById(id);
        if(user==null || user.getStatus()== UserStatus.FREEZE){
            throw new RuntimeException("用户状态异常！");
        }
        //2.查地址
        List<Address> addressList = Db.lambdaQuery(Address.class).eq(Address::getUserId, id).list();
        //3.封装Vo
        //3.1 转User的PO为VO
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        //3.2 转地址VO
        if(CollUtil.isNotEmpty(addressList)){
            userVO.setAddresses(BeanUtil.copyToList(addressList, AddressVO.class));
        }
        return userVO;
    }

    @Override
    public List<UserVO> queryUserAndAddressByIds(List<Long> ids) {
        //1.查用户
        List<User> users = listByIds(ids);
        if(CollUtil.isEmpty(users)){
            return Collections.emptyList();
        }
        //2.查地址
        //2.1 获取用户id集合
        List<Long> userIds = users.stream().map(User::getId).collect(Collectors.toList());
        //2.2 根据id集合查地址
        List<Address> addresses = Db.lambdaQuery(Address.class).in(Address::getUserId, userIds).list();
        //2.3 地址VO预处理
        List<AddressVO> addressVOList = BeanUtil.copyToList(addresses, AddressVO.class);
        //2.4 地址分组：相同用户的放入一个集合（组）中
        Map<Long, List<AddressVO>> addressMap = new HashMap<>(0);
        if(CollUtil.isNotEmpty(addresses)){
            addressMap = addressVOList.stream().collect(Collectors.groupingBy(AddressVO::getUserId));
        }
        //3.转VO返回
        List<UserVO> userVOList = new ArrayList<>(users.size());
        for (User user : users) {
            //3.1 UserPO转UserVO
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            userVOList.add(userVO);
            //3.2 地址VO并入userVO
            userVO.setAddresses(addressMap.get(user.getId()));
        }
        return userVOList;
    }

    @Override
    public PageDTO<UserVO> queryUsersPage(UserQuery query) {
        String name = query.getName();
        Integer status = query.getStatus();
        //1 构建分页条件
        Page<User> page = query.toPageSortByUpdateTime();
        //2.分页查询
        Page<User> p = lambdaQuery()
                        .like(name != null, User::getUsername, name)
                        .eq(status != null, User::getStatus, status)
                        .page(page);
        //3.封装返回
//        return PageDTO.of(p, UserVO.class);
        return PageDTO.of(p,user -> { //自定义转换器
            //1.拷贝基础属性
            UserVO vo = BeanUtil.copyProperties(user, UserVO.class);
            //2.处理特殊逻辑
            vo.setUsername(vo.getUsername().substring(0,vo.getUsername().length()-2) + "**"); //隐藏用户名后两位

            return vo;
        });
    }


}
