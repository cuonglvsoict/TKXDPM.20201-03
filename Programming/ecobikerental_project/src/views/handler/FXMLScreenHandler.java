package views.handler;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public abstract class FXMLScreenHandler {

	private FXMLLoader loader;
	private Parent rootContent;

	public FXMLScreenHandler(String fxmlPath) throws IOException {
		URL url = Paths.get(fxmlPath).toUri().toURL();
		this.loader = new FXMLLoader(url);
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
