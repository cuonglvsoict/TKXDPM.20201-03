package controller;

import java.util.logging.Logger;

/**
 * All the controllers must extend the BaseController
 * @author vancuonglee
 * @since 1.0
 *
 */
public class BaseController {

	/**
	 * object for logging infomation to console
	 */
	private Logger LOGGER;
	
	public BaseController() {
		
	}
	
	public BaseController(Logger logger) {
		LOGGER = logger;
	}

	public Logger getLOGGER() {
		return LOGGER;
	}

	public void setLOGGER(Logger lOGGER) {
		LOGGER = lOGGER;
	}
}
