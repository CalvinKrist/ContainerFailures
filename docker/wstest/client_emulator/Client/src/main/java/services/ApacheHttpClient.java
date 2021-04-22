package services;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ApacheHttpClient {
	private String host;
	private String port;
	private int computeSeconds;
	private HttpClient httpClient;

	private CSV csv;

	public ApacheHttpClient(String host, String port, String fileName, int computeSeconds){
		this.host = host;
		this.port = port;
		this.computeSeconds = computeSeconds;
		csv = new CSV(fileName);
		httpClient = HttpClientBuilder.create().build();
	}

	public void sendPostRequest(String type, String json){
		HttpPost postRequest = new HttpPost("http://" + host + ":" + port + "/server-example/rest/wstest/" + type);
		HttpResponse response;
		boolean serverFail = false;
		long firstResponseError = 0;
		int statusCode;
		try {
			StringEntity input = new StringEntity(json);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			long startExecute = System.nanoTime();
			while (true){
				try {
					Thread.sleep(computeSeconds * 1000); // Add compute delay on each attempt
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

	public void sendGetRequest(String type, int computeSeconds){
		boolean serverFail = false;
		long firstResponseError = 0;
		int statusCode;
		HttpGet getRequest = new HttpGet("http://" + host + ":" + port + "/server-example/rest/wstest/" + type);
		getRequest.addHeader("accept", "application/json");
		HttpResponse response;
		long startExecute = System.nanoTime();
		while (true){
            try {
                Thread.sleep(computeSeconds * 1000); // Add compute delay on each attempt
				response = httpClient.execute(getRequest);
                statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == 200 || statusCode == 204)
                    break;
                else{
                    firstResponseError = System.nanoTime();
					EntityUtils.consume(response.getEntity());
                }
            }catch (IOException e) {
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