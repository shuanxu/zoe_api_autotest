package com.xx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author xuxiong
 * @date 2020/6/10 - 10:49
 * JAVA自动化学习
 **/
public class HttpUtils {

    /**
     * 发送请求
     * @param type           接口类型
     * @param contentType    Content_type
     * @param url            接口地址
     * @param params         请求体
     */
    public static HttpResponse call(String type,String contentType,String url,String params) {

        try {
            //判断请求是否为post请求
            if ("post".equals(type)) {
                //判断Content_type是否为json
                if ("json".equals(contentType)) {
                    return executePost4Json(url, params);
                }
                //判断Content_type是否为form
                else if ("form".equals(contentType)) {
                    params = json2Form(params);
                    return executePost4Form(url, params);
                }else {
                    System.out.println("没有发送请求！");
                }
            }
            //判断请求是否为get请求
            else if ("get".equals(type)) {
                return executeGet(url);
            }
            //判断请求是否为patch请求
            else if ("patch".equals(type)) {
                return executePatch(url, params);
            }
            else {
                System.out.println("没有发送请求！");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json格式转成form格式
     * @param params  参数
     * @return
     */
    public static String json2Form(String params){
        String flag = "";
        //json数据转成map
        Map<String, String> map = JSONObject.parseObject(params, Map.class);
        Set<String> set = map.keySet();
        //拼接成form格式，key=value形式
        for (String s : set) {
            flag += s +"="+ map.get(s)+"&";
        }
        //去掉最后多余的&
        params = flag.substring(0, flag.length() - 1);
        return params;
    }


    /**发送get请求
     * @param url
     * url必须带参数
     * @return
     * @throws Exception
     */
    public static HttpResponse executeGet(String url) throws Exception {
        HttpGet get = new HttpGet(url);
        get.addHeader("X-Lemonban-Media-Type","lemonban.v1");
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        printResponse(response);
        return response;
    }

    /**
     *发送post请求(json格式)
     * @param url     接口请求地址
     * @param params    请求体
     * @return
     * @throws Exception
     */
    public static HttpResponse executePost4Json(String url,String params) throws Exception {
        HttpPost post = new HttpPost(url);
        post.addHeader("requestId", String.valueOf(System.currentTimeMillis()));
        post.addHeader("langType", "zh");
        post.addHeader("orgCode", "2");
        post.addHeader("isIntercept", "false");
        post.addHeader("operator", "2");
        post.addHeader("apiKey", "HIS5");
        post.addHeader("clientId", "1");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        post.addHeader("deptCode", "123000");
        StringEntity entity = new StringEntity(params,"UTF-8");
        post.setEntity(entity);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(post);
//        printResponse(response);
        return response;
    }
    /**
     *发送post请求(form格式)
     * @param url     接口请求地址
     * @param params    请求体
     * @return
     * @throws Exception
     */
    public static HttpResponse executePost4Form(String url,String params) throws Exception {
        HttpPost post = new HttpPost(url);
        post.addHeader("X-Lemonban-Media-Type","lemonban.v1");
        post.addHeader("Content-Type","application/x-www-form-urlencoded");
        StringEntity entity = new StringEntity(params,"UTF-8");
        post.setEntity(entity);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(post);
//        printResponse(response);
        return response;
    }

    /**
     *发送patch请求
     * @param url  接口请求地址
     * @param body 请求体
     * @return
     * @throws Exception
     */
    public static HttpResponse executePatch(String url,String body) throws Exception {
        HttpPatch patch = new HttpPatch(url);
        patch.addHeader("X-Lemonban-Media-Type","lemonban.v1");
        patch.addHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(body,"UTF-8");
        patch.setEntity(entity);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(patch);
        printResponse(response);
        return response;
    }

    //打印响应
    public static String printResponse(HttpResponse response) throws Exception {
        Header[] headers = response.getAllHeaders();
        System.out.print("响应头-->");
        System.out.println(Arrays.toString(headers));
        int code = response.getStatusLine().getStatusCode();
        System.out.println("状态码-->"+code);
        HttpEntity responseEntity = response.getEntity();
        String entityBody = EntityUtils.toString(responseEntity);
        System.out.println("响应体-->"+entityBody);
        return entityBody;
    }

    //获取用户id
    public static String getMemberId(String json) throws Exception {
        JSONObject ob = JSON.parseObject(json);
        String data = String.valueOf(ob.get("data"));
        Object obid = JSON.parseObject(data).get("id");
        String id = String.valueOf(obid);
        return id;
    }
}
