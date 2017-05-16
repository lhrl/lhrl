                 package com.lhrl.common.util.http;

                 import org.apache.http.Consts;
                 import org.apache.http.HttpHost;
                 import org.apache.http.NameValuePair;
                 import org.apache.http.client.config.RequestConfig;
                 import org.apache.http.client.entity.UrlEncodedFormEntity;
                 import org.apache.http.client.methods.CloseableHttpResponse;
                 import org.apache.http.client.methods.HttpPost;
                 import org.apache.http.impl.client.CloseableHttpClient;
                 import org.apache.http.message.BasicNameValuePair;

                 import java.io.IOException;
                 import java.util.ArrayList;
                 import java.util.List;
                 import java.util.Map;

                 /**
                  * form表单post提交
                  *
                  * @author Wang Peng
                  *
                  */
                 public class FormPostRequestExecutor implements RequestExecutor<String, Map<String, String>> {

                     @Override
                     public String execute(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, Map<String, String> data) throws IOException {

                         HttpPost httpPost = new HttpPost(uri);
                         if (httpProxy != null) {
                             RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
                             httpPost.setConfig(config);
                         }
                         List<NameValuePair> nvps = new ArrayList<>();
                         for (String key : data.keySet()) {
                             nvps.add(new BasicNameValuePair(key, data.get(key)));
                         }
                         httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
                         CloseableHttpResponse response = httpclient.execute(httpPost);
                         String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
                         return responseContent;

                     }

                 }
