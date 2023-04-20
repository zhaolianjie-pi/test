package com.game.controller;

import com.alibaba.fastjson.JSON;
import com.game.core.dao.BoundingBoxDao;
import com.game.core.entity.BoundingBox;
import com.game.core.entity.BoundingBoxDO;
import com.game.core.entity.BoundingBoxQuery;
import com.game.core.entity.CitySeat;
import com.game.core.entity.Entity;
import com.game.core.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.RegionalBlocking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaolianjie
 * @date 2022年12月07日 11:14
 */
//@Api("测试类")
@RestController
@RequestMapping("/boundingBox")
public class BoundingBoxController {

    @Autowired
    private BoundingBoxDao boundingBoxDao;


    @GetMapping("/hello")
    public String hello() {
        buildBoundingBoxDO();
        return "hello";
    }


    public List<BoundingBoxDO> buildBoundingBoxDO() {
        List<BoundingBoxDO> resultList = new ArrayList<>();
        List<BoundingBox> list = RegionalBlocking.regionalBlocking(28.52, 35.52, 107.31, 120.31, 200);
        System.out.println(list.size());
        List<CitySeat> configList = RegionalBlocking.getBoundingBoxConfig("C:\\Users\\123456\\Desktop\\区域划分.xls");

        for (BoundingBox boundingBox : list) {
            BoundingBoxDO boundingBoxDO = new BoundingBoxDO();
            boundingBoxDO.setSouthLatitude(boundingBox.getSouthLatitude());
            boundingBoxDO.setNorthLatitude(boundingBox.getNorthLatitude());
            boundingBoxDO.setEastLongitude(boundingBox.getEastLongitude());
            boundingBoxDO.setWestLongitude(boundingBox.getWestLongitude());
            for (CitySeat citySeat : configList) {
                if (citySeat.getBoundingBox().contains(boundingBox.getCenter())) {
                    boundingBoxDO.setProvince(citySeat.getProvice());
                    boundingBoxDO.setCity(citySeat.getCity());
                    System.out.println(JSON.toJSONString(boundingBoxDO));
                }
            }
            boundingBoxDao.create(boundingBoxDO);
            resultList.add(boundingBoxDO);
        }
        return resultList;
    }

    @GetMapping("/save")
    public Entity test1(BoundingBoxDO boundingBoxDO) {


        return null;
    }

    @PostMapping("/query")
    public Result test2(BoundingBoxQuery boundingBoxQuery) {

        return Result.success().data(boundingBoxDao.findBoundingBox(boundingBoxQuery));
    }
}
