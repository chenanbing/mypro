package com.cab.dao.mapper.order;

import com.cab.bean.entity.order.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int insertBatch(@Param("records") List<Order> records);

    List<Order> selectByModel(Order record);

    List<Long> selectIdByModel(Order record);

    List<Order> selectByIds(@Param("ids") List<Integer> ids);
}