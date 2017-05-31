package com.jk.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz的配置类：
 * 1.SchedulerFactoryBean
 * 2.Scheduler类
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private SpringBeanJobFactory springBeanJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //把job交给spring来管理，这样job就能使用spring产生的Bean了。
        schedulerFactoryBean.setJobFactory(springBeanJobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler(){
        return schedulerFactoryBean().getScheduler();
    }
}
