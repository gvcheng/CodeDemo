package com.gvc.mp.domain.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gvc.mp.domain.po.User;
import com.gvc.mp.domain.vo.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@ApiModel(description = "分页结果")
public class PageDTO<T> {
    @ApiModelProperty("总条数")
    private long total;
    @ApiModelProperty("总页数")
    private long pages;
    @ApiModelProperty("集合")
    private List<?> list;

    public static<PO, VO> PageDTO<VO> of(Page<PO> p, Class clazz) {
        //3.1 总条数，总页数，当前页数据
        PageDTO<VO> dto = new PageDTO<>();
        dto.setTotal(p.getTotal());
        dto.setPages(p.getPages());
        List<PO> records = p.getRecords();
        if(CollUtil.isEmpty(records)){
            dto.setList(Collections.emptyList());
            return dto;
        }
        //3.2 转vo
        List<VO> voList = BeanUtil.copyToList(records, clazz);
        dto.setList(voList);
        return dto;
    }

    public static<PO, VO> PageDTO<VO> of(Page<PO> p, Function<PO, VO> converter) {
        //3.1 总条数，总页数，当前页数据
        PageDTO<VO> dto = new PageDTO<>();
        dto.setTotal(p.getTotal());
        dto.setPages(p.getPages());
        List<PO> records = p.getRecords();
        if(CollUtil.isEmpty(records)){
            dto.setList(Collections.emptyList());
            return dto;
        }
        //3.2 转vo
        List<VO> voList = records.stream().map(converter).collect(Collectors.toList());//手动转换
        dto.setList(voList);
        return dto;
    }

}
