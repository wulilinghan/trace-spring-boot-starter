package top.b0x0.log.trace.aop;

import java.lang.annotation.*;


/**
 * @author tanglinghan Created By 2022-08-29 11:26
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TraceId {
}
