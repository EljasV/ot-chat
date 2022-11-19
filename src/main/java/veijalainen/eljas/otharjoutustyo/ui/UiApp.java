package veijalainen.eljas.otharjoutustyo.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UiApp extends Application {
	@Override
	public void start(Stage stage) throws Exception {

		Label loginLabel = new Label("Login");
		loginLabel.setMinSize(100, 20);

		BorderPane loginPane = new BorderPane();

		HBox hBox = new HBox(40);

		hBox.setPadding(new Insets(10));

		loginPane.getChildren().add(hBox);

		TextField textField = new TextField("username");

		textField.setMinSize(200, 20);

		hBox.getChildren().addAll(loginLabel, textField);

		Scene loginScene = new Scene(loginPane, 300, 225);


		BorderPane borderPane = new BorderPane();
		Scene testScene = new Scene(borderPane, 300, 400);


		stage.setScene(loginScene);
		stage.show();
	}

}
