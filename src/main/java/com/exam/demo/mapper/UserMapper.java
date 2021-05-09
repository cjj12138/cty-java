package com.exam.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.demo.entity.User;
import com.exam.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    public User checkuser();

    @Select("update User set SCORED=#{scored} where USERID=#{userId}")
    void updateScore(@Param("userId") String userId,@Param("scored") int x);


    @Select("insert into exchange_goods_msg values(#{goodsTitle},#{username},#{uuid})")
    void insertIntoGoodMsg(@Param("goodsTitle") String goodsTitle, @Param("username") String username,@Param("uuid") String uuid);

    @Select("select user_id,user_role_id from user_login where user_id=#{userId}")
    User getRoleId(String userId);
}
