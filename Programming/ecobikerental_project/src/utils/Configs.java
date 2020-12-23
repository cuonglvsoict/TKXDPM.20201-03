package utils;

/**
 * The class {@code Configs} contains a all parameters for the application. User
 * needs to set up database infomation
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class Configs {

	public static String databaseName;;
	public static String databaseUserName;
	public static String databasePassword;

	public static final String LOGO_PATH = "img/logo.png";

	public static final String APP_CODE = "CO3UOyJZ2Xo=";
	public static final String SECRET_KEY = "BriZN2k8024=";

	public static final String CURRENCY = "VND";
	public static final String PAYMENT_API_VERSION = "1.0.1";

	public static final String PAY_ORDER_COMMAND_CODE = "pay";
	public static final String REFUND_COMMAND_CODE = "refund";

	public static final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	public static final String PAYMENT_BASE_URL = "https://ecopark-system-api.herokuapp.com";
	public static final String RESET_BALANCE_PATH = "/api/card/reset-balance";
	public static final String PAYMENT_REQUEST_PATH = "/api/card/processTransaction";

	public static final String SPLASH_SCREEN = "views/fxml/SplashScreen.fxml";

	public static final String HOME_SCREEN = "src/views/fxml/viewbike/HomeScreen.fxml";
	public static final String STATION_DETAIL_SCREEN = "src/views/fxml/viewbike/StationDetails.fxml";
	public static final String BIKE_DETAILS_SCREEN = "src/views/fxml/viewbike/BikeDetailsScreen.fxml";
	public static final String SEARCH_RESULT_SCREEN = "src/views/fxml/viewbike/SearchingResult.fxml";
	
	public static final String GET_BARCODE_SCREEN = "src/views/fxml/rentbike/EnterBarcodeScreen.fxml";
	public static final String RENT_ORDER_CONFIRM_SCREEN = "src/views/fxml/rentbike/RentalOrderConfirmScreen.fxml";

	public static final String RETURN_BIKE_SCREEN = "src/views/fxml/returnbike/ReturnBikeScreen.fxml";
	public static final String GET_BARCODE_TO_RETURN_BIKE = "src/views/fxml/returnbike/EnterBarcodeReturnBikeScreen.fxml";

	public static final String PAYMENT_FORM_SCREEN = "src/views/fxml/payment/PaymentFormScreen.fxml";
	public static final String PAYMENT_CONFIRM_SCREEN = "src/views/fxml/payment/PaymentConfirmScreen.fxml";
	public static final String PAYMENT_RESULT_SCREEEN = "src/views/fxml/payment/PaymentResultScreen.fxml";
}
