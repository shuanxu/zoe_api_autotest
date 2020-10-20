package com.xx.pojo;

/**
 * @author xuxiong
 * @date 2020/6/22 - 11:30
 * JAVA自动化学习
 **/
public class Write2ExcelData {
    //回写到Excel需要的sheetIndex
    private int startSheet;
    //回写到Excel需要的rowNum
    private int rownum;
    //回写到Excel需要的cellNum
    private int cellnum;
    //回写到Excel的内容
    private Object content;

    public Write2ExcelData() {
    }

    public Write2ExcelData(int startSheet, int rownum, int cellnum, Object content) {
        this.startSheet = startSheet;
        this.rownum = rownum;
        this.cellnum = cellnum;
        this.content = content;
    }

    public int getSheetIndex() {
        return startSheet;
    }

    public void setSheetIndex(int sheetIndex) {
        this.startSheet = sheetIndex;
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public int getCellnum() {
        return cellnum;
    }

    public void setCellnum(int cellnum) {
        this.cellnum = cellnum;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "回写数据内容{" +
                "sheetIndex=" + startSheet +
                ", rownum=" + rownum +
                ", cellnum=" + cellnum +
                ", content='" + content + '\'' +
                '}';
    }
}
