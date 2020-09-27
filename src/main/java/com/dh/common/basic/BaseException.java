package com.dh.common.basic;

/**
 * V2ray Controller Exception
 *
 * @author: Nevernow
 * @Date: 17:31 2019/7/12
 */
public class BaseException extends RuntimeException {
    private BaseExceptionEnum error;
    private String rmsg;
    private String lmsg;

    public BaseException(BaseExceptionEnum error) {
        super(error.getMeg());
        this.error = error;
        this.rmsg = "";
        this.lmsg = "";
    }

    public BaseException(String lmsg, BaseExceptionEnum error, String... rmsg) {
        super(lmsg);
        this.lmsg = lmsg;
        this.error = error;
        if (null == rmsg) {
            this.rmsg = "";
            return;
        }
        this.rmsg = String.join("", rmsg);
    }

    @Override
    public String getMessage() {
        return this.lmsg + error.getMeg() + this.rmsg;
    }

    public BaseExceptionEnum getErr() {
        return error;
    }

    public String getErrCode() {
        return error.name();
    }
}
