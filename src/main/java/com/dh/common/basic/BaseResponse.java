package com.dh.common.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * Base Response Data Transfer Object
 *
 * @author: Nevernow
 * @Date: 17:40 2019/7/12
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseResponse {
    /**
     * log Object
     */
    private static Logger log = LoggerFactory.getLogger(BaseResponse.class);
    String errCode;
    String meg;
    Object result;

    public BaseResponse() {
        this.errCode = "0";
        this.meg = "success";
    }

    public void setError(String meg, String errCode) {
        this.meg = meg;
        this.errCode = errCode;
    }



    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMeg() {
        return meg;
    }

    public void setMeg(String meg) {
        this.meg = meg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }


    public static BaseResponse run(Supplier function) {
        BaseResponse result = new BaseResponse();
        try {
            result.setResult(function.get());
        } catch (Exception e) {
            log.error("error!", e);
            result.setError(e.getMessage(), "INNER_EXCEPTION");
        }
        return result;
    }
}
