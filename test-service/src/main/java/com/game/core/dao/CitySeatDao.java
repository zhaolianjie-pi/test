package com.game.core.dao;

import com.game.core.entity.CitySeat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CitySeatDao {

    CitySeat findById(Integer id);

    void create(CitySeat citySeat);

    void insertBatch(List<CitySeat> list);


    void update(CitySeat citySeat);
}
