package veijalainen.eljas.otharjoutustyo.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UiApp extends Application {
	Scene loginScene;
	Scene welcomeScene;
	Scene createAccountScene;

	@Override
	public void start(Stage stage) throws Exception {

		welcomeScene = createWelcomeScene(stage);
		loginScene = createLoginScene(stage);
		createAccountScene = createCreateAccountScene();


		stage.setScene(welcomeScene);
		stage.show();
	}

	private Scene createCreateAccountScene() {
		AnchorPane anchorPane = new AnchorPane();
		return new Scene(anchorPane);

	}

	private Scene createWelcomeScene(Stage stage) {
		AnchorPane anchorPane = new AnchorPane();

		VBox vBox = createCenteredVBox(anchorPane);

		Label welcomeLabel = new Label("Welcome!");
		vBox.getChildren().add(welcomeLabel);


		Button loginButton = new Button("Login");
		loginButton.setOnAction(actionEvent -> stage.setScene(loginScene));
		vBox.getChildren().add(loginButton);

		Button createAccountButton = new Button("Create account");
		createAccountButton.setOnAction(actionEvent -> stage.setScene(createAccountScene));
		vBox.getChildren().add(createAccountButton);


		return new Scene(anchorPane);
	}

	private static VBox createCenteredVBox(AnchorPane anchorPane) {
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(20, 10, 20, 10));
		vBox.setSpacing(10);
		anchorPane.getChildren().add(vBox);


		vBox.setAlignment(Pos.CENTER);

		AnchorPane.setLeftAnchor(vBox, 0.0);
		AnchorPane.setRightAnchor(vBox, 0.0);
		return vBox;
	}

	private Scene createLoginScene(Stage stage) {

		AnchorPane anchorPane = new AnchorPane();
		VBox vBox = createCenteredVBox(anchorPane);

		Label loginLabel = new Label("Login");
		vBox.getChildren().add(loginLabel);


		return new Scene(anchorPane);
	}

}
