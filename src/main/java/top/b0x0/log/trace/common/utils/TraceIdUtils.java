package top.b0x0.log.trace.common.utils;

import java.util.UUID;

/**
 * @author tanglinghan Created By 2022-08-29 10:30
 **/
public class TraceIdUtils {

    private TraceIdUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 获取traceId
     *
     * @return /
     */
    public static String getTraceId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }


}