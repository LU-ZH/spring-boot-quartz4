package com.jk.task;

import com.jk.job.HelloJob;
import com.jk.service.HelloService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by dell on 2017/5/29.
 *
 * Quartz中的job
 */
@Service
public class MyTask extends BaseTask{

    /**
     * 为什么HelloService会为null？
     *
     * Quartz中的job是由Quartz框架【动态】创建的（通过配置job的className，通过反射进行动态创建），
     *
     * 所以在job中使用spring bean的话，是无法进行使用的。（Job类，不是一个spring bean）。
     *
     * 如何将我们的job交给Spring进行管理？
     *
     * 使用AutoWireCapableBeanFactory进行自动注入job
     */
    @Resource
    private HelloService helloService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException{
        System.out.println("MyTask.execute() "+new Date());
        helloService.hello();
    }

    @Override
    public String getCronExpression() {
        return "0/3 * * * * ?";//每3秒执行
    }

}
