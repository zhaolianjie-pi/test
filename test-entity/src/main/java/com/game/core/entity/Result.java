package com.game.core.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果封装
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;

    private String msg = "";

    private Object data = null;

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result() {
        super();
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }


    public static Result success() {
        Result result = new Result();
        result.setStatus(ResultStatus.SUCCESS);
        return result;
    }

    public static Result successData(Object obj) {
        return success().data(obj);
    }

    public static Result newResultStutus(Integer status) {
        Result result = new Result();
        result.setStatus(status);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setStatus(ResultStatus.ERROR);
        return result;
    }

    public static Result dataError() {
        Result result = new Result();
        result.setStatus(ResultStatus.DATAEXCPTION);
        return result;
    }

    /**
     * 未登录
     *
     * @return
     */
    public static Result noLogin() {
        Result result = new Result();
        result.setStatus(ResultStatus.NOLOGIN);
        result.setMsg("登录状态已失效,请重新登录!");
        return result;
    }


    public static Result userBanned() {
        Result result = new Result();
        result.setStatus(ResultStatus.USER_BANNED);
        result.setMsg("用户被封禁!");
        return result;
    }


    public static Result accessDeny() {
        Result result = new Result();
        result.setStatus(ResultStatus.ACCESS_DENY);
        return result;
    }

    /**
     * 版本太低
     *
     * @return
     */
    public static Result versionsLow() {
        Result result = new Result();
        result.setStatus(ResultStatus.VERSIONS_LOW);
        result.setMsg("当前APP版本过低，请更新APP版本!");
        return result;
    }


    /**
     * 自定义msg
     *
     * @return
     */
    public static Result gloBalResult(String msg) {
        Result result = new Result();
        result.setStatus(ResultStatus.ERROR);
        result.setMsg(msg);
        return result;
    }

    public static Result newUser() {
        Result result = new Result();
        result.setStatus(ResultStatus.NEWUSER);
        return result;
    }

    /**
     * 钉钉扫码后登陆
     *
     * @return
     */
    public static Result dingTalkNoLogin() {
        Result result = new Result();
        result.setStatus(ResultStatus.DINGTALK_NOLOGIN);
        result.setMsg("钉钉扫码后登录");
        return result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
