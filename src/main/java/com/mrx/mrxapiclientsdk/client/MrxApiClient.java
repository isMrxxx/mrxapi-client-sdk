package com.mrx.mrxapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.mrx.mrxapiclientsdk.model.AvatarParams;
import com.mrx.mrxapiclientsdk.model.BaiduParams;
import com.mrx.mrxapiclientsdk.model.Gpt;
import com.mrx.mrxapiclientsdk.model.User;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.mrx.mrxapiclientsdk.utils.SignUtils.getSign;

/**
 * @Auther: Mrx
 * @DateTime: 2023/6/5 10:43
 * @Description:
 */
public class MrxApiClient {
    private   String GATEWAY_HOST = "http://101.42.22.170:8090";

    private String accessKey;

    private String secretKey;

    public void setGATEWAY_HOST(String gateway_Host){
        this.GATEWAY_HOST=gateway_Host;
    }
    public MrxApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getBaiduInfo(BaiduParams baiduParams){
        String parameters = JSON.toJSONString(baiduParams);
        String result = onlineInvoke(parameters, "/api/baidu/baiduInfo");
        return result;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getAvatarUrl(AvatarParams avatarParams){
        String parameters = JSON.toJSONString(avatarParams);
        String result = onlineInvoke(parameters, "/api/avatar/avatarUrl");
        return result;
    }


    private Map<String, String> getHeaderMap(String body) {
        String encode=null;
        try {
            encode = URLEncoder.encode(body, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> hasMap = new HashMap<>();
        String nonce = RandomUtil.randomNumbers(4);
        String currentTime = String.valueOf(System.currentTimeMillis() / 1000);
        hasMap.put("accessKey", accessKey);
        hasMap.put("nonce", nonce);
        hasMap.put("body", encode);
        hasMap.put("timestamp", currentTime);
        hasMap.put("sign", getSign(encode, secretKey));
        return hasMap;
    }

    public String getUsernameByPost(User user) {
        String parameters = JSON.toJSONString(user);
        String result = onlineInvoke(parameters,"/api/name/user");
        return result;
    }

    public String getGptByGet(Gpt gpt) {
        String parameters = JSON.toJSONString(gpt);
        String result = onlineInvoke(parameters,"/api/name/gpt");
        return result;
    }

    public String onlineInvoke(String parameters,String url) {
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + url)
                .addHeaders(getHeaderMap(parameters))
                .body(parameters)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        return result;
    }



}
