package services;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ApacheHttpClient {
	private String host;
	private String port;
	private HttpClient httpClient;

	private CSV csv;

	public ApacheHttpClient(String host, String port, String fileName){
		this.host = host;
		this.port = port;
		csv = new CSV(fileName);
		
		// see https://www.baeldung.com/httpclient-timeout
		int timeout = 60; // seconds
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(timeout * 1000)
				.setConnectionRequestTimeout(timeout * 1000)
				.setSocketTimeout(timeout * 1000).build();
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}

	public void sendPostRequest(String type, String json){
		HttpPost postRequest = new HttpPost("http://" + host + ":" + port + "/server-compute/rest/wstest/" + type);
		HttpResponse response;
		boolean serverFail = false;
		long firstResponseError = 0;
		int statusCode;
		
		//System.out.println("Sending request to http://" + host + ":" + port + "/server-compute/rest/wstest/" + type);
		
		try {
			StringEntity input = new StringEntity(json);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			long startExecute = System.nanoTime();
			while (true){
				try {
					response = httpClient.execute(postRequest);
					statusCode = response.getStatusLine().getStatusCode();
					if(statusCode == 200 || statusCode == 204){
						break;
					}
					else{
						firstResponseError = System.nanoTime();
						EntityUtils.consume(response.getEntity());
					}
				} catch (IOException e) {
					//e.printStackTrace();
					serverFail = true;
				}
			}
			long endExecute = System.nanoTime();

			EntityUtils.consume(response.getEntity());

			//System.out.println("Time execution = " + (endExecute-startExecute) + " ns");
			csv.writeFile(type,serverFail,startExecute,endExecute,firstResponseError);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendGetRequest(String type){
		boolean serverFail = false;
		long firstResponseError = 0;
		int statusCode;
		HttpGet getRequest = new HttpGet("http://" + host + ":" + port + "/server-compute/rest/wstest/" + type);
		getRequest.addHeader("accept", "application/json");
		HttpResponse response;
		long startExecute = System.nanoTime();
		while (true){
            try {
                response = httpClient.execute(getRequest);
                statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == 200 || statusCode == 204)
                    break;
                else{
                    firstResponseError = System.nanoTime();
					EntityUtils.consume(response.getEntity());
                }
            }catch (IOException e) {
                //e.printStackTrace();
            	serverFail = true;
            }
        }
		long endExecute = System.nanoTime();

		try {
			EntityUtils.consume(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("Time execution = " + (endExecute-startExecute) + " ns");
		csv.writeFile(type,serverFail,startExecute,endExecute,firstResponseError);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public CSV getCsv() {
		return csv;
	}

	public void setCsv(CSV csv) {
		this.csv = csv;
	}
}