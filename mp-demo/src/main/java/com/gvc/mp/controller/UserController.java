package com.gvc.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.gvc.mp.domain.dto.PageDTO;
import com.gvc.mp.domain.dto.UserFormDTO;
import com.gvc.mp.query.UserQuery;
import com.gvc.mp.domain.po.User;
import com.gvc.mp.domain.vo.UserVO;
import com.gvc.mp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @ApiOperation("新增用户接口")
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userDTO) {
        //1、把DTO 拷贝至 PO (这里采用hutool工具拷贝)
        User user = BeanUtil.copyProperties(userDTO, User.class);
        //2、新增
        userService.save(user);
    }

    @ApiOperation("删除用户接口")
    @DeleteMapping ("/{id}")
    public void deleteUser(@ApiParam("用户id") @PathVariable("id") Long id) {
        userService.removeById(id);
    }

    @ApiOperation("根据ID查询用户接口")
    @GetMapping ("/{id}")
    public UserVO queryUserById(@ApiParam("用户id") @PathVariable("id") Long id) {
        return userService.queryUsersAndAddressById(id);
    }

    @ApiOperation("根据ID批量查询用户接口")
    @GetMapping
    public List<UserVO> queryUserByIds(@ApiParam("用户id集合") @RequestParam("ids") List<Long> ids) {
        return userService.queryUserAndAddressByIds(ids);
    }

    @ApiOperation("扣减用户余额接口")
    @PutMapping ("/{id}/deduction/{money}")
    public void deductBalance(
            @ApiParam("用户id") @PathVariable("id") Long id,
            @ApiParam("扣减的金额") @PathVariable("money") Integer money) {
        userService.deductBalance(id,money);
    }

    @ApiOperation("根据多条件批量查询用户接口")
    @GetMapping("/list")
    public List<UserVO> queryUsers(UserQuery query) {
        List<User> userList = userService.queryUsers(query.getName(),query.getStatus(),query.getMinBalance(),query.getMaxBalance());
        return BeanUtil.copyToList(userList, UserVO.class);
    }

    @ApiOperation("根据多条件分页查询用户接口")
    @GetMapping("/page")
    public PageDTO<UserVO> queryUsersPage(UserQuery query) {
        return userService.queryUsersPage(query);
    }
}
