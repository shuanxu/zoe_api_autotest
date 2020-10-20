package com.xx.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Set;

/**
 * @author xumaomao
 * @date 2020/7/7
 * JAVA自动化学习
 **/
public class AssertUtils {

    private static Logger logger = Logger.getLogger(AssertUtils.class);

    /**
     * 响应断言结果
     * @param except         期望结果
     * @param entityBody     相应体
     */
    public static String CheckUserStatusAssert(String except, String entityBody) {
        String assertStr = "用例失败";
        boolean assertFlag = false;
        //判断响应体是否不为空
        if (StringUtils.isNotBlank(entityBody)&&StringUtils.isNotBlank(except)) {
            Map<String,Object> exceptMap = JSONObject.parseObject(except, Map.class);
            Set<String> keySet = exceptMap.keySet();
            for (String path : keySet) {
                String exceptValue = String.valueOf(exceptMap.get(path));
                String actualValue = String.valueOf(JSONPath.read(entityBody, path));
                if (exceptValue.equals(actualValue)) {
                    assertFlag = true;
                    assertStr = "用例通过";
                }
            }
        }
        logger.info("响应断言结果为："+assertFlag);

        return assertStr;
    }
}
