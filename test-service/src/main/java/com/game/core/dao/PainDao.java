package com.game.core.dao;

import com.game.core.entity.Pain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PainDao {

    Pain findById(Integer id);

}
