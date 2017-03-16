package io.swagger.client.util;

import com.sun.java.browser.plugin2.DOM;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by easemob on 2017/3/16.
 */
public class DoMethod {
    private Logger logger = LoggerFactory.getLogger(DoMethod.class);
    public ApiResponse<String> doAction(Action action,String authorization) throws ApiException {
        ApiResponse<String> resp = null;
        try {
            resp =  action.send(authorization);
        } catch (ApiException e) {
            if(e.getCode()==401){
                logger.info("The current token is invalid, re-generating token for you and calling it again");
                TokenUtil.initTokenByProp();
                authorization = TokenUtil.getAccess_token();
                resp = action.send(authorization);
                return resp;
            }
            if(e.getCode()==429){
                logger.warn("The interface call is too frequent");
                throw e;
            }
            if(e.getCode()>=500){
                logger.info("The server connection failed and is being reconnected");
                long time = 5;
                for(int i=0;i<5;i++) {
                    try {
                        TimeUnit.SECONDS.sleep(time);
                        logger.info("Reconnection is in progress..."+i);
                        resp = action.send(authorization);
                        if(resp.getStatusCode()==200){
                           return resp;
                        }
                    }catch(ApiException e1){
                        time*=1.5;
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                logger.warn("The server may be faulty. Please try again later");
                throw e;
            }
        }
        return resp;
    }
}
