package top.b0x0.log.trace.aop;

import top.b0x0.log.trace.common.constants.TraceConstant;
import top.b0x0.log.trace.common.utils.TraceIdUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * 全链路日志追踪id
 * 给 @Scheduled，@PostConstruct，@TraceId 创建traceId切面
 *
 * @author tanglinghan Created By 2022-08-29 10:30
 **/
@Component
@Aspect
public class TraceLogAspect {
    private static final Logger log = LoggerFactory.getLogger(TraceLogAspect.class);

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled) ||" +
            " @annotation(javax.annotation.PostConstruct) ||" +
            " @annotation(top.b0x0.log.trace.aop.TraceId)")
    public void traceLogPointCut() {

    }

    /**
     * 方法调用之前调用
     *
     * @param joinPoint /
     */
    @Before(value = "traceLogPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        String traceId = TraceIdUtils.getTraceId();
        MDC.put(TraceConstant.TRACE_ID, traceId);
        log.warn(TraceConstant.TRACE_ID + ":{}", traceId);
    }

    /**
     * 方法执行后清除MDC
     *
     * @param joinPoint   /
     * @param returnValue returning = "returnValue"这个的变量returnValue要和方法入参Object returnValue的returnValue保持一致
     */
    @AfterReturning(pointcut = "traceLogPointCut()", returning = "returnValue")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue) {
        MDC.clear();
    }
}
