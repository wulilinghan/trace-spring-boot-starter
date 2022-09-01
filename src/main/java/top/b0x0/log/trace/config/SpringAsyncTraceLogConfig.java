package top.b0x0.log.trace.config;

import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import top.b0x0.log.trace.async.TaskDecoratorMDC;

import java.util.concurrent.Executor;

/**
 * @author wuliling Created By 2022-09-01 22:38
 **/
public class SpringAsyncTraceLogConfig extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(50);
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setQueueCapacity(1000);
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        threadPoolTaskExecutor.setTaskDecorator(new TaskDecoratorMDC());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
