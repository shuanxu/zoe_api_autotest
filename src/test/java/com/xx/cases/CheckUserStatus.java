package com.xx.cases;

import com.xx.constants.Constants;
import com.xx.pojo.CaseInfo;
import com.xx.utils.AssertUtils;
import com.xx.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CheckUserStatus extends BaseCases {

    int k = 1;//记录test方法执行的次数

    @Test(dataProvider = "getData",description = "CheckUserStatus接口")
    public void test(CaseInfo caseInfo) throws Exception {
         //参数化替换
        paramsReplace(caseInfo);
       HttpResponse response = HttpUtils.call(
                caseInfo.getType(),
                caseInfo.getContentType(),
                caseInfo.getUrl(),
                caseInfo.getParams()
        ) ;
        //获取响应体，打印响应体并返回响应结果
        String entityBody = HttpUtils.printResponse(response);
        //响应结果回写到excel中
        getWriteDataList(caseInfo.getId(), Constants.RESPONSE_WRITE_BACK_CELL_INDEX, entityBody);
        //获取响应断言
        String assertStr = AssertUtils.CheckUserStatusAssert(caseInfo.getExcept(), entityBody);
        //断言结果回写到excel中
        getWriteDataList(caseInfo.getId(), Constants.RESPONSE_ASSERT_WRITE_BACK_CELL_INDEX, assertStr);
        //报表断言
        Assert.assertEquals(assertStr, "用例通过");
    }

    @BeforeMethod
    public void whatTest(){
        System.out.println("---------这是第"+k+"次测试-----------");
        k++;
    }
}
