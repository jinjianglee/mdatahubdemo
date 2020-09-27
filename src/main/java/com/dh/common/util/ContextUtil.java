package com.dh.common.util;

import com.dh.MdataHubApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class ContextUtil {
    public static Object getBean(String beanName) {
        return MdataHubApplication.APPLICATION.getBean(beanName);
    }

    public static Object getBean(Class bean) {
        return MdataHubApplication.APPLICATION.getBean(bean);
    }

    public static ConfigurableApplicationContext getContext(Class bean) {
        return MdataHubApplication.APPLICATION;
    }
}
