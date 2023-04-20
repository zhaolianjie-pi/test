package com.game.core.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Pain {
    private Integer id;
    private String openId;
    private Integer pindex;
    private String name;
    private Integer status;
    private Date startTime;
    private Date endTime;
    private String timeRange;
    private Integer floor;
    private String messageId;
    private String departMentName; //部门名
}
