package top.b0x0.log.trace.async;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * spring异步线程获得主线程的上下文,获取主线程traceId
 *
 * @author tanglinghan Created By 2022-08-29 16:04
 */
public class TaskDecoratorMDC implements TaskDecorator {

    @Override
    public Runnable decorate(@Nullable Runnable runnable) {
        if (runnable == null) {
            return null;
        }
        Map<String, String> map = MDC.getCopyOfContextMap();
        return () -> {
            try {
                if (map != null) {
                    MDC.setContextMap(map);
                }
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}