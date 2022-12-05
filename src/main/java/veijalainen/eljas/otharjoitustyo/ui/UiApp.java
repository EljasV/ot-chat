package veijalainen.eljas.otharjoitustyo.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import veijalainen.eljas.otharjoitustyo.dao.MessageFileDao;
import veijalainen.eljas.otharjoitustyo.dao.UserFileDao;
import veijalainen.eljas.otharjoitustyo.domain.ChatService;
import veijalainen.eljas.otharjoitustyo.domain.Message;
import veijalainen.eljas.otharjoitustyo.domain.Session;
import veijalainen.eljas.otharjoitustyo.domain.User;
import veijalainen.eljas.otharjoitustyo.util.Result;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class UiApp extends Application {
	public static final Border DEFAULT_BORDER = new Border(
			  new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID,
						 new CornerRadii(1),
						 BorderWidths.DEFAULT));
	Scene loginScene;
	Scene welcomeScene;
	Scene createAccountScene;
	Scene contactsScene;
	Scene chatScene;

	ChatService chatService;
	private Session session;

	private User chattingTo;


	@Override
	public void start(Stage stage) throws Exception {

		UserFileDao userDao = new UserFileDao("users.txt");
		MessageFileDao messageFileDao = new MessageFileDao("messages.txt");
		if (!userDao.isWorking()) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't use users.txt file!");
			alert.show();
			return;
		}
		if (!messageFileDao.isWorking()) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't use messages.txt file!");
			alert.show();
			return;
		}


		chatService = new ChatService(userDao, messageFileDao);


		welcomeScene = createWelcomeScene(stage);
		loginScene = createLoginScene(stage);
		createAccountScene = createCreateAccountScene(stage);
		contactsScene = createContactsScene(stage);

		chatScene = createChatScene(stage);

		stage.setScene(welcomeScene);
		stage.show();
	}

	private Scene createChatScene(Stage stage) {
		BorderPane borderPane = new BorderPane();

		if (chattingTo == null) {
			return new Scene(borderPane);
		}

		AnchorPane topAnchorPane = new AnchorPane();
		topAnchorPane.setBorder(DEFAULT_BORDER);
		topAnchorPane.setMinWidth(50);


		AnchorPane innerTopPane = new AnchorPane();
		AnchorPane.setTopAnchor(innerTopPane, 10.0);
		AnchorPane.setBottomAnchor(innerTopPane, 10.0);
		AnchorPane.setLeftAnchor(innerTopPane, 10.0);
		AnchorPane.setRightAnchor(innerTopPane, 10.0);

		Button backButton = new Button("Back");
		backButton.setAlignment(Pos.CENTER_LEFT);
		innerTopPane.getChildren().add(backButton);

		backButton.setOnAction(actionEvent -> {
			chattingTo = null;
			stage.setScene(contactsScene);
		});


		Label contactNameLabel = new Label(chattingTo.getUsername());
		contactNameLabel.setAlignment(Pos.CENTER);
		AnchorPane.setLeftAnchor(contactNameLabel, 0.0);
		AnchorPane.setRightAnchor(contactNameLabel, 0.0);
		innerTopPane.getChildren().add(contactNameLabel);


		Button logoutButton = new Button("Logout");
		innerTopPane.getChildren().add(logoutButton);
		AnchorPane.setRightAnchor(logoutButton, 0.0);
		logoutButton.setAlignment(Pos.CENTER_RIGHT);

		logoutButton.setOnAction(actionEvent -> {
			session = null;
			stage.setScene(loginScene);
		});

		topAnchorPane.getChildren().add(innerTopPane);
		borderPane.setTop(topAnchorPane);


		VBox vBox = new VBox();
		List<Message> messages = chatService.getMessageHistory(session.getUser().getUsername(), chattingTo.getUsername());

		for (Message message : messages) {
			String data = message.data;

			AnchorPane anchorPane = new AnchorPane();

			Label label = new Label(data);

			if (message.fromName.equals(session.getUser().getUsername())) {
				AnchorPane.setRightAnchor(label, 10.0);
			} else {
				AnchorPane.setLeftAnchor(label, 10.0);
			}
			anchorPane.getChildren().add(label);

			label.setBorder(DEFAULT_BORDER);

			vBox.getChildren().add(anchorPane);
		}

		ScrollPane scrollPane = new ScrollPane(vBox);
		scrollPane.setFitToWidth(true);

		borderPane.setCenter(scrollPane);


		AnchorPane inputPane = new AnchorPane();
		inputPane.setBorder(DEFAULT_BORDER);
		TextField inputField = new TextField();
		inputPane.getChildren().add(inputField);


		Button sendButton = new Button("Send");
		sendButton.setOnAction(actionEvent -> {
			Message message = new Message(session.getUser().getUsername(), chattingTo.getUsername(), inputField.getText(), Instant.now().getEpochSecond());
			chatService.sendMessage(message);
			chatScene = createChatScene(stage);
			stage.setScene(chatScene);

		});
		AnchorPane.setRightAnchor(sendButton, 0.0);
		AnchorPane.setLeftAnchor(inputField, 0.0);
		AnchorPane.setRightAnchor(inputField, 50.0);
		inputPane.getChildren().add(sendButton);


		borderPane.setBottom(inputPane);

		return new Scene(borderPane, 360, 640);
	}

	private Scene createContactsScene(Stage stage) {
		BorderPane borderPane = new BorderPane();

		AnchorPane topAnchorPane = new AnchorPane();
		topAnchorPane.setBorder(DEFAULT_BORDER);
		topAnchorPane.setMinWidth(50);


		AnchorPane innerTopPane = new AnchorPane();
		AnchorPane.setTopAnchor(innerTopPane, 10.0);
		AnchorPane.setBottomAnchor(innerTopPane, 10.0);
		AnchorPane.setLeftAnchor(innerTopPane, 10.0);
		AnchorPane.setRightAnchor(innerTopPane, 10.0);

		Label topLabel = new Label("Chat");
		topLabel.setAlignment(Pos.CENTER_LEFT);
		innerTopPane.getChildren().add(topLabel);


		Button logoutButton = new Button("Logout");
		innerTopPane.getChildren().add(logoutButton);

		AnchorPane.setRightAnchor(logoutButton, 0.0);

		logoutButton.setAlignment(Pos.CENTER_RIGHT);

		logoutButton.setOnAction(actionEvent -> {
			session = null;
			stage.setScene(loginScene);
		});

		topAnchorPane.getChildren().add(innerTopPane);

		borderPane.setTop(topAnchorPane);


		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		borderPane.setCenter(scrollPane);
		VBox vBox = new VBox();
		scrollPane.setContent(vBox);


		List<User> userList = chatService.getUsers();

		for (User user :
				  userList) {

			if (session != null && Objects.equals(user, session.getUser())) {
				continue;
			}

			AnchorPane anchorPane = new AnchorPane();
			anchorPane.setMaxHeight(30);
			anchorPane.setBorder(DEFAULT_BORDER);

			Label userLabel = new Label(user.getUsername());
			userLabel.setPadding(new Insets(10));
			userLabel.setAlignment(Pos.CENTER_LEFT);

			anchorPane.setOnMouseClicked(mouseEvent -> {
				chattingTo = user;
				chatScene = createChatScene(stage);
				stage.setScene(chatScene);
			});

			anchorPane.getChildren().add(userLabel);
			vBox.getChildren().add(anchorPane);
		}


		return new Scene(borderPane, 360, 640);
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
			Result<Void, ChatService.CreateUserErrorCode> result = chatService.createUser(usernameField.getText(), password1Field.getText(), password2Field.getText());

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
			Result<Session, Void> result = chatService.login(usernameField.getText(), passwordField.getText());

			if (result.success()) {
				resetInfo.run();
				session = result.get();
				contactsScene = createContactsScene(stage);
				stage.setScene(contactsScene);
			}
		});

		buttonPair.getChildren().addAll(backButton, continueButton);
		vBox.getChildren().add(buttonPair);

		return new Scene(anchorPane);
	}

}
