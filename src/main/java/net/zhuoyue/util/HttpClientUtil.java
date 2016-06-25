package net.zhuoyue.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpClientUtil {
	
	private static Logger LOG = LogManager.getLogger(HttpClientUtil.class);
	
	private static int TIMEOUT = 10000;
	
	public static String getHtmlResponse(String baseUrl, String suffixUrl, Map<String, String> params) {
		StringBuilder res = new StringBuilder();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream in = null;
		try {
		    HttpGet httpGet = getHttpGet(baseUrl, suffixUrl, params);
		    response = httpclient.execute(httpGet);
		    //LOG.info("response status:" + response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    if (null != entity) {
		        in = entity.getContent();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                res.append(line);
	            }
		    }
		} catch (Exception e) {
			LOG.error("get response error", e);
		} finally {
		    close(in, response, httpclient);
		}
		return res.toString();
	}
	
	public static String getHtmlResponse(String url) {
	    StringBuilder res = new StringBuilder();
	    HttpURLConnection conn = null;
        InputStream in = null;
        try {
            URL obj = new URL(url);
            conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) ...");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            conn.setConnectTimeout(TIMEOUT);
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            int code = conn.getResponseCode();
            //LOG.info("code=" + code);
            if (200 == code) {
                in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    res.append(line);
                }
            }
        } catch (Exception e) {
            LOG.error("get response error", e);
        } finally {
            close(in, conn);
        }
        return res.toString();
	}
	
	public static HttpGet getHttpGet(String baseUrl, String suffixUrl, Map<String, String> params) {
		String param = "";
		for (String key : params.keySet()) {
			param += key + "=" + params.get(key) + "&";
		}
		param = param.substring(0, param.length() - 1);
		String url = baseUrl + suffixUrl + "?" + param;
        //LOG.info("url:" + url);
		RequestConfig defaultRequestConfig = RequestConfig.custom()
	            .setCookieSpec(CookieSpecs.DEFAULT)
	            .setExpectContinueEnabled(true)
	            .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
	            .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
	            .build();
		RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
                .setSocketTimeout(TIMEOUT)
                .setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(TIMEOUT)
                .build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		
		return httpGet;
	}
	
	public static HttpGet getHttpGet(String url) {
	    //LOG.info("url:" + url);
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .build();
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
                .setSocketTimeout(TIMEOUT)
                .setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(TIMEOUT)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        
        return httpGet;
    }

	public static void close(InputStream in, CloseableHttpResponse response, CloseableHttpClient httpclient) {
	    if (null != in) {
            try {
                in.close();
            } catch (IOException e) {
                LOG.error("close in error",e);
            }
        }
        if (null != response) {
            try {
                response.close();
            } catch (IOException e) {
                LOG.error("close response error",e);
            }
        }
        if (null != httpclient) {
            try {
                httpclient.close();
            } catch (IOException e) {
                LOG.error("close httpclient error",e);
            }
        }    
	}
	
	public static void close(InputStream in, HttpURLConnection conn) {
        if (null != in) {
            try {
                in.close();
            } catch (IOException e) {
                LOG.error("close in error",e);
            }
        }
        if (null != conn) {
            try {
                conn.disconnect();
            } catch (Exception e) {
                LOG.error("close response error",e);
            }
        }  
    }
	
	public static void main(String[] args) {
//		String amazonurl = "https://www.amazon.com"
//				+ "/iPhone-Screen-Protector-amFilm-Tempered/dp/B014EB532U/ref=sr_1_2?"
//				+ "s=wireless&ie=UTF8&qid=1466326495&sr=1-2&keywords=iphone+6s+plus+tempered+glass";
		
		String host = "https://www.amazon.com";
		String url = "/iPhone-Screen-Protector-amFilm-Tempered/dp/B014EB532U/ref=sr_1_2";
		Map<String, String> params = new HashMap<String, String>();
		params.put("s", "wireless");
		params.put("ie", "UTF8");
		params.put("qid", "1466326495");
		params.put("sr", "1-2");
		params.put("keywords", "iphone+6s+plus+tempered+glass");
		
//		HttpClientUtil.getHtmlResponse(host, url, params);
		
		url = "https://www.amazon.com/gp/pdp/profile/AWJKQAC0XO5NI/ref=cm_cr_arp_d_pdp?ie=UTF8";

		System.out.println(HttpClientUtil.getHtmlResponse(url));
	}

}
