package com.mgp.plugs.ejob;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.script.ScriptJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/7/17 15:01
 * 抽象添加job方法
 */
@Component
public class ElasticJobHandler {
    @Autowired
    private ZookeeperRegistryCenter regCenter;
    /**
     * 监听job启动和完毕时间
     */
    @Resource
    private ElasticJobListener elasticJobListener;

    /**
     * 事件追踪
     */
    @Resource
    private JobEventConfiguration jobEventConfiguration;


    /**
     * @Description simple任務配置類
     */
    private LiteJobConfiguration getSimpleLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters,
                                                         final String jobParameters,
                                                         final String description,
                                                         final String scriptCommandLine){

        //在分布式的场景下由于网络、时钟等原因，可能导致Zookeeper的数据与真实运行的作业产生不一致，这种不一致通过正向的校验无法完全避免。
        // 需要另外启动一个线程定时校验注册中心数据与真实作业状态的一致性，即维持Elastic-Job的最终一致性。
        //
        //在2.0.6之前的版本中，网络不稳定的环境下Elastic-Job有可能有的作业分片并未执行，重启一下就能修复。
        // 在2.0.6，版本中Elastic-Job在提供reconcileIntervalMinutes设置修复状态服务执行间隔分钟数，
        // 用于修复作业服务器不一致状态，默认每10分钟检测并修复一次。
        //参考：http://elasticjob.io/docs/elastic-job-lite/02-guide/job-reconcile/
        //失效转移
        //failover：是否开启任务执行失效转移，开启表示如果作业在一次任务执行中途宕机，允许将该次未完成的任务在另一作业节点上补偿执行
        //错过补偿
        //misfire: 是否开启错过任务重新执行，默认开启（因为上一次任务没有执行完，导致的定时任务没有触发，
        // 一般与monitorExecution幂等性-代码控制联用，默认开启），无法处理服务器宕机的情况，因为宕机导致的定时任务未运行代码自行处理，频繁的任务没效果，比如每20s等

        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                        .shardingItemParameters(shardingItemParameters).jobParameter(jobParameters).failover(true).misfire(true).build(),
                        jobClass.getCanonicalName())
                      ).overwrite(true).reconcileIntervalMinutes(5).jobShardingStrategyClass("com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy").build();
    }

    /**
     * @Description script任務配置類
     */
    private LiteJobConfiguration getScriptLiteJobConfiguration(final Class<? extends ScriptJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters,
                                                         final String jobParameters,
                                                         final String description,
                                                         final String scriptCommandLine){
        return LiteJobConfiguration.newBuilder(new ScriptJobConfiguration(JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters).jobParameter(jobParameters).failover(true).misfire(true).build(),
                scriptCommandLine)
        ).overwrite(true).reconcileIntervalMinutes(5).jobShardingStrategyClass("com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy").build();
    }

    /**
     * @Description dataFlow任務配置類,streamingProcess：true 代表开启流式作业，默认false，
     * 一般用这个模式都是要开启流式的，不然用simple就行
     */
    private LiteJobConfiguration getDataFlowLiteJobConfiguration(final Class<? extends DataflowJob> jobClass,
                                                               final String cron,
                                                               final int shardingTotalCount,
                                                               final String shardingItemParameters,
                                                               final String jobParameters,
                                                               final String description,
                                                               final String scriptCommandLine){
        return LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters).jobParameter(jobParameters).failover(true).misfire(true).build(),
                jobClass.getCanonicalName(), true)
        ).overwrite(true).reconcileIntervalMinutes(5).jobShardingStrategyClass("com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy").build();
    }

    /**
     * 抽象添加SimpleJob方法
     * @param simpleJob
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void addSimpleJob(final SimpleJob simpleJob,
                             final String cron,
                             final Integer shardingTotalCount,
                             final String shardingItemParameters,
                             final String jobParameters,
                             final String description,
                             final String scriptCommandLine,
                             final String jobType)
            throws IllegalAccessException, InstantiationException {

        //根据scriptCommandLine有没有参数值判断是那种类型的任务
        //这个对象不适合太频繁的定时，最好是用于一天一次这种的
        LiteJobConfiguration jobConfig = getSimpleLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount,
                    shardingItemParameters, jobParameters, description, scriptCommandLine);
        new SpringJobScheduler(simpleJob, regCenter, jobConfig, jobEventConfiguration, elasticJobListener).init();
    }

    /**
     * 抽象添加 DataflowJob 方法
     * @param DataflowJob
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void addDataflowJob(final DataflowJob dataflowJob,
                             final String cron,
                             final Integer shardingTotalCount,
                             final String shardingItemParameters,
                             final String jobParameters,
                             final String description,
                             final String scriptCommandLine,
                             final String jobType)
            throws IllegalAccessException, InstantiationException {

        //根据scriptCommandLine有没有参数值判断是那种类型的任务
        //这个对象不适合太频繁的定时，最好是用于一天一次这种的
        LiteJobConfiguration jobConfig = getDataFlowLiteJobConfiguration(dataflowJob.getClass(), cron, shardingTotalCount,
                    shardingItemParameters, jobParameters, description, scriptCommandLine);
        new SpringJobScheduler(dataflowJob, regCenter, jobConfig, jobEventConfiguration, elasticJobListener).init();
    }

    /**
     * 抽象添加 ScriptJob 方法
     * @param ScriptJob
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void addScriptJob(final ScriptJob scriptJob,
                               final String cron,
                               final Integer shardingTotalCount,
                               final String shardingItemParameters,
                               final String jobParameters,
                               final String description,
                               final String scriptCommandLine,
                               final String jobType)
            throws IllegalAccessException, InstantiationException {

        //根据scriptCommandLine有没有参数值判断是那种类型的任务
        //这个对象不适合太频繁的定时，最好是用于一天一次这种的
        LiteJobConfiguration jobConfig = getScriptLiteJobConfiguration(scriptJob.getClass(), cron, shardingTotalCount,
                shardingItemParameters, jobParameters, description, scriptCommandLine);
        new SpringJobScheduler(scriptJob, regCenter, jobConfig, jobEventConfiguration, elasticJobListener).init();
    }
}
