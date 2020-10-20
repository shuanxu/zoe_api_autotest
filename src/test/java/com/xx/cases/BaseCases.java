package com.xx.cases;


import com.xx.pojo.CaseInfo;
import com.xx.pojo.DataProcessing;
import com.xx.pojo.Write2ExcelData;
import com.xx.utils.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.util.Set;

/**所有Test的父类
 * @author xuxiong
 * @date 2020/6/22 - 10:01
 * JAVA自动化学习
 **/
public class BaseCases {

    //定义成员变量来接收从testng.xml读取的参数
    int startSheet;
    int sheetNum;
    Class clazz;
    private static Logger logger = Logger.getLogger(BaseCases.class);

    @DataProvider
    public Object[] getData(){
        Object[] data = ExcelUtils.getArrayData(startSheet, sheetNum, clazz);
        return data;
    }

    //加载类之前获取testng.xml文件中的参数
    @BeforeClass
    @Parameters({"sheetIndex","sheetNum","classFullName"})
    public void getXmlParams(int startSheet, int sheetNum, String classFullName) throws Exception {
        this.startSheet = startSheet;
        this.sheetNum = sheetNum;
        this.clazz = Class.forName(classFullName);//通过全类名获取对象字节码
    }

    /**
     * 获取回写数据list
     * @param rownum      回写行号index
     * @param cellnum     回写单元格index
     * @param content     回写数据（响应体）
     */
    public void getWriteDataList(int rownum, int cellnum, Object content) {
        Write2ExcelData data = new Write2ExcelData(startSheet, rownum, cellnum, content);
        ExcelUtils.write2ExcelDataList.add(data);
    }

    public static void paramsReplace(CaseInfo caseInfo) {
        //循环varsParams替换占位符
        Set<String> keySet = DataProcessing.Params.keySet();
        //key是占位符，value是实际值
        for (String key : keySet) {
            String value = String.valueOf(DataProcessing.Params.get(key));
            //替换参数中的占位符
            if (StringUtils.isNotBlank(caseInfo.getExcept())) {
                String replace = caseInfo.getExcept().replace(key, value);
                caseInfo.setExcept(replace);
            }
            if (StringUtils.isNotBlank(caseInfo.getParams())) {
                String replace = caseInfo.getParams().replace(key, value);
                caseInfo.setParams(replace);
            }
            if (StringUtils.isNotBlank(caseInfo.getUrl())) {
                String replace = caseInfo.getUrl().replace(key, value);
                caseInfo.setUrl(replace);
            }
        }
    }
}
