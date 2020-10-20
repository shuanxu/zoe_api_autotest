package com.xx.constants;

import java.util.HashMap;
import java.util.Map;


/**
 * @author xuxiong
 * @date 2020/6/17 - 10:14
 * JAVA自动化学习
 **/
public class Constants {
    //获取测试用例文件存放路径
    public static String EXCEL_PATH = System.getProperty("user.dir")+"/src/test/resources/api_cases.xls";
    //获取properties文件存放路径
    public static String PROPERTIES_PATH = Constants.class.getClassLoader().getResource("./params.properties").getPath();
    //响应回写单元格的index
    public static int RESPONSE_WRITE_BACK_CELL_INDEX = 8;
    //响应断言回写单元格的index
    public static int RESPONSE_ASSERT_WRITE_BACK_CELL_INDEX = 9;
}
