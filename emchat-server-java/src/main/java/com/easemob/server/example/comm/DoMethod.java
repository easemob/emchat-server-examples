package com.easemob.server.example.comm;

import com.google.gson.Gson;
import io.swagger.client.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by easemob on 2017/3/16.
 */
public class DoMethod {
    private Logger logger = LoggerFactory.getLogger(DoMethod.class);
    public Object sendHttpRequest(MyHttpRequest myHttpRequest, String authorization)  {
       Object result = null;
        try {
            result =  myHttpRequest.doHttpRequest(authorization);
        } catch (ApiException e) {
            if(e.getCode()==401){
                logger.info("The current token is invalid, re-generating token for you and calling it again");
                TokenUtil.initTokenByProp();
                authorization = TokenUtil.getAccessToken();
                try {
                    result = myHttpRequest.doHttpRequest(authorization);
                } catch (ApiException e1) {
                  logger.error(e1.getMessage());
                }
                return result;
            }
            if(e.getCode()==429){
                logger.warn("The interface call is too frequent");
            }
            if(e.getCode()>=500){
                logger.info("The server connection failed and is being reconnected");
                long time = 5;
                for(int i=0;i<5;i++) {
                    try {
                        TimeUnit.SECONDS.sleep(time);
                        logger.info("Reconnection is in progress..."+i);
                        result = myHttpRequest.doHttpRequest(authorization);
                        if(result!=null){
                           return result;
                        }
                    }catch(ApiException e1){
                        time*=1.5;
                    } catch (InterruptedException e1) {
                       logger.error(e1.getMessage());
                    }
                }
                System.out.println(e);
                logger.warn("The server may be faulty. Please try again later");
                logger.error(e.getMessage());
            }
            Gson gson = new Gson();
            Map<String,String> map = gson.fromJson(e.getResponseBody(), Map.class);
            logger.error(e.getCode()+":"+e.getMessage());
            logger.error(map.get("error"));
        }
        return result;
    }
}
