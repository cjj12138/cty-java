package com.exam.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.demo.entity.GoodInfo;
import com.exam.demo.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Select("select t2.* \n" +
            "from my_like t1 \n" +
            "left join good_info t2 on t1.GOOD_ID=t2.GOOD_ID\n" +
            "where user_id=#{userId} ")
    List<GoodInfo> selectMyfvt(@Param("userId") String userId);

    @Select("delete from  my_like where good_id=#{goodId} and user_id=#{userId}")
    void deleteMyLove(@Param("userId") String userId,@Param("goodId") String goodId);

    @Select("select t1.ORDER_OWNER,t2.GOOD_ID, t2.GOOD_DES, t2.GOOD_NAME, t2.GOOD_OWNER, t2.GOOD_PRICE, t2.GOOD_SIZE, t3.order_cn_status as GOOD_STATUS\n" +
            "from order_info t1\n" +
            "    LEFT JOIN good_info t2 on t1.GOOD_ID = t2.GOOD_ID\n" +
            "left join order_status t3 on t1.ORDER_STATUS = t3.order_status\n" +
            "where t1.ORDER_OWNER = #{userId}")
    List<GoodInfo> getMyOrder(@Param("userId") String userId);

    @Select("update order_info set order_status = #{status}  where good_Id=#{goodId}")
    void updateOrderId(@Param("goodId") String goodId,@Param("status") String status);

}
