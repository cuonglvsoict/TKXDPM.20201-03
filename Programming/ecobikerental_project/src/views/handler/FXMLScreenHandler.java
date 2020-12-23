package views.handler;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * The abstract class {@code FXMLScreenHandler} takes responsibility for loading
 * GUI content
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public abstract class FXMLScreenHandler {

	/**
	 * the scene content loader
	 */
	private FXMLLoader loader;

	/**
	 * The object contains scene content
	 */
	private Parent rootContent;

	/**
	 * Initializes a newly created {@code FXMLScreenHandler} from the url to fxml
	 * resource
	 * 
	 * @param fxmlPath
	 * @throws IOException
	 */
	public FXMLScreenHandler(String fxmlPath) throws IOException {
//		URL url = Paths.get(fxmlPath).toUri().toURL();
		this.loader = new FXMLLoader(getClass().getResource(fxmlPath));
		this.loader.setController(this);
		this.rootContent = loader.load();
	}

	public FXMLLoader getLoader() {
		return loader;
	}

	public void setLoader(FXMLLoader loader) {
		this.loader = loader;
	}

	public Parent getRootContent() {
		return rootContent;
	}

	public void setRootContent(Parent rootContent) {
		this.rootContent = rootContent;
	}

}
