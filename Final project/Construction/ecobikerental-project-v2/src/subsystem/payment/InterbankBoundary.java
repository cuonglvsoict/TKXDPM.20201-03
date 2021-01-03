package subsystem.payment;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import utils.Configs;

/**
 * Class {@code InterbankBoundary} to communicate with inter bank
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class InterbankBoundary {

	/**
	 * communicate to interbank using PATH method
	 * 
	 * @param url:         PATH to payment service
	 * @param requestBody: payment infomation
	 * @return the response message
	 * @throws IOException
	 */
	protected static String query(String url, String requestBody) throws IOException {

		HttpPatch httpPatch = new HttpPatch(url);
		System.out.println(requestBody);
		httpPatch.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpResponse httpResponse = httpClient.execute(httpPatch);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			// throw exception here

		}

		return EntityUtils.toString(httpResponse.getEntity());
	}

	/**
	 * method translates response code from inter bank to notify message to display
	 * for use
	 * 
	 * @param responseCode: response code from inter bank
	 * @param command:      payment command
	 * @return message to display
	 */
	protected static String getPaymentResultMessage(String responseCode, String command) {
		switch (responseCode) {
		case "00": {
			if (command.equals(Configs.PAY_ORDER_COMMAND_CODE)) {
				return "Payment accepted, your bike has been unlocked!!!";
			} else {
				return "Successful transaction, your deposit has been refunded!!!";
			}
		}
		case "01": {
			return responseCode + " Invalid card!!! Please verify your infomation and try again";
		}
		case "02": {
			return responseCode + " Payment failed due to not enough balance error!!!";
		}
		case "03": {
			return responseCode + " Payment failed due to internal Interbank Server Error!!!";
		}
		case "04": {
			return responseCode + " Payment failded due to interbank server cannot verify infomation!!!";
		}
		case "05": {
			return responseCode + " Payment failded due to missing transaction infomation!!!";
		}
		case "06": {
			return responseCode + " Payment failded due to missing transaction infomation!!!";
		}
		case "07": {
			return responseCode + " Payment failed due to invalid amount number!!!";
		}
		default: {
			return responseCode + " Payment failded";
		}
		}
	}
	
}
