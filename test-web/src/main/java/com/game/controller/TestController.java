package com.game.controller;

import com.game.core.entity.Result;
import com.game.core.entity.Entity;
import com.game.core.entity.Pain;
import com.game.core.service.Painservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaolianjie
 * @date 2022年12月07日 11:14
 */
//@Api("测试类")
@RestController
@RequestMapping("/test/test1")
public class TestController {

    @Autowired
    private Painservice painservice;


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello1")
    public Entity test1(Integer userId) {
        Entity entity = new Entity();
        entity.setUserId(userId);
        entity.setName("123");

        return entity;
    }

    @PostMapping("/hello2")
    public Result test2(Pain pain) {
        Map<String, String> map = new HashMap<String, String>();

        return Result.success().data(painservice.findById(1));
    }
}
