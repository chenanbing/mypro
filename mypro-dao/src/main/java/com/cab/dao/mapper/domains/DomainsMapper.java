package com.cab.dao.mapper.domains;

import com.cab.bean.entity.domains.Domains;

public interface DomainsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Domains record);

    int insertSelective(Domains record);

    Domains selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Domains record);

    int updateByPrimaryKey(Domains record);
}