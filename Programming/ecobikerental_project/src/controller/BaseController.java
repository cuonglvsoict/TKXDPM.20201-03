package controller;

import java.util.logging.Logger;

public class BaseController {

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
