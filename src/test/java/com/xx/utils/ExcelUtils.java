package com.xx.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.xx.constants.Constants;
import com.xx.pojo.CaseInfo;
import com.xx.pojo.Write2ExcelData;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuxiong
 * @date 2020/6/15 - 11:05
 * JAVA自动化学习
 **/
public class ExcelUtils {

    /**
     * 读取excel测试用例文件
     * @param startSheet   开始sheet索引
     * @param sheetNum     读取sheet个数
     * @param clazz        每行对象的字节码
     * @return
     * @throws Exception
     */
    public static List readExcel(int startSheet, int sheetNum, Class clazz) {
        try {
            //获取文件输入流
            InputStream is = ExcelUtils.class.getClassLoader().getResourceAsStream("./api_cases.xls");
            ImportParams params = new ImportParams();
            //设置开始读取sheet的位置
            params.setStartSheetIndex(startSheet);
            //设置读取sheet的个数
            params.setSheetNum(sheetNum);
            List caseInfoList = ExcelImportUtil.importExcel(is, clazz, params);
            is.close();
            return caseInfoList;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将集合转化成数组
     * @param startSheet   开始sheet索引
     * @param sheetNum     读取sheet个数
     * @param clazz        每行对象的字节码
     * @return
     * @throws Exception
     */
    public static Object[] getArrayData(int startSheet, int sheetNum, Class clazz) {
        try {
            List<CaseInfo> list = readExcel(startSheet, sheetNum, clazz);
            //将数组转化成一位数字，用于testng数据驱动
            Object[] datas = list.toArray();
            return datas;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    //回写数据的list
    public static List<Write2ExcelData> write2ExcelDataList = new ArrayList<>();
    /**
     * 回写数据到excel中
     */
    public static void write2Excel() {
        //如果回写数据的集合不为空，则写入excel中
        if (!write2ExcelDataList.isEmpty()){
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(Constants.EXCEL_PATH);
                Workbook excel = WorkbookFactory.create(fis);
                for (Write2ExcelData data : write2ExcelDataList) {
                    Sheet sheet = excel.getSheetAt(data.getSheetIndex());
                    Row row = sheet.getRow(data.getRownum());
                    Cell cell = row.getCell(data.getCellnum(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellValue(String.valueOf(data.getContent()));
                    //toString()有局限，当对象是int类型是就没办法转成字符串
//                    cell.setCellValue(data.getContent().toString());
                }
                fos = new FileOutputStream(Constants.EXCEL_PATH);
                excel.write(fos);
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                //finally一定执行，以下是关流的基本语法
                try {
                    fis.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
