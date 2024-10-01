package com.anderfolg.regpayment.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class BusinessClientService {
    @Value("${url.prefix}")
    private static String urlPrefix;
    // GET method
    public static String get(String endpoint) {
        String requestUrl = urlPrefix + endpoint;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet getRequest = new HttpGet(requestUrl);
            log.debug("Processing GET request with URL: {}", requestUrl);

            try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
                if (response.getCode() == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    log.debug("GET response: {}", responseBody);
                    return responseBody;
                } else {
                    log.error("Failed GET response: {} with status code: {}",
                            response.getEntity(), response.getCode());
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("Error during GET request: ", e);
            return null;
        }
    }

    // POST method
    public static String post(String endpoint, String jsonBody) {
        String requestUrl = urlPrefix + endpoint;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost(requestUrl);
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setEntity(new StringEntity(jsonBody));
            log.debug("Processing POST request with URL: {}", requestUrl);

            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                if (response.getCode() == 201) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    log.debug("POST response: {}", responseBody);
                    return responseBody;
                } else {
                    log.error("Failed POST response: {} with status code: {}",
                            response.getEntity(), response.getCode());
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("Error during POST request: ", e);
            return null;
        }
    }}
