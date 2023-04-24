package com.game.controller;

import com.game.core.entity.Result;
import com.game.core.entity.Entity;
import com.game.core.entity.Pain;
import com.game.core.service.Painservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Entity entity1 = null;
        entity1.setUserId(userId);
        entity1.setName("123123");
        Integer a = 100;
        Integer b = 0;
        System.out.println(a/b);

        return entity1;
    }

    @PostMapping("/hello2")
    public Result test2(Pain pain) {
        Map<String, String> map = new HashMap<String, String>();

        return Result.success().data(painservice.findById(1));
    }
}
