package com.gvc.mapper;

import com.gvc.pojo.Comment;
import org.apache.ibatis.annotations.Select;


public interface CommentMapper {
    @Select("select * from t_comment where id = #{id}")
    Comment findById(Integer id);
}
