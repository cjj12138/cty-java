package com.exam.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.demo.entity.GoodInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author A
 */
public interface GoodInfoMapper extends BaseMapper<GoodInfo> {

    @Select("select good_pic from  good_pic where good_id=#{goodId} LIMIT 1")
    String getGoodPath(@Param("goodId") String goodId);

    @Select("select good_pic from  good_pic where good_id=#{goodId}")
    List<String> getGoodPathMorePic(@Param("goodId") String goodId);

    @Select("update good_info set good_status =#{status} where good_id=#{goodId}")
    void updateStatus(@Param("goodId")String goodId,@Param("status") String status);

    @Select("insert into my_like values (#{goodId},#{userId})")
    void insertIntoMyLike(@Param("goodId") String goodId,@Param("userId") String userId);

    @Select("select GOOD_ID from my_like where user_id=#{userId} ")
    List<String> selectMyfvt(@Param("userId") String userId);
}
