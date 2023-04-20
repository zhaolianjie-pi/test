package com.game.core.dao;

import com.game.core.entity.BoundingBoxDO;
import com.game.core.entity.BoundingBoxQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoundingBoxDao {

    BoundingBoxDO findById(Integer id);

    void create(BoundingBoxDO boundingBoxDO);

    void insertBatch(List<BoundingBoxDO> list);

    BoundingBoxDO findBoundingBox(BoundingBoxQuery query);

    void update(BoundingBoxDO boundingBoxDO);
}
