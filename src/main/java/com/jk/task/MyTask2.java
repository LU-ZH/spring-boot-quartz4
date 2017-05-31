package com.jk.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dell on 2017/5/30.
 */
@Service
public class MyTask2 extends BaseTask {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("MyTask2.execute() " + new Date());
    }

    @Override
    public String getCronExpression() {
        return "0/6 * * * * ?";//每4秒执行
    }

}
