package veijalainen.eljas.otharjoutustyo.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import veijalainen.eljas.otharjoutustyo.dao.UserMemoryDao;
import veijalainen.eljas.otharjoutustyo.domain.ChatService;
import veijalainen.eljas.otharjoutustyo.domain.User;
import veijalainen.eljas.otharjoutustyo.util.Result;

public class UiApp extends Application {
	Scene loginScene;
	Scene welcomeScene;
	Scene createAccountScene;
	Scene contactsScene;

	ChatService chatService;


	@Override
	public void init() throws Exception {
		chatService = new ChatService(new UserMemoryDao());
	}

	@Override
	public void start(Stage stage) throws Exception {

		welcomeScene = createWelcomeScene(stage);
		loginScene = createLoginScene(stage);
		createAccountScene = createCreateAccountScene(stage);
		contactsScene = createContactsScene(stage);


		stage.setScene(welcomeScene);
		stage.show();
	}

	private Scene createContactsScene(Stage stage) {
		AnchorPane anchorPane = new AnchorPane();
		VBox centeredVBox = createCenteredVBox(anchorPane);

		centeredVBox.getChildren().addAll(new Label("a"), new Label("b"), new Label("c"));

		return new Scene(anchorPane);
	}

	private Scene createCreateAccountScene(Stage stage) {
		AnchorPane anchorPane = new AnchorPane();
		VBox vBox = createCenteredVBox(anchorPane);

		Label title = new Label("Create account");
		vBox.getChildren().add(title);

		HBox usernamePair = new HBox();
		usernamePair.setAlignment(Pos.CENTER);
		usernamePair.setSpacing(10);
		Label usernameLabel = new Label("Username");
		TextField usernameField = new TextField();
		usernamePair.getChildren().addAll(usernameLabel, usernameField);
		vBox.getChildren().add(usernamePair);

		HBox password1Pair = new HBox();
		password1Pair.setAlignment(Pos.CENTER);
		password1Pair.setSpacing(10);
		Label password1Label = new Label("Password");
		TextField password1Field = new TextField();
		password1Pair.getChildren().addAll(password1Label, password1Field);
		vBox.getChildren().add(password1Pair);

		HBox password2Pair = new HBox();
		password2Pair.setAlignment(Pos.CENTER);
		password2Pair.setSpacing(10);
		Label password2Label = new Label("Repeat password");
		TextField password2Field = new TextField();
		password2Pair.getChildren().addAll(password2Label, password2Field);
		vBox.getChildren().add(password2Pair);

		Label infoLabel = new Label();
		vBox.getChildren().add(infoLabel);

		HBox buttonPair = new HBox();
		buttonPair.setAlignment(Pos.CENTER);
		buttonPair.setSpacing(10);

		Button backButton = new Button("Back");
		backButton.setOnAction(actionEvent -> stage.setScene(welcomeScene));


		Button continueButton = new Button("Continue");

		Runnable resetInfo = () -> {
			usernameField.setText("");
			password1Field.setText("");
			password2Field.setText("");
		};

		continueButton.setOnAction(actionEvent -> {
			Result<Void, ChatService.createUserErrorCode> result = chatService.createUser(usernameField.getText(), password1Field.getText(), password2Field.getText());

			if (result.success()) {
				resetInfo.run();
				stage.setScene(welcomeScene);
			} else {
				infoLabel.setText(result.getError().name());
			}
		});

		buttonPair.getChildren().addAll(backButton, continueButton);
		vBox.getChildren().add(buttonPair);

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

		HBox usernamePair = new HBox();
		usernamePair.setAlignment(Pos.CENTER);
		usernamePair.setSpacing(10);
		Label usernameLabel = new Label("Username");
		TextField usernameField = new TextField();
		usernamePair.getChildren().addAll(usernameLabel, usernameField);
		vBox.getChildren().add(usernamePair);

		HBox passwordPair = new HBox();
		passwordPair.setAlignment(Pos.CENTER);
		passwordPair.setSpacing(10);
		Label passwordLabel = new Label("Password");
		TextField passwordField = new TextField();
		passwordPair.getChildren().addAll(passwordLabel, passwordField);
		vBox.getChildren().add(passwordPair);

		HBox buttonPair = new HBox();
		buttonPair.setAlignment(Pos.CENTER);
		buttonPair.setSpacing(10);

		Runnable resetInfo = () -> {
			usernameField.setText("");
			passwordField.setText("");
		};

		Button backButton = new Button("Back");
		backButton.setOnAction(actionEvent -> {
			stage.setScene(welcomeScene);
			resetInfo.run();
		});


		Button continueButton = new Button("Continue");


		continueButton.setOnAction(actionEvent -> {
			Result<User, Void> result = chatService.login(usernameField.getText(), passwordField.getText());

			if (result.success()) {
				resetInfo.run();
				stage.setScene(contactsScene);
			}
		});

		buttonPair.getChildren().addAll(backButton, continueButton);
		vBox.getChildren().add(buttonPair);

		return new Scene(anchorPane);
	}

}
