package net.zhuoyue.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpClientUtil {
	
	private static Logger LOG = LogManager.getLogger(HttpClientUtil.class);
	private HttpClient client;
	private HttpMethod method;

	public HttpClientUtil() {
		LOG.info("init http client");
		client = new HttpClient();
		client.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
		client.getParams().setParameter("http.socket.timeout", new Integer(5000));
		client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
	}
	
	public String getHtmlResponse(String host, String url, Map<String, String> params) {
		StringBuilder res = new StringBuilder();
		InputStream in = null;
		try {
			client.getHostConfiguration().setHost(host);
			method = getMethod(url, params);
			client.executeMethod(method);
			in = method.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				res.append(line);
			}
		} catch (Exception e) {
			LOG.error("get response error", e);
		} finally {
			if (null != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOG.error("close in error",e);
				}
			}
		}
		return res.toString();
	}

	public HttpMethod getMethod(String url, Map<String, String> params) {
		String param = "";
		for (String key : params.keySet()) {
			param += key + "=" + params.get(key) + "&";
		}
		param = param.substring(0, param.length() - 1);
		System.out.println(url + "?" + param);
		GetMethod get = new GetMethod(url + "?" + param);
		get.releaseConnection();
		return get;
	}

	public HttpMethod postMethod(String url, Map<String, String> params) {
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		post.setRequestHeader("Accept", "*/*");
		NameValuePair[] values = new NameValuePair[params.size()];
		int i = 0;
		for (String key : params.keySet()) {
			values[i] = new NameValuePair(key, params.get(key));
			i++;
		}
		post.setRequestBody(values);
		post.releaseConnection();
		return post;
	}

	public static void main(String[] args) {
//		String amazonurl = "https://www.amazon.com"
//				+ "/iPhone-Screen-Protector-amFilm-Tempered/dp/B014EB532U/ref=sr_1_2?"
//				+ "s=wireless&ie=UTF8&qid=1466326495&sr=1-2&keywords=iphone+6s+plus+tempered+glass";
		
		String host = "www.amazon.com";
		String url = "/iPhone-Screen-Protector-amFilm-Tempered/dp/B014EB532U/ref=sr_1_2";
		Map<String, String> params = new HashMap<String, String>();
		params.put("s", "wireless");
		params.put("ie", "UTF8");
		params.put("qid", "1466326495");
		params.put("sr", "1-2");
		params.put("keywords", "iphone+6s+plus+tempered+glass");
		
		HttpClientUtil client = new HttpClientUtil();
		System.out.println(client.getHtmlResponse(host, url, params));
	}

}
