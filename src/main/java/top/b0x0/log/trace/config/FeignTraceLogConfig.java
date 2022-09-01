package top.b0x0.log.trace.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.b0x0.log.trace.interceptor.FeignMDCInterceptor;

/**
 * Feign MDC Config
 *
 * @author wuliling Created By 2022-09-01 22:33
 **/
@Configuration
public class FeignTraceLogConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignMDCInterceptor();
    }

}
