package com.easemob;

import com.easemob.server.example.api.impl.EasemobFile;
import com.easemob.server.example.comm.TokenUtil;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;

/**
 * Created by easemob on 2017/3/22.
 */
public class FileTest {
    private EasemobFile easemobFile = new EasemobFile();
    @Test
    public void uploadFile(){
        String path = TokenUtil.class.getClassLoader().getResource("pic/chick.jpg").getPath();
        File file = new File(path);
        Object result = easemobFile.uploadFile(file);
        System.out.println(result);
        Assert.assertNotNull(result);

    }
}
