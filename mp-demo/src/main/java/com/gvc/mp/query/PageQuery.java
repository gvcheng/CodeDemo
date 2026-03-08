package com.gvc.mp.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gvc.mp.domain.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分页查询实体")
public class PageQuery {
    @ApiModelProperty("页码")
    private Integer pageNo = 1;
    @ApiModelProperty("页面大小")
    private Integer pageSize = 5;
    @ApiModelProperty("排序字段")
    private String sortBy;
    @ApiModelProperty("是否升序")
    private boolean isAsc = true;

    public <T> Page<T> toPage(OrderItem...items) {
        //1 构建分页条件
        //1.1 分页条件
        Page<T> page = Page.of(pageNo, pageSize);
        //1.2 排序条件
        if (StrUtil.isNotBlank(sortBy)) {//非空
            page.addOrder(new OrderItem(sortBy, isAsc));
        }else if (items!=null && items.length>0){
            //为空，默认按照更新时间排序
            page.addOrder(items);
        }
        return page;
    }

    public <T> Page<T> toPage(String defaultSortBy, boolean defaultIsAsc) {
        return toPage(new OrderItem(defaultSortBy, defaultIsAsc));
    }

    public <T> Page<T> toPageSortByCreateTime() {
        return toPage(new OrderItem("create_time", false));
    }

    public <T> Page<T> toPageSortByUpdateTime() {
        return toPage(new OrderItem("update_time", false));
    }
}
