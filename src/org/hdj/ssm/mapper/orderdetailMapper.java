package org.hdj.ssm.mapper;

import org.hdj.ssm.po.orderdetail;

public interface orderdetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(orderdetail record);

    int insertSelective(orderdetail record);

    orderdetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(orderdetail record);

    int updateByPrimaryKey(orderdetail record);
}