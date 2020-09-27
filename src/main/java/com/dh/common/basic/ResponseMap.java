package com.dh.common.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * @author KaiFa
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class ResponseMap {
    /**
     * log Object
     */
    private static Logger log = LoggerFactory.getLogger(BaseResponse.class);
    String code;
    String message;
    Object data;

    public ResponseMap() {
        this.code = "0";
        this.message = "success";
    }

    public void setError(String meg, String errCode) {
        this.code = errCode;
        this.message = meg;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        ResponseMap.log = log;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseMap run(Supplier function) {
        ResponseMap result = new ResponseMap();
        try {
            result.setData(function.get());
        } catch (Exception e) {
            log.error("code", e);
            result.setError(e.getMessage(), "fail");
        }
        return result;
    }
}
