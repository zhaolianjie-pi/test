package com.game.core.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhaolianjie
 * @date 2023年03月03日 9:42
 */
@Data
public class BoundingBoxDO {

    private Integer id;
    private double southLatitude;
    private double northLatitude;
    private double westLongitude;
    private double eastLongitude;

    private String province;

    private String city;

    private Date createTime;

    private Date updateTime;

}
