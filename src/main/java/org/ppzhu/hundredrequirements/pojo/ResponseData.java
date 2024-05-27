package org.ppzhu.hundredrequirements.pojo;

import lombok.Data;

@Data
public class ResponseData {
    private String message;
    private int code;
    private Object data;

    public ResponseData(){

    }

    public ResponseData(String message, int code, Object data){
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static ResponseData error(String message, int code){
        ResponseData responseData = new ResponseData();
        responseData.setMessage(message);
        responseData.setCode(code);
        return responseData;
    }

    public static ResponseData success(String message){
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.setMessage(message);
        return responseData;
    }

    public static ResponseData success(Object data){
        ResponseData responseData = new ResponseData();
        responseData.setData(data);
        responseData.setMessage("注册成功");
        responseData.setCode(200);
        return responseData;
    }
}
