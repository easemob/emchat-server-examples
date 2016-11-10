package com.easemob.server.example.comm.utils;

import com.easemob.server.example.comm.MyX509TrustManager;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.ClientBuilder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestAPIUtils {
    /**
     * Obtain a JerseyClient whit SSL
     *
     * @return Jersey Client
     */
    public static JerseyClient getJerseyClient(boolean isSSL, String CacertFilePath, String CacertFilePassword) {
        ClientBuilder clientBuilder = JerseyClientBuilder.newBuilder().register(MultiPartFeature.class);

        // Create a secure JerseyClient
        if (isSSL) {
            try {
                TrustManager[] tm = new TrustManager[]{new MyX509TrustManager(CacertFilePath, CacertFilePassword)};

                SSLContext sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, tm, new SecureRandom());

                clientBuilder.sslContext(sslContext).hostnameVerifier(SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return (JerseyClient) clientBuilder.build().register(JacksonJsonProvider.class);
    }

    /**
     * Create a httpClient instance
     *
     * @param isSSL if the request is protected by ssl
     * @return HttpClient instance
     */
    public static HttpClient getHttpClient(boolean isSSL, String CacertFilePath, String CacertFilePassword) {
        CloseableHttpClient client = null;

        if (isSSL) {
            try {
               /* X509HostnameVerifier verifier = new X509HostnameVerifier() {
                    public void verify(String host, SSLSocket ssl) throws IOException {
                    }

                    public void verify(String host, X509Certificate cert) throws SSLException {
                    }

                    public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                    }

                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                };*/

                TrustManager[] tm = new TrustManager[]{new MyX509TrustManager(CacertFilePath, CacertFilePassword)};

                SSLContext sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, tm, new SecureRandom());

                client = HttpClients.custom().setSslcontext(sslContext).setHostnameVerifier(SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER).build();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            client = HttpClients.createDefault();
        }

        return client;
    }

    /**
     * Check illegal String
     *
     * @param regex reg expression
     * @param str   string to be validated
     * @return if matched
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.lookingAt();
    }


}
