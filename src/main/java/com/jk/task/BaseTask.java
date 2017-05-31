package com.jk.task;

import com.jk.job.HelloJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by dell on 2017/5/29.
 */
public class BaseTask implements Job{

    @Autowired
    private Scheduler scheduler;

    @PostConstruct //等同于init-method
    public void init(){
        //定义jobDetail
        JobDetail jobDetail = JobBuilder.newJob(this.getClass()).withIdentity(this.getClass().getSimpleName()+"job",this.getClass().getSimpleName()+"group").build();

        //定义trigger
//        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1").startNow().withSchedule(simpleScheduleBuilder).build();
        String cronExpression = getCronExpression();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(this.getClass().getSimpleName()+"_trigger", this.getClass().getSimpleName()+"_group").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();

        try {
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

//        try {
//            scheduler.deleteJob(new JobKey("job1","group1"));
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }

    public String getCronExpression(){
        return null;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //这里并没有真正意义上的实现

    }
}
