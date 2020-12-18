package subsystem.payment;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class InterbankBoundary {
	
	protected static String query(String url, String requestBody) throws IOException {
		HttpPatch httpPatch = new HttpPatch(url);
		httpPatch.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpResponse httpResponse = httpClient.execute(httpPatch);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			// throw exception here
		}

		// extract return code
		HttpEntity httpEntity = httpResponse.getEntity();
		return EntityUtils.toString(httpEntity);
	}
}
