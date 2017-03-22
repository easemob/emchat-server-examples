package com.easemob.server.example.comm;

import com.google.gson.Gson;
import io.swagger.client.ApiException;
import io.swagger.client.api.AuthenticationApi;
import io.swagger.client.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by easemob on 2017/3/14.
 */
public  class TokenUtil {

    public static String ORG_NAME;
    public static String APP_NAME;
    public static String GRANT_TYPE;
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;
    private static Token BODY;
    private static AuthenticationApi API = new AuthenticationApi();
    private static String ACESS_TOKEN;
    private static Double EXPIREDAT = -1D;
    private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);
    /**
     * 初始化Token放到内存
     * @throws ApiException
     */
    public static void initTokenByProp() {
        InputStream inputStream = TokenUtil.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ORG_NAME = prop.getProperty("ORG_NAME");
        APP_NAME = prop.getProperty("APP_NAME");
        GRANT_TYPE = prop.getProperty("GRANT_TYPE");
        CLIENT_ID = prop.getProperty("CLIENT_ID");
        CLIENT_SECRET = prop.getProperty("CLIENT_SECRET");
        BODY = new Token().clientId(CLIENT_ID).grantType(GRANT_TYPE).clientSecret(CLIENT_SECRET);
        String resp = null;
        try {
            resp = API.orgNameAppNameTokenPost(ORG_NAME, APP_NAME,BODY);
        } catch (ApiException e) {
            logger.error(e.getMessage());
        }
        Gson gson = new Gson();
        Map map = gson.fromJson(resp, Map.class);
        ACESS_TOKEN = " Bearer "+map.get("access_token");
        EXPIREDAT = System.currentTimeMillis() + (Double)map.get("expires_in");
    }

    /**
     * 得到内存中封装好的Token
     * @return
     * @throws ApiException
     */
    public static String getAccessToken(){
        if(ACESS_TOKEN==null || isExpired()){
                initTokenByProp();
        }
        return ACESS_TOKEN;
    }
    private static Boolean isExpired() {
        return System.currentTimeMillis() > EXPIREDAT;
    }



}

