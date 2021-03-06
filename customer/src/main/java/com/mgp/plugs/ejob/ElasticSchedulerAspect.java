package com.mgp.plugs.ejob;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.script.ScriptJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/7/17 17:13
 * 定义扫描方法
 */
@Component
public class ElasticSchedulerAspect implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Autowired
    private ElasticJobHandler elasticJobHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
        registrJob(applicationContext);
    }

    /**
     * 解析context信息，开始注册
     * @param applicationContext
     */
    private void registrJob(ApplicationContext applicationContext) {
        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForAnnotation(ElasticScheduler.class);
        for (String beanName : beanNamesForAnnotation) {
            Class<?> handlerType = applicationContext.getType(beanName);
            Object bean = applicationContext.getBean(beanName);
            ElasticScheduler annotation = AnnotationUtils.findAnnotation(handlerType, ElasticScheduler.class);
            addJobToContext(annotation,bean);
        }
    }

    /**
     * 将任务添加到容器中
     * @param elasticScheduler
     * @param bean
     */
    private void addJobToContext(ElasticScheduler elasticScheduler, Object bean) {
        String cron = elasticScheduler.cron();
        String name = elasticScheduler.name();
        String description = elasticScheduler.description();
        String shardingItemParameters = elasticScheduler.shardingItemParameters();
        Integer shardingTotalCount = elasticScheduler.shardingTotalCount();
        String jobParamters = elasticScheduler.jobParameters();
        String scriptCommandLine = elasticScheduler.scriptCommandLine();
        String jobType = elasticScheduler.jobType();
        try {
            //根据scriptCommandLine有没有参数值判断是那种类型的任务
            //这个对象不适合太频繁的定时，最好是用于一天一次这种的
            if("simple".equals(jobType)) {

                elasticJobHandler.addSimpleJob((SimpleJob) bean,cron,shardingTotalCount,shardingItemParameters,jobParamters, description, scriptCommandLine, jobType);

            }else if("dataFlow".equals(jobType)){

                elasticJobHandler.addDataflowJob((DataflowJob) bean,cron,shardingTotalCount,shardingItemParameters,jobParamters, description, scriptCommandLine, jobType);

            }else if("script".equals(jobType)){

                elasticJobHandler.addScriptJob((ScriptJob) bean,cron,shardingTotalCount,shardingItemParameters,jobParamters, description, scriptCommandLine, jobType);

            }else{

                elasticJobHandler.addSimpleJob((SimpleJob) bean,cron,shardingTotalCount,shardingItemParameters,jobParamters, description, scriptCommandLine, jobType);

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
