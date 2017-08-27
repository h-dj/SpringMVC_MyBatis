package org.hdj.ssm.mapper;

import org.hdj.ssm.po.orders;

public interface ordersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(orders record);

    int insertSelective(orders record);

    orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(orders record);

    int updateByPrimaryKey(orders record);
}