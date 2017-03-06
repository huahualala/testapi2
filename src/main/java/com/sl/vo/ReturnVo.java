package com.sl.vo;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/3/2 0002.
 */
public class ReturnVo {

    private Integer errCode;
    private String msg;
    private Map<Object,Object> datas;

    public ReturnVo() {
        errCode = 1;
        msg = "请求成功！";
        datas = new HashMap<Object, Object>();
    }

    public ReturnVo(Integer errCode, String msg, Map<Object, Object> datas) {
        this.errCode = errCode;
        this.msg = msg;
        this.datas = datas;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<Object, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<Object, Object> datas) {
        this.datas = datas;
    }
}
