package com.game.core.service.impl;

import com.game.core.dao.PainDao;
import com.game.core.entity.Pain;
import com.game.core.service.Painservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaolianjie
 * @date 2022年12月07日 15:01
 */
@Service
public class PainServiceImpl implements Painservice {
    @Autowired
    private PainDao painDao;

    @Override
    public Pain findById(Integer id) {
        return painDao.findById(id);
    }
}
