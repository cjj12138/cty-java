package com.exam.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author A
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Select("insert into good_pic (good_id,good_pic) values(#{uuid} ,#{filePath}) ")
    void insertPath(@Param("uuid") String uuid,@Param("filePath") String filePath);
}
