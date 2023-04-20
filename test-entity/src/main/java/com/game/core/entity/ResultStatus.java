package com.game.core.entity;

import java.io.Serializable;

/**
 * Author: koabs
 * 8/22/16.
 * 返回结果状态
 */
public class ResultStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    //接口正常返回
    public static Integer SUCCESS = 200;

    //系统内部错误,需要前端捕获的
    public static Integer ERROR = 500;

    //用户未登陆
    public static Integer NOLOGIN = 501;

    public static Integer USER_BANNED = 504;


    public static Integer NOUNIONID = 502;

    //没有formToken，不能提交表单
    public static Integer DATAEXCPTION = 503;

    //新用户待绑定
    public static Integer NEWUSER = 201;

    //手机号已注册
    public static Integer MOBILE_DUPLICATE = 202;

    //IM用户注册失败
    public static Integer IM_REGIST_FAIL = 203;

    //无接口调用权限
    public static Integer ACCESS_DENY = 403;

    //强制更新
    public static Integer VERSIONS_LOW = 600;

    //需要签约
    public static Integer NEED_SIGN = 700;
    //超过提现限制
    public static Integer OVER_WITHDRAW_LIMIT = 701;

    // 钉钉未扫码
    public static Integer DINGTALK_NOLOGIN = 801;

    // 需要进行风控拦截
    public static Integer RISK_INTERCEPTOR_NEEDED = 802;

    // 风控拦截器未通过
    public static Integer RISK_INTERCEPTOR_DENY = 803;


}
