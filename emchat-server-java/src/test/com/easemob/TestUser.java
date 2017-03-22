package com.easemob;

import com.easemob.server.example.api.impl.EasemobIMUsers;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by easemob on 2017/3/21.
 */
public class TestUser {

    Logger logger = LoggerFactory.getLogger(TestUser.class);
    private EasemobIMUsers easemobIMUsers = new EasemobIMUsers();


    @Test
    public void createUser(){
        RegisterUsers users = new RegisterUsers();
        User user = new User().username("aaaa123456"+new Random().nextInt(500)).password("123456");
        User user1 = new User().username("aaa123456"+new Random().nextInt(500)).password("123456");
        users.add(user);
        users.add(user1);
        Object result = easemobIMUsers.createNewIMUserSingle(users);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void aaa(){

    }
}
