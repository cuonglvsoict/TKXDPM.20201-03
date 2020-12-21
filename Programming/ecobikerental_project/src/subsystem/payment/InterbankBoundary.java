package subsystem.payment;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Class {@code InterbankBoundary} to communicate with inter bank
 * @author vancuonglee
 * @since 1.0
 *
 */
public class InterbankBoundary {
	
	/**
	 * communicate to interbank using PATH method
	 * @param url: PATH to payment service
	 * @param requestBody: payment infomation
	 * @return the response message
	 * @throws IOException
	 */
	protected static String query(String url, String requestBody) throws IOException {
		
		HttpPatch httpPatch = new HttpPatch(url);
		httpPatch.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpResponse httpResponse = httpClient.execute(httpPatch);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			// throw exception here
			
		}

		return EntityUtils.toString(httpResponse.getEntity());
	}
}
