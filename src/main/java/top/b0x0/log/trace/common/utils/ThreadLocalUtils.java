package top.b0x0.log.trace.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * ThreadLocal Util
 * 推荐本次请求的值在响应时调用 clearAll()方法，不要留到下次请求去覆盖，很容易出错，也容易造成内存泄漏
 *
 * @author ManJiis Created By 2021-12-16 9:58
 * @since 1.8
 **/
public class ThreadLocalUtils {
    private ThreadLocalUtils() {
    }

    /**
     * 初始化
     */
    private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = ThreadLocal.withInitial(() -> new HashMap<>(8));

    public static Map<String, Object> getThreadContext() {
        return THREAD_CONTEXT.get();
    }

    public static <T> T get(String key) {
        return get(key, null);
    }

    /**
     * 获取指定key的值为null时返回入参的默认值
     *
     * @param key          /
     * @param defaultValue /
     * @param <T>          /
     * @return /
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, T defaultValue) {
        Map<String, Object> map = THREAD_CONTEXT.get();
        return (T) Optional.ofNullable(map.get(key)).orElse(defaultValue);
    }

    public static void put(String key, Object value) {
        Map<String, Object> map = THREAD_CONTEXT.get();
        map.put(key, value);
    }

    public static void put(Map<String, Object> keyValueMap) {
        Map<String, Object> map = THREAD_CONTEXT.get();
        map.putAll(keyValueMap);
    }

    @SuppressWarnings("unchecked")
    public static <T> T remove(String key) {
        Map<String, Object> map = THREAD_CONTEXT.get();
        return (T) map.remove(key);
    }

    /**
     * 清除当前线程的 THREAD_CONTEXT 里的 Map<String, Object>
     */
    public static void clear() {
        THREAD_CONTEXT.get().clear();
    }

    /**
     * 清除当前线程的THREAD_CONTEXT
     */
    public static void clearAll() {
        THREAD_CONTEXT.remove();
    }


}
