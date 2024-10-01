package com.anderfolg.businessrest.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class ClientService {
    @Value("${url.prefix}")
    private static String urlPrefix;

    public static String get( String endpoint ) {
        String requestUrl = urlPrefix + endpoint;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet getRequest = new HttpGet(requestUrl);
            log.debug("Processing GET request with URL: {}", requestUrl);

            try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
                if ( response.getCode() == 200 ) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    log.debug("GET response: {}", responseBody);
                    return responseBody;
                } else {
                    log.error("Failed GET response: {} with status code: {}", response.getEntity(), response.getCode());
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("Error during GET request: ", e);
            return null;
        }
    }

    public static String post( String endpoint, String jsonBody ) {
        String requestUrl = urlPrefix + endpoint;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost(requestUrl);
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setEntity(new StringEntity(jsonBody));
            log.debug("Processing POST request with URL: {}", requestUrl);

            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                if ( response.getCode() == 201 ) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    log.debug("POST response: {}", responseBody);
                    return responseBody;
                } else {
                    log.error("Failed POST response: {} with status code: {}", response.getEntity(), response.getCode());
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("Error during POST request: ", e);
            return null;
        }
    }

    public static String update( String endpoint, String jsonBody ) {
        String requestUrl = urlPrefix + endpoint;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut putRequest = new HttpPut(requestUrl);
            putRequest.setHeader("Content-Type", "application/json");
            putRequest.setEntity(new StringEntity(jsonBody));
            log.debug("Processing PUT request with URL: {}", requestUrl);

            try (CloseableHttpResponse response = httpClient.execute(putRequest)) {
                if ( response.getCode() == 200 ) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    log.debug("PUT response: {}", responseBody);
                    return responseBody;
                } else {
                    log.error("Failed PUT response: {} with status code: {}", response.getEntity(), response.getCode());
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("Error during PUT request: ", e);
            return null;
        }
    }

    public static boolean delete( String endpoint ) {
        String requestUrl = urlPrefix + endpoint;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpDelete deleteRequest = new HttpDelete(requestUrl);
            log.debug("Processing DELETE request with URL: {}", requestUrl);

            try (CloseableHttpResponse response = httpClient.execute(deleteRequest)) {
                if ( response.getCode() == 204 ) {
                    log.debug("DELETE request successful for URL: {}", requestUrl);
                    return true;
                } else {
                    log.error("Failed DELETE response with status code: {}", response.getCode());
                    return false;
                }
            }
        } catch (Exception e) {
            log.error("Error during DELETE request: ", e);
            return false;
        }
    }
}