package com.dh.common.util;

import com.dh.common.basic.BaseException;
import com.dh.common.basic.BaseExceptionEnum;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author KaiFa
 */
public class ValidatUtils {
    /**
     * 空判断校验
     *
     * @param rerrMsg: 额外错误信息
     * @param obj:     对象
     * @param e:       错误码
     * @return: void
     * @author: Nevernow
     * @Date: 2019/9/9 18:09
     */
    public static void validateRequire(Object obj, String lerrMsg, BaseExceptionEnum e, String... rerrMsg) {
        if (null == obj) {
            throw new BaseException(lerrMsg, e, rerrMsg);
        }
        if (obj instanceof String && StringUtils.isEmpty(obj)) {
            throw new BaseException(lerrMsg, e, rerrMsg);
        }
        if (obj instanceof Collection && CollectionUtils.isEmpty((Collection) obj)) {
            throw new BaseException(lerrMsg, e, rerrMsg);
        }
    }

    public static void validateRequire(Object str, String lmsg, String... rMsg) {
        validateRequire(str, lmsg, BaseExceptionEnum.VALIDATE_REQUIRE_FAILE, rMsg);
    }
}
