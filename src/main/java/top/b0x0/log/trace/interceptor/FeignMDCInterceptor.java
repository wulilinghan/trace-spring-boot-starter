package top.b0x0.log.trace.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import top.b0x0.log.trace.common.constants.TraceConstant;

/**
 * Feign Interceptor
 *
 * @author wuliling Created By 2022-09-01 22:31
 **/
public class FeignMDCInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(TraceConstant.TRACE_ID, MDC.get(TraceConstant.TRACE_ID));
    }
}
