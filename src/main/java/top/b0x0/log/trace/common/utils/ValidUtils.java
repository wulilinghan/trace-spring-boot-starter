package top.b0x0.log.trace.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tanglinghan Created By 2022-07-15 10:20
 **/
public class ValidUtils {

    public static void validMap(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("map is empty!");
        }
    }

    /**
     * map参数校验
     *
     * @param map           需要校验的map
     * @param needCheckKeys 需要校验不为空的key
     */
    public static void validMapValNotNull(Map<String, Object> map, String... needCheckKeys) {
        validMap(map);

        if (needCheckKeys == null) {
            return;
        }
        List<String> msgList = new ArrayList<>();
        for (String key : needCheckKeys) {
            Object val = map.get(key);
            if (val == null || StringUtils.isBlank(val.toString())) {
                msgList.add(key + " is empty");
            }
        }
        String joinMsg = String.join(",", msgList);
        if (joinMsg.length() > 0) {
            throw new IllegalArgumentException(joinMsg);
        }
    }

    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, String errorMsg) {
        if (obj == null) {
            throw new NullPointerException(String.valueOf(errorMsg));
        }
        return obj;
    }
}
