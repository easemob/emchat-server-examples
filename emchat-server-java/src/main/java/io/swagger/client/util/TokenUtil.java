package io.swagger.client.util;

import com.google.gson.Gson;
import io.swagger.client.ApiException;
import io.swagger.client.api.AuthenticationApi;
import io.swagger.client.model.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by easemob on 2017/3/14.
 */
public  class TokenUtil {

    private static String org_name;
    private static String app_name;
    private static String grant_type;
    private static String client_id;
    private static String client_secret;
    private static Token body;
    private static AuthenticationApi api = new AuthenticationApi();
    private static String access_token;
    private static Double expiredAt = -1D;

    /**
     * 初始化Token放到内存
     * @throws ApiException
     */
    public static void initTokenByProp() throws ApiException {
        InputStream inputStream = TokenUtil.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        org_name = prop.getProperty("ORG_NAME");
        app_name = prop.getProperty("APP_NAME");
        grant_type = prop.getProperty("GRANT_TYPE");
        client_id = prop.getProperty("CLIENT_ID");
        client_secret = prop.getProperty("CLIENT_SECRET");
        body = new Token().clientId(client_id).grantType(grant_type).clientSecret(client_secret);
        String resp = api.orgNameAppNameTokenPost(org_name, app_name,body);
        Gson gson = new Gson();
        Map map = gson.fromJson(resp, Map.class);
        access_token = " Bearer "+map.get("access_token");
        expiredAt = System.currentTimeMillis() + (Double)map.get("expires_in");
    }

    /**
     * 得到内存中封装好的Token
     * @return
     * @throws ApiException
     */
    public static String getAccess_token() throws ApiException {
        if(access_token==null || isExpired()){
                initTokenByProp();
        }
        return access_token;
    }
    private static Boolean isExpired() {
        return System.currentTimeMillis() > expiredAt;
    }



}

