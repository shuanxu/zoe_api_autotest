package com.xx.pojo;

import com.xx.constants.Constants;
import com.xx.utils.ExcelUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**数据处理类
 * @author xuxiong
 * @date 2020/6/22 - 15:21
 * JAVA自动化学习
 **/
public class DataProcessing {
    //创建日志对象
    private static Logger logger = Logger.getLogger(DataProcessing.class);
    //参数化数据
    public static Map<String,Object> Params = new HashMap<>();
    //初始化数据
    @BeforeSuite
    public void init() {
        logger.info("--------初始化数据---------");
        //读取properties文件中的参数
        FileInputStream fis = null;
        try {
            Properties property = new Properties();
            fis = new FileInputStream(Constants.PROPERTIES_PATH);
            property.load(fis);
            //property也是key=value形式，直接转成MAP放入varsParams
            Params.putAll((Map)property);
            logger.info("参数化数据--------"+Params);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关流
            try {
                fis.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //测试结束后将相应数据一次性批量回写到excel文件中
    @AfterSuite
    public void finish2Write() {
        ExcelUtils.write2Excel();
        logger.info("--------测试结果回写到excel完成---------");
    }
}
