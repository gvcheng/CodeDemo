package com.gvc.mp.service.impl;

import com.gvc.mp.domain.po.Address;
import com.gvc.mp.mapper.AddressMapper;
import com.gvc.mp.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gvcheng
 * @since 2026-03-06
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
