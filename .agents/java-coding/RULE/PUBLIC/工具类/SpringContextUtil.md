package com.ygsoft.jt.teng.fw.core.base.util;

import com.ygsoft.jt.teng.fw.core.log.IJtLog;
import com.ygsoft.jt.teng.fw.core.log.JtLogFactory;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static final IJtLog LOG = JtLogFactory.getLog(SpringContextUtil.class);
    private static ApplicationContext applicationContext;
    private static MessageSource messageSource;
    private static Environment environment;

    public SpringContextUtil(MessageSource bundleMessageSource) {
        messageSource = bundleMessageSource;
    }

    private void setApplicationContextInternal(ApplicationContext newapplicationContext) {
        applicationContext = newapplicationContext;
    }

    public void setApplicationContext(ApplicationContext newapplicationContext) {
        this.setApplicationContextInternal(newapplicationContext);
    }

    public void setMessageSource(MessageSource newmessageSource) {
        messageSource = newmessageSource;
    }

    public static MessageSource getMessageSource() {
        return messageSource;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext != null ? applicationContext : null;
    }

    public static <T> T getSpringBean(Class<T> clazz) {
        String className = clazz.getSimpleName();
        String beanId;
        if (clazz.isInterface() && className.charAt(0) == 'I' && Character.isUpperCase(className.charAt(1))) {
            beanId = StringUtil.firstToLowerCase(className.substring(1));
        } else {
            beanId = StringUtil.firstToLowerCase(className);
        }

        T bean = null;
        ApplicationContext context = getApplicationContext();

        try {
            if (context != null) {
                bean = context.getBean(beanId, clazz);
            }
        } catch (BeansException var7) {
            if (context != null) {
                Map<String, T> beans = context.getBeansOfType(clazz);
                if (!beans.isEmpty()) {
                    bean = beans.values().iterator().next();
                }
            }
        }

        return bean;
    }

    // 获取Spring对象
    public static Object getSpringBean(String beanId) {
        if (beanId != null && beanId.trim().length() > 0) {
            try {
                ApplicationContext context = getApplicationContext();
                return context != null ? context.getBean(beanId) : null;
            } catch (Exception var2) {
                if (LOG.isErrorEnabled()) {
                    LOG.error("Spring beanId [" + beanId + "] not found");
                }

                return null;
            }
        } else {
            return null;
        }
    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static void setEnvironment(Environment env) {
        environment = env;
    }
}
