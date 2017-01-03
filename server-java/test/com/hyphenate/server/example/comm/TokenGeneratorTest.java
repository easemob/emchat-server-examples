package com.hyphenate.server.example.comm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * TokenGenerator Tester.
 *
 * @author <Hyphenate>
 * @version 1.0
 * @since <pre>2016.11</pre>
 */
public class TokenGeneratorTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testRequest_1() throws Exception {
        ClientContext context = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES);
        TokenGenerator tokenGenerator = new TokenGenerator(context);
        String token = tokenGenerator.request(false);
        assertNotNull(token);
    }

    @Test
    public void testRequest_2() throws Exception {
        ClientContext context = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES);
        TokenGenerator tokenGenerator = new TokenGenerator(context);
        String token = tokenGenerator.request(true);
        assertNotNull(token);
    }
}
