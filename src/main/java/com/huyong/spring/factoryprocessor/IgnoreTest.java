package com.huyong.spring.factoryprocessor;

import com.huyong.spring.bean.Techer;
import com.huyong.spring.bean.User;
import com.huyong.spring.bean.UserAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class IgnoreTest implements BeanFactoryPostProcessor, BeanFactoryAware {

    BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.ignoreDependencyInterface(UserAware.class);
    }
}
