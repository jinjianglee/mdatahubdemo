package com.dh.common.basic;

/**
 * @author KaiFa
 */

public enum BaseExceptionEnum {
    /**
     * 必填字段为空!
     */
    VALIDATE_REQUIRE_FAILE("错误!"),
    /**
     * 必填对象不存在!
     */
    VALIDATE_ORDER_ID_FAILED("为空!"),
    /**
     * 必填对象不存在!
     */
    VALIDATE_ORDER_ID_REPEAT(""),
    ;


    String meg;

    BaseExceptionEnum(String meg) {
        this.meg = meg;
    }

    public String getMeg() {
        return meg;
    }
}
